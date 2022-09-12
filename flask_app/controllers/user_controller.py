from flask_app import app
from flask import render_template, request, session, flash, redirect
from flask_bcrypt import Bcrypt
from flask_app.models.user_model import User

bcrypt = Bcrypt(app)

@app.route('/register')
def show_register_form():
    return render_template('register.html')


@app.route('/register/submit', methods = ['POST'] )
def create_user():
    if User.validate_register(request.form) == False:
        return redirect ("/register")
    
    if User.validate_register(request.form) == True:
            data = {
                'email' : request.form['email']
            }
            result = User.get_one( data )
            if result == None:
                data = {
                    'first_name' : request.form['first_name'],
                    'last_name' : request.form['last_name'],
                    'email' : request.form['email'],
                    'password' : bcrypt.generate_password_hash(request.form['password'])
                }

                user_id = User.create_user(data)
                print(user_id)

                session['email'] = request.form['email']
                session['first_name'] = request.form['last_name']
                session['id'] = user_id
                return redirect('/dashboard')
            else:
                flash("That email already exists, please select another one.", "error_register_email")
                return redirect("/register")
    else:
        redirect('/register')


@app.route('/login')
def login():
    if User.validate_session():
        return redirect('/dashboard')
    else:
        return render_template ('login.html')


@app.route('/login/submit', methods = ['POST'])
def login_submit():
    data = {
        'email' : request.form['email']
    }

    result = User.get_one(data)
    if result == None:
        flash( "Wrong login information", "error_login")
        return redirect('/login')
    else:
        if not bcrypt.check_password_hash(result.password, request.form['password']):
            flash( "Wrong login information.", "error_login")
            return redirect('/login')
        else:
            session['email'] = result.email
            session[ 'first_name'] = result.first_name
            session[ 'last_name' ] = result.last_name
            session[ 'user_id' ] = result.id
            return redirect("/dashboard")

@app.route('/logout')
def logout():
    session.clear()
    return redirect('/login')



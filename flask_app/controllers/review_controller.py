from flask import session, request, render_template, redirect, flash
from flask_app import app
from flask_app.models.review_model import Review
from flask_app.models.user_model import User
from flask_app.models.artist_model import Artist



# displays all reviews
@app.route("/dashboard")
def get_reviews():
    if User.validate_session():
        reviews = Review.get_all()
        artists = Artist.get_all_artists()
        return render_template("dashboard.html", artists = artists, reviews = reviews)
    else:
        return redirect("/")

# display review form
# pass data for artist name here
@app.route("/add/review")
def display_review():
    if User.validate_session():
        artists = Artist.get_all_artists()
        return render_template("review.html", artists = artists)
    else:
        return redirect("/login")

# create a review
@app.route("/review/new", methods = [ 'POST'] )
def create_review():
    if Review.validate_create_review(request.form) == False:
        return redirect ("/dashboard")
    else:
        data = {
            "user_id": session['user_id'],
            "artist_id": request.form['artist_id'],
            "review" : request.form[ 'review' ],
            "num_stars" : request.form ['num_stars']
        }

        Review.create(data)
        return redirect("/dashboard")


#get one review
@app.route("/review/<int:id>")
def get_review(id):
    if User.validate_session():
        data = {
            'id' : id
        }
        review = Review.get_one(data)
        return render_template("displayReview.html", review = review)
    else: 
        return redirect("/dashboard")

#edit one review
@app.route("/review/edit/<int:id>")
def edit_review(id):
    if User.validate_session():
        data = {
            'id' : id
        }
        review = Review.get_one(data)
        return render_template("displayEditReview.html", review = review) 
    else:
        return redirect("/")

#delete review
@app.route("/review/delete/<int:id>")
def delete_review(id):
    data = {
        "id" : id
    }
    Review.delete_one(data)
    return redirect("/dashboard")
from flask import session, request, render_template, redirect, flash
from flask_app import app
from flask_app.models.review_model import Review
from flask_app.models.user_model import User
from flask_app.models.artist_model import Artist

# display artist form
@app.route("/add/artist")
def display_artist():
    if User.validate_session():
        return render_template("artist.html")
    else:
        return redirect("/login")


@app.route("/artist/new", methods = [ 'POST' ])
def create_artist():
    if Artist.validate_create_artist(request.form) == False:
        return redirect ("/dashboard")
    else:
        data = {
            "artist_name": request.form[ 'artist_name' ]
        }

        Artist.create_artist(data)
        return redirect("/dashboard")
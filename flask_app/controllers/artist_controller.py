from xml.dom.minidom import Identified
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

# display all artists
@app.route("/view/artists")
def get_artists():
    
    artists = Artist.get_all_artists()
    return render_template("dashboard.html", artists = artists)



# create artist
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


# get one artist
@app.route("/review/<int:id>")
def get_artist(id):
    if User.validate_session():
        data = {
            'id' : id
        }
        artist = Artist.get_artist(data)
        return render_template("displayArtist.html")
    else:
        return redirect("/dashboard")

#delete artist
@app.route("/artist/delete/<int:id>")
def delete_artist(id):
    data = {
        "id" : id
    }
    Artist.delete_artist(data)
    return redirect("/dashboard")



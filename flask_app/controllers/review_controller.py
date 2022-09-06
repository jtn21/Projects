from flask import session, request, render_template, redirect, flash
from flask_app import app
from flask_app.models.review_model import Review
from flask_app.models.user_model import User



# displays all reviews
@app.route("/dashboard")
def get_reviews():
    if User.validate_session():
        reviews = Review.get_all()
        return render_template("dashboard.html", reviews = reviews)
    else:
        return redirect("/")

# display review form
@app.route("/add/review")
def display_review():
    if User.validate_session():
        return render_template("review.html")
    else:
        return redirect("/login")

# create a review
@app.route("/review/new", methods = [ 'POST'] )
def create_review():
    if Review.validate_create(request.form) == False:
        return redirect ("/dashboard")
    else:
        data = {
            "review" : request.form[ 'review' ],
            "num_stars" : request.form ['num_stars']
        }

        Review.create(data)
        return redirect("/dashboard")

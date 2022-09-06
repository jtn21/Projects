from flask_app.config.mysqlconnection import connectToMySQL
from flask_app import DATABASE
from flask import flash

class Review:
    def __init__(self, data) :
        self.id = data['id']
        self.created_at = ['created_at']
        self.updated_at = ['updated_at']
        self.user_id = ['user_id']
        self.artist_id = ['artist_id']
        self.review = data ['review']
        self.num_stars = ['num_stars']

    
    @classmethod
    def create( cls, data ):
        query = "INSERT INTO reviews( created_at, updated_at, user_id, artist_id, review, num_stars ) "
        query += "VALUES( %(created_at)s, %(updated_at)s, %(user_id)s, %(artist_id)s, %(review)s, %(num_stars)s );"

        return connectToMySQL( DATABASE).query_db( query, data)

    @classmethod
    def get_all(cls):
        query =" SELECT * "
        query += " FROM reviews;"

        result = connectToMySQL(DATABASE).query_db(query)
        reviews = []

        if len(result) > 0:
            for review in result:
                reviews.append(review)
        return reviews

    @classmethod
    def get_one(cls, data):
        query = "SELECT * "
        query += "FROM reviews "
        query += "WHERE id = %(id)s;"

        result = connectToMySQL(DATABASE).query_db(query,data)

        if len(result) > 0:
            return cls(result[0])
        else: 
            return None

    @classmethod
    def delete_one(cls, data):
        query = "DELETE FROM reviews "
        query += "WHERE id = %(id)s;"

        return connectToMySQL(DATABASE).query_db(query,data)


    @classmethod
    def update_one(cls, data):
        query = "UPDATE reviews "
        query += "SET review = %(review)s, num_stars = %(num_stars)s"
        query += "WHERE id = %(id)s;"

        return connectToMySQL(DATABASE).query_db(query,data)

    @staticmethod
    def validate_create(data):
        isValid = True
        if data['review'] == "":
            isValid = False
            flash("Please provide a review", "error_create_review")
        if len(data['num_stars']) <= 0:
            isValid = False
            flash("Rating must be higher than 0", "error_create_higher_rating")
        return isValid
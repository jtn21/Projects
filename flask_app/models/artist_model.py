from flask_app.config.mysqlconnection import connectToMySQL
from flask_app import DATABASE
from flask import flash

class Artist:
    def __init__(self, data) :
        self.id = data['id']
        self.created_at = ['created_at']
        self.updated_at = ['updated_at']
        self.artist_name = ['artist_name']

    
    @classmethod
    def create_artist(cls, data):
        query = "INSERT INTO artists(artist_name )"
        query += "VALUES( %(artist_name)s );"

        return connectToMySQL( DATABASE).query_db( query, data)

    @classmethod
    def get_all_artists(cls, data):
        query = " SELECT * "
        query += " FROM artists;"

        result = connectToMySQL(DATABASE).query_db(query)
        artists = []

        if len(result) > 0:
            for artist in result:
                artists.append(artist)
        return artists


    @staticmethod
    def validate_create_artist(data):
        isValid = True
        if data['artist_name'] == "":
            isValid = False
            flash("Please provide Artist Name", "error_create_artist")
        return isValid

    @classmethod
    def get_artist(cls, data):
        query = "SELECT * "
        query += "FROM artists "
        query += "WHERE id = %(id)s;"

        result = connectToMySQL(DATABASE).query_db(query,data)

        if len(result) > 0:
            return cls(result[0])
        else: 
            return None
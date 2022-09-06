from flask_app.config.mysqlconnection import connectToMySQL
from flask_app import DATABASE

class Artist:
    def __init__(self, data) :
        self.id = data['id']
        self.created_at = ['created_at']
        self.updated_at = ['updated_at']
        self.artist_name = ['artist_name']

    
    @classmethod
    def create_artist(cls, data):
        query = "INSERT INTO artists(created_at, updated_at, artist_name )"
        query += "VALUES( %(created_at)s, %(updated_at)s, %(artist_name)s );"

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
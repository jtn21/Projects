from flask_app.config.mysqlconnection import MySQLConnection, connectToMySQL
from flask_app import DATABASE
from flask import flash, session
import re

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

class User:
    def __init__(self, data):
        self.id = data['id']
        self.first_name = data['first_name']
        self.last_name = data['last_name']
        self.email = data['email']
        self.password = data['password']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']

# register/create a user
    @classmethod
    def create_user( cls, data):
        query = "INSERT INTO users (first_name, last_name, email, password) "
        query += "VALUES ( %(first_name)s, %(last_name)s, %(email)s, %(password)s) ;"

        return connectToMySQL(DATABASE).query_db(query, data)

# check to see if user already exists by checking for existing email
    @classmethod
    def get_one( cls, data):
        query = "SELECT * FROM users WHERE email = %(email)s;"

        result = connectToMySQL(DATABASE).query_db(query,data)
        print(result)
        if len(result) > 0:
            return cls( result[0])
        else: 
            return None

# validate registration
    @staticmethod
    def validate_register(data):
        isValid = True

        if data['first_name'] == "":
            isValid = False
            flash("Please provide first name.", "error_register_first_name" )

        if len(data['first_name']) < 2:
            isValid = False
            flash( "Your first name must have at least 2 characters.", "error_register_first_name_two" )

        if data['last_name'] == "":
            isValid = False
            flash("Please provide last name.", "error_register_last_name" )

        if len(data['last_name']) < 2:
            isValid = False
            flash( "Your last name must have at least 2 characters.", "error_register_last_name_two" )

        if data['email'] == "":
            isValid = False
            flash("Please provide your email.", "error_register_email" )
        
        if not EMAIL_REGEX.match(data['email']):
            isValid = False
            flash("Invalid email address.", "error_invalid_email")

        if data['password'] != data['confirm_password']:
            isValid = False
            flash("Your passwords do not match.", "error_register_password_confirmation" )
        
        if data['password'] == "":
            isValid = False
            flash("You must provide a password.", "error_register_password" )
        
        if data['confirm_password'] == "":
            isValid = False
            flash("You must provide a password confirmation.", "error_register_password_confirmation" )
        
        return isValid

    @staticmethod
    def validate_session():
        if "user_id" not in session:
            return False
        else:
            return True
    
    @staticmethod
    def validate_login():
        pass
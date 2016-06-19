import os

from flask import Flask, request, jsonify


app = Flask(__name__)

@app.route('/')
def hello():
    return 'SplitCash API'

@app.route('/api/user')
def user():
    return 'OK'

@app.route('/api/login')
def login():
    response = {'name': 'Hello'}
    return jsonify(**response)

app.run(host=os.getenv('IP', '0.0.0.0'),port=int(os.getenv('PORT', 8080)))

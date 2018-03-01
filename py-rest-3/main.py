#!/usr/bin/env python

import os
import uuid
from flask import Flask,  jsonify, request
from flask_restful import Resource, Api, reqparse
from flask_sqlalchemy import SQLAlchemy
from flask_marshmallow import Marshmallow


app = Flask(__name__)
basedir = os.path.abspath(os.path.dirname(__file__))
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + \
    os.path.join(basedir, 'db.sqlite')
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
db = SQLAlchemy(app)
ma = Marshmallow(app)
api = Api(app, prefix="/api/v1")

parser = reqparse.RequestParser()
parser.add_argument("name", type=str, required=True,
                    help="This field must not be blank.")
parser.add_argument("phone", type=str, required=True,
                    help="This field must not be blank.")
parser.add_argument("email", type=str, required=True,
                    help="This field must not be blank.")


class SubscriberTable(db.Model):
    id = db.Column(db.String(80), primary_key=True)
    name = db.Column(db.String(80), unique=True)
    phone = db.Column(db.String(80), unique=True)
    email = db.Column(db.String(80), unique=True)

    def __init__(self, name, phone, email):
        self.id = uuid.uuid4().hex
        self.name = name
        self.phone = phone
        self.email = email


class SubscriberTableSchema(ma.Schema):
    class Meta:
        fields = ('id', 'name', 'phone', 'email')


try:
    db.create_all()
except:
    pass

subscriber_table_schema = SubscriberTableSchema()
subscribers_table_schema = SubscriberTableSchema(many=True)


class Subscriber(Resource):
    def get(self, id):
        try:
            data = SubscriberTable.query.get(id)
            return subscriber_table_schema.jsonify(data)
        except:
            return {"WARN": "[{}] primary key does not exist".format(id)}, 404

    def delete(self, id):
        try:
            data = SubscriberTable.query.get(id)
            db.session.delete(data)
            db.session.commit()
            return subscriber_table_schema.jsonify(data)
        except:
            return {"WARN": "[{}] primary key does not exist".format(id)}, 404

    def put(self, id):
        args = parser.parse_args()
        try:
            data = SubscriberTable.query.get(id)
            data.name = args['name']
            data.phone = args['phone']
            data.email = args['email']
            db.session.commit()
            return subscriber_table_schema.jsonify(data)
        except:
            return {'ERROR': 'Unique constraint(s) violated, please fix your data'}, 400


class Subscribers(Resource):
    def get(self):
        data = SubscriberTable.query.all()
        dump = subscribers_table_schema.dump(data)
        return jsonify(dump.data)

    def post(self):
        args = parser.parse_args()
        name = args['name']
        phone = args['phone']
        email = args['email']
        try:
            data = SubscriberTable(name, phone, email)
            db.session.add(data)
            db.session.commit()
            return subscriber_table_schema.jsonify(data)
        except:
            return {'ERROR': 'Unique constraint(s) violated, please fix your data'}, 400


api.add_resource(Subscriber, '/subscriber/<string:id>')
api.add_resource(Subscribers, '/subscriber')

if __name__ == '__main__':
    app.run(port=5000, debug=True)

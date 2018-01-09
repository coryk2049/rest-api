#!/usr/bin/env python

import uuid
from flask import Flask, request
from flask_restful import Resource, Api, reqparse

app = Flask(__name__)
api = Api(app, prefix="/api/v1")
subscribers = {}
parser = reqparse.RequestParser()
parser.add_argument("name", type=str, required=True,
                    help="This field must not be blank.")
parser.add_argument("phone", type=str, required=True,
                    help="This field must not be blank.")
parser.add_argument("email", type=str, required=True,
                    help="This field must not be blank.")


def make_data(id, args):
    subscriber_list = []
    subscriber = {}
    subscriber['name'] = args['name']
    subscriber['phone'] = args['phone']
    subscriber['email'] = args['email']
    subscriber_list.append(subscriber)
    return subscriber_list


def make_resp(id):
    subscriber = {}
    subscriber[id] = subscribers[id]
    return {"subscriber": kv}


class Subscriber(Resource):
    def get(self, id):
        try:
            return make_resp(id)
        except:
            return {"subscriber": {}}

    def delete(self, id):
        try:
            del subscribers[id]
            return {"message": "[{}] deleted".format(id)}
        except:
            return {"message": "[{}] does not exist".format(id)}

    def put(self, id):
        args = parser.parse_args()
        try:
            del subscribers[id]
        except:
            pass
        subscribers[id] = make_data(id, args)
        return make_resp(id)


class SubscriberList(Resource):
    def get(self):
        return {"subscribers": subscribers}

    def post(self):
        args = parser.parse_args()
        id = uuid.uuid4().hex
        if id not in subscribers.keys():
            subscribers[id] = make_data(id, args)
        return make_resp(id)


api.add_resource(Subscriber, '/subscriber/<string:id>')
api.add_resource(SubscriberList, '/subscribers')

if __name__ == '__main__':
    app.run(port=5000, debug=True)

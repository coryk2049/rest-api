#!/usr/bin/env python

from flask import Flask, request
from flask_restful import Resource, Api, reqparse

app = Flask(__name__)
api = Api(app, prefix="/api/v1")

# subscriber key value store
subscribers = {}


class Subscriber(Resource):
    parser = reqparse.RequestParser()
    parser.add_argument("email", type=str, required=True,
                        help="This field must not be blank.")

    def make_resp(self, name):
        subscriber = {}
        subscriber[name] = subscribers[name]
        return {"subscriber": subscriber}

    def get(self, name):
        try:
            return self.make_resp(name)
        except:
            return {"subscriber": {}}

    def delete(self, name):
        try:
            del subscribers[name]
            return {"message": "[{}] deleted".format(name)}
        except:
            return {"message": "[{}] does not exist".format(name)}

    def put(self, name):
        args = self.parser.parse_args()
        try:
            del subscribers[name]
        except:
            pass
        subscribers[name] = args['email']
        return self.make_resp(name)

    def post(self, name):
        args = self.parser.parse_args()
        if name not in subscribers.keys():
            subscribers[name] = args['email']
        return self.make_resp(name)


class SubscriberList(Resource):
    def get(self):
        return {"subscribers": subscribers}


api.add_resource(Subscriber, '/subscriber/<string:name>')
api.add_resource(SubscriberList, '/subscribers')

if __name__ == '__main__':
    app.run(port=5000, debug=True)

# py-rest-2

Another implementation of Subscribers REST API using Python 2.7, and Flask modules.

## Prerequisites

```
# virtualenv venv
# source venv/bin/activate
# pip install flask flask-restful
# [..]
```

## Model

```
Objects:
  subscribers - Dictionary List
     subscriber - Dictionary
               id - String (uuid.uuid4().hex)
             name - String
            phone - String
            email - String
```

## API Operations

```
   PUT: /api/v1/subscriber/<string:id> - Update subscriber by id
   GET: /api/v1/subscriber/<string:id> - Get subscriber by id
DELETE: /api/v1/subscriber/<string:id> - Delete subscriber by id
   GET: /api/v1/subscribers            - Get subscriber repository
  POST: /api/v1/subscribers            - Create subscriber profile
```

## Execute Program

```
# python main.py
```

## Test Tool

Download `https://www.getpostman.com/`

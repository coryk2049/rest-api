# py-rest-1

Simple implementation of Subscribers REST API using Python 2.7, and Flask modules.

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
  subscribers - Dictionary
           name - String
          email - String
```

## API Operations

```
  POST: /api/v1/subscriber/<string:name> - Create subscriber by name
   PUT: /api/v1/subscriber/<string:name> - Update subscriber by name
   GET: /api/v1/subscriber/<string:name> - Get subscriber by name
DELETE: /api/v1/subscriber/<string:name> - Delete subscriber by name
   GET: /api/v1/subscribers              - Get subscriber repository
```

## Execute Program

```
# python main.py
```

## Test Tool

Download `https://www.getpostman.com/`

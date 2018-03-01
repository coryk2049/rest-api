# py-rest-3

Yet another implementation of Subscriber REST API using Python 2.7, and Flask modules.

### Prerequisites

```
# virtualenv venv
# source venv/bin/activate
# pip install flask flask-restful flask_sqlalchemy flask_marshmallow marshmallow-sqlalchemy  
# [..]
```

### Model

```
Objects:
  db - Database (sqlite)
     subscriber_table - Table
               id - String(80) // Primary Key
             name - String(80) // Unique Key
            phone - String(80) // Unique Key
            email - String(80) // Unique Key
```

### API Operations

```
  POST: /api/v1/subscriber             - Create new subscriber profile
   PUT: /api/v1/subscriber/<string:id> - Update subscriber by id
   GET: /api/v1/subscriber/<string:id> - Get subscriber by id
DELETE: /api/v1/subscriber/<string:id> - Delete subscriber by id
   GET: /api/v1/subscriber             - Get all subscribers in repository
```

### Execute Program

```
# python main.py
```

### Test Tool

- https://www.getpostman.com/

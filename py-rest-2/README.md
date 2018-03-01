# py-rest-2

Another implementation of Subscriber REST API using Python 2.7, and Flask modules.

### Prerequisites

```
# virtualenv venv
# source venv/bin/activate
# pip install flask flask-restful
# [..]
```

### Model

```
Objects:
  subscribers - Dictionary List
     subscriber - Dictionary
               id - String (uuid.uuid4().hex)
             name - String
            phone - String
            email - String
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

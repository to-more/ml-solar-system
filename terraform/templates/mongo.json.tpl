[
  {
    "essential": true,
    "memory": 128,
    "name": "mongo_ml",
    "cpu": 256,
    "networkMode": "host",
    "image": "${REPOSITORY_MONGO_URL}:1.0.0",
    "portMappings": [
        {
            "containerPort": 27017,
            "protocol": "tcp",
            "hostPort": 27017
        }
    ]
  }
]

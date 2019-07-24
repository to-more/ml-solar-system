[
  {
    "essential": true,
    "memory": 512,
    "name": "solar_system",
    "cpu": 256,
    "networkMode": "host",
    "image": "${REPOSITORY_URL}:1.0.0",
    "portMappings": [
        {
            "containerPort": 8080,
            "protocol": "tcp",
            "hostPort": 8080
        }
    ]
  }
]

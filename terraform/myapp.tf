# app

data "template_file" "mongo_ml-task-definition-template" {
  template = file("templates/mongo.json.tpl")
  vars = {
    REPOSITORY_MONGO_URL = replace("748837581525.dkr.ecr.us-east-2.amazonaws.com/mongo_ml", "https://", "")
  }
}

data "template_file" "solar_system-task-definition-template" {
  template = file("templates/app.json.tpl")
  vars = {
    REPOSITORY_URL = replace("748837581525.dkr.ecr.us-east-2.amazonaws.com/solar_system", "https://", "")
  }
}


resource "aws_ecs_task_definition" "mongo_ml-task-definition" {
  family                = "mongo_ml"
  container_definitions = data.template_file.mongo_ml-task-definition-template.rendered
  network_mode          = "host"
  memory                = "128"
}

resource "aws_ecs_task_definition" "solar_system-task-definition" {
  family                = "solar_system"
  container_definitions = data.template_file.solar_system-task-definition-template.rendered
  network_mode          = "host"
  memory                = "512"
}

//resource "aws_elb" "solarsys-elb" {
//  name = "solarsys-elb"
//
//  listener {
//    instance_port     = 8080
//    instance_protocol = "http"
//    lb_port           = 80
//    lb_protocol       = "http"
//  }
//
//  health_check {
//    healthy_threshold   = 3
//    unhealthy_threshold = 3
//    timeout             = 30
//    target              = "HTTP:8080/"
//    interval            = 60
//  }
//
//  cross_zone_load_balancing   = true
//  idle_timeout                = 400
//  connection_draining         = true
//  connection_draining_timeout = 400
//
//  subnets         = [aws_subnet.main-public-1.id, aws_subnet.main-public-2.id]
//  security_groups = [aws_security_group.solarsys-elb-securitygroup.id]
//
//  tags = {
//    Name = "solarsys-elb"
//  }
//}

resource "aws_ecs_service" "mongo_ml-service" {
  name            = "mongo_ml"
  cluster         = aws_ecs_cluster.solar-system-cluster.id
  task_definition = aws_ecs_task_definition.mongo_ml-task-definition.arn
  desired_count   = 1
  depends_on      = [aws_iam_policy_attachment.ecs-service-attach1]

  lifecycle {
    ignore_changes = [task_definition]
  }
}

resource "aws_ecs_service" "solar_system-service" {
  name            = "solar_system"
  cluster         = aws_ecs_cluster.solar-system-cluster.id
  task_definition = aws_ecs_task_definition.solar_system-task-definition.arn
  desired_count   = 1

  depends_on      = [aws_iam_policy_attachment.ecs-service-attach1]


  lifecycle {
    ignore_changes = [task_definition]
  }
}


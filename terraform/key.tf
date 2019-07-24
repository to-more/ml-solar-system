resource "aws_key_pair" "solar-keypair" {
  key_name   = "solar-keypair"
  public_key = file(var.PATH_TO_PUBLIC_KEY)
  lifecycle {
    ignore_changes = [public_key]
  }
}


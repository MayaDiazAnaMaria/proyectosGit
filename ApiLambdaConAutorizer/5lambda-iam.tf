resource "aws_iam_role" "invocation_role" {
  name = "CE-MCE-api_gateway_auth_invocation_rol-terraform"
  assume_role_policy = "${file("1iam/2lambda-assume-policy.json")}"
}

resource "aws_iam_role_policy" "invocation_policy" {
  name = "CE-MCE-api_gateway_auth_invocation_policy-terraform"
  role = "${aws_iam_role.invocation_role.id}"
  policy = "${file("1iam/3lambda-policy.json")}"
}

resource "aws_iam_role" "lambda" {
  name = "CE-MCE-lambda_rol-terraform"
  assume_role_policy = "${file("1iam/4lambda-rol.json")}"
}
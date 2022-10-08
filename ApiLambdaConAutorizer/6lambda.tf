resource "aws_lambda_function" "authorizer" {
  filename      = "index.zip"
  function_name = "authorizer"
  role          = aws_iam_role.lambda.arn
  handler       = "index.handler"
  runtime = "nodejs16.x"
  package_type = "Zip"
  source_code_hash = filebase64sha256("index.zip")
}

resource "aws_lambda_function" "function" {
  filename      = "function.zip"
  function_name = "funcion_ejemplo"
  role          = aws_iam_role.lambda.arn
  handler       = "function.handler"
  runtime = "nodejs16.x"
  package_type = "Zip"
  source_code_hash = filebase64sha256("function.zip")
}
resource "aws_api_gateway_rest_api" "api_gw_rest" {
  name                   = "api_gw_authorizer_rest_api"
  depends_on = [aws_lambda_function.authorizer,aws_lambda_function.function]
  endpoint_configuration {
    types = [
      "REGIONAL",
    ]
  }
}

resource "aws_api_gateway_authorizer" "api_gw_auth" {
  name                   = "api_gw_authorizer"
  rest_api_id            = aws_api_gateway_rest_api.api_gw_rest.id
  authorizer_uri         = aws_lambda_function.authorizer.invoke_arn
  type                    = "TOKEN"
  identity_source=  "method.request.header.authorizationToken"
  authorizer_result_ttl_in_seconds= 0
  authorizer_credentials = aws_iam_role.invocation_role.arn
}

resource "aws_api_gateway_method" "api_gw_method" {
  rest_api_id   = aws_api_gateway_rest_api.api_gw_rest.id
  resource_id   = aws_api_gateway_rest_api.api_gw_rest.root_resource_id
  http_method   = "POST"
  authorization = "CUSTOM"
  authorizer_id      = aws_api_gateway_authorizer.api_gw_auth.id
}

resource "aws_api_gateway_integration" "integration" {
  rest_api_id             = aws_api_gateway_rest_api.api_gw_rest.id
  resource_id   = aws_api_gateway_rest_api.api_gw_rest.root_resource_id
  http_method             = aws_api_gateway_method.api_gw_method.http_method
  integration_http_method = "POST"
  type                    = "AWS"
  uri                     = aws_lambda_function.function.invoke_arn
}

resource "aws_api_gateway_deployment" "api_gw_deployment" {
  rest_api_id = aws_api_gateway_rest_api.api_gw_rest.id

  triggers = {
    redeployment = sha1(jsonencode([
      aws_api_gateway_rest_api.api_gw_rest.root_resource_id,
      aws_api_gateway_method.api_gw_method.id,
      aws_api_gateway_integration.integration.id,
    ]))
  }

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_api_gateway_stage" "example" {
  deployment_id = aws_api_gateway_deployment.api_gw_deployment.id
  rest_api_id             = aws_api_gateway_rest_api.api_gw_rest.id 
  stage_name    = "stage"
}

resource "aws_api_gateway_method_response" "response_200" {
  rest_api_id = aws_api_gateway_rest_api.api_gw_rest.id
  resource_id   = aws_api_gateway_rest_api.api_gw_rest.root_resource_id
  http_method = aws_api_gateway_method.api_gw_method.http_method
  status_code = "200"
  response_models = {
    "application/json" = "Empty"
  }
}

resource "aws_api_gateway_integration_response" "IntegrationResponse" {
  rest_api_id = aws_api_gateway_rest_api.api_gw_rest.id
  resource_id   = aws_api_gateway_rest_api.api_gw_rest.root_resource_id
  http_method = aws_api_gateway_method.api_gw_method.http_method
  status_code = aws_api_gateway_method_response.response_200.status_code

  # Transforms the backend JSON response to XML
  response_templates = {
    "application/xml" = <<EOF
#set($inputRoot = $input.path('$'))
<?xml version="1.0" encoding="UTF-8"?>
<message>
    $inputRoot.body
</message>
EOF
  }
}


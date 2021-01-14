# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/lambda_function

resource "aws_lambda_function" "lambda_tf_way_function" {
  filename = var.lambda_zip_filename
  function_name = var.lambda_function_name
  handler = var.lambda_function_handler
  role = var.lambda_role_arn
  runtime = "java11"
  memory_size = 512
  layers = var.lambda_tf_way_layer == "" ? [] : [var.lambda_tf_way_layer]
}
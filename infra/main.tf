
provider "aws" {
  region = var.aws_region
}

module "lambda_role" {
  source = "./modules/lambda-role"
}

locals {
  lambda_name          = "vikingosSpikeTerraformLambda"
  zip_file_name        = "../code/build/libs/code-0.1-all.jar"
  handler_name         = "co.vikingos.spikes.aws.BookRequestHandler"
  rest_api_name        = "BookRequest"
  rest_api_description = "REST API gateway for BookRequest"
}

module "tf_aws_lambda" {
  source                  = "./modules/lambda"
  lambda_function_name    = local.lambda_name
  lambda_function_handler = local.handler_name
  lambda_role_arn         = module.lambda_role.lambda_role_arn
  lambda_zip_filename     = local.zip_file_name
}


module "lambda_api_gateway" {
  source                             = "./modules/api-gateway"
  lambda_tf_way_rest_api_name        = local.rest_api_name
  lambda_tf_way_rest_api_description = local.rest_api_description
}

module "lambda_api_gateway_integration" {
  source = "./modules/api-gateway-lambda-integration"

  lambda_tf_way_lambda_function_name = module.tf_aws_lambda.tf_way_lambda_function_name
  lambda_tf_way_lambda_invoke_arn    = module.tf_aws_lambda.tf_way_lambda_invoke_arn

  lambda_tf_way_rest_api_id            = module.lambda_api_gateway.lambda_tf_way_rest_api_id
  lambda_tf_way_rest_api_execution_arn = module.lambda_api_gateway.lambda_tf_way_rest_api_execution_arn

  lambda_tf_way_resource_id = module.lambda_api_gateway.lambda_tf_way_rest_api_root_resource_id

  lambda_tf_way_method                      = module.lambda_api_gateway.lambda_tf_way_rest_api_method
  lambda_tf_way_method_response_status_code = module.lambda_api_gateway.lambda_tf_way_rest_api_method_response_status_code
}
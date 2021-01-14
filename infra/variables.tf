variable "function_name" {
  default = "vikingos_spike_terraform_lambda_java"
}

variable "runtime" {
  default = "java11" # https://docs.aws.amazon.com/lambda/latest/dg/lambda-runtimes.html
}

variable "output_path" {
  description = "Path to function's deployment package into local filesystem. eg: /path/lambda_function.jar"
  default = "../code/build/libs/code-0.1-all.jar"
}

variable "aws_region"  {
    default = "us-east-2"
}

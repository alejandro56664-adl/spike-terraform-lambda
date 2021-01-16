# spike-terraform-lambda

Trabajo realizado para la célula "Vikingos":

![logo célula vikingos](doc/assets/logo_vikingos.jpeg)

## Contenido

1. [Objetivos](#1-objetivos)
2. [Introducción](#2-introducción)
3. [Conceptos](#3-conceptos)
4. [Diseño PoC](#4-diseño-de-la-poc)
5. [Configuración del ambiente de desarrollo](#5-configuración-del-ambiente-de-desarrollo)
6. [Detalles de implementación](#6-detalles-de-implementación)
7. [Referencias](#7-referencias)



## 1. Objetivos

- Comprender el funcionamiento de Terraform y su aplicación para la creación de infraestructura como código.
- Entender el funcionamiento y configuración de las lambdas en AWS.
- Implementar un demo sencillo utilizando Terraform y lambdas.


## 2. Introducción

Spike sobre el uso del lenguaje HCL para la creación y gestión de lambdas en aws. En este caso particular se crea una lambda en nodejs activada por un trigger del API Gateway. Tutorial pensado para quienes tienen cero experiencia con Terraform. Los modulos para este laboratorio fueron obtenidos del repo https://github.com/nsriram/lambda-the-terraform-way/.

## 3. Conceptos

### 3.1 Infraestructura como código
- Introducción a HCL

Diagrama esquematico funcionamiento de Terraform:
![Diagrama esquematico modelo terraform](doc/assets/diagramas-terraform%20model.png)


- modulos
- provedores
- recursos



### 3.2 AWS Lambda con Micronaut
- Runtime (Java 11, Coretto)
- IAM (https://github.com/nsriram/lambda-the-terraform-way/blob/master/docs/04-iam-account-setup.md)
- Layers

Diagrama esquematico layers

![Diagrama esquematico layers](doc/assets/diagramas-lambda%20layers.png)

- Desencadenadores 

Diagrama esquematico modelo lambda

![Diagrama esquematico modelo lambda](doc/assets/diagramas-lambda%20model.png)


- API Gateway
- Diferecias entre API HTTP y API REST
- Seguridad

### 3.3 Buenas practicas y recomendaciones


## 4. Diseño de la PoC

A continuación se presenta un esquema de la PoC

Diagrama estructura PoC

![Diagrama estructura PoC](doc/assets/diagramas-static.png)

Diagrama comportamiento PoC

![Diagrama comportamiento PoC](doc/assets/diagramas-dynamic.png)

Ejemplo request:

```json
{
    "name": "value1"
}
```

Ejemplo response:

```json
{
    "name": "value1",
    "isbn": "xxxxx"
}
```

```sh
./gradlew shadowJar
```

## 5. Configuración del ambiente de desarrollo

- Terraform con tfenv
- AWS Cli v2
- JDK Java 11
- IDE decente
- Una cuenta de AWS

- Creación Key desde el portal para el cli

Follow these steps to install the AWS SAM CLI using Homebrew:

```sh
brew tap aws/tap
brew install aws-sam-cli
```

Verifique la instalación

```sh
sam --version
```

Debería ver el siguiente mensaje del  AWS SAM CLI:

 SAM CLI, version 1.15.0

Para construir el entorno de pruebas local:

```sh
sam build
```

```sh
echo '{"name": "value1" }' | sam local invoke --event - --debug --profile "$AWS_PROFILE"
```

```sh
aws lambda invoke --function-name helloWorldLambda \
    --log-type Tail \
    --payload '{"name":"hola"}' \
    --profile "$AWS_PROFILE" outputfile.txt
```


```sh
terraform init
terraform plan
terraform apply --auto-approve  
```

## 6. Detalles de implementación

Diagrama de despliegue/Estructura proyecto infra:
![Diagrama de despliegue PoC](doc/assets/diagramas-deploy.png)

## 7. Referencias:

1. [Tutorial Hashicorp](https://learn.hashicorp.com/tutorials/terraform/infrastructure-as-code?in=terraform/aws-get-started)

2. [Lambda the Terraform Way](https://github.com/nsriram/lambda-the-terraform-way)

3. [Deploy a Serverless Micronaut function to AWS Lambda Java 11 Runtime](https://guides.micronaut.io/mn-serverless-function-aws-lambda/guide/index.html)

4. [Serverless SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-using-invoke.html)

5. [Creating your first AWS Java web application](https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/javav2/usecases/creating_first_project)
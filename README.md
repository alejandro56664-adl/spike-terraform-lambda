# spike-terraform-lambda
Spike sobre el uso del lenguaje HCL para la creación y gestión de lambdas en aws. En este caso particular se crea una lambda en nodejs activada por un trigger del API Gateway. Tutorial pensado para quienes tienen cero experiencia con Terraform. Los modulos para este laboratorio fueron obtenidos del repo https://github.com/nsriram/lambda-the-terraform-way/

## Contenido

1. Objetivos
2. Introducción
3. Conceptos
4. Diseño PoC
5. Configuración del ambiente de desarrollo
6. Detalles de implementación
7. Referencias



## 1. Objetivos

- Comprender el funcionamiento de Terraform y su aplicación para la creación de infraestructura como código.
- Entender el funcionamiento y configuración de las lambdas en AWS.
- Implementar un demo sencillo utilizando Terraform y lambdas.


## 2. Introducción



## 3. Conceptos

### 3.1 Infraestructura como código
- Introducción a HCL
- modulos
- provedores
- recursos

### 3.2 AWS Lambda con Micronaut
- Runtime (Java 11, Coretto)
- IAM (https://github.com/nsriram/lambda-the-terraform-way/blob/master/docs/04-iam-account-setup.md)
- Layers
- Desencadenadores 
- API Gateway
- Diferecias entre API HTTP y API REST
- Seguridad

## 3.3 Buenas practicas y recomendaciones



## 4. Diseño de la PoC

A continuación se presenta un esquema de la PoC

[!Esquema lambda](doc/assets/esquema_lambda.png)

Ejemplo request:

```json
{

}
```

Ejemplo response:
```json
{

}
```

## 5. Configuración del ambiente de desarrollo

- Terraform con tfenv
- AWS Cli v2
- Java 11 con jenv
- IDE decente
- Una cuenta de AWS

- Creación Key desde el portal para el cli

reutilizar ese cli para el modulo iam de terraform

Terraform
Now we will run terraform script to create the IAM user.

TF_VAR keybase_id
Before we can run the main.tf terraform script, we have to configure the keybase id as a TF_VAR. TF_VARs allow values to be passed to terraform variables via environment. The variable declared in main.tf should be prefixed by TF_VAR_ i.e., TF_VAR_keybase_id.

You can provide this value for your id as below, by replacing the key_base_userid field.

```sh
export TF_VAR_keybase_id=key_base_userid
```

Terraform Apply
After setting TF_VAR_keybase_id environment variable, lets run terraform apply.

```sh
terraform init
terraform apply --auto-approve  
```

## 7. Referencias:

1. [Tutorial Hashicorp](https://learn.hashicorp.com/tutorials/terraform/infrastructure-as-code?in=terraform/aws-get-started)

2. [Lambda the Terraform Way](https://github.com/nsriram/lambda-the-terraform-way)

3. [Deploy a Serverless Micronaut function to AWS Lambda Java 11 Runtime](https://guides.micronaut.io/mn-serverless-function-aws-lambda/guide/index.html)


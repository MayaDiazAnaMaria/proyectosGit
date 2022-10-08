/*terraform {
  backend "s3" {
        bucket = "maya"
        encrypt = true
        key = "terraform.tfstate"
        region = "us-east-1"
  }
}*/
provider "aws"{
        region = "us-east-1"
        access_key = "AKIAWXRGOG4CIWL3TCXL"
        secret_key = "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"
}
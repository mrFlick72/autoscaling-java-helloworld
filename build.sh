mvn clean install

aws s3 cp target/autoscaling-java-helloworld.jar s3://${BUCKET_KEY}/autoscaling-java-helloworld.jar
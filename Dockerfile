FROM openjdk:17
ADD target/bookstore.jar bookstore.jar
ENTRYPOINT ["java","-jar","bookstore.jar"]
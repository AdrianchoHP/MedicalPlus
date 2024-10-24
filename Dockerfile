FROM openjdk:17
COPY "./target/MedicalPlus7-1.jar" "app.jar"
EXPOSE 8046
ENTRYPOINT [ "java","-jar","app.jar" ]
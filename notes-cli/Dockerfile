FROM openjdk:17-jdk-slim

WORKDIR /app

COPY src/ /app/src/
RUN javac src/com/example/*.java

VOLUME /app/data
CMD ["java", "-cp", "src", "com.example.App"]
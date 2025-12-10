FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY src ./src
RUN mkdir -p /app/data

# Компилируем при запуске образа (упрощённо)
CMD ["sh", "-c", "javac src/com/example/*.java && java -cp src com.example.App \"$@\""]
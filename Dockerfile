FROM mcr.microsoft.com/playwright/java:v1.54.0-noble

WORKDIR /app

COPY pom.xml .
COPY src ./src

COPY testng.xml .
COPY testng-single.xml .
COPY testng-api.xml .

RUN mvn dependency:go-offline

CMD ["mvn", "clean", "test"]
FROM eclipse-temurin:11-jre

COPY order/order-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
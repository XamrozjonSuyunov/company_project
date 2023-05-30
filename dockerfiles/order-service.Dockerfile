FROM eclipse-temurin:17-jre

COPY order/order-service/build/libs/order-service.jar .

ENTRYPOINT ["java", "-jar", "order-service.jar"]
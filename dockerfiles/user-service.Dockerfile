FROM eclipse-temurin:17-jre

COPY user/user-service/build/libs/user-service.jar .

ENTRYPOINT ["java", "-jar", "user-service.jar"]
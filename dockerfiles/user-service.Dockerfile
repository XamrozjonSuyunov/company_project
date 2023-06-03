FROM eclipse-temurin:11-jre

COPY user/user-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
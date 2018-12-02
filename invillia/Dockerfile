FROM java:8-jre

ADD ./target/invillia-1.0.1.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=dev", "-jar", "/app/invillia-1.0.1.jar"]

EXPOSE 8080
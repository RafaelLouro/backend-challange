FROM java:8-jre

ADD ./target/provider-1.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/provider-1.0.1.jar"]

EXPOSE 8001
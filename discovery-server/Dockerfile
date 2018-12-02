FROM java:8-jre

ADD ./target/discovery-server-1.0.1.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=dev", "-jar", "/app/discovery-server-1.0.1.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8761/actuator/health || exit 1

EXPOSE 8761
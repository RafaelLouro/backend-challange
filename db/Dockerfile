FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=rootpw
ENV MYSQL_DATABASE=app
ENV MYSQL_USER=invillia_db
ENV MYSQL_PASSWORD=123456

HEALTHCHECK --interval=30s --timeout=30s CMD mysqladmin ping -h localhost || exit 1

EXPOSE 3306
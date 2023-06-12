FROM mysql
ENV MYSQL_DATABASE="easybuy"
ENV MYSQL_ROOT_PASSWORD=""
COPY dumpeasybuy.sql /docker-entrypoint-initdb.d/dumpeasybuy.sql
EXPOSE 3306

FROM postgres:13

# Environment variables
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root123
ENV POSTGRES_DB=userdb

COPY init_user.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]
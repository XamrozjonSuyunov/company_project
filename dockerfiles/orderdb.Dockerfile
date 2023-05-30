FROM postgres:13

# Environment variables
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root123
ENV POSTGRES_DB=orderdb

COPY init_order.sql /docker-entrypoint-initdb.d/

EXPOSE 5434

CMD ["postgres"]
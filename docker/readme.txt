1 ставим docker c сайта
2 через spring boot initialize создаем проект со всем нужным
3 берем и запускаем docker-compose, предварительно написам sql.sql(там создание базы и юзера для контейнера)
4 данные для подключени к базе пишем в aplication.propperty
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=user_test
    spring.datasource.password=123456
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.show-sql: true

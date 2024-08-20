# Task Management System 

## Описание

Проект представляет собой 
тестовый сервер управления задачами.

<details open>
<summary><b>Стек</b></summary>

1. Фреймворк: Spring boot
2. Сборщик: Gradle
3. ORM: Hibernate
4. БД: Postgres
5. Контейнер: Docker-compose
6. Документация: Springdoc-openapi, Javadoc
7. Авторизация: Spring Security
</details>

<details open> 
<summary><b>Для начала работы</b></summary>

* в терминале: gradle build
* в терминале: docker-compose up
* swagger http://localhost:8080/swagger-ui/index.html
* JSON: http://localhost:8080/v3/api-docs
* yaml: http://localhost:8080/v3/api-docs.yaml
* в терминале: ./gradlew
</details>

<details open> 
<summary><b>Работа с пользователями</b></summary>

1. Регистрация пользователя. Endpoint: ``POST localhost:8080/api/auth/signup``
```json
{
  "username": "Nikolay",
  "email": "nikolay@mail.ru",
  "password": "password"
}
```
2. Аутентификация пользователя. Endpoint: ``POST localhost:8080/api/auth/login``
```json
{
  "email": "nikolay@mail.ru",
  "password": "password"
}
```

</details>

<details open> 
<summary><b>Работа с задачами</b></summary>


1. Создание задачи. Endpoint: ``POST localhost:8080/api/task``
```json
{
  "title": "Fix leakage",
  "description": "Leak detected in the bathroom ",
  "status": "IN_PROSSES",
  "priority": "CRITICAL"
}
```
2. Просмотр задачи по id. Endpoint: ``GET localhost:8080/api/task/{id_task}``
3. Изменение задачи по id. Endpoint: ``PUT localhost:8080/api/task/{id_task}``
```json
{
  "status": "DONE"
}
```
4. Удаление задачи по id. Endpoint: ``DELETE localhost:8080/api/task/{id_task}``
5. Назначение исполнителя для задачи по username. Endpoint: ``PUT localhost:8080/api/task/{id_task}/executor/{username}``
</details>

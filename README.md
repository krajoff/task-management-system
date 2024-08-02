# Spring Boot with PostgreSQL

## Описание

Проект представляет собой тестовый сервер электронных кошельков с минимальным функционалом в соответствии с заданием.

### 0. Для начала работы

* gradle build -x test
* docker-compose up

### 1. Пополнение или снятие с кошелька

**Endpoint:** `POST <server_url>/api/v1/wallet`

**Пример запроса:**

```json
{
  "walletId": "UUID",
  "operationType": "DEPOSIT or WITHDRAW",
  "amount": 1000
}
```

### 2. Получение текущего баланса

**Endpoint:** `GET <server_url>/api/v1/wallets/{WALLET_UUID}`

**Пример запроса:**

```json
{
  "walletId": "094813d4-e17a-4862-b275-4dcceaa81e33"
}
```

### 3. Создание кошелька

**Endpoint:** `POST <server_url>/api/v1/wallet`

**Пример запроса:**

```json
{
  "balance": 100
}
```
### 4. Удаление кошелька

**Endpoint:** `DELETE <server_url>/api/v1/wallets/{WALLET_UUID}`

**Пример запроса:**

```json
{
  "walletId": "094813d4-e17a-4862-b275-4dcceaa81e33"
}
```
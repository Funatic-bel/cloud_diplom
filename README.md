# Cloud Storage Service

REST API сервис для облачного хранения файлов с авторизацией.

---

## 🚀 Функционал

- Авторизация пользователей (login/logout)
- Загрузка файлов
- Просмотр списка файлов пользователя
- Удаление файлов
- Скачивание файлов
- Защита через token (auth-token)

---

## 🔐 Авторизация

После login возвращается токен:

```json
{
  "auth-token": "xxxx"
}

## Run

mvn clean package
docker-compose up --build

# Система Учета Времени Выполнения Методов

## Описание Проекта

Проект представляет собой систему для асинхронного логирования и анализа времени выполнения методов в приложении на основе Spring Boot с использованием Spring AOP. Система позволяет отслеживать время выполнения методов, помеченных специальными аннотациями, и сохранять данные в базу данных для последующего анализа.

## Функциональность

- **Аннотации для отслеживания времени**: `@TrackTime` и `@TrackAsyncTime` для синхронного и асинхронного отслеживания соответственно.
- **Аспекты на основе Spring AOP**: для перехвата вызовов методов и измерения времени их выполнения.
- **Сервис для сохранения данных**: асинхронное сохранение данных о времени выполнения методов в базе данных.
- **REST API**: предоставление статистики по времени выполнения методов через REST API.
- **Документация Api**: Доступна по ссылке после запуска проекта http://localhost:8081/swagger-ui/index.html .
## Как начать использовать

### Предварительные требования

- JDK 17
- Maven
- Docker

### Установка и Запуск

1. **Клонирование репозитория**

   ```bash
   git clone https://github.com/daniilvohromeev/t1-execution-time-of-methods
   cd t1-execution-time-of-methods
   docker compose -f docker-compose-non-dev.yml up -d

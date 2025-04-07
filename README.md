# 🏷️ SERVICES TEST TASK

![Java](https://img.shields.io/badge/Java-21+-green?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0+-brightgreen?style=flat-square&logo=spring)

---

## 📚 Описание проекта

**SERVICES TEST TASK** —  это набор микросервисов, разработанный для управления данными о компаниях и их пользователях. Проект состоит из двух основных сервисов:
1. User Service : Хранит информацию о пользователях.
2. Company Service : Хранит информацию о компаниях.

Проект использует следующие технологии:

* Spring Cloud для взаимодействия между сервисами.
* Netflix Eureka для сервисного обнаружения.
* Spring Cloud Gateway для маршрутизации запросов.
* Spring Cloud Config для централизованного хранения конфигураций.
* PostgreSQL в качестве базы данных (запускается в Docker).
* Docker Compose для контейнеризации и оркестрации сервисов.


---

## 🛠️ Технологии

- **Backend**: Spring Boot, Spring Data JPA, Hibernate, Feign Client.
- **База данных**: PostgreSQL.
- **Инструменты разработки**: Maven, IntelliJ IDEA.
- **Инструменты контейнеризации**: Docker

---

## 📂 Структура проекта

``` bash
service-test-task/
├── api-gateway
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.gateway/
│   │       │       ├── ApiGatewayApplication.java
│   │       │       └── GatewayConfig.java
│   │       └── resources/
│   │           └── application.yml
│   └── pom.xml
├── company-service
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.company_service/
│   │       │       ├── controller/
│   │       │       ├── dto/
│   │       │       ├── entity/
│   │       │       ├── repository/
│   │       │       ├── service/
│   │       │       └── CompanyServiceApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
├── config-server
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.config_server/
│   │       │       └── ConfigServerApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
├── eureka-server
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.eureka_server/
│   │       │       └── EurekaServerApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
├── user-service
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.user_service/
│   │       │       ├── controller/
│   │       │       ├── dto/
│   │       │       ├── entity/
│   │       │       ├── repository/
│   │       │       ├── service/
│   │       │       └── UserServiceApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
└── docker-compose.yml
```

---

## 🔧 Установка и запуск

1. **Склонируйте репозиторий**:
   ```bash
   git clone https://github.com/11qfour/ServicesTest
   cd service-test-task

2. **Запустите Docker Compose** 
   
   Убедитесь, что у вас установлены Docker и Docker Compose. Запустите все сервисы с помощью команды
   ```bash
   docker-compose up --build
   
3. **Проверьте работу сервисов:**
   После запуска всех сервисов вы можете проверить их работу через API или Swagger UI.

---

## 📊 Архитектура

**Микросервисы**
1. User Service : Отвечает за управление пользователями.
2. Company Service : Отвечает за управление компаниями.
3. API Gateway : Маршрутизирует запросы к соответствующим сервисам.
4. Eureka Server : Обнаруживает и регистрирует сервисы.
5. Config Server : Централизованное хранение конфигураций.

**Технологии взаимодействия**
1. Feign Client : Используется для взаимодействия между сервисами.
2. Spring Cloud Gateway : Маршрутизация запросов между сервисами.
3. Spring Cloud Eureka : Автоматическое обнаружение сервисов.
   

---
## 📋 Логика взаимодействия

**User Service**
* GET /api/users : Возвращает список всех пользователей.
* POST /api/users : Создает нового пользователя.
* GET /api/users/{userId} : Получает пользователя по ID.
* PUT /api/users/{userId} : Обновляет пользователя по ID.
* DELETE /api/users/{userId} : Удаляет пользователя по ID.

**Company Service**
* GET /api/companies : Возвращает список всех компаний.
* POST /api/companies : Создает новую компанию.
* GET /api/companies/{companyId} : Получает компанию по ID.
* PUT /api/companies/{companyId} : Обновляет компанию по ID.
* DELETE /api/companies/{companyId} : Удаляет компанию по ID.

**Взаимодействие между сервисами**
* User Service → Company Service : При получении пользователя также возвращается информация о компании.
* Company Service → User Service : При получении компании также возвращается список пользователей.

---

## 🧪 Тестирование выполняется через Postman

Ниже приведены скриншоты выполнения некоторых вышеуказанных запросов в Postman и другие важные моменты:

#### Создание клиента
![Docker screen](https://github.com/11qfour/ServicesTest/tree/main/media/ConsoleLogWhileDockerBuited.png)

#### Успешное создание компании
![Success Post Request to Company](https://github.com/11qfour/ServicesTest/tree/main/media/SuccessPostRequestCompanies.png)

#### Скриншот Eureka server
![Eureka Server screen](https://github.com/11qfour/ServicesTest/tree/main/media/EurekaWithServicesOnEnvPort.png)

#### Ошибка создания пользователя
![Error Post Request to User](https://github.com/11qfour/ServicesTest/tree/main/media/ServerErrorInPostReqUser.png)

#### Ошибка получения компаний
![Error Get Request to Company](https://github.com/11qfour/ServicesTest/tree/main/media/ErrorGetCompanyServerError.png)

---

## ✉️ Контакты
+ Email: elevenfourprod@yandex.ru
- GitHub: @11qfour
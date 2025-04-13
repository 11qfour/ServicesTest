# 🏷️ SERVICES TEST TASK

![Java](https://img.shields.io/badge/Java-21+-green?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0+-brightgreen?style=flat-square&logo=spring)

---

## 📚 Описание проекта

**SERVICES TEST TASK** —  это набор микросервисов, разработанный для управления данными о компаниях и их пользователях. Проект состоит из двух основных сервисов:
1. User Service : Хранит, передает и изменяет информацию о пользователях.
2. Company Service : Хранит, передает и изменяет информацию о компаниях.

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
- **Инструменты контейнеризации**: Docker, Docker Desktop
- **Инструменты тестирования**: Postman
---

## 📂 Структура проекта

``` bash
service-test-task/
├── api-gateway
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.gateway/
│   │       │       └── config
│   │       │           ├── ApiGatewayApplication.java
│   │       │       └── GatewayConfig.java
│   │       └── resources/
│   │           └── application.yml
│   └── pom.xml
├── common-dto
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.DTO/
│   │       │       ├── CompanyRequestDto.java
│   │       │       ├── CompanyResponseDto.java
│   │       │       ├── CompanyResponseFromUserDto.java //особое DTO для вызова через CompanyClient
│   │       │       ├── UserRequestDto.java
│   │       │       ├── UserResponseDto.java
│   │       │       └── UserResponseFromCompanyDto.java //особое DTO для вызова через UserClient
├── company-service
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.company_service/
│   │       │       ├── client/
│   │       │       ├── controller/
│   │       │       ├── entity/
│   │       │       ├── exception/
│   │       │       ├── mapper/
│   │       │       ├── repository/
│   │       │       ├── service/
│   │       │       └── CompanyServiceApplication.java
│   │   └── resources/
│   │       ├── db.changelog/ //миграции
│   │       └── application.yml
│   └── pom.xml
├── config-server
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.config.server/
│   │       │       └── ConfigServerApplication.java
│   │   └── resources/
│   │       ├── configs/
│   │       └── application.yml
│   └── pom.xml
├── eureka-server
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.eureka.server/
│   │       │       └── EurekaServerApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
├── user-service
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com.example.service_test_task.user_service/
│   │       │       ├── client/
│   │       │       ├── controller/
│   │       │       ├── entity/
│   │       │       ├── exception/
│   │       │       ├── mapper/
│   │       │       ├── repository/
│   │       │       ├── service/
│   │       │       └── UserServiceApplication.java
│   │   └── resources/
│   │       ├── db.changelog/ //миграции
│   │       └── application.yml
│   └── pom.xml
├── .env
└── docker-compose.yml
```

---

## 🔧 Установка и запуск

1. **Склонируйте репозиторий**:
   ```bash
   git clone https://github.com/11qfour/ServicesTest
   cd ServicesTest
   ```

2. **Настройте переменные окружения**

   Отредактируйте файл .env (порты, учетные данные БД)

3. **Запустите Docker Compose** 
   
   Убедитесь, что у вас установлены Docker и Docker Compose. Запустите все сервисы с помощью команды
   ```bash
   docker-compose up --build -d
   ```

   Сервисы будут доступны на портах:

   Eureka Server: http://localhost:9861

   API Gateway: http://localhost:8080

   User Service: http://localhost:8081

   Company Service: http://localhost:8082

4. **Проверьте статус сервисов:**
   ```bash
   docker-compose ps
   ```
   
5. **Проверьте работу сервисов:**
   После запуска в вы можете проверить их работу через Postman или посмотреть логи ошибок
   ```bash
   docker-compose up logs -f

6. **При необходимости остановите сервисы:**
   ```bash
   docker-compose down
   ```

---

## 📊 Архитектура

### **Микросервисы**
1. User Service : Отвечает за управление пользователями.
2. Company Service : Отвечает за управление компаниями.
3. API Gateway : Маршрутизирует запросы к соответствующим сервисам.
4. Eureka Server : Обнаруживает и регистрирует сервисы.
5. Config Server : Централизованное хранение конфигураций.

### **Технологии взаимодействия**
1. Feign Client : Используется для взаимодействия между сервисами.
2. Spring Cloud Gateway : Маршрутизация запросов между сервисами.
3. Spring Cloud Eureka : Автоматическое обнаружение сервисов.
   

---
## 📋 Логика взаимодействия

### **User Service**
* GET /api/users : Возвращает список всех пользователей.
* POST /api/users : Создает нового пользователя.
* GET /api/users/{userId} : Получает пользователя по ID.
* PUT /api/users/{userId} : Обновляет пользователя по ID.
* DELETE /api/users/{userId} : Удаляет пользователя по ID.

### **Company Service**
* GET /api/companies : Возвращает список всех компаний.
* POST /api/companies : Создает новую компанию.
* GET /api/companies/{companyId} : Получает компанию по ID.
* PUT /api/companies/{companyId} : Обновляет компанию по ID.
* DELETE /api/companies/{companyId} : Удаляет компанию по ID.

### **Взаимодействие между сервисами**
* User Service → Company Service : При получении пользователя также возвращается информация о компании.
* Company Service → User Service : При получении компании также возвращается список пользователей.

---

## 🧪 Тестирование через Postman

Ниже приведены скриншоты выполнения некоторых вышеуказанных запросов в Postman и другие важные моменты:

### Создание клиентов
![Docker screen](https://github.com/11qfour/ServicesTest/raw/main/media/ConsoleLogWhileDockerBuited.png)

### Скриншот Eureka server
![Eureka Server screen](https://github.com/11qfour/ServicesTest/raw/main/media/EurekaWithServicesOnEnvPort.png)

### Успешные выполнения запросов к компании

#### Добавление компании
![Success Post Request to Company](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessPostRequestCompanies.png)

#### Получение компании с сотрудниками
![Success Get Request to Companies](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessGetCompanies.png)
![Success Get Request to Company by id](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessGet1Company.png)

#### Изменение данных о компании
![Success Put Request to Company](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessPutCompany.png)

#### Удаление компании
![Success DELETE Request to Company](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessDeleteCompany.png)
![Success Check DELETE Request to Company](https://github.com/11qfour/ServicesTest/raw/main/media/CheckSuccessDeleteCompany.png)

### Успешные выполнения запросов к пользователям

#### Добавление пользователя
![Success Post Request to User](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessPostUser.png)
![Success Post Request to User to Not Exist Company](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessErrorPostUserToNotExistCompany.png)

#### Получение сотрудников с компаниями
![Success Get Request to Users](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessGetUsers.png)
![Success Get Request to User by id](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessGet1User.png)

#### Изменение данных о сотрудниках
![Success PUT Request to User](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessPutUser.png)
![Success Check PUT Request to User](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessCheckPutUserInConsole.png)

#### Удаление сотрудников
![Success DELETE Request to User](https://github.com/11qfour/ServicesTest/raw/main/media/SuccessDeleteCompany.png)
![Success Check DELETE Request to Company](https://github.com/11qfour/ServicesTest/raw/main/media/CheckSuccessDeleteCompany.png)

---

## ✉️ Контакты
+ Email: elevenfourprod@yandex.ru
- GitHub: @11qfour
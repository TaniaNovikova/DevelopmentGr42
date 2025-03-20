Setting Up Security for an Electronics Online Store with Spring Security
Objective
Configure a security system for the REST API of an electronics online store using Spring Security. Implement in-memory user authentication and access control to different resources.

Requirements
Add two users with roles:

customer with password customerpass and role CUSTOMER.
manager with password managerpass and role MANAGER.
Use BCryptPasswordEncoder to hash passwords.
Configure access control for REST endpoints:

/products/public/list – accessible to all users (regardless of authentication).
/products/customer/cart – accessible only to users with the CUSTOMER role.
/products/manager/add – accessible only to users with the MANAGER role.
Create a ProductController with three methods:

GET /products/public/list – returns a list of products (accessible to all).
GET /products/customer/cart – accessible only to users with the CUSTOMER role, simulates viewing the cart.
GET /products/manager/add – accessible only to users with the MANAGER role, simulates adding a new product.
Configure Spring Security:

Use the default login form (formLogin()).
Disable CSRF protection (csrf().disable()).
Implement role-based authorization.
***************************************************************************************
Цель
Настроить систему безопасности для REST API интернет-магазина электроники, используя Spring Security. Реализовать аутентификацию пользователей в памяти и разграничение доступа к разным ресурсам.
electronics store
Требования к выполнению задания
Добавьте двух пользователей с ролями:

customer с паролем customerpass и ролью CUSTOMER
manager с паролем managerpass и ролью MANAGER
Используйте BCryptPasswordEncoder для хеширования паролей.
Настройте разграничение доступа к REST-эндпоинтам:

/products/public/list – доступен всем пользователям (независимо от авторизации).
/products/customer/cart – доступен только пользователям с ролью CUSTOMER.
/products/manager/add – доступен только пользователям с ролью MANAGER.
Создайте контроллер ProductController, который содержит три метода:

GET /products/public/list – возвращает список товаров (доступен всем).
GET /products/customer/cart – доступен только пользователям с ролью CUSTOMER, имитирует просмотр корзины.
GET /products/manager/add – доступен только пользователям с ролью MANAGER, имитирует добавление нового товара.
Настройте Spring Security:

Используйте форму входа по умолчанию (formLogin()).
Отключите CSRF-защиту (csrf().disable()).
Реализуйте авторизацию на основе ролей.

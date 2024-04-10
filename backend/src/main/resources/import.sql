INSERT INTO user(id, document, email, name, password, phone, registration_date)
VALUES(1, "09363198448", 'admin_getme@gmail.com', 'System Admin', '$2a$10$Fqu1pQGk2BAq9Au8IIAAMOnK/IaW5L/codaOfYTFyI.wSmuqS96OG', '5583999570615', '2024-04-10 03:46:39');

insert into role values(1, "ROLE_ADMIN"), (2, "ROLE_USER");

insert into user_role values(1, 1);

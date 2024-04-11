insert into tb_role values (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

insert into tb_user (id, name, document, phone, email, password, registration_date) values (1, 'System Admin', '09363198448', '5583999570615', 'admin_getme@gmail.com', '$2a$10$Fqu1pQGk2BAq9Au8IIAAMOnK/IaW5L/codaOfYTFyI.wSmuqS96OG', '2024-04-10 03:46:39');

insert into tb_user_role values (1, 1);

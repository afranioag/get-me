INSERT INTO TB_ROLE(authority) VALUES ('ROLE_ADMIN'), ('ROLE_USER');
INSERT INTO tb_user (registration_date, document, email, image, "name", "password", phone) VALUES('2024-04-16 00:00:00', '09363198448', 'afranio.cet@gmail.com', 'https://example.com/johndoe.jpg', 'Afranio Alves', '$2a$10$jLnC23sHVSCKlS3OUHQdZOtE/YvR6SfFS/zwZnQK61IQqLY.l2Ute', '5583999570615');
INSERT INTO tb_user_role VALUES(1,1)

INSERT INTO tb_person(age, gender, height, status, last_update, registration_date, user_id, body_mark, "document", eye_color, hair_color, "name", photo, physical_description, psychological_description)VALUES(30, 0, 175, 0, '2024-04-16 03:02:15.857', '2024-04-16 03:02:15.820', 1, 'Scar on left arm', '123456789', 'Blue', 'Brown', 'John Doe', '', 'Average build, 175 cm tall', 'Friendly and outgoing');
INSERT INTO tb_report(latitude, longitude, person_id, user_id, city, country, neighborhood, "number", postal_code, state)VALUES(34.0530, -118.2437, 1, 1, 'Los Angeles', 'United States', 'Downtown', '123', '90001', 'California');

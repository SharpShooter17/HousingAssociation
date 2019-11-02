INSERT INTO user_t (id, email, enabled, first_name, password, last_name, telephone, token, token_expiration_date)
VALUES (1, 'admin@example.com', true, 'Admin', '$2a$10$y5DanIGzTu7ganx5it1VUOKmRQdM10Fx8guRX4c1XIf0dRdmTRbZ.', 'Administrator', '0123456789', 'token', '2050-01-01');
-- Password is: password

insert into user_role_t values (1, 2);
insert into user_role_t values (1, 3);
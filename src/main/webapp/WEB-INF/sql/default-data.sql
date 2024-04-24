INSERT INTO Users (password, email, name, phone, surname, username, birthDate)
    VALUES ('123456', 'example@users.com', 'Admin', '555 55 55', 'Default', 'admin', '1990-01-01');

INSERT INTO UserModel_roles (roles, UserModel_id) VALUES (0, 1);
INSERT INTO UserModel_roles (roles, UserModel_id) VALUES (1, 1);
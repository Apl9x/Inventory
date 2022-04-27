INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Mother board', 199.99, 15);
INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Key board', 121.5, 12);
INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Speaker', 203.5, 15);
INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Monitor', 249.99, 10);
INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Mouse', 79.99, 21);
INSERT INTO PRODUCT (NAME, PRICE, QUANTITY) VALUES ('Graphic Card', 499.99, 9);

-- USER
-- non-encrypted password: letmein
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
                                                                              (1,  'admin', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Administrator', 'Adminstrator'),
                                                                              (2,  'cons_jane', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jane', 'Doe'),
                                                                              (3,  'cons_mark', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Mark', 'Smith'),
                                                                              (4,  'wally', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Walter', 'Adams');

-- ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_CONS', 'Consumer');

INSERT INTO user_role(user_id, role_id) VALUES
                                            (1, 1), -- give admin ROLE_ADMIN
                                            (2, 2),  -- give Jane ROLE_CONS
                                            (3, 2),  -- give Mark ROLE_CONS
                                            (4, 1),  -- give Wally ROLE_ADMIN
                                            (4, 2);  -- give Wally ROLE_CONS


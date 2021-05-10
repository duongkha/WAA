INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('SELLER');
INSERT INTO roles (name) VALUES ('BUYER');

-- password is the same as username
INSERT INTO USERS(username,password,first_name,last_name,enabled)
VALUES('admin','$2a$10$JirblkU.nrFQVWpRJkeTcev6J1I/UoZvIDdjPUiIHKAeBrtZq8iYe','Admin','Admin',1);
INSERT INTO USERS(username,password,first_name,last_name,enabled)
VALUES('seller','$2a$10$2jDKoi9ODI484KIJKm4DLu8jFnlQl1DVL2vks3ELzEq/pgmwvrYti','Seller','Seller',1);
INSERT INTO USERS(username,password,first_name,last_name,enabled)
VALUES('buyer','$2a$10$mtfpfkTYQfvt.K/TD1noR.8HIZWx2HEDe6Yxfw.HZlbxxQ0NKbgn6','Buyer','Buyer',1);

INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT USER_ID FROM USERS WHERE USERNAME='ADMIN' LIMIT 1) , (SELECT ROLE_ID FROM ROLES WHERE NAME='ADMIN' LIMIT 1);

INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT USER_ID FROM USERS WHERE USERNAME='SELLER' LIMIT 1) , (SELECT ROLE_ID FROM ROLES WHERE NAME='SELLER' LIMIT 1);

INSERT INTO USERS_ROLES(user_id,role_id)
SELECT (SELECT USER_ID FROM USERS WHERE USERNAME='BUYER' LIMIT 1) , (SELECT ROLE_ID FROM ROLES WHERE NAME='BUYER' LIMIT 1);

CREATE TABLE user_roles(
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(25) NOT NULL,
  description VARCHAR(100) NOT NULL,
  CONSTRAINT role_name_unique UNIQUE(name)
);

CREATE TABLE functionalities(
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(25) NOT NULL,
  description VARCHAR(100) NOT NULL,
  CONSTRAINT functionality_name_unique UNIQUE(name)
);

CREATE TABLE users(
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(25) NOT NULL,
  password VARCHAR(130) NOT NULL
);

CREATE TABLE user_roles_users(
  role_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT user_roles_fk FOREIGN KEY(role_id) REFERENCES user_roles(id),
  CONSTRAINT users_fk FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE roles_functionalities(
  role_id BIGINT NOT NULL,
  functionality_id BIGINT NOT NULL,
  CONSTRAINT user_roles_fk FOREIGN KEY(role_id) REFERENCES user_roles(id),
  CONSTRAINT functionalities_fk FOREIGN KEY(functionality_id) REFERENCES functionalities(id)
);

CREATE SEQUENCE user_roles_seq START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE functionalities_seq START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 NO CYCLE;

CREATE TABLE roles (
  name VARCHAR(50) NOT NULL, 
  description VARCHAR(255), 
  PRIMARY KEY("name")
)

INSERT INTO roles (name, description) VALUES ('admin', 'This is the super user of the application');
INSERT INTO roles (name, description) VALUES ('manager', 'This role can post new jobs.');

ALTER TABLE "public"."user_roles"
  DROP CONSTRAINT "user_role_pk" RESTRICT;
    
ALTER TABLE user_roles
  ADD CONSTRAINT "user_roles_fk" FOREIGN KEY ("role")
    REFERENCES roles("name")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;
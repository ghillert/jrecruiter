ALTER TABLE "public"."jobs" ADD COLUMN "longitude" NUMERIC;
ALTER TABLE "public"."jobs" ADD COLUMN "latitude" NUMERIC;

ALTER TABLE jobs ALTER job_id TYPE numeric;

drop table app_user;

CREATE TABLE users_tmp
(
  user_name   character varying(50)  NOT NULL,
  user_passwd character varying(100) NOT NULL,
  first_name  character varying(50)  NOT NULL,
  last_name   character varying(50)  NOT NULL,
  company character varying(50),
  phone       character varying(25),
  fax         character varying(25),
  email       character varying(50)  NOT NULL,
  register_date timestamp with time zone  NOT NULL,
  expire_date timestamp with time zone,
  update_date timestamp with time zone
)
WITHOUT OIDS;
ALTER TABLE users OWNER TO postgres;


delete from users where email = '';
delete from users where user_name = '';

insert into users_tmp
       (user_name,     user_passwd, first_name, last_name,       phone, fax,     email, register_date, expire_date, update_date, company)
select  u.user_name, u.user_passwd, u.first_name, u.last_name, u.phone, u.fax, u.email, u.register_date, u.expire_date, u.update_date, u.company

 from users u,

(SELECT uu.email as email, max(uu.register_date) as d
 	 FROM users uu where email in (

SELECT email
FROM users
GROUP BY email
HAVING ( COUNT(email) >= 1 )

      )
 	 GROUP BY uu.email) s where
u.email = s.email and u.register_date = s.d


drop table users;

CREATE TABLE users
(
  id numeric DEFAULT nextval('users_id_seq'::regclass),
  user_name   character varying(50)  NOT NULL,
  user_passwd character varying(100) NOT NULL,
  first_name  character varying(50)  NOT NULL,
  last_name   character varying(50)  NOT NULL,
  company character varying(50),
  phone       character varying(25),
  fax         character varying(25),
  email       character varying(50)  NOT NULL,
  register_date timestamp with time zone  NOT NULL,
  expire_date timestamp with time zone,
  update_date timestamp with time zone,
    CONSTRAINT users_id_pk PRIMARY KEY (id),
	CONSTRAINT users_user_name_unique UNIQUE (user_name),
	CONSTRAINT users_email UNIQUE (email)
)
WITHOUT OIDS;
ALTER TABLE users OWNER TO postgres;

insert into users
       (user_name,   user_passwd, first_name, last_name,       phone, fax,     email, register_date, expire_date, update_date, company)
select  u.user_name, u.user_passwd, u.first_name, u.last_name, u.phone, u.fax, u.email, u.register_date, u.expire_date, u.update_date, u.company
from users_tmp u

drop table users_tmp;



CREATE TABLE users_roles_tmp
(
    'roles_id' numeric,
	'users_id' numeric;
)
WITHOUT OIDS;
ALTER TABLE users_roles OWNER TO postgres;

insert into users_roles_tmp (users_id, role)

select u.id, r.id from users_roles ur, users u, roles r
where ur.user_name = u.user_name
and ur.role = r.name

drop table users_roles;

CREATE TABLE user_roles
(
  user_name character varying(25) NOT NULL,
  "role" character varying(25) NOT NULL,
  CONSTRAINT user_roles_fk FOREIGN KEY ("role")
      REFERENCES roles (name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITHOUT OIDS;
ALTER TABLE user_roles OWNER TO postgres;

CREATE TABLE roles
(
  name character varying(50) NOT NULL,
  description character varying(255),
  id numeric NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
  CONSTRAINT roles_pk PRIMARY KEY (id),
  CONSTRAINT roles_unique UNIQUE (name)
)
WITHOUT OIDS;
ALTER TABLE roles OWNER TO postgres;





CREATE TABLE jobs (
    job_id serial NOT NULL,
    business_name character varying(50) NOT NULL,
    business_location character varying(50),
    job_title character varying(50) NOT NULL,
    salary numeric(18,0),
    description character varying NOT NULL,
    web_site character varying(50),
    business_address1 character varying(50),
    business_address2 character varying(50),
    business_city character varying(30),
    business_state character varying(20),
    business_zip character varying(15),
    business_phone character varying(15),
    business_email character varying(50),
    industry character varying(30),
    job_restrictions character varying,
    register_date timestamp without time zone,
    expire_date timestamp without time zone,
    update_date timestamp without time zone,
    status integer NOT NULL,
    user_name character varying(25)
);

CREATE TABLE user_roles (
    user_name character varying(25) NOT NULL,
    role character varying(25) NOT NULL
);

CREATE TABLE users (
    user_name character varying(25) NOT NULL,
    user_passwd character varying(25) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    phone character varying(25),
    fax character varying(25),
    email character varying(50),
    register_date timestamp without time zone,
    expire_date timestamp without time zone,
    update_date timestamp without time zone,
    company character varying(25)
);


ALTER TABLE ONLY jobs
    ADD CONSTRAINT jobs_pk PRIMARY KEY (job_id);

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pk PRIMARY KEY (user_name);

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_role_pk PRIMARY KEY (user_name, role);

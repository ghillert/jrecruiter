
#### 2010-Jan-02 Gunnar Hillert
ALTER TABLE users ADD COLUMN USER_AUTHENTICATION_TYPE bigint;
UPDATE users SET USER_AUTHENTICATION_TYPE=1;
ALTER TABLE USERS ALTER COLUMN USER_AUTHENTICATION_TYPE SET NOT NULL;

#### 2009-Dec-22 Gunnar Hillert
ALTER TABLE jobs ADD COLUMN business_Phone_Extension character varying(15);

#### 2009-Nov-04 Gunnar Hillert
ALTER TABLE job_count_per_days ADD COLUMN automatically_cleaned boolean;
UPDATE job_count_per_days
SET automatically_cleaned=false
ALTER TABLE job_count_per_days ALTER COLUMN automatically_cleaned SET STORAGE PLAIN;
ALTER TABLE job_count_per_days ALTER COLUMN automatically_cleaned SET NOT NULL;
ALTER TABLE job_count_per_days ALTER COLUMN automatically_cleaned SET DEFAULT false;


--
-- Configuration Table
--

CREATE TABLE configuration (
  message_key VARCHAR NOT NULL, 
  message_text TEXT, 
  last_modified TIMESTAMP WITHOUT TIME ZONE, 
  CONSTRAINT configuration_pkey PRIMARY KEY("message_key")
);

--
-- Configuration Data
--

INSERT INTO configuration (message_key, message_text, last_modified) VALUES ('mail.jobposting.subject', '[ajug-jobs] A new job posting: ${jobTitle}', '2006-01-21 16:40:41');
INSERT INTO configuration (message_key, message_text, last_modified) VALUES ('mail.jobposting.email', 'test@test.com', '2006-01-21 16:40:41');
INSERT INTO configuration (message_key, message_text, last_modified) VALUES ('mail.password.subject', 'jRecruiter Account Information - Lost Password', '2006-01-21 16:40:42');
INSERT INTO configuration (message_key, message_text, last_modified) VALUES ('mail.password.body', 

'Hello, 

Your requested password is: ${password}

', '2006-01-21 16:40:42');

INSERT INTO configuration (message_key, message_text, last_modified) VALUES ('mail.jobposting.body', 

'A new job has been added:

Job ID:               ${jobId}
Job Title:            ${jobTitle}
Location:             ${businessLocation}
Business Name:        ${businessName}

Job Description:
----------------

${description}

Job Restrictions:
----------------

${jobRestrictions}

This job was posted on: ${updateDate}

', '2006-01-21 16:40:41');


--
-- Statistics Table
--

CREATE TABLE statistics (
  job_id INTEGER NOT NULL, 
  counter INTEGER DEFAULT 0 NOT NULL, 
  last_access TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL, 
  unique_visits INTEGER DEFAULT 0 NOT NULL, 
  CONSTRAINT statistics_pkey PRIMARY KEY("job_id"), 
  CONSTRAINT statistics_fk FOREIGN KEY ("job_id")
    REFERENCES jobs("job_id")
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE
);
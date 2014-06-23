jRecruiter
==========

jRecruiter is the job posting service of the [Atlanta Java Users Group][].

[Atlanta Java Users Group]: http://www.ajug.org/


Build Status:

[![Build Status](https://travis-ci.org/ghillert/jrecruiter.png?branch=master)](https://travis-ci.org/ghillert/jrecruiter)

### Configuration

Configuration information is retrieved from a properties file called `jrecruiter.properties`

````
server.address=http://localhost:8080/jrecruiter
database.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
database.hibernate.show_sql=false
database.jdbc.driverClassName=org.postgresql.Driver
database.jdbc.url=jdbc:postgresql://localhost:5432/jrecruiter
database.jdbc.username=
database.jdbc.password=

twitter.username=
twitter.password=
bitly.username=
bitly.password=

key.recaptcha.private=
key.recaptcha.public=

mail.host=localhost=
````

### Testing SMTP for Development

http://nilhcem.github.io/FakeSMTP

## Postgres on Mac

Before starting up Postgres you may have to execute:

```
sudo sysctl -w kern.sysv.shmmax=12582912
sudo sysctl -w kern.sysv.shmall=12582912
```


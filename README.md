# Contacts List
Implementation of individual task for iTechArt Java course

##Requirements
    JDK 8 or higher
    Gradle 4.10.2 or higher
    PostgreSQL 11 or higher
    
## Setup
1. `git clone https://github.com/ivanjermakov/contacts-list.git`
2. `gradle build`
3. Execute `database.sql` on previously created PSQL database.
4. Fill next properties in `application.properties`:

        spring.mail.username=
        spring.mail.password=
        mail.smtp.user=
        mail.smtp.password=

## Run
Web application will start on `:8080` port.
    
    gradle bootRun




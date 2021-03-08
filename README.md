SQL - required tables

project

CREATE TABLE project (
    projectid INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (projectid)
);

ticket

CREATE TABLE ticket (
    ticketid INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    projectid INT,
    PRIMARY KEY (ticketid),
    CONSTRAINT FK_ProjectTicket FOREIGN KEY (projectid)
    REFERENCES project(projectid)
);
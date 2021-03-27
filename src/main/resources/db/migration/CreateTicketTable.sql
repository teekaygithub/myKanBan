CREATE TABLE IF NOT EXISTS ticket (
    ticketid INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    projectid INT NOT NULL,
    status enum('TODO','INPROGRESS','DONE','CANCELLED') NOT NULL DEFAULT 'TODO',
    PRIMARY KEY (tickedid),
    CONSTRAINT fk_projectid FOREIGN KEY ('projectid') REFERENCES project('id')
);
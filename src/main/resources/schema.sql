-- Create enum for Lector degrees
CREATE TYPE degree AS ENUM ('ASSISTANT', 'ASSOCIATE_PROFESSOR', 'PROFESSOR');

-- Create the 'lectors' table
CREATE TABLE lector (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    degree degree NOT NULL
);

-- Create the 'departments' table
CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    head_id INT NOT NULL,
    CONSTRAINT fk_head FOREIGN KEY (head_id) REFERENCES lector(id) ON DELETE SET NULL
);

-- Create the join table for the many-to-many relationship
CREATE TABLE department_lectors (
    department_id INT,
    lector_id INT,
    PRIMARY KEY (department_id, lector_id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE,
    CONSTRAINT fk_lector FOREIGN KEY (lector_id) REFERENCES lector(id) ON DELETE CASCADE
);
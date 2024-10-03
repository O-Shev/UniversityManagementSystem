-- Insert sample data (Optional)
INSERT INTO lector (name, salary, degree) VALUES
('John Doe', 1000, 'ASSISTANT'),
('Jane Smith', 7000, 'ASSOCIATE_PROFESSOR'),
('Ivan Petrenko', 4000, 'ASSISTANT'),
('Petro Ivanov', 7000, 'ASSOCIATE_PROFESSOR'),
('David Brown', 10000, 'PROFESSOR');

INSERT INTO department (name, head_id) VALUES
('Computer Science', 1),
('Physics', 4);

INSERT INTO department_lectors (department_id, lector_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5);
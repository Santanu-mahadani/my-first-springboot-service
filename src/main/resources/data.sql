
-- Department Table
CREATE TABLE department (
                            id INT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL
);

-- Instructor Table
CREATE TABLE instructor (
                            id INT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            email VARCHAR(100) NOT NULL
);

-- Course Table
CREATE TABLE course (
                        id INT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        code VARCHAR(20) NOT NULL,
                        credits INT NOT NULL,
                        description VARCHAR(255),
                        instructor_id INT,
                        FOREIGN KEY (instructor_id) REFERENCES instructor(id)
);

-- Student Table
CREATE TABLE student (
                         id INT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         phone VARCHAR(20),
                         address VARCHAR(255),
                         department_id INT,
                         FOREIGN KEY (department_id) REFERENCES department(id)
);

-- Enrollment Table
CREATE TABLE enrollment (
                            id INT PRIMARY KEY,
                            student_id INT,
                            course_id INT,
                            enrollment_date DATE,
                            FOREIGN KEY (student_id) REFERENCES student(id),
                            FOREIGN KEY (course_id) REFERENCES course(id)
);



-- Sample Departments
INSERT INTO department (id, name) VALUES (1, 'Computer Science');
INSERT INTO department (id, name) VALUES (2, 'Mathematics');

-- Sample Instructors
INSERT INTO instructor (id, name, email) VALUES (1, 'Dr. Alice', 'alice@university.edu');
INSERT INTO instructor (id, name, email) VALUES (2, 'Dr. Bob', 'bob@university.edu');

-- Sample Courses
INSERT INTO course (id, name, code, credits, description, instructor_id) VALUES (1, 'Algorithms', 'CS101', 4, 'Intro to Algorithms', 1);
INSERT INTO course (id, name, code, credits, description, instructor_id) VALUES (2, 'Calculus', 'MATH101', 3, 'Intro to Calculus', 2);

-- Sample Students
INSERT INTO student (id, name, email, phone, address, department_id) VALUES (1, 'John Doe', 'john.doe@example.com', '1234567890', '123 Main St', 1);
INSERT INTO student (id, name, email, phone, address, department_id) VALUES (2, 'Jane Smith', 'jane.smith@example.com', '0987654321', '456 Elm St', 2);

-- Sample Enrollments
INSERT INTO enrollment (id, student_id, course_id, enrollment_date) VALUES (1, 1, 1, '2024-01-10');
INSERT INTO enrollment (id, student_id, course_id, enrollment_date) VALUES (2, 2, 2, '2024-01-11');


CREATE TABLE IF NOT EXISTS users2 (
                                     user_id SERIAL PRIMARY KEY,
                                     nickname VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    last_name VARCHAR(255)
    );
INSERT INTO users2 (nickname, name, last_name) VALUES
                                                     ('johndoe', 'John', 'Doe'),
                                                     ('mikebrown', 'Mike', 'Brown'),
                                                     ('sarahconnor', 'Sarah', 'Connor'),
                                                     ('alexsmith', 'Alex', 'Smith'),
                                                     ('emilydavis', 'Emily', 'Davis'),
                                                     ('chrisjohnson', 'Chris', 'Johnson'),
                                                     ('karenwilson', 'Karen', 'Wilson'),
                                                     ('roberthall', 'Robert', 'Hall'),
                                                     ('lisawhite', 'Lisa', 'White');
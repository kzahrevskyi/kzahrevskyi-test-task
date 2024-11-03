CREATE TABLE IF NOT EXISTS users (
                                     user_id SERIAL PRIMARY KEY,
                                     login VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
    );
INSERT INTO users (login, first_name, last_name) VALUES
                                                     ('stevenadams', 'Steven', 'Adams'),
                                                     ('katiegreen', 'Katie', 'Green'),
                                                     ('danieltaylor', 'Daniel', 'Taylor'),
                                                     ('jessicaanderson', 'Jessica', 'Anderson'),
                                                     ('michaelmoore', 'Michael', 'Moore'),
                                                     ('lindaperez', 'Linda', 'Perez'),
                                                     ('kevinsmith', 'Kevin', 'Smith'),
                                                     ('nancytorres', 'Nancy', 'Torres'),
                                                     ('paulhernandez', 'Paul', 'Hernandez'),
                                                     ('susanroberts', 'Susan', 'Roberts');
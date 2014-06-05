-- database schema automatically loaded by unitils before tests are run

CREATE TABLE employees(
  id INTEGER IDENTITY,
  name VARCHAR NOT NULL,
  title VARCHAR NOT NULL,
  salary DECIMAL NOT NULL,
  UNIQUE(name)
);

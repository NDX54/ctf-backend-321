-- Table for base users
CREATE TABLE base_users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

-- Table for students inheriting from base_users
CREATE TABLE students (
    user_id SERIAL PRIMARY KEY REFERENCES base_users(user_id),
    year_level INTEGER NOT NULL,
    score INTEGER DEFAULT 0
) INHERITS (base_users);

-- Table for teachers inheriting from base_users
CREATE TABLE teachers (
    user_id SERIAL PRIMARY KEY REFERENCES base_users(user_id),
    school VARCHAR(255) NOT NULL
) INHERITS (base_users);
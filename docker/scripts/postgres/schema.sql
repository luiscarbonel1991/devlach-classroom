-- Creating the database tables

-- User Table
CREATE TABLE IF NOT EXISTS app_users (
                       user_id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Profile Table
CREATE TABLE IF NOT EXISTS profiles (
                          profile_id BIGSERIAL PRIMARY KEY,
                          user_id BIGSERIAL NOT NULL,
                          role VARCHAR(50) NOT NULL CHECK (role IN ('student', 'teacher')),
                          full_name VARCHAR(255),
                          bio TEXT,
                          profile_pic_url VARCHAR(255),
                          FOREIGN KEY (user_id) REFERENCES app_users(user_id)
);

-- Regular Availability Table for Teachers
CREATE TABLE IF NOT EXISTS regular_availability (
                                      availability_id BIGSERIAL PRIMARY KEY,
                                      profile_id BIGSERIAL NOT NULL,
                                      day_of_week VARCHAR(50) CHECK (day_of_week IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')),
                                      start_time TIME WITHOUT TIME ZONE,
                                      end_time TIME WITHOUT TIME ZONE,
                                      FOREIGN KEY (profile_id) REFERENCES profiles(profile_id)
);

-- Weekly Availability Table for Teachers
CREATE TABLE IF NOT EXISTS weekly_availability (
                                     weekly_id BIGSERIAL PRIMARY KEY,
                                     profile_id BIGSERIAL NOT NULL,
                                     date DATE,
                                     start_time TIME WITHOUT TIME ZONE,
                                     end_time TIME WITHOUT TIME ZONE,
                                     FOREIGN KEY (profile_id) REFERENCES profiles(profile_id)
);

-- Classes Table
CREATE TABLE IF NOT EXISTS classes (
                         class_id BIGSERIAL PRIMARY KEY,
                         teacher_profile_id BIGSERIAL NOT NULL,
                         student_profile_id BIGSERIAL NOT NULL,
                         start_time TIMESTAMP WITHOUT TIME ZONE,
                         end_time TIMESTAMP WITHOUT TIME ZONE,
                         status VARCHAR(50) CHECK (status IN ('scheduled', 'completed', 'cancelled')),
                         FOREIGN KEY (teacher_profile_id) REFERENCES profiles(profile_id),
                         FOREIGN KEY (student_profile_id) REFERENCES profiles(profile_id)
);

-- Reviews Table
CREATE TABLE IF NOT EXISTS reviews (
                         review_id BIGSERIAL PRIMARY KEY,
                         class_id BIGSERIAL NOT NULL,
                         student_profile_id BIGSERIAL NOT NULL,
                         rating INT CHECK (rating >= 1 AND rating <= 5),
                         comment TEXT,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (class_id) REFERENCES classes(class_id),
                         FOREIGN KEY (student_profile_id) REFERENCES profiles(profile_id)
);

-- Additional indices, unique constraints, and optimizations as needed

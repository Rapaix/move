CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              from_user_id BIGINT NOT NULL,
                              to_user_id BIGINT NOT NULL,
                              amount DECIMAL(10, 2) NOT NULL,
                              timestamp DATETIME NOT NULL,
                              FOREIGN KEY (from_user_id) REFERENCES users(id),
                              FOREIGN KEY (to_user_id) REFERENCES users(id)
);
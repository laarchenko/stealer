CREATE TABLE site (
                      id SERIAL PRIMARY KEY,
                      display_name VARCHAR(255) NOT NULL,
                      enabled BOOLEAN,
                      value VARCHAR(255) NOT NULL
);

CREATE TABLE item (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      site_id BIGINT REFERENCES site(id) ON DELETE CASCADE,
                      url VARCHAR(255) NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE user_item (
                           user_id BIGINT NOT NULL,
                           item_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, item_id),
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
);

CREATE TABLE item_details (
                              id SERIAL PRIMARY KEY,
                              item_id BIGINT NOT NULL,
                              price NUMERIC(10, 2) NOT NULL,
                              currency VARCHAR(64) NOT NULL,
                              size INT NOT NULL,
                              size_type VARCHAR(64) NOT NULL,
                              timestamp TIMESTAMPTZ NOT NULL,
                              FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
);

INSERT INTO users(id, name)
VALUES (1, 'SomeHappyUser');

INSERT INTO site(id, display_name, enabled, value)
VALUES (1, 'Dollskill', true, 'DOLLSKILL');

INSERT INTO item(id, name, url, site_id)
VALUES (1, 'obsidian-pocket-combat-boots', 'https://www.dollskill.com/products/obsidian-pocket-combat-boots', 1);

INSERT INTO item_details(id, item_id, price, timestamp, size, size_type, currency)
VALUES (10, 1, 100.00, '2023-09-01 20:00:00'::TIMESTAMPTZ, 10, 'UK', 'USD');

INSERT INTO user_item(user_id, item_id)
VALUES (1, 1);

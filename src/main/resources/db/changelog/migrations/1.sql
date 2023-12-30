CREATE TABLE sites (
                       id SERIAL PRIMARY KEY,
                       display_name VARCHAR(255) NOT NULL,
                       enabled BOOLEAN,
                       value VARCHAR(255) NOT NULL,
                       url VARCHAR(255) NOT NULL
);

CREATE TABLE items (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       site_id BIGINT REFERENCES sites(id) ON DELETE CASCADE,
                       url VARCHAR(255) NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE subscriptions (
                               id SERIAL PRIMARY KEY,
                               type VARCHAR(255),
                               item_id BIGINT NOT NULL REFERENCES items(id) ON DELETE CASCADE
);

CREATE TABLE user_subscription (
                                   user_id BIGINT NOT NULL,
                                   subscription_id BIGINT NOT NULL,
                                   PRIMARY KEY (user_id, subscription_id),
                                   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                   FOREIGN KEY (subscription_id) REFERENCES subscriptions(id) ON DELETE CASCADE
);

CREATE TABLE item_details (
                              id SERIAL PRIMARY KEY,
                              item_id BIGINT NOT NULL REFERENCES items(id) ON DELETE CASCADE,
                              price NUMERIC(10, 2) NOT NULL,
                              currency VARCHAR(64) NOT NULL,
                              size INT NOT NULL,
                              size_type VARCHAR(64) NOT NULL,
                              timestamp TIMESTAMPTZ NOT NULL
);

INSERT INTO sites (display_name, enabled, value, url)
VALUES ('Dollskill', true, 'DOLLSKILL', 'https://www.dollskill.com');

INSERT INTO items (name, site_id, url)
VALUES ('obsidian-pocket-combat-boots', 1, '/products/obsidian-pocket-combat-boots');

INSERT INTO users (name)
VALUES ('Sample User');

INSERT INTO subscriptions (type, item_id)
VALUES ('PRICEDROP', 1);

INSERT INTO user_subscription (user_id, subscription_id)
VALUES (1, 1);

INSERT INTO item_details (item_id, price, timestamp, size, size_type, currency)
VALUES (1, 200.00, '2023-09-01 20:00:00'::TIMESTAMPTZ, 10, 'UK', 'USD');

CREATE TABLE site (
                       id BIGINT PRIMARY KEY,
                       display_name VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       value VARCHAR(255) NOT NULL
);

CREATE TABLE item (
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    site_id BIGINT,
    url     VARCHAR(255) NOT NULL,
    CONSTRAINT FK_item_site FOREIGN KEY (site_id) REFERENCES site (id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_item (
    user_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    CONSTRAINT PK_user_item PRIMARY KEY (user_id, item_id),
    CONSTRAINT FK_user_item_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FK_user_item_item FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);

CREATE TABLE price (
                       id BIGINT PRIMARY KEY,
                       item_id BIGINT NOT NULL,
                       price NUMERIC(10, 2) NOT NULL,
                       timestamp TIMESTAMP NOT NULL,
                       CONSTRAINT FK_price_item FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);

ALTER TABLE item ADD CONSTRAINT FK_item_price FOREIGN KEY (id) REFERENCES price (id) ON DELETE CASCADE;


INSERT INTO site(id, display_name, enabled, value)
VALUES (1, 'Dollskill', true, 'DOLLSKILL')
CREATE TABLE addresses
(
    id           BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_primary   BIT(1)                NOT NULL DEFAULT 0,
    created_at   datetime              NOT NULL DEFAULT NOW(),
    updated_at   datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    user_id      BIGINT                NOT NULL,
    postal_code  VARCHAR(20)           NOT NULL,
    address_type VARCHAR(50)           NULL,
    city         VARCHAR(50)           NOT NULL,
    country      VARCHAR(50)           NOT NULL,
    state        VARCHAR(50)           NOT NULL,
    landmark     VARCHAR(100)          NULL,
    street       VARCHAR(100)          NOT NULL
);

CREATE TABLE brands
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_active     BIT(1)                NOT NULL DEFAULT 1,
    version       INT                   NULL     DEFAULT 0,
    created_at    datetime              NOT NULL DEFAULT NOW(),
    updated_at    datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    name          VARCHAR(100)          NOT NULL,
    logo_url      VARCHAR(200)          NULL,
    `description` VARCHAR(500)          NULL
);

CREATE TABLE cart_items
(
    id             BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    quantity       INT                   NOT NULL DEFAULT 1,
    total_price    DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    unit_price     DECIMAL(10, 2)        NOT NULL DEFAULT 0.00,
    cart_id        BIGINT                NOT NULL,
    created_at     datetime              NOT NULL DEFAULT NOW(),
    product_id     BIGINT                NOT NULL,
    selected_color VARCHAR(50)           NULL,
    selected_size  VARCHAR(50)           NULL
);

CREATE TABLE categories
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_active     BIT(1)                NOT NULL DEFAULT 1,
    created_at    datetime              NOT NULL DEFAULT NOW(),
    updated_at    datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    name          VARCHAR(100)          NOT NULL,
    slug          VARCHAR(100)          NOT NULL,
    image_url     VARCHAR(200)          NULL,
    `description` VARCHAR(500)          NULL
);

CREATE TABLE coupon_redemptions
(
    id              BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    discount_id     BIGINT                NOT NULL,
    order_id        BIGINT                NULL,
    redemption_date datetime              NOT NULL DEFAULT NOW(),
    user_id         BIGINT                NULL
);

CREATE TABLE discount_applicable_categories
(
    category_id BIGINT NOT NULL,
    discount_id BIGINT NOT NULL
);

CREATE TABLE discount_applicable_products
(
    discount_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL
);

CREATE TABLE discounts
(
    id                      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    applies_to_all_products BIT(1)                NOT NULL DEFAULT 0,
    current_uses            INT                   NOT NULL DEFAULT 0,
    is_active               BIT(1)                NOT NULL DEFAULT 1,
    max_uses                INT                   NOT NULL DEFAULT 1000,
    min_order_amount        INT                   NOT NULL DEFAULT 0,
    value                   DECIMAL(5, 2)         NOT NULL,
    created_at              datetime              NOT NULL DEFAULT NOW(),
    end_date                datetime              NOT NULL,
    start_date              datetime              NOT NULL,
    code                    VARCHAR(50)           NOT NULL,
#     type                    ENUM                  NOT NULL,
    name                    VARCHAR(100)          NOT NULL
);

CREATE TABLE inventory
(
    id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    available_quantity  INT                   NOT NULL DEFAULT 0,
    minimum_stock_level INT                   NOT NULL DEFAULT 5,
    reserved_quantity   INT                   NOT NULL DEFAULT 0,
    sold_quantity       INT                   NOT NULL DEFAULT 0,
    version             INT                   NULL     DEFAULT 0,
    last_stock_update   datetime              NOT NULL DEFAULT NOW(),
    product_id          BIGINT                NOT NULL
);

CREATE TABLE inventory_history
(
    id                 BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    quantity_change    INT                   NOT NULL,
    change_date        datetime              NOT NULL DEFAULT NOW(),
    changed_by_user_id BIGINT                NULL,
    product_id         BIGINT                NOT NULL,
    reason             VARCHAR(500)          NULL,
    reference_id       VARCHAR(255)          NOT NULL,
    change_type        ENUM                  NOT NULL
);

CREATE TABLE order_items
(
    id                       BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    quantity                 INT                   NOT NULL DEFAULT 1,
    total_price              DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    unit_price               DECIMAL(10, 2)        NOT NULL DEFAULT 0.00,
    order_id                 BIGINT                NOT NULL,
    product_id               BIGINT                NOT NULL,
    selected_color           VARCHAR(50)           NULL,
    selected_size            VARCHAR(50)           NULL,
    product_name_at_purchase VARCHAR(100)          NULL
);

CREATE TABLE order_status_history
(
    id                 BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    changed_by_user_id BIGINT                NULL,
    created_at         datetime              NOT NULL DEFAULT NOW(),
    order_id           BIGINT                NOT NULL,
    notes              VARCHAR(500)          NULL,
    status             ENUM                  NOT NULL
);

CREATE TABLE orders
(
    id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    shipping_amount     DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    subtotal            DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    tax_amount          DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    total               DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    version             INT                   NULL     DEFAULT 0,
    billing_address_id  BIGINT                NULL,
    created_at          datetime              NOT NULL DEFAULT NOW(),
    shipping_address_id BIGINT                NOT NULL,
    updated_at          datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    user_id             BIGINT                NOT NULL,
    order_number        VARCHAR(20)           NOT NULL,
    admin_notes         VARCHAR(500)          NULL,
    customer_notes      VARCHAR(500)          NULL,
    status              ENUM                  NOT NULL
);

CREATE TABLE payment_methods
(
    id             BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_active      BIT(1)                NOT NULL DEFAULT 1,
    is_default     BIT(1)                NOT NULL DEFAULT 0,
    created_at     datetime              NOT NULL DEFAULT NOW(),
    expires_at     datetime              NULL,
    user_id        BIGINT                NOT NULL,
    card_type      VARCHAR(20)           NULL,
    card_last_four VARCHAR(100)          NULL,
    display_name   VARCHAR(100)          NOT NULL,
    type           ENUM                  NOT NULL
);

CREATE TABLE payments
(
    id                  BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    amount              DECIMAL(12, 2)        NOT NULL,
    created_at          datetime              NOT NULL DEFAULT NOW(),
    order_id            BIGINT                NOT NULL,
    payment_date        datetime              NOT NULL DEFAULT NOW(),
    transaction_id      VARCHAR(50)           NOT NULL,
    transaction_details VARCHAR(500)          NULL,
    method              ENUM                  NOT NULL,
    status              ENUM                  NOT NULL
);

CREATE TABLE product_attributes
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    product_id BIGINT                NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    value      VARCHAR(200)          NOT NULL
);

CREATE TABLE product_images
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    display_order INT                   NOT NULL DEFAULT 0,
    product_id    BIGINT                NOT NULL,
    alt_text      VARCHAR(100)          NULL,
    image_url     VARCHAR(255)          NOT NULL
);

CREATE TABLE product_reviews
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_approved   BIT(1)                NOT NULL DEFAULT 0,
    rating        INT                   NOT NULL DEFAULT 5,
    order_item_id BIGINT                NULL,
    product_id    BIGINT                NOT NULL,
    review_date   datetime              NOT NULL DEFAULT NOW(),
    user_id       BIGINT                NOT NULL,
    review_text   VARCHAR(1000)         NULL
);

CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    price          DECIMAL(10, 2)        NOT NULL,
    stock_quantity INT                   NOT NULL DEFAULT 0,
    version        INT                   NULL     DEFAULT 0,
    brand_id       BIGINT                NULL,
    category_id    BIGINT                NULL,
    created_at     datetime              NOT NULL DEFAULT NOW(),
    updated_at     datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    name           VARCHAR(100)          NOT NULL,
    `description`  VARCHAR(1000)         NOT NULL,
    status         ENUM                  NOT NULL
);

CREATE TABLE review_helpful_votes
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    is_helpful BIT(1)                NOT NULL DEFAULT 1,
    review_id  BIGINT                NOT NULL,
    user_id    BIGINT                NOT NULL,
    voted_at   datetime              NOT NULL DEFAULT NOW()
);

CREATE TABLE shopping_carts
(
    id              BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    item_count      INT                   NOT NULL DEFAULT 0,
    shipping_amount DECIMAL(12, 2)        NULL     DEFAULT 0.00,
    subtotal        DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    tax_amount      DECIMAL(12, 2)        NULL     DEFAULT 0.00,
    total           DECIMAL(12, 2)        NOT NULL DEFAULT 0.00,
    created_at      datetime              NOT NULL DEFAULT NOW(),
    updated_at      datetime              NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    user_id         BIGINT                NOT NULL
);

CREATE TABLE user_roles
(
    user_id BIGINT                                                                           NOT NULL,
    `role`  ENUM ('CUSTOMER', 'ADMIN', 'INVENTORY_MANAGER', 'CUSTOMER_SUPPORT', 'MARKETING') NOT NULL,
    PRIMARY KEY (user_id, `role`)
);

CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT                               NOT NULL PRIMARY KEY,
    version      INT                                                 NULL     DEFAULT 0,
    created_at   datetime                                            NOT NULL DEFAULT NOW(),
    updated_at   datetime                                            NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    phone_number VARCHAR(15)                                         NULL,
    username     VARCHAR(50)                                         NOT NULL,
    email        VARCHAR(100)                                        NOT NULL,
    password     VARCHAR(255)                                        NOT NULL,
    status       ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED') NOT NULL DEFAULT 'ACTIVE'
);

-- Indexes and Constraints
ALTER TABLE users
    ADD CONSTRAINT UK6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
ALTER TABLE inventory
    ADD CONSTRAINT UKce3rbi3bfstbvvyne34c1dvyv UNIQUE (product_id);
ALTER TABLE payments
    ADD CONSTRAINT UKlryndveuwa4k5qthti0pkmtlx UNIQUE (transaction_id);
ALTER TABLE orders
    ADD CONSTRAINT UKnthkiu7pgmnqnu86i2jyoe2v7 UNIQUE (order_number);
ALTER TABLE brands
    ADD CONSTRAINT UKoce3937d2f4mpfqrycbr0l93m UNIQUE (name);
ALTER TABLE categories
    ADD CONSTRAINT UKoul14ho7bctbefv8jywp5v3i2 UNIQUE (slug);
ALTER TABLE users
    ADD CONSTRAINT UKr43af9ap4edm43mmtq01oddj6 UNIQUE (username);
ALTER TABLE shopping_carts
    ADD CONSTRAINT UKt5ao4h91q3su6hi9d2haxdr2t UNIQUE (user_id);
ALTER TABLE categories
    ADD CONSTRAINT UKt8o6pivur7nn124jehx7cygw5 UNIQUE (name);

-- Foreign Keys
ALTER TABLE addresses
    ADD CONSTRAINT FK1fa36y2oqhao3wgg2rw1pi459 FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE cart_items
    ADD CONSTRAINT FK1re40cjegsfvw58xrkdp6bac6 FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE orders
    ADD CONSTRAINT FK32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE product_reviews
    ADD CONSTRAINT FK35kxxqe2g9r4mww80w9e3tnw9 FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE shopping_carts
    ADD CONSTRAINT FK3iw2988ea60alsp0gnvvyt744 FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE inventory_history
    ADD CONSTRAINT FK49wg1acruen0couirm6l5c56w FOREIGN KEY (changed_by_user_id) REFERENCES users (id);
ALTER TABLE discount_applicable_categories
    ADD CONSTRAINT FK4t0up68o43jr2mlocf316mk10 FOREIGN KEY (discount_id) REFERENCES discounts (id);
ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FK55hw3269sovxs2ftufcmflgtd FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE product_reviews
    ADD CONSTRAINT FK58i39bhws2hss3tbcvdmrm60f FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE orders
    ADD CONSTRAINT FK66jolu65brloux12yi37qy3ky FOREIGN KEY (billing_address_id) REFERENCES addresses (id);
ALTER TABLE payments
    ADD CONSTRAINT FK81gagumt0r8y3rmudcgpbk42l FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE discount_applicable_categories
    ADD CONSTRAINT FK98dwdvngr2518vwpu0kckwj0c FOREIGN KEY (category_id) REFERENCES categories (id);
ALTER TABLE discount_applicable_products
    ADD CONSTRAINT FK9p6gg1dudx3c6rtkvda059m96 FOREIGN KEY (discount_id) REFERENCES discounts (id);
ALTER TABLE products
    ADD CONSTRAINT FKa3a4mpsfdf4d2y6r8ra3sc8mv FOREIGN KEY (brand_id) REFERENCES brands (id);
ALTER TABLE product_reviews
    ADD CONSTRAINT FKau5g3dylb9eh7ua5xjjw6uopw FOREIGN KEY (order_item_id) REFERENCES order_items (id);
ALTER TABLE order_items
    ADD CONSTRAINT FKbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_status_history
    ADD CONSTRAINT FKbnuj0gvhjwxodmmu7gj3iivse FOREIGN KEY (changed_by_user_id) REFERENCES users (id);
ALTER TABLE product_attributes
    ADD CONSTRAINT FKcex46yvx4g18b2pn09p79h1mc FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE review_helpful_votes
    ADD CONSTRAINT FKgx6qwn5ifdyoofofjhe19q29b FOREIGN KEY (review_id) REFERENCES product_reviews (id);
ALTER TABLE user_roles
    ADD CONSTRAINT FKhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FKhh2nwtfw87c8wa5fewr8t0dlb FOREIGN KEY (discount_id) REFERENCES discounts (id);
ALTER TABLE payment_methods
    ADD CONSTRAINT FKin7rtmim3ljrrhh5kxbq27s2v FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE review_helpful_votes
    ADD CONSTRAINT FKjfav1re52dbap61hf2c0ne81r FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE orders
    ADD CONSTRAINT FKmk6q95x8ffidq82wlqjaq7sqc FOREIGN KEY (shipping_address_id) REFERENCES addresses (id);
ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FKn5pam1bg5g5ye2xfvntv57j0b FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_status_history
    ADD CONSTRAINT FKnmcbg3mmbt8wfva97ra40nmp3 FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_items
    ADD CONSTRAINT FKocimc7dtr037rh4ls4l95nlfi FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE products
    ADD CONSTRAINT FKog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES categories (id);
ALTER TABLE cart_items
    ADD CONSTRAINT FKojy3ibx281qswho045bw4q0da FOREIGN KEY (cart_id) REFERENCES shopping_carts (id);
ALTER TABLE inventory
    ADD CONSTRAINT FKq2yge7ebtfuvwufr6lwfwqy9l FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE product_images
    ADD CONSTRAINT FKqnq71xsohugpqwf3c9gxmsuy FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE discount_applicable_products
    ADD CONSTRAINT FKrdhca881db4r56hmfvv5fvcuy FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE inventory_history
    ADD CONSTRAINT FKsiuv2mjpopsrdaje80c48mern FOREIGN KEY (product_id) REFERENCES products (id);

-- Indexes
CREATE INDEX FK1fa36y2oqhao3wgg2rw1pi459 ON addresses (user_id);
CREATE INDEX FK1re40cjegsfvw58xrkdp6bac6 ON cart_items (product_id);
CREATE INDEX FK32ql8ubntj5uh44ph9659tiih ON orders (user_id);
CREATE INDEX FK35kxxqe2g9r4mww80w9e3tnw9 ON product_reviews (product_id);
CREATE INDEX FK49wg1acruen0couirm6l5c56w ON inventory_history (changed_by_user_id);
CREATE INDEX FK4t0up68o43jr2mlocf316mk10 ON discount_applicable_categories (discount_id);
CREATE INDEX FK55hw3269sovxs2ftufcmflgtd ON coupon_redemptions (user_id);
CREATE INDEX FK58i39bhws2hss3tbcvdmrm60f ON product_reviews (user_id);
CREATE INDEX FK66jolu65brloux12yi37qy3ky ON orders (billing_address_id);
CREATE INDEX FK81gagumt0r8y3rmudcgpbk42l ON payments (order_id);
CREATE INDEX FK98dwdvngr2518vwpu0kckwj0c ON discount_applicable_categories (category_id);
CREATE INDEX FK9p6gg1dudx3c6rtkvda059m96 ON discount_applicable_products (discount_id);
CREATE INDEX FKa3a4mpsfdf4d2y6r8ra3sc8mv ON products (brand_id);
CREATE INDEX FKau5g3dylb9eh7ua5xjjw6uopw ON product_reviews (order_item_id);
CREATE INDEX FKbioxgbv59vetrxe0ejfubep1w ON order_items (order_id);
CREATE INDEX FKbnuj0gvhjwxodmmu7gj3iivse ON order_status_history (changed_by_user_id);
CREATE INDEX FKcex46yvx4g18b2pn09p79h1mc ON product_attributes (product_id);
CREATE INDEX FKgx6qwn5ifdyoofofjhe19q29b ON review_helpful_votes (review_id);
CREATE INDEX FKhh2nwtfw87c8wa5fewr8t0dlb ON coupon_redemptions (discount_id);
CREATE INDEX FKin7rtmim3ljrrhh5kxbq27s2v ON payment_methods (user_id);
CREATE INDEX FKjfav1re52dbap61hf2c0ne81r ON review_helpful_votes (user_id);
CREATE INDEX FKmk6q95x8ffidq82wlqjaq7sqc ON orders (shipping_address_id);
CREATE INDEX FKn5pam1bg5g5ye2xfvntv57j0b ON coupon_redemptions (order_id);
CREATE INDEX FKnmcbg3mmbt8wfva97ra40nmp3 ON order_status_history (order_id);
CREATE INDEX FKocimc7dtr037rh4ls4l95nlfi ON order_items (product_id);
CREATE INDEX FKog2rp4qthbtt2lfyhfo32lsw9 ON products (category_id);
CREATE INDEX FKojy3ibx281qswho045bw4q0da ON cart_items (cart_id);
CREATE INDEX FKqnq71xsohugpqwf3c9gxmsuy ON product_images (product_id);
CREATE INDEX FKrdhca881db4r56hmfvv5fvcuy ON discount_applicable_products (product_id);
CREATE INDEX FKsiuv2mjpopsrdaje80c48mern ON inventory_history (product_id);
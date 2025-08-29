CREATE TABLE addresses
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NOT NULL,
    version      INT                   NOT NULL,
    street       VARCHAR(100)          NOT NULL,
    city         VARCHAR(50)           NOT NULL,
    state        VARCHAR(50)           NOT NULL,
    postal_code  VARCHAR(20)           NOT NULL,
    country      VARCHAR(50)           NOT NULL,
    landmark     VARCHAR(100)          NULL,
    is_primary   BIT(1)                NOT NULL,
    user_id      BIGINT                NOT NULL,
    address_type VARCHAR(50)           NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

CREATE TABLE brands
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NOT NULL,
    version       INT                   NOT NULL,
    name          VARCHAR(100)          NOT NULL,
    `description` VARCHAR(500)          NULL,
    logo_url      VARCHAR(200)          NULL,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

CREATE TABLE cart_items
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    version        INT                   NOT NULL,
    cart_id        BIGINT                NOT NULL,
    product_id     BIGINT                NOT NULL,
    quantity       INT                   NOT NULL,
    unit_price     DECIMAL(10, 2)        NOT NULL,
    total_price    DECIMAL(12, 2)        NOT NULL,
    selected_color VARCHAR(50)           NULL,
    selected_size  VARCHAR(50)           NULL,
    CONSTRAINT pk_cart_items PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NOT NULL,
    version       INT                   NOT NULL,
    name          VARCHAR(100)          NOT NULL,
    `description` VARCHAR(500)          NULL,
    image_url     VARCHAR(200)          NULL,
    is_active     BIT(1)                NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE coupon_redemptions
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NOT NULL,
    version         INT                   NOT NULL,
    discount_id     BIGINT                NOT NULL,
    order_id        BIGINT                NULL,
    user_id         BIGINT                NULL,
    redemption_date datetime              NOT NULL,
    CONSTRAINT pk_coupon_redemptions PRIMARY KEY (id)
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
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    created_at              datetime              NOT NULL,
    updated_at              datetime              NOT NULL,
    version                 INT                   NOT NULL,
    name                    VARCHAR(100)          NOT NULL,
    code                    VARCHAR(50)           NOT NULL,
    type                    VARCHAR(20)           NOT NULL,
    value                   DECIMAL(5, 2)         NOT NULL,
    start_date              datetime              NOT NULL,
    end_date                datetime              NOT NULL,
    max_uses                INT                   NOT NULL,
    current_uses            INT                   NOT NULL,
    is_active               BIT(1)                NOT NULL,
    applies_to_all_products BIT(1)                NOT NULL,
    min_order_amount        INT                   NOT NULL,
    CONSTRAINT pk_discounts PRIMARY KEY (id)
);

CREATE TABLE inventory
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NOT NULL,
    version             INT                   NOT NULL,
    product_id          BIGINT                NOT NULL,
    available_quantity  INT                   NOT NULL,
    reserved_quantity   INT                   NOT NULL,
    sold_quantity       INT                   NOT NULL,
    minimum_stock_level INT                   NOT NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);

CREATE TABLE inventory_history
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NOT NULL,
    version         INT                   NOT NULL,
    product_id      BIGINT                NOT NULL,
    quantity_change INT                   NOT NULL,
    change_type     VARCHAR(20)           NOT NULL,
    reason          VARCHAR(500)          NULL,
    reference_id    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_inventory_history PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    created_at               datetime              NOT NULL,
    updated_at               datetime              NOT NULL,
    version                  INT                   NOT NULL,
    order_id                 BIGINT                NOT NULL,
    product_id               BIGINT                NOT NULL,
    quantity                 INT                   NOT NULL,
    unit_price               DECIMAL(10, 2)        NOT NULL,
    total_price              DECIMAL(12, 2)        NOT NULL,
    selected_color           VARCHAR(50)           NULL,
    selected_size            VARCHAR(50)           NULL,
    product_name_at_purchase VARCHAR(100)          NULL,
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NOT NULL,
    version             INT                   NOT NULL,
    order_number        VARCHAR(20)           NOT NULL,
    user_id             BIGINT                NOT NULL,
    shipping_address_id BIGINT                NOT NULL,
    billing_address_id  BIGINT                NULL,
    subtotal            DECIMAL(12, 2)        NOT NULL,
    tax_amount          DECIMAL(12, 2)        NOT NULL,
    shipping_amount     DECIMAL(12, 2)        NOT NULL,
    total               DECIMAL(12, 2)        NOT NULL,
    status              VARCHAR(20)           NOT NULL,
    customer_notes      VARCHAR(500)          NULL,
    admin_notes         VARCHAR(500)          NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE payment_methods
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    version        INT                   NOT NULL,
    user_id        BIGINT                NOT NULL,
    type           VARCHAR(20)           NOT NULL,
    display_name   VARCHAR(100)          NOT NULL,
    card_last_four VARCHAR(100)          NULL,
    card_type      VARCHAR(20)           NULL,
    is_default     BIT(1)                NOT NULL,
    expires_at     datetime              NULL,
    is_active      BIT(1)                NOT NULL,
    CONSTRAINT pk_payment_methods PRIMARY KEY (id)
);

CREATE TABLE payments
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NOT NULL,
    version             INT                   NOT NULL,
    order_id            BIGINT                NOT NULL,
    amount              DECIMAL(12, 2)        NOT NULL,
    method              VARCHAR(20)           NOT NULL,
    status              VARCHAR(20)           NOT NULL,
    transaction_id      VARCHAR(50)           NOT NULL,
    transaction_details VARCHAR(500)          NULL,
    payment_date        datetime              NOT NULL,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);

CREATE TABLE product_attributes
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    version    INT                   NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    value      VARCHAR(200)          NOT NULL,
    product_id BIGINT                NOT NULL,
    CONSTRAINT pk_product_attributes PRIMARY KEY (id)
);

CREATE TABLE product_images
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    version    INT                   NOT NULL,
    image_url  VARCHAR(255)          NOT NULL,
    alt_text   VARCHAR(100)          NULL,
    product_id BIGINT                NOT NULL,
    CONSTRAINT pk_product_images PRIMARY KEY (id)
);

CREATE TABLE product_reviews
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    version    INT                   NOT NULL,
    comment    VARCHAR(1000)         NOT NULL,
    rating     INT                   NOT NULL,
    product_id BIGINT                NOT NULL,
    user_id    BIGINT                NOT NULL,
    CONSTRAINT pk_product_reviews PRIMARY KEY (id)
);

CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    version        INT                   NOT NULL,
    name           VARCHAR(100)          NOT NULL,
    `description`  VARCHAR(1000)         NOT NULL,
    price          DECIMAL(10, 2)        NOT NULL,
    stock_quantity INT                   NOT NULL,
    category_id    BIGINT                NULL,
    brand_id       BIGINT                NULL,
    status         VARCHAR(20)           NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE shopping_carts
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NOT NULL,
    version         INT                   NOT NULL,
    user_id         BIGINT                NOT NULL,
    subtotal        DECIMAL(12, 2)        NOT NULL,
    tax_amount      DECIMAL(12, 2)        NULL,
    shipping_amount DECIMAL(12, 2)        NULL,
    total           DECIMAL(12, 2)        NOT NULL,
    item_count      INT                   NOT NULL,
    CONSTRAINT pk_shopping_carts PRIMARY KEY (id)
);

CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NOT NULL,
    version      INT                   NOT NULL,
    username     VARCHAR(50)           NOT NULL,
    password     VARCHAR(255)          NOT NULL,
    email        VARCHAR(100)          NOT NULL,
    phone_number VARCHAR(10)           NULL,
    status       VARCHAR(20)           NOT NULL,
    roles        VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE brands
    ADD CONSTRAINT uc_brands_name UNIQUE (name);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

ALTER TABLE inventory
    ADD CONSTRAINT uc_inventory_product UNIQUE (product_id);

ALTER TABLE orders
    ADD CONSTRAINT uc_orders_ordernumber UNIQUE (order_number);

ALTER TABLE payments
    ADD CONSTRAINT uc_payments_transactionid UNIQUE (transaction_id);

ALTER TABLE shopping_carts
    ADD CONSTRAINT uc_shopping_carts_user UNIQUE (user_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE addresses
    ADD CONSTRAINT FK_ADDRESSES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE cart_items
    ADD CONSTRAINT FK_CART_ITEMS_ON_CART FOREIGN KEY (cart_id) REFERENCES shopping_carts (id);

ALTER TABLE cart_items
    ADD CONSTRAINT FK_CART_ITEMS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FK_COUPON_REDEMPTIONS_ON_DISCOUNT FOREIGN KEY (discount_id) REFERENCES discounts (id);

ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FK_COUPON_REDEMPTIONS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE coupon_redemptions
    ADD CONSTRAINT FK_COUPON_REDEMPTIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE inventory_history
    ADD CONSTRAINT FK_INVENTORY_HISTORY_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE inventory
    ADD CONSTRAINT FK_INVENTORY_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_BILLING_ADDRESS FOREIGN KEY (billing_address_id) REFERENCES addresses (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_SHIPPING_ADDRESS FOREIGN KEY (shipping_address_id) REFERENCES addresses (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE payments
    ADD CONSTRAINT FK_PAYMENTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE payment_methods
    ADD CONSTRAINT FK_PAYMENT_METHODS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE product_attributes
    ADD CONSTRAINT FK_PRODUCT_ATTRIBUTES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_images
    ADD CONSTRAINT FK_PRODUCT_IMAGES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_reviews
    ADD CONSTRAINT FK_PRODUCT_REVIEWS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_reviews
    ADD CONSTRAINT FK_PRODUCT_REVIEWS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE shopping_carts
    ADD CONSTRAINT FK_SHOPPING_CARTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE discount_applicable_categories
    ADD CONSTRAINT fk_disappcat_on_category_entity FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE discount_applicable_categories
    ADD CONSTRAINT fk_disappcat_on_discount_entity FOREIGN KEY (discount_id) REFERENCES discounts (id);

ALTER TABLE discount_applicable_products
    ADD CONSTRAINT fk_disapppro_on_discount_entity FOREIGN KEY (discount_id) REFERENCES discounts (id);

ALTER TABLE discount_applicable_products
    ADD CONSTRAINT fk_disapppro_on_product_entity FOREIGN KEY (product_id) REFERENCES products (id);
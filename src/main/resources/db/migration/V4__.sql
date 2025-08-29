ALTER TABLE inventory_history
    DROP FOREIGN KEY FK49wg1acruen0couirm6l5c56w;

ALTER TABLE discount_applicable_categories
    DROP FOREIGN KEY FK4t0up68o43jr2mlocf316mk10;

ALTER TABLE orders
    DROP FOREIGN KEY FK66jolu65brloux12yi37qy3ky;

ALTER TABLE discount_applicable_categories
    DROP FOREIGN KEY FK98dwdvngr2518vwpu0kckwj0c;

ALTER TABLE discount_applicable_products
    DROP FOREIGN KEY FK9p6gg1dudx3c6rtkvda059m96;

ALTER TABLE product_reviews
    DROP FOREIGN KEY FKau5g3dylb9eh7ua5xjjw6uopw;

ALTER TABLE order_status_history
    DROP FOREIGN KEY FKbnuj0gvhjwxodmmu7gj3iivse;

ALTER TABLE product_attributes
    DROP FOREIGN KEY FKcex46yvx4g18b2pn09p79h1mc;

ALTER TABLE review_helpful_votes
    DROP FOREIGN KEY FKgx6qwn5ifdyoofofjhe19q29b;

ALTER TABLE user_roles
    DROP FOREIGN KEY FKhfh9dx7w3ubf1co1vdev94g3f;

ALTER TABLE review_helpful_votes
    DROP FOREIGN KEY FKjfav1re52dbap61hf2c0ne81r;

ALTER TABLE orders
    DROP FOREIGN KEY FKmk6q95x8ffidq82wlqjaq7sqc;

ALTER TABLE order_status_history
    DROP FOREIGN KEY FKnmcbg3mmbt8wfva97ra40nmp3;

ALTER TABLE discount_applicable_products
    DROP FOREIGN KEY FKrdhca881db4r56hmfvv5fvcuy;

CREATE TABLE attributes
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    version    INT                   NOT NULL,
    is_deleted BIT(1)                NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    value      VARCHAR(200)          NOT NULL,
    CONSTRAINT pk_attributes PRIMARY KEY (id)
);

CREATE TABLE card_payment_details
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NOT NULL,
    updated_at        datetime              NOT NULL,
    version           INT                   NOT NULL,
    is_deleted        BIT(1)                NOT NULL,
    payment_method_id BIGINT                NOT NULL,
    card_last_four    VARCHAR(4)            NULL,
    card_type         VARCHAR(20)           NULL,
    expires_at        datetime              NULL,
    CONSTRAINT pk_card_payment_details PRIMARY KEY (id)
);

CREATE TABLE discount_rules
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NOT NULL,
    version     INT                   NOT NULL,
    is_deleted  BIT(1)                NOT NULL,
    discount_id BIGINT                NOT NULL,
    rule_type   VARCHAR(20)           NOT NULL,
    product_id  BIGINT                NULL,
    category_id BIGINT                NULL,
    brand_id    BIGINT                NULL,
    CONSTRAINT pk_discount_rules PRIMARY KEY (id)
);

CREATE TABLE order_addresses
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NOT NULL,
    version     INT                   NOT NULL,
    is_deleted  BIT(1)                NOT NULL,
    street      VARCHAR(100)          NOT NULL,
    city        VARCHAR(50)           NOT NULL,
    state       VARCHAR(50)           NOT NULL,
    postal_code VARCHAR(20)           NOT NULL,
    country     VARCHAR(50)           NOT NULL,
    landmark    VARCHAR(100)          NULL,
    CONSTRAINT pk_order_addresses PRIMARY KEY (id)
);

CREATE TABLE product_variants
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    version        INT                   NOT NULL,
    is_deleted     BIT(1)                NOT NULL,
    product_id     BIGINT                NOT NULL,
    sku            VARCHAR(255)          NULL,
    price          DECIMAL(10, 2)        NOT NULL,
    stock_quantity INT                   NOT NULL,
    CONSTRAINT pk_product_variants PRIMARY KEY (id)
);

CREATE TABLE variant_attributes
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NOT NULL,
    version      INT                   NOT NULL,
    is_deleted   BIT(1)                NOT NULL,
    variant_id   BIGINT                NOT NULL,
    attribute_id BIGINT                NOT NULL,
    CONSTRAINT pk_variant_attributes PRIMARY KEY (id)
);

ALTER TABLE product_reviews
    ADD comment VARCHAR(1000) NULL;

ALTER TABLE product_reviews
    ADD created_at datetime NULL;

ALTER TABLE product_reviews
    ADD is_deleted BIT(1) NULL;

ALTER TABLE product_reviews
    ADD updated_at datetime NULL;

ALTER TABLE product_reviews
    ADD version INT NULL;

ALTER TABLE product_reviews
    MODIFY comment VARCHAR(1000) NOT NULL;

ALTER TABLE coupon_redemptions
    ADD created_at datetime NULL;

ALTER TABLE coupon_redemptions
    ADD is_deleted BIT(1) NULL;

ALTER TABLE coupon_redemptions
    ADD updated_at datetime NULL;

ALTER TABLE coupon_redemptions
    ADD version INT NULL;

ALTER TABLE coupon_redemptions
    MODIFY created_at datetime NOT NULL;

ALTER TABLE inventory
    ADD created_at datetime NULL;

ALTER TABLE inventory
    ADD is_deleted BIT(1) NULL;

ALTER TABLE inventory
    ADD updated_at datetime NULL;

ALTER TABLE inventory
    MODIFY created_at datetime NOT NULL;

ALTER TABLE inventory_history
    ADD created_at datetime NULL;

ALTER TABLE inventory_history
    ADD is_deleted BIT(1) NULL;

ALTER TABLE inventory_history
    ADD updated_at datetime NULL;

ALTER TABLE inventory_history
    ADD version INT NULL;

ALTER TABLE inventory_history
    MODIFY created_at datetime NOT NULL;

ALTER TABLE order_items
    ADD created_at datetime NULL;

ALTER TABLE order_items
    ADD is_deleted BIT(1) NULL;

ALTER TABLE order_items
    ADD updated_at datetime NULL;

ALTER TABLE order_items
    ADD version INT NULL;

ALTER TABLE order_items
    MODIFY created_at datetime NOT NULL;

ALTER TABLE product_images
    ADD created_at datetime NULL;

ALTER TABLE product_images
    ADD is_deleted BIT(1) NULL;

ALTER TABLE product_images
    ADD updated_at datetime NULL;

ALTER TABLE product_images
    ADD version INT NULL;

ALTER TABLE product_images
    MODIFY created_at datetime NOT NULL;

ALTER TABLE product_reviews
    MODIFY created_at datetime NOT NULL;

ALTER TABLE addresses
    ADD is_deleted BIT(1) NULL;

ALTER TABLE addresses
    ADD version INT NULL;

ALTER TABLE addresses
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE brands
    ADD is_deleted BIT(1) NULL;

ALTER TABLE brands
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE cart_items
    ADD is_deleted BIT(1) NULL;

ALTER TABLE cart_items
    ADD updated_at datetime NULL;

ALTER TABLE cart_items
    ADD version INT NULL;

ALTER TABLE cart_items
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE categories
    ADD is_deleted BIT(1) NULL;

ALTER TABLE categories
    ADD version INT NULL;

ALTER TABLE categories
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE coupon_redemptions
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE discounts
    ADD is_deleted BIT(1) NULL;

ALTER TABLE discounts
    ADD updated_at datetime NULL;

ALTER TABLE discounts
    ADD version INT NULL;

ALTER TABLE discounts
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE inventory
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE inventory_history
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE order_items
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE orders
    ADD is_deleted BIT(1) NULL;

ALTER TABLE orders
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE payment_methods
    ADD is_deleted BIT(1) NULL;

ALTER TABLE payment_methods
    ADD updated_at datetime NULL;

ALTER TABLE payment_methods
    ADD version INT NULL;

ALTER TABLE payment_methods
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE payments
    ADD is_deleted BIT(1) NULL;

ALTER TABLE payments
    ADD updated_at datetime NULL;

ALTER TABLE payments
    ADD version INT NULL;

ALTER TABLE payments
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE product_images
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE product_reviews
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE products
    ADD is_deleted BIT(1) NULL;

ALTER TABLE products
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE shopping_carts
    ADD is_deleted BIT(1) NULL;

ALTER TABLE shopping_carts
    ADD version INT NULL;

ALTER TABLE shopping_carts
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE users
    ADD is_deleted BIT(1) NULL;

ALTER TABLE users
    ADD roles VARCHAR(255) NULL;

ALTER TABLE users
    MODIFY is_deleted BIT(1) NOT NULL;

ALTER TABLE users
    MODIFY roles VARCHAR(255) NOT NULL;

ALTER TABLE cart_items
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE coupon_redemptions
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE discounts
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE inventory
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE inventory_history
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE order_items
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE payment_methods
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE payments
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE product_images
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE product_reviews
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE addresses
    MODIFY version INT NOT NULL;

ALTER TABLE cart_items
    MODIFY version INT NOT NULL;

ALTER TABLE categories
    MODIFY version INT NOT NULL;

ALTER TABLE coupon_redemptions
    MODIFY version INT NOT NULL;

ALTER TABLE discounts
    MODIFY version INT NOT NULL;

ALTER TABLE inventory_history
    MODIFY version INT NOT NULL;

ALTER TABLE order_items
    MODIFY version INT NOT NULL;

ALTER TABLE payment_methods
    MODIFY version INT NOT NULL;

ALTER TABLE payments
    MODIFY version INT NOT NULL;

ALTER TABLE product_images
    MODIFY version INT NOT NULL;

ALTER TABLE product_reviews
    MODIFY version INT NOT NULL;

ALTER TABLE shopping_carts
    MODIFY version INT NOT NULL;

ALTER TABLE card_payment_details
    ADD CONSTRAINT uc_card_payment_details_payment_method UNIQUE (payment_method_id);

ALTER TABLE product_variants
    ADD CONSTRAINT uc_product_variants_sku UNIQUE (sku);

ALTER TABLE card_payment_details
    ADD CONSTRAINT FK_CARD_PAYMENT_DETAILS_ON_PAYMENT_METHOD FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id);

ALTER TABLE discount_rules
    ADD CONSTRAINT FK_DISCOUNT_RULES_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE discount_rules
    ADD CONSTRAINT FK_DISCOUNT_RULES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE discount_rules
    ADD CONSTRAINT FK_DISCOUNT_RULES_ON_DISCOUNT FOREIGN KEY (discount_id) REFERENCES discounts (id);

ALTER TABLE discount_rules
    ADD CONSTRAINT FK_DISCOUNT_RULES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_BILLING_ADDRESS FOREIGN KEY (billing_address_id) REFERENCES order_addresses (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_SHIPPING_ADDRESS FOREIGN KEY (shipping_address_id) REFERENCES order_addresses (id);

ALTER TABLE product_variants
    ADD CONSTRAINT FK_PRODUCT_VARIANTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE variant_attributes
    ADD CONSTRAINT FK_VARIANT_ATTRIBUTES_ON_ATTRIBUTE FOREIGN KEY (attribute_id) REFERENCES attributes (id);

ALTER TABLE variant_attributes
    ADD CONSTRAINT FK_VARIANT_ATTRIBUTES_ON_VARIANT FOREIGN KEY (variant_id) REFERENCES product_variants (id);

DROP TABLE discount_applicable_categories;

DROP TABLE discount_applicable_products;

DROP TABLE order_status_history;

DROP TABLE product_attributes;

DROP TABLE review_helpful_votes;

DROP TABLE user_roles;

ALTER TABLE orders
    DROP COLUMN admin_notes;

ALTER TABLE orders
    DROP COLUMN customer_notes;

ALTER TABLE orders
    DROP COLUMN status;

ALTER TABLE discounts
    DROP COLUMN applies_to_all_products;

ALTER TABLE discounts
    DROP COLUMN type;

ALTER TABLE payment_methods
    DROP COLUMN card_last_four;

ALTER TABLE payment_methods
    DROP COLUMN card_type;

ALTER TABLE payment_methods
    DROP COLUMN expires_at;

ALTER TABLE payment_methods
    DROP COLUMN type;

ALTER TABLE inventory_history
    DROP COLUMN change_date;

ALTER TABLE inventory_history
    DROP COLUMN changed_by_user_id;

ALTER TABLE inventory_history
    DROP COLUMN change_type;

ALTER TABLE product_images
    DROP COLUMN display_order;

ALTER TABLE brands
    DROP COLUMN is_active;

ALTER TABLE product_reviews
    DROP COLUMN is_approved;

ALTER TABLE product_reviews
    DROP COLUMN order_item_id;

ALTER TABLE product_reviews
    DROP COLUMN review_date;

ALTER TABLE product_reviews
    DROP COLUMN review_text;

ALTER TABLE inventory
    DROP COLUMN last_stock_update;

ALTER TABLE products
    DROP COLUMN price;

ALTER TABLE products
    DROP COLUMN stock_quantity;

ALTER TABLE products
    DROP COLUMN status;

ALTER TABLE categories
    DROP COLUMN slug;

ALTER TABLE inventory_history
    ADD change_type VARCHAR(20) NOT NULL;

ALTER TABLE addresses
    MODIFY created_at datetime NOT NULL;

ALTER TABLE brands
    MODIFY created_at datetime NOT NULL;

ALTER TABLE cart_items
    MODIFY created_at datetime NOT NULL;

ALTER TABLE categories
    MODIFY created_at datetime NOT NULL;

ALTER TABLE orders
    MODIFY created_at datetime NOT NULL;

ALTER TABLE payments
    MODIFY created_at datetime NOT NULL;

ALTER TABLE products
    MODIFY created_at datetime NOT NULL;

ALTER TABLE shopping_carts
    MODIFY created_at datetime NOT NULL;

ALTER TABLE users
    MODIFY created_at datetime NOT NULL;

ALTER TABLE payments
    DROP COLUMN method;

ALTER TABLE payments
    DROP COLUMN status;

ALTER TABLE payments
    ADD method VARCHAR(20) NOT NULL;

ALTER TABLE users
    MODIFY phone_number VARCHAR(10);

ALTER TABLE orders
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE payments
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE products
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE users
    DROP COLUMN status;

ALTER TABLE users
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE discounts
    ADD type VARCHAR(20) NOT NULL;

ALTER TABLE payment_methods
    ADD type VARCHAR(20) NOT NULL;

ALTER TABLE addresses
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE brands
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE categories
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE orders
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE products
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE shopping_carts
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE users
    MODIFY updated_at datetime NOT NULL;

ALTER TABLE brands
    MODIFY version INT NOT NULL;

ALTER TABLE inventory
    MODIFY version INT NOT NULL;

ALTER TABLE orders
    MODIFY version INT NOT NULL;

ALTER TABLE products
    MODIFY version INT NOT NULL;

ALTER TABLE users
    MODIFY version INT NOT NULL;
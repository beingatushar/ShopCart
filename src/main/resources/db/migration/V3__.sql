ALTER TABLE inventory_history
    DROP FOREIGN KEY FK49wg1acruen0couirm6l5c56w;

ALTER TABLE product_reviews
    DROP FOREIGN KEY FKau5g3dylb9eh7ua5xjjw6uopw;

ALTER TABLE order_status_history
    DROP FOREIGN KEY FKbnuj0gvhjwxodmmu7gj3iivse;

ALTER TABLE review_helpful_votes
    DROP FOREIGN KEY FKgx6qwn5ifdyoofofjhe19q29b;

ALTER TABLE user_roles
    DROP FOREIGN KEY FKhfh9dx7w3ubf1co1vdev94g3f;

ALTER TABLE review_helpful_votes
    DROP FOREIGN KEY FKjfav1re52dbap61hf2c0ne81r;

ALTER TABLE order_status_history
    DROP FOREIGN KEY FKnmcbg3mmbt8wfva97ra40nmp3;

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

ALTER TABLE product_attributes
    ADD created_at datetime NULL;

ALTER TABLE product_attributes
    ADD is_deleted BIT(1) NULL;

ALTER TABLE product_attributes
    ADD updated_at datetime NULL;

ALTER TABLE product_attributes
    ADD version INT NULL;

ALTER TABLE product_attributes
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

ALTER TABLE product_attributes
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

ALTER TABLE product_attributes
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

ALTER TABLE product_attributes
    MODIFY version INT NOT NULL;

ALTER TABLE product_images
    MODIFY version INT NOT NULL;

ALTER TABLE product_reviews
    MODIFY version INT NOT NULL;

ALTER TABLE shopping_carts
    MODIFY version INT NOT NULL;

DROP TABLE order_status_history;

DROP TABLE review_helpful_votes;

DROP TABLE user_roles;

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
    DROP COLUMN status;

ALTER TABLE orders
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE payments
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE products
    DROP COLUMN status;

ALTER TABLE products
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE users
    DROP COLUMN status;

ALTER TABLE users
    ADD status VARCHAR(20) NOT NULL;

ALTER TABLE discounts
    DROP COLUMN type;

ALTER TABLE discounts
    ADD type VARCHAR(20) NOT NULL;

ALTER TABLE payment_methods
    DROP COLUMN type;

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
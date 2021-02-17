create schema ecommercetest;
create table ecommercetest.users (
        id identity PRIMARY KEY,
		role VARCHAR(10) NOT NULL,
        account_non_expired boolean default false,
        account_non_locked boolean default false,
        credentials_non_expired boolean default false,
        email VARCHAR(255) unique,
        enabled boolean default false,
        password VARCHAR(255),
        created_on TIMESTAMP,
		modified_on TIMESTAMP,
		modified_by VARCHAR(255),
        CONSTRAINT UK_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email)
);
create table ecommercetest.brand (
        id identity PRIMARY KEY,
        name VARCHAR(255) not null,
        created_on TIMESTAMP,        
		modified_on TIMESTAMP,
		modified_by VARCHAR(255)
);
create table ecommercetest.product (
        id identity PRIMARY KEY,
        name VARCHAR(255) not null,
        created_on TIMESTAMP,
		modified_on TIMESTAMP,
		modified_by VARCHAR(255)
);
create table ecommercetest.price_product (
        id identity PRIMARY KEY,
        brand_id BIGINT not null,
        product_id BIGINT not null,
        start_date TIMESTAMP not null,
        end_date TIMESTAMP not null,
        priority INT not null,
        price NUMERIC(10,2) not null,
        currency VARCHAR(10) not null,
        created_on TIMESTAMP,
		modified_on TIMESTAMP,
		modified_by VARCHAR(255),
		foreign key(brand_id) references ecommercetest.brand(id),
		foreign key(product_id) references ecommercetest.product(id)
);

insert into ecommercetest.brand (id, name) values (1, 'ZARA');
insert into ecommercetest.product (id, name) values (1, 'Product 1');
insert into ecommercetest.product (id, name) values (2, 'Product 2');
insert into ecommercetest.product (id, name) values (3, 'Product 3');
insert into ecommercetest.product (id, name) values (4, 'Product 4');
insert into ecommercetest.product (id, name) values (35455, 'Product 4');
insert into ecommercetest.price_product (brand_id, start_date, end_date, product_id, priority, price, currency) values (1,'2020-06-14 00.00.00','2020-12-31 23.59.59',35455, 0, 35.50, 'EUR');
insert into ecommercetest.price_product (brand_id, start_date, end_date, product_id, priority, price, currency) values (1,'2020-06-14 15.00.00','2020-06-14 18.30.00',35455, 1, 25.45, 'EUR');
insert into ecommercetest.price_product (brand_id, start_date, end_date, product_id, priority, price, currency) values (1,'2020-06-15 00.00.00','2020-06-15 11.00.00',35455, 1, 30.50, 'EUR');
insert into ecommercetest.price_product (brand_id, start_date, end_date, product_id, priority, price, currency) values (1,'2020-06-15 16.00.00','2020-12-31 23.59.59',35455, 1, 38.95, 'EUR');
insert into ecommercetest.users (id, account_non_expired,account_non_locked,credentials_non_expired,email,enabled,password,role) values (1, true, true, true, 'user@test.com', true, '$2y$12$ZPOWTbogCzaVEcoRjlgoyevF/K7Rp5YogwpCZ63c/vubxHDKiLanS', 'USER');
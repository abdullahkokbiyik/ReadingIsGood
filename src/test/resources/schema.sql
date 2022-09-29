create table if not exists author
(
    id          serial
        primary key,
    author_name varchar(200) not null,
    surname     varchar(200) not null,
    nationality varchar(50)  not null,
    uid         varchar(38)  not null
);
create unique index if not exists ui_author_uid
    on author (uid);

create table if not exists book
(
    id           serial
        primary key,
    book_name    varchar(128)     not null,
    stock_amount bigint           not null,
    cost         double precision not null,
    author_id    bigint           not null
        constraint fk_book_author_id
            references author,
    uid          varchar(38)      not null
);
create unique index if not exists ui_book_uid
    on book (uid);

create table if not exists customer
(
    id            serial
        primary key,
    customer_name varchar(128) not null,
    email         varchar(200) not null
);
create unique index if not exists ui_customer_email
    on customer (email);

create table if not exists customer_order
(
    id           serial
        primary key,
    order_date   timestamp        not null,
    book_id      bigint           not null
        constraint fk_order_book_id
            references book,
    customer_id  bigint           not null
        constraint fk_order_customer_id
            references customer,
    num_of_books bigint           not null,
    order_cost   double precision not null
);
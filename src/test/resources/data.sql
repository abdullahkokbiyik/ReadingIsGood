INSERT INTO public.author (id, author_name, surname, nationality, uid) VALUES (10000, 'Test', 'Author', 'Turkey', 'testuidauthor');
INSERT INTO public.author (id, author_name, surname, nationality, uid) VALUES (10001, 'Test2', 'Author2', 'England', 'testuidauthor2');

INSERT INTO public.book (id, book_name, stock_amount, cost, author_id, uid) VALUES (10000, 'Test Book', 15, 12.5, 10000, 'testuidbook');
INSERT INTO public.book (id, book_name, stock_amount, cost, author_id, uid) VALUES (10001, 'Test Book 2', 20, 13.5, 10000, 'testuidbook2');
INSERT INTO public.book (id, book_name, stock_amount, cost, author_id, uid) VALUES (10002, 'Test Book 3', 17, 10.5, 10001, 'testuidbook3');
INSERT INTO public.book (id, book_name, stock_amount, cost, author_id, uid) VALUES (10003, 'Test Book 4', 10, 15, 10001, 'testuidbook4');

INSERT INTO public.customer (id, customer_name, email) VALUES (10000, 'Test Customer', 'testcustomer@gmail.com');
INSERT INTO public.customer (id, customer_name, email) VALUES (10001, 'Test Customer 2', 'testcustomer2@gmail.com');

INSERT INTO public.customer_order (id, order_date, book_id, customer_id, num_of_books, order_cost) VALUES (10000, '2022-09-28', 10000, 10000, 2, 25);
INSERT INTO public.customer_order (id, order_date, book_id, customer_id, num_of_books, order_cost) VALUES (10001, '2022-09-28', 10001, 10000, 3, 40.5);
INSERT INTO public.customer_order (id, order_date, book_id, customer_id, num_of_books, order_cost) VALUES (10002, '2022-09-28', 10002, 10001, 4, 42);
INSERT INTO public.customer_order (id, order_date, book_id, customer_id, num_of_books, order_cost) VALUES (10003, '2022-09-28', 10003, 10001, 5, 75);
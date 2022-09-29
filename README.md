
# Reading is Good



In this case work, a small scale book delivery system implemented. User can add books, customers, authors inside books and place orders; query book details, order details, customer details, order details and monthly order details of particular customer. Example requests exist under request folder. JSON object inside request folder should be imported on Postman to get ready to use.

Used technologies in this project are:

- PostgreSQL
- Java Spring Boot
- Docker
- Maven
- H2 In Memory Database
- Swagger
- Liquibase
- Redis
- Git

# Endpoints

## Book

In book endpoint, user can do:
- Adding book with author
- Updating stock amount of book
- Getting details of the particular book by id and unique index separately

There are unique index properties in book and author. If another book is trying to add to the database with existing unique index, application returns error message and does not add books. If author inside of a book with existing unique index is trying to add to the database, application gets existing author and sets it on the book and continues operation. In other words, another book of the author added to database.

There are rules in updating stock amount of book also. Update amount should be valid, greater than zero. Else, application returns an error message and does not update stock amount.

There are two ways to get details of the book. Since id value is unknown when added immediately, using unique index would be more useful. If id value is known, id value can be used to get details of the book.

## Customer

In customer endpoint, user can do:
- Adding customer.
- Getting orders of particular customer with pagination.
- Getting customer details by email.

Email property of customer is unique. If a customer is trying to add with an email address that already exists in database, application returns an error message and does not persists customer object to database.

User can get orders of particular customer. But, two additional parameter must be sent except of customer id. These are page number and page size parameters. Page number has minimum value 0. If less value passed, application returns an error message. Page size has minimum value 1. If less value passed, application returns an error message. These parameters provides pagination. For example, if page size is given 2 and page number given 1; it means result is second page and in page there are 2 records.

User can get customer details with unique email address. If user need to know customer id or just want to know about user, this endpoint can be useful.

## Order

In order endpoint, user can do:
- Adding orders.
- Getting order details.
- Getting orders by date interval with pagination.

User can add orders with 2 foreign keys, book id and customer id. So that, querying book with unique index and customer with email can be useful. If order with book id that does not exist in database, application returns an error message. Same way, if order with customer id that does not exist in database, application returns an error message. Both ways, order object does not persist.

User can query orders with id and get all details of orders. 

User can get orders by date interval. User must pass 2 date parameters without time, start date and end date respectively. Besides these, page size and page number parameters must be passed which are mentioned before.

## Stats

In stats endpoint, user can do:
- Getting monthly stats of customers.

In this endpoint, user can query monthly stats of customer. With customer id, application gets the orders of customer and returns cumulative stats of the orders of customer.

## Running

Example requests are available under requests folder. They must be imported on Postman. Simply, File -> Import and after dragging request to opening window, request would be imported and ready to use.
Application is running on default port:
```sh
http://localhost:8080
```

## Docker

Reading is Good case work includes Dockerfile.
```sh
docker-compose up -d --build
```

Script builds the project. After building, 

```sh
docker-compose up
```

with command above, application stands up with all logs. If logs would like to be detached:

```sh
docker-compose up -d 
```

script should be run. 

# Database

In database topic, test and production databases are seperated. Test database is H2 in memory database. So it prevented to mixed with production database. 

# Testing

In directory that pom.xml exists, below command should be executed for testing:

```sh
mvn test
```

# Liquibase

To clean up the database, run 
```sh
mvn liquibase:dropAll liquibase:update
```
command.

# Swagger

For documentation of the application, you can check the link below:

```sh
http://localhost:8080/readingisgood/swagger-ui.html#/
```
Note that application must be up and running to see this documentation page.


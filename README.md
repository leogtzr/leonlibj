# leonlib
These are the books I own in my personal library

![shelf](./src/main/resources/static/assets/images/leonlib-shelfs.jpg)

The objective of this simple application is to expose a way to explore the inventory of books I own.
I confess that I have bought the same book multiple times due to not knowing that I already have it.


## Technical Details

### Stack

- Java
- Bootstrap
- H2
- Docker
- Shell (Bash)


## How to run it

To run the application locally (when a _redis server_ is running, :6379), use the `run_app.dev.sh` script.
To run the application fully on Docker, use the `run_app.sh` script.

## How it looks

### Home Page

![home page](./src/main/resources/static/images/howitlooks/index.png)

### Searching a book

![search](./src/main/resources/static/images/howitlooks/search.png)

### Books per author

![books per author](./src/main/resources/static/images/howitlooks/books_per_author.png)

### Book Information

![books per author](./src/main/resources/static/images/howitlooks/book_info.png)

### All the books

![all books](./src/main/resources/static/images/howitlooks/allbooks.png)

### Add book

![all books](./src/main/resources/static/images/howitlooks/add_book.png)

### Authentication

There is a simple Auth mechanism used to like any book in my library.

![auth](./src/main/resources/static/images/howitlooks/auth.png)


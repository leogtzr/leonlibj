CREATE TABLE IF NOT EXISTS books (
	id INTEGER PRIMARY KEY,
	title VARCHAR(500) NOT NULL,
	author VARCHAR(500) NOT NULL,
	description VARCHAR(500),
	read BOOLEAN DEFAULT FALSE,
	added_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	goodreads_link VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS book_images (
	image_id INTEGER PRIMARY KEY,
	book_id INTEGER NOT NULL REFERENCES books(id),
	name varchar(250) NOT NULL,
	added_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
	user_id VARCHAR(500) PRIMARY KEY,
	email VARCHAR(500) NOT NULL UNIQUE,
	name VARCHAR(500),
	oauth_identifier VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_likes (
	id INTEGER PRIMARY KEY,
	book_id INTEGER REFERENCES books(id),
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	user_id VARCHAR(500) REFERENCES users(user_id),
	UNIQUE(book_id, user_id)
);

CREATE INDEX IF NOT EXISTS idx_books_title ON books (title);
CREATE INDEX IF NOT EXISTS idx_books_author ON books (author);
CREATE INDEX IF NOT EXISTS idx_books_added_on ON books (added_on);
CREATE INDEX IF NOT EXISTS idx_book_images_book_id ON book_images (book_id);

DROP SCHEMA IF EXISTS product_service CASCADE;
CREATE SCHEMA product_service;

CREATE TYPE product_service.product_type AS ENUM ('PHYSICAL', 'DIGITAL');

CREATE TYPE product_service.item_status AS ENUM 
    ('AVAILABLE', 'RESERVED', 'LOANED', 'STORAGE', 'LOST');

CREATE TYPE product_service.item_condition AS ENUM 
    ('NEW', 'GOOD', 'USED', 'DAMAGED');

CREATE TYPE product_service.location_type AS ENUM 
    ('ZONE', 'AISLE', 'SHELF', 'RACK', 'WAREHOUSE');

CREATE TYPE product_service.file_format AS ENUM 
    ('PDF', 'EPUB', 'MOBI');


CREATE TABLE product_service.authors (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(180) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE product_service.categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    parent_id BIGINT,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE product_service.locations (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(150),
    location_type product_service.location_type NOT NULL,
    parent_id BIGINT,
    description TEXT,
    created_at TIMESTAMP DEFAULT now(),
    CONSTRAINT locations_code_key UNIQUE (code)
);

CREATE TABLE product_service.products (
    id BIGSERIAL PRIMARY KEY,
    isbn VARCHAR(20),
    title VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
    description TEXT,
    language_code VARCHAR(10),
    publication_date DATE,
    product_type product_service.product_type NOT NULL DEFAULT 'PHYSICAL',
    publisher VARCHAR(180),
    edition VARCHAR(80),
    total_pages INTEGER,
    category_id BIGINT,
    active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    CONSTRAINT products_isbn_key UNIQUE (isbn)
);

CREATE TABLE product_service.product_items (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    barcode VARCHAR(100) NOT NULL,
    rfid_code VARCHAR(100),
    status product_service.item_status DEFAULT 'AVAILABLE',
    condition_state product_service.item_condition DEFAULT 'GOOD',
    location_id BIGINT,
    acquisition_date DATE DEFAULT CURRENT_DATE,
    last_revision_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT now(),
    CONSTRAINT product_items_barcode_key UNIQUE (barcode),
    CONSTRAINT product_items_rfid_code_key UNIQUE (rfid_code)
);

CREATE TABLE product_service.product_digital_files (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    file_url TEXT NOT NULL,
    file_format product_service.file_format NOT NULL,
    file_size_bytes BIGINT,
    checksum VARCHAR(255),
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE product_service.product_authors (
    product_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, author_id)
);


ALTER TABLE product_service.product_authors
    ADD FOREIGN KEY (author_id) REFERENCES product_service.authors(id) ON DELETE CASCADE;

ALTER TABLE product_service.product_authors
    ADD FOREIGN KEY (product_id) REFERENCES product_service.products(id) ON DELETE CASCADE;

ALTER TABLE product_service.products
    ADD FOREIGN KEY (category_id) REFERENCES product_service.categories(id) ON DELETE SET NULL;

ALTER TABLE product_service.categories
    ADD FOREIGN KEY (parent_id) REFERENCES product_service.categories(id) ON DELETE SET NULL;

ALTER TABLE product_service.product_items
    ADD FOREIGN KEY (location_id) REFERENCES product_service.locations(id) ON DELETE SET NULL;

ALTER TABLE product_service.product_items
    ADD FOREIGN KEY (product_id) REFERENCES product_service.products(id) ON DELETE CASCADE;

ALTER TABLE product_service.product_digital_files
    ADD FOREIGN KEY (product_id) REFERENCES product_service.products(id) ON DELETE CASCADE;


CREATE INDEX idx_products_category ON product_service.products(category_id);
CREATE INDEX idx_items_location ON product_service.product_items(location_id);
CREATE INDEX idx_items_product ON product_service.product_items(product_id);
CREATE INDEX idx_locations_parent ON product_service.locations(parent_id);

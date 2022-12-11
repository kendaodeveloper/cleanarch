
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE product
(
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  "description" VARCHAR NOT NULL,
  category VARCHAR,
  price MONEY NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE INDEX index_product_description ON product ("description");

CREATE TABLE customer
(
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  "name" VARCHAR NOT NULL,
  email VARCHAR NOT NULL UNIQUE,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  birth_date DATE NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE INDEX index_customer_name ON customer ("name");

CREATE TABLE customer_address (
  customer_id UUID NOT NULL,
  address_number INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  PRIMARY KEY (customer_id, address_number),
  FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE customer_favorite_product
(
  customer_id UUID NOT NULL,
  product_id UUID NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP,
  PRIMARY KEY (customer_id, product_id),
  FOREIGN KEY (customer_id) REFERENCES customer (id),
  FOREIGN KEY (product_id) REFERENCES product (id)
);

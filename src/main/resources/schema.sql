CREATE TABLE CAR (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  model VARCHAR NOT NULL,
  production_year INTEGER NOT NULL,
  color VARCHAR NOT NULL,
  capacity INTEGER NOT NULL,
  horse_power INTEGER NOT NULL,
  is_rented BOOLEAN NOT NULL,
  rent_by VARCHAR NULL
);



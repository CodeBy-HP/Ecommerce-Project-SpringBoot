-- Note: Spring maps camelCase Java variables (productAvailable) to snake_case SQL columns (product_available)
INSERT INTO product (name, description, brand, price, category, release_date, product_available, quantity)
VALUES ('Tata Nexon', 'Compact SUV', 'Tata', 800000.00, 'Car', '2023-01-01', true, 50);

INSERT INTO product (name, description, brand, price, category, release_date, product_available, quantity)
VALUES ('Maruti Swift', 'Hatchback', 'Maruti', 600000.00, 'Car', '2023-05-15', true, 120);
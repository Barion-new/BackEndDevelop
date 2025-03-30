DELETE FROM employee WHERE store_id = 1;
DELETE FROM category WHERE store_id = 1;
DELETE FROM store WHERE store_id = 1;

INSERT INTO store (store_id, store_name, store_type, password, onboarding_status)
VALUES (1, '가게', '업종', 'qwer1234', true);

-- 1. 자식 테이블들부터 삭제
DELETE FROM orders WHERE store_id = 0;
DELETE FROM employee WHERE store_id = 0;
DELETE FROM category WHERE store_id = 0;

-- 2. 부모 테이블 삭제
DELETE FROM store WHERE store_id = 0;

-- 3. 새로운 store 데이터 삽입
INSERT INTO store (store_id, store_name, store_type, password, onboarding_status)
VALUES (0, '가게', '업종', 'qwer1234', true);

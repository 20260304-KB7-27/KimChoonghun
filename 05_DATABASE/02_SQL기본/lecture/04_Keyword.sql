-- DISTINCT
-- 중복 제거

SELECT
    distinct userID
FROM
    buytbl;

-- LIMIT
-- select 문의 결과 집합에서 반환할 행의 갯수를 제한할때 사용
-- 페이징할 때도 사용 가능 (offset)
-- limit [offset], [rowcount] : offset 행부터 rowcount만큼 가져옴
-- limit [rowcount] : rowcount 만큼 가져옴

SELECT
    *
FROM
    buytbl
ORDER BY
    price
LIMIT 5;

-- offset
SELECT * FROM buytbl order by price limit 3,5;
-- SELECT
-- 특정 테이블에서 원하는 데이터를 조회

use sqldb;

select -- 조회해줘
    userID  -- userID 컬럼을
from buytbl; -- buytbl 테이블에서

-- 단순한 텍스트 출력

SELECT(5+5);
select now(); -- 데이터베이스 (MYSQL) 내장함수
select concat('bear', '안녕', 'mysql') as 'Full Name'; -- 문자열 합치기

select
    name,
    concat(mobile1, ' ', mobile2) as 전화번호
from usertbl;
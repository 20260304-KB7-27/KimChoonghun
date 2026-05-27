-- SELF JOIN
-- 하나의 테이블을 서로 다른 별칭을 붙여 두 번 참조하는 JOIN
-- 같은 테이블안에서 행끼리 비교하거나 관계를 찾을때 사용

use employees;

-- 같은 생년월일인 직원들 조회
select
    concat(e1.first_name, ' ', e1.last_name) as 직원A이름,
    e1.birth_date                           as 생년월일,
    concat(e2.first_name, ' ', e2.last_name) as 직원B이름
from employees e1
join employees e2 on e1.birth_date = e2.birth_date
and e1.emp_no < e2.emp_no -- 중복, 자기쌍 제거
limit 20;
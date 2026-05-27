use employees;

select *
from employees
where emp_no in (1,2,3,4,5,6);

-- 현재 d005 부서에 재직중인 직원들의 상세 정보
-- in 안에 서브쿼리 작성
select
    e.emp_no,
    e.first_name,
    e.last_name,
    e.gender
from employees e
where emp_no in (
    select emp_no
    from dept_emp
    where dept_no = 'd005'
    and to_date = '9999-01-01'
    )
limit 5;

-- NOT IN - 서브쿼리로 없는 것 찾기
-- 관리자 였던 적이 없는 직원의 수

SELECT count(*)
FROM employees e
WHERE e.emp_no not in (
    SELECT emp_no
    from dept_manager # 관리자를 했던 직원들의 emp_no
    );

-- NOT IN의 NULL 함정
-- != AND 비교로 동작하는데 NULL과 비교하게 되면 TRUE/FALSE가 아닌 UNKNOWN이 나온다.
-- 모든 결과가 전부 제외되는 문제 발생
use sqldb;

-- 우리가 기대하는거
-- 010, 016, 011이 아닌 나머지
select *
from usertbl
where mobile1 not in (
    select mobile1
    from usertbl
    where addr='서울' -- null 값으로 인해 아무것도 나오지 않음(공집합)
    );

-- not in 오류 해결 쿼리
select *
from usertbl
where mobile1 not in (
    select mobile1
    from usertbl
    where addr='서울' and mobile1 is not null -- null 제외
);

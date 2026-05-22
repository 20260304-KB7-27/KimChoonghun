use employees;

-- ORDER BY
-- 특정 컬럼이나 값에 따라 정렬하는데 사용
-- 주의! 데이터가 많을때 정렬하면 매우 오래 걸릴 수 있다.

select
    emp_no,
    first_name,
    last_name
from employees
order by
    emp_no desc
;

-- 컬럼에 따라
select
    emp_no,
    first_name,
    last_name,
    hire_date
from employees
order by
    last_name,
    first_name
    desc
;
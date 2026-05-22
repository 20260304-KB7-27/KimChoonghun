-- SUB QUERY
-- 다른 쿼리에서 실행되는 쿼리 (보조역할)
-- 메인쿼리 실행중에 서브 쿼리를 실행해서 결과 값을 메인쿼리에 전달하는 방식

# 규칙
-- 서브쿼리는 소괄호로 묶여야한다.
-- 서브쿼리는 연산자의 오른쪽에 있어야 한다.
-- 서브쿼리는 ordery by 지원 안됨

use employees;

-- employees db에서 각 부서별 관리자를 출력
-- 조건, 현재 재직자만 (관리자의 코드는 dept_no = d005, 재직중인건 9999)
with current_manager as (SELECT emp_no
from dept_manager
where to_date = '9999-01-01'
  and dept_no = 'd005')

select * from employees
where emp_no = (SELECT emp_no from current_manager)
/*
 - UNION / UNION ALL
 - 두개 이상의 SELECT 결과를 세로 방향으로 합치는 연산

 UNION
 - 두 SELECT 결과를 합친 뒤 중복을 제거
 - 내부적으로 자체 정렬을 수행

 UNION ALL
 - 두 SELECT 결과를 합침(중복 제거는 X)
 - UNION 보다 따르다 (중복 제거를 안 하므로)

 규칙
 - 각 SELECT의 컬럼 수가 같아야 하며
 - 대응하는 컬럼의 데이터 타입이 호환되어야 한다.
 - 최종 컬럼명은 첫번째 SELECT의 컬럼명을 따른다.
 */

 use employees;

-- d001 부서 현재 재직자
select *
from dept_emp
where dept_no = 'd001' and to_date = '9999-01-01';

-- d001 부서 과거 포함 관리자 전원
select emp_no
from dept_manager
where dept_no = 'do001';

-- d001 부서 현재 재직자와 d001 부서 과거 포함 관리자
select emp_no,
       dept_no,
       from_date,
       to_date,
       'dept_emp' as 출처
from dept_emp
where
    dept_no = 'd001'
  and
    to_date = '9999-01-01'

union # 합치고 중복 제거

select
    emp_no,
    dept_no,
    from_date,
    to_date,
    'dept_manager' as 출처
from dept_manager
where dept_no = 'd001';

select emp_no,
       dept_no,
       from_date,
       to_date,
       'dept_emp' as 출처
from dept_emp
where
    dept_no = 'd001'
  and
    emp_no > 110000
  and
    to_date = '9999-01-01'

union all # 합치고 중복 제거

select
    emp_no,
    dept_no,
    from_date,
    to_date,
    'dept_manager' as 출처
from dept_manager
where dept_no = 'd001';
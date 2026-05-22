-- DML
-- 데이터의 값을 삽입, 수정, 삭제 하는 SQL 문

-- INSERT
Insert into
    employees
values
    (2, 20260522, '충훈', '김', 'M', 20260304);

select * from employees limit 2;

update
    employees
set
    last_name = '김',
    first_name = '충'
where
    emp_no = 2
SELECT
    emp_no,
    hire_date,
    year(hire_date),
    month(hire_date),
    day(hire_date)
    from employees
limit 10l;

/*
 DATEDIFF(date1, date2)
 - date1에서 date2를 뺀 일수 차이
 */
select
    emp_no,
    hire_date,
    DATEDIFF(curdate(), hire_date)
from employees
limit 10;

/*
 DAT    E_FORMAT(date, format)
    - %Y : 4자리년도, %m : 2자리 월, %d: 2자리 일
    - %y : 4자리년도, %m : 2자리 월, %d: 2자리 일
 */


select
    emp_no,
    hire_date,
    DATE_FORMAT(hire_date, '%Y년 %m월 %d일')
from employees
limit 10;
/*
 CASE ~ WHEN
*/
SELECT
    emp_no,
    salary,
    case
        when salary >= 100000 then 'S등급'
        when salary >= 80000 then 'A등급'
        when salary >= 50000 then 'B등급'
        ELSE 'C등급'
    end as 급여등급
from salaries
where to_date = '9999-01-01'
limit 10;

/*
    IFNULL(column, default_value)
    - column의 값이 null이면 default_value를 반호나하고, null이 아니면 원래 값을 반환
*/
use sqldb;

select
    name,
    mobile1,
    mobile2,
    IFNULL(
        CONCAT(mobile1, '-', mobile2),
        '번호 없음'
    ) as 전화번호
from usertbl;
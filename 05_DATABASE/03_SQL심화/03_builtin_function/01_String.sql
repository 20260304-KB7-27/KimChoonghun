-- 문자열 내장함수
use employees;

/*
 CONCAT(str1, str2, ...)
 - 여러 문자열을 합쳐주는 함수
*/
SELECT
    emp_no,
    concat(first_name, ' ', last_name) as full_name
    from
        employees
    limit 5;

/*
 UPPER / LOWER
*/

SELECT
    first_name,
    upper(first_name) as 대문자이름,
    lower(first_name) as 소문자이름
from employees
limit 5, 10;

/*
 - SUBSTRING(str, pos, len) : 문자열의 pos 위치부터 len 만큼의 문자 추출
 - LEFT(str, len) : 왼쪽에서부터 str의 len개 만큼 문자 추출
 - RIGHT(str, len) : 오른쪽에서부터 str의 len개 만큼 문자 추출
*/
SELECT
    first_name,
    left(first_name,3) as '앞 3글자',
    right(first_name,3) as '뒤 3글자',
    substring(first_name, 2, 4) as "2번째부터 6번째까지 글자"
from employees
limit 5,5;
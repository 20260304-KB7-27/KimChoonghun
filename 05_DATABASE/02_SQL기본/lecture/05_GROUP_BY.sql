/*
 그룹화
 -- 결과 집합을 특정 열의 값에 따라 그룹화 하는데 사용
 -- HAVING은 GROUP BY 절과 함께 사용하여 그룹에 대한 조건 적용
*/

use employees;

SELECT * FROM salaries;

-- 직원별 급여 횟수 조회
SELECT
    emp_no,
    count(*)
FROM salaries
GROUP BY emp_no;

SELECT
    emp_no,
    count(*) as 급여횟수,
    avg(salary) as 평균급여,
    sum(salary) as 총급여
FROM salaries
GROUP BY emp_no
HAVING 평균급여 >= 50000 and 평균급여 <= 60000;
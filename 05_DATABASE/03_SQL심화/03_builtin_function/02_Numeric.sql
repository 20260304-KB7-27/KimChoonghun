use employees;

/*
 ROUND(number, decimals)
 - 숫자를 지정한 소숫점 자리수로 반올림
 - decimals가 0이면 정수로 반올림
*/
-- 직원별 평균 급여 반올림해서 정수 출력
SELECT
    emp_no,
    avg(salary) as "원본 평균 급여",
    ROUND(avg(salary)) as "평균 급여 정수 반올림",
    ROUND(avg(salary),2) as "평균 급여 소수 둘째 자리까지 반올림",
    FLOOR(avg(salary)) as "평균 급여 올림",
    CEIL(avg(salary)) as " 평균 급여 내림"
from salaries
group by emp_no
limit 5;

/*
 FORMAT (number, decimals)
 - 숫자 천 단위 콤마 추가 및 소수점 자리수 지정 출력
 - 문자열으로 반환된다.
 */
 select
     emp_no,
     salaries,
     format(salary, 0) as "급여 포맷"
 from salaries
 group by emp_no
limit 5;
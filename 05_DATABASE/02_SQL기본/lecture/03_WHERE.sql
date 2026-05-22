-- ColumnA != B : 컬럼의 내용 중 B와 같지 않은 데이터만 보여주기 -> 확장문법
-- ColumnA <> B : 컬럼의 내용 중 B와 같지 않은 데이터만 보여주기 -> 표준
select
    emp_no,
    first_name,
    last_name,
    gender
from
    employees
where
#     gender != 'M';
    gender <> 'M';

SELECT *
from employees
where
    emp_no not between 10050 and 10060;

/*
 패턴
 % : 0개 이상의 문자를 나타냄
 '%apple%' -> apple 포함된 모든 문자열을 의미

 _ : 1개의 문자를 나타냄
 'a_k' 는 'a'로 시작하고 'k'로 끝나느 세 글자 문자열을 의미
 */

/*
  IN 연산자
  - 특정 열의 값이 지정된 목록 중 하나와 일치하는 데이터
*/
SELECT
    *
FROM
    employees
WHERE
    emp_no in (2, 3, 55333, 19525);

SELECT *
FROM buytbl
WHERE groupName is null;
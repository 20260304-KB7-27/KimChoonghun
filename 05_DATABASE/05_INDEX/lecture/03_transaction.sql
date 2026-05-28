/*
    트랜잭션 (Transaction)
    - 데이터베이스 작업을 하나의 논리적인 작업 단위로 묶은 것
*/
select @@autocommit; -- 현재 세션의 자동커밋 상태

set autocommit = false; -- autocommit 비활성화

-- 트랜잭션 시작
start transaction;

-- 이후에 동작하는 query 들은 하나의 작업 단위로 묶임
delete from buytbl where num=3;
delete from buytbl where num=4;

select * from buytbl; -- 현재 트랜잭션 안에는 삭제된 것처럼 보이나 실제 DB에서는 사라지지 ㅇ낳음

rollback; -- transaction 시작 이전 가장 최근 commit으로 돌아간다.

commit; -- commit을 했을 때 DB에 반영된다.

set autocommit = true; -- autocommit 활성화

select @@autocommit; -- 현재 세션의 자동커밋 상태
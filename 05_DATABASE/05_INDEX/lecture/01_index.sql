/*
 클러스터형 인덱스
 - 테이블의 기본키(PK)가 자동으로 클러스터형 인덱스가 된다.
 - 데이터가 기본키 순서대로 정렬(클러스터형 인덱스대로 정렬되어 저장)
 - 한 테이블에 클러스터형 인덱스 한 개만 존재 가능

 보조 인덱스
 - PRIMARY KEY가 아닌 모든 인덱스
 - 인덱스를 직접 생성해줄수 있음
 - UNIQUE 제약 조건을 넣으면 고유 인덱스가 생성됨
*/
DROP TABLE IF EXISTS usertbl;
create table usertbl (
  userID CHAR(8) not null primary key,
  name varchar(10) not null,
  birthYear INT NOT NULL,
  addr NCHAR(2) NOT NULL
);
SELECT * FROM usertbl; -- userID 기준으로 정렬됨

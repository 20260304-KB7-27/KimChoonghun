/* 사용자 계정 생성 + 권한 부여하기 */

-- scoula_db DB(스키마) 생성
create database scoula_db;

drop user if exists 'scoula'@'%';

-- 모든 호스트에서 접속 가능
create user 'scoula'@'%' identified by '1234';

-- scoula 계정에 scoula
grant all privileges on scoula_db.* to 'scoula'@'%';

grant all privileges on sqldb.* to 'scoula'@'%';

-- 모든 권한 부여
flush privileges;
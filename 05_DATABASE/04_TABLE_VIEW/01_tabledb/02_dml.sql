use tabledb;

insert into
    usertbl
values
    ('LSG', '이승기', 1987, '서울', '011', '11111111', 182, '2008-02-21');

insert into
    usertbl
values
    ('NBJ', '나비족', 1987, '서울', '011', '11111111', 182, '2008-02-21');

SELECT * from usertbl;

-- buytbl
insert into buytbl
values (null, 'LSG', '운동화', NULL, 30, 2);

-- 참조무결성 : 외래키가 참조하는 데이터는 반드시 실제로 존재해야 한다.
insert into buytbl
values (null, 'NBJ', '운동화', NULL, 30, 2);

delete from usertbl
where userID = 'LSG';
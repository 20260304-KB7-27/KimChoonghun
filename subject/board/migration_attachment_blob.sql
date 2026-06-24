-- tbl_board_attachment: path(파일경로) -> file(LONGBLOB) 마이그레이션
ALTER TABLE tbl_board_attachment ADD COLUMN file LONGBLOB NULL;
ALTER TABLE tbl_board_attachment DROP COLUMN path;

-- BLOB(64KB) -> LONGBLOB(4GB) 용량 확장
ALTER TABLE tbl_board_attachment MODIFY file LONGBLOB NULL;

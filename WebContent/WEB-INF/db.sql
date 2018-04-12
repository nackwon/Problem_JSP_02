select * from zipcode_tbl;
delete MEMBER_TBL where user_id = 'aaaaaaaa';
DROP TABLE zipcode2_tbl;
SELECT * FROM member_tbl;
DELETE member_tbl WHERE user_id LIKE 'admin';
delete zipcode_tbl where seq = 'SEQ';
COMMIT;
CREATE TABLE zipcode2_tbl(
	seq NUMBER(5) PRIMARY KEY,
	zipcode VARCHAR2(100) not null,
	sido VARCHAR2(100) not null,
	gugun VARCHAR2(100) not null,
	dong VARCHAR2(100) not null,
	ri VARCHAR2(100),
	bldg VARCHAR2(100),
	bunji VARCHAR2(100)
);

SELECT * FROM ZIPCODE2_TBL;
SELECT count(*) from ZIPCODE2_TBL;
//자동으로 순서대로 숫자를 붙여줌 그것을 sequence라고 함
//이 sequence는 한번 만들어 놓고 사용하면 
//sql문이 실패를 해도 계속해서 증가하게 된다
//그래서 우리가 원하는 값이 안나온다
//다시 하려고 할 때 드랍시켜 새로 만드는 것이 좋다
CREATE sequence zipcode_seq2 start WITH 1;
//드랍 시키기
DROP sequence zipcode_seq;
SELECT zipcode_seq2.nextval from dual;
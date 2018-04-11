select * from zipcode_tbl;
DROP TABLE zipcode_tbl;
delete zipcode_tbl where seq = 'SEQ';
CREATE TABLE zipcode_tbl(
	seq VARCHAR2(100) PRIMARY KEY,
	zipcode VARCHAR2(100),
	sido VARCHAR2(100),
	gugun VARCHAR2(100),
	dong VARCHAR2(100),
	ri VARCHAR2(100),
	bldg VARCHAR2(100),
	bunji VARCHAR2(100)
);

SELECT * FROM member_tbl;
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
//�ڵ����� ������� ���ڸ� �ٿ��� �װ��� sequence��� ��
//�� sequence�� �ѹ� ����� ���� ����ϸ� 
//sql���� ���и� �ص� ����ؼ� �����ϰ� �ȴ�
//�׷��� �츮�� ���ϴ� ���� �ȳ��´�
//�ٽ� �Ϸ��� �� �� ������� ���� ����� ���� ����
CREATE sequence zipcode_seq2 start WITH 1;
//��� ��Ű��
DROP sequence zipcode_seq;
SELECT zipcode_seq2.nextval from dual;
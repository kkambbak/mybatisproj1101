create table tb_board(
    seq number primary key, 
    title varchar2(200), 
    writer varchar2(200),
    wdate date, 
    hit number default 0,
    contents clob
);  -- char:고정길이(2000 byte), 성별, 우편번호, 사원번호, 학번, 주민번호 
    -- 등 고정길이 사용하는것만 쓰자 
    -- varchar2( variant character : 가변길이, 4000byte)
    -- 날짜 : date  - 날짜와 시간 
    -- clob - 2G 까지 대용량 저장가능, mysql에서는 longtext
-- DML(Data Maniplation Language) insert, update, delete 
-- 잘못 칠수도 있고 
insert into tb_board values(1, '제목1', '홍길동', sysdate, 0, '내용1');
commit;

-- sqlplus user01/1234@127.0.0.1/xe
-- conn user01/1234
-- procedure:반환값이없다(void) , function :반환값이 있으면
-- PL/SQL
/* */
create or replace procedure insert_data
is
begin 
    delete from tb_board;  -- 
    for i in 1..450 loop 
       insert into tb_board (seq, title, writer, contents, wdate)
       values(i, '제목'||i, '작성자'||i, '내용'||i, sysdate);
    end loop;
    commit;
end;
/
exec insert_data;    

select count(*)from tb_board; 

/* 오라클에서 사용하는 페이징쿼리가 
1. rownum 사용하기- 가짜필드, 데이터를 select 할때마다 번호를 하나씩 붙인다. 
2. row_number 윈도우 분석함수
*/

select * from tb_board where rownum<10;

-- 데이터를 가져와야  번호를 붙이는데 rownum>10 를 수행할 수 없다.
select * from tb_board where rownum>10 and rownum<=20;
-- 서브쿼리 
select A.rnum, A.seq, A.title, A.contents, A.writer, 
   to_char(A.wdate, 'yyyy-mm-dd') wdate, A.hit
from(
    select rownum rnum, A.seq, A.title, A.contents, A.writer, A.wdate, A.hit
    from(   
        select A.seq, A.title, A.contents, A.writer, A.wdate, A.hit 
        from tb_board A 
        order by seq desc
    )A where rownum<=30
)A
where rnum>20;

------- 오라클 10g 부터 윈도우함수(분석함수) : 어디부터 어디까지 지정이 가능
-- row_number() over( 정렬필드값들 )
-- 권장사항 ,테이블명을 직접 쓰고 * 
-- 테이블정보가 저장된 공간에서 가서 하나씩 찾아서 퍼옴 
-- aliasing 테이블에 별명을 주고 한번에 임시공간에 테이블정보가이동하고 
-- 필요한 정보를 가져온다.
select A.seq, A.title, A.writer, A.wdate, A.contents, A.hit,rnum, pg 
from 
(
    select A.seq, A.title, A.writer, A.wdate, A.contents,A.hit,
           row_number() over( order by seq desc) as rnum,
           ceil(row_number() over( order by seq desc)/10) as pg 
    from tb_board A
)A where pg=1;

-- ceil - 올림  11 -> 2  1 ->1 

drop table tb_member;

CREATE SEQUENCE seq_member
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 999999999
       NOCYCLE
       NOCACHE;

create table tb_member
	  	 (
		   	 seq_member number primary key
		   	 ,user_id varchar2(15)
		   	 ,password varchar2(15)
		   	 ,user_name varchar2(15)
		   	 ,phone varchar2(11)
		   	 ,email varchar2(20)
		   	 ,zipcode varchar2(5)
		   	 ,address1 varchar2(200)
		   	 ,address2 varchar2(200)
		   	 ,wdate date
	  	 );

select * from tb_member;

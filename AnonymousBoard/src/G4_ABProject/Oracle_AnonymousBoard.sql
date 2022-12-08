select * from tab;

create sequence boardNum_seq
INCREMENT by 1
start with 2
MINVALUE 2
MAXVALUE 9999
NOCYCLE
CACHE 30
order;

create table AnonymousBoard (
    boardNum number(8) primary key,
    title varchar2(40) not null,
    boardContents varchar2(500) not null,
    privatePW varchar2(10) not null
);

insert into AnonymousBoard values(1, '메인공지', '임시 내용', 1223334444);
insert into AnonymousBoard values(2, '두번째공지', '임시 내용ㅁㄴㅇ', 123456);
insert into ANONYMOUSBOARD values (3, '삭제된 게시물입니다.', '삭제된 게시물', 1234);
insert into ANONYMOUSBOARD values (4, '2다음 숫자는 4이다.', 'false', 1234);

select * from ANONYMOUSBOARD;

commit
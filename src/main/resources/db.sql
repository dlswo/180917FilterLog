select * from tbl_board order by BNO desc
;

insert into TBL_BOARD(BNO, TITLE, CONTENT, WRITER)
values ( SEQ_BOARD.nextval,?,?,?)
;

select *
from
    (
  select
   /*+INDEX_DESC(tbl_board pk_board) */

   rownum rn, bno, title, CONTENT, WRITER, REGDATE, UPDATEDATE, VIEWCNT
  from TBL_BOARD
  where BNO > 0
  and rownum <= (? * ?))
where rn > (?-1)*?
;
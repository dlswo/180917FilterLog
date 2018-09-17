package org.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class QueryExecutor implements Executor{

    protected Connection con = null;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;

    public void executeAll(){ // 처음,중간,끝을 실행 (밖에서는 이것만 쓰임)
                                // 처음과 끝은 항상 고정이고 중간(인터페이스)만 바뀜

        try {
            makeConnection();
            doJob();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally{
            closeAll();
        }
    }

    private void  makeConnection()throws Exception{ // 처음 - 연결되는 것을 지정한다.
        Class.forName("oracle.jdbc.OracleDriver");
        con = DriverManager.getConnection(               "jdbc:oracle:thin:@10.10.10.91:1521:XE",                    "honeyrock",                    "12345678");
    }

    private void closeAll(){ // 끝 - 연결을 끝낸다.
        System.out.println("close All...." + con);
        if(rs != null) {
            try { rs.close(); }catch(Exception e){}
        }
        if(stmt != null) {
            try { stmt.close(); }catch(Exception e){}
        }
        if(con != null) {
            try { con.close(); }catch(Exception e){}
        }
    }

}

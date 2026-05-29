package jdbc.section01;

import jdbc.common.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Application1 {
    /*
        Statement
        - JDBC에서 SQL 문을 실행하기 위한 interface
        - SQL문을 문자열 그대로
     */

    public static void main(String[] args) {
        // Connection
        Connection con = JDBCUtil.getConnection();

        // java.sql의 interface로 import
        Statement stmt = null;

        // 결과집합 (Select) 인터페이스
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            // SQL Injection
            String userInput = "' OR '1' = '1";

            String query = "Select * from usertbl where name = '" + userInput + "'";

            rset = stmt.executeQuery(query);

            while (rset.next()) {
                System.out.println(
                        rset.getString("id") + ", "
                    + rset.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

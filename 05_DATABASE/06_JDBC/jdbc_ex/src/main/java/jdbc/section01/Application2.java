package jdbc.section01;

import jdbc.common.JDBCUtil;

import java.sql.*;

public class Application2 {
    /*
        PreparedStatement
        - Statement의 SQL 실행 성능과 보안성을 향상시키기 위해 만들어짐
        - 쿼리를 미리 컴파일해두고 실행
     */

    public static void main(String[] args) {
        // Connection
        Connection con = JDBCUtil.getConnection();

        // java.sql의 interface로 import
        PreparedStatement pstmt = null;

        // 결과집합 (Select) 인터페이스
        ResultSet rset = null;

        try {
            String query = "select * from usertbl where role = ?";
            pstmt = con.prepareStatement(query);

            String userInput = "user";
            pstmt.setString(1, userInput);
            rset = pstmt.executeQuery();

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

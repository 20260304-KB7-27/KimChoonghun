package jdbc.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

// Util 인스턴스를 안 만들고 static 메서드만 따로 사용하는 패턴
public class JDBCUtil {

    // 싱글톤처럼 Connection 객체 하나로만 관리되는 Class
    static Connection conn = null;

    // static 초기화 블럭
    static {
        // Properties에 있는 DB 접속 정보 가져오기
        try {
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
            // 읽어온 정보 변수에 담기
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String password = properties.getProperty("password");

            // JDBC Driver 등록
            // Class.forName : 런타임에 문자열(경로)에 해당하는 클래스를 동적으로 로드
            Class.forName(driver);

            // DB 연결 객체 생성
            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

//    public static void main(String[] args) throws IOException {
//        Properties properties = new Properties();
//        properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
//
//        System.out.println(properties.getProperty("driver"));
//        System.out.println(properties.getProperty("url"));
//        System.out.println(properties.getProperty("id"));
//    }
}

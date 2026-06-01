package org.scoula.travel.dao;

import com.mysql.cj.protocol.Resultset;
import org.scoula.database.JDBCUtil;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelDaoImpl implements TravelDao {

    Connection conn = JDBCUtil.getConnection();

    // 여행지 추가
    @Override
    public void insert(TravelVO travel) {
        String sql = "insert into tbl_travel(no, district,title,description, address, phone) values(?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, travel.getNo());
            pstmt.setString(2, travel.getDistrict());
            pstmt.setString(3, travel.getTitle());
            pstmt.setString(4, travel.getDescription());
            pstmt.setString(5, travel.getAddress());
            pstmt.setString(6, travel.getPhone());

            int count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertImage(TravelImageVO image) {

        String sql = "insert into tbl_travel_image(filename, travel_no) values(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, image.getFilename());
            pstmt.setLong(2, image.getTravelNo());

            int count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT count(*) FROM tbl_travel";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery(); // JDBC 결과 집합 (여기서 이미 실행됨)
        ) {
            // ResultSet 생성 직후에 가리키는 행이 없다.
            rs.next();
            // 인덱스로 int값 가져오기
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getDistricts() {
        List<String> districts = new ArrayList<>(); // 결과를 담아줄 List
        String sql = "SELECT distinct district FROM tbl_travel";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery(); // JDBC 결과 집합 (여기서 이미 실행됨)
        ) {
            while (rs.next()) {
                // 조회한 행마다 매핑을 통해 VO 객체로 변환
                String district = rs.getString("district");
                districts.add(district);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return districts;
    }

    @Override
    public List<TravelVO> getTravels() {

        List<TravelVO> travels = new ArrayList<>(); // 결과를 담아줄 List
        String sql = "SELECT * FROM tbl_travel order by district, title";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery(); // JDBC 결과 집합 (여기서 이미 실행됨)
        ) {
            while (rs.next()) {
                // 조회한 행마다 매핑을 통해 VO 객체로 변환
                TravelVO travel = map(rs);
                travels.add(travel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travels;
    }

    private TravelVO map(ResultSet rs) throws SQLException {
        /*
            Builder Pattern
            - 단계적으로 값을 설정하고 객체를 생성하는 패턴
            - 필드가 많거나 선택적으로 입력해야하는 값이 많은 객체를 만들때 사용
        */
        return TravelVO.builder()
                .no(rs.getLong("no"))
                .district(rs.getString("district"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .build();
    }

    @Override
    public List<TravelVO> getTravels(int page) {
        List<TravelVO> travels = new ArrayList<>(); // 결과를 담아줄 List
        String sql = "SELECT * FROM tbl_travel order by district, title limit ?, ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            int count = 10;
            int start = (page - 1) * count;

            pstmt.setInt(1, start);
            pstmt.setInt(2, count);

            ResultSet rs = pstmt.executeQuery(); // JDBC 결과 집합

            while (rs.next()) {
                // 조회한 행마다 매핑을 통해 VO 객체로 변환
                TravelVO travel = map(rs);
                travels.add(travel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travels;
    }

    @Override
    public List<TravelVO> getTravels(String district){

        List<TravelVO> travels = new ArrayList<>(); // 결과를 담아줄 List
        String sql = "SELECT * FROM tbl_travel WHERE district = ? order by district, title";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, district);
            ResultSet rs = pstmt.executeQuery(); // JDBC 결과 집합 (여기서 이미 실행됨)
            while (rs.next()) {
                // 조회한 행마다 매핑을 통해 VO 객체로 변환
                TravelVO travel = map(rs);
                travels.add(travel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travels;
    }

    @Override
    public Optional<TravelVO> getTravel(Long no) {
        TravelVO travel = null;

        String sql = """
                    SELECT t.*, ti.no as tino, ti.filename, ti.travel_no
                    FROM tbl_travel t
                    LEFT JOIN tbl_travel_image ti
                    ON t.no = ti.travel_no
                    WHERE t.no = ?;
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, no);

            try (ResultSet rs = pstmt.executeQuery();) { // JDBC 결과 집합
                // 조회한 데이터가 없으면 Optional.empty(); 반환
                // 있으면 Optional로 VO를 감싸서 반환
                if (rs.next()) {
                    travel = map(rs);

                    // 이미지 목록
                    List<TravelImageVO> images = new ArrayList<>();
                    do {
                        TravelImageVO image = TravelImageVO.builder()
                                .no(rs.getLong("tino"))
                                .filename(rs.getString("filename"))
                                .travelNo(rs.getLong("travel_no"))
                                .build();

                        images.add(image);
                    } while (rs.next());

                    travel.setImages(images);

                    return Optional.of(travel);

                } else {
                    return Optional.empty();
                }
            } catch (SQLException e) {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

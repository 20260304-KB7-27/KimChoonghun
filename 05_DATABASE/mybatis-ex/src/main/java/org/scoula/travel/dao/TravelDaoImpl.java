package org.scoula.travel.dao;

import org.apache.ibatis.session.SqlSession;
import org.scoula.travel.database.MyBatisConfig;
import org.scoula.travel.domain.TravelVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
    SqlSession 메서드
    - selectOne(id) : 결과가 정확히 1건인 select
    - selectList(id) : 결과가 0건 이상인 (응답이 List 형태로 나온다)
    - insert(id, param) : return이 영향받은 행의 수
    - update(id, param) : return이 영향받은 행의 수
    - delete(id, param) : return이 영향받은 행의 수
    Transaction
    - commit() : 변경사항을 DB에 반영
    - rollback() : 변경사항 취소
 */
public class TravelDaoImpl implements TravelDao{

    static final String NAMESPACE = "org.scoula.travel.dao.TravelDao.";

    @Override
    public int getTotalCount() {
        return MyBatisConfig.getSqlSession()
                .selectOne(NAMESPACE + "getTotalCount");
    }

    @Override
    public List<TravelVO> getTravels(int start, int end) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        param.put("start", start);
        param.put("end", end);
        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getTravels", param);
    }

    @Override
    public List<String> getDistricts() {

        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getDistricts");
    }


    @Override
    public List<TravelVO> getTravelsByDistrict(String district) {
        return MyBatisConfig.getSqlSession()
                .selectList(NAMESPACE + "getTravelsByDistrict", district);
    }

    @Override
    public Optional<TravelVO> getTravel(Long no) {
        return MyBatisConfig.getSqlSession()
                .selectOne(NAMESPACE + "getTravel", no);
    }

    @Override
    public void insert(TravelVO travel) {
        try (SqlSession session = MyBatisConfig.getSqlSession()) {
            session.insert(NAMESPACE + "insert", travel); // XML 네임스페이스 주의 (보통 .insert 형태입니다)
            session.commit(); // ✨ 가장 중요: DB에 변경사항 확정
        }
    }

    @Override
    public void update(TravelVO travel) {
        try (SqlSession session = MyBatisConfig.getSqlSession()) {
            session.update(NAMESPACE + "update", travel);
            session.commit();
        }
    }

    @Override
    public void remove(Long no) {
        try (SqlSession session = MyBatisConfig.getSqlSession()) {
            session.delete(NAMESPACE + "remove", no);
            session.commit();
        }
    }
}

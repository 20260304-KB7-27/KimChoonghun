package org.scoula.travel.service;

import lombok.RequiredArgsConstructor;
import org.scoula.travel.dao.TravelDao;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    // 객체가 생성될 때 한번만 초기화 되도록 (final)
    final TravelDao dao;

    /*
        모든 목록을 조회해서 출력(콘솔)
        - 전체 목록 보여주기
    */
    @Override
    public void printTravels() {
        List<TravelVO> travels = dao.getTravels();
        printTravels(travels);
    }

    private void printTravels(List<TravelVO> travels) {
        for (TravelVO travel : travels) {
            System.out.printf("%3d %6s %s\n", travel.getNo(), travel.getDistrict(), travel.getTitle());
        }
    }

    /*
        지역으로 여행지를 조회
        1. 지역 목록 조회
        2. 사용자가 선택한 지역 검색후 조회
    */
    @Override
    public void printTravelsByDistrict() {
        // 총 페이지 갯수
        List<String> allDistricts = dao.getDistricts();
        System.out.printf("지역 리스트\n");
        int n = allDistricts.toArray().length;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d. %s\n", i+1, allDistricts.get(i));
        }

        // 사용자한테 입력 받기
        int idx = getNumber(String.format("지역 선택: ", allDistricts));
        if (1 <= idx && idx <= n) {
            // page대로 조회
            List<TravelVO> travels = dao.getTravels(allDistricts.get(idx-1));

            // 출력
            printTravels(travels);
        } else {
            printTravelsByDistrict();
        }
    }

    /*
        목록 페이징 조회 (10개 단위)
        - 총 페이지 갯수 조회를 위해 전체 목록 갯수 조회가 필요
        - 페이징 처리된 조회 필요
     */
    @Override
    public void printTravelsByPage() {
        // 총 페이지 갯수
        int totalPage = dao.getTotalCount() / 10;
        System.out.printf("총 %d 페이지\n", totalPage);

        // 사용자한테 입력 받기
        int page = getNumber(String.format("페이지선택( 1 ~ %d )", totalPage));

        // page대로 조회
        List<TravelVO> travels = dao.getTravels(page);

        // 출력
        printTravels(travels);
    }

    // Scanner 숫자값 입력받는 메서드
    private int getNumber(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        return num;
    }

    /*
        단건 조회
        - 단건 no 기준으로 조회
        - image join 조회
    */
    @Override
    public void printTravel() {
        long no = getNumber("관광지 No: ");

        // Optional.orElseThrow() :
        // Optional 객체안에 값이 있으면 값을 반환하고 없으면 지정한 예외를 던짐
        TravelVO travel = dao.getTravel(no)
                .orElseThrow(NoSuchElementException::new);

        // 결과 출력
        System.out.println("권역: " + travel.getDistrict());
        System.out.println("제목: " + travel.getTitle());
        System.out.println("설명: " + travel.getDescription());
        System.out.println("주소: " + travel.getAddress());
        System.out.println("전화번호: " + travel.getPhone());
        System.out.println("사진들");
        for (TravelImageVO image : travel.getImages()) {
            System.out.printf(" [%3d] %s\n", image.getNo(), image.getFilename());
        }
    }
}

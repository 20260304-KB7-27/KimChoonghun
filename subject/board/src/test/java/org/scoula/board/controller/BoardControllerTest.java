package org.scoula.board.controller;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    RootConfig.class,
    ServletConfig.class
})
@Log4j2
@WebAppConfiguration // 웹 환경 테스트임을 선언, MockMvc 활성화
class BoardControllerTest {
    @Autowired
    BoardService service;

    @Autowired
    WebApplicationContext ctx; // 웹 애플리케이션 컨텍스 -> mockMVC 만들때 기반으로써 사용

    // 실제 서버를 사용하지 ㅇ낳고 컨트롤러를 호출/검증할 수 있게 해주는 가짜 MVC 객체
    private MockMvc mockMvc;

    @BeforeEach // 각 테스트 실행 전에
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void list() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) // ResultActions 리턴
                .andReturn() // 요청 처리 결과 (MvcResult)
                .getModelAndView() // 컨트롤러가 반환한 뷰 + 모델 -> ModelAndView
                .getModelMap() // 뷰에 전달되는 모델 데이터 -> Model(ModelMap)
        );
    }
}
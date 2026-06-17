package org.scoula.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 어플리케이션 전반적인 설정
    // Service, Repository, Data, 트랜잭션 웹이랑 무관한 설정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // 웹/MVC 관련 설정
    // Controller, ViewResolver 등 웹관련 설정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴, / : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // POST body 문자(한글) 인코딩 필터 설정 - UTF-8 설정
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

    @Value("${upload.location}")
    private String location;

    @Value("${upload.max-file-size}")
    private long maxFileSize;

    @Value("${upload.max-request-size}")
    private long maxRequestSize;

    @Value("${upload.file-size-threshold}")
    private int fileSizeThreshold;

    // Multipart 요청 처리를 위한 빈 등록
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}

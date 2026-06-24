package org.scoula.sample.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SampleServiceImpl implements SampleService{

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {
        log.info("Target 메서드 동작합니다!");
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}

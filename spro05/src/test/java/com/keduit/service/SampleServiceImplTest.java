package com.keduit.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceImplTest {

    @Autowired
    private SampleService service;

    @Autowired
    private SampleTxService sampleTxService;

    @Test
    public void testClass(){
        log.info(service);
        log.info(service.getClass().getName());
    }
    
    @Test
    public void testDoAdd() throws Exception {
        log.info(service.doAdd("123","400"));
    }

    @Test
    public void testException() throws Exception {
        log.info(service.doAdd("123","하윙"));
    }

    @Test
    public void testLong(){
        String str = "Lorem ipsum dolor sit amet, consectetur adipiscing "
                + "elit, sed do eiusmod tempor incididunt ut labore et "
                + "deserunt mollit anim id est laborum.";
        log.info(str.getBytes().length);
        sampleTxService.addData(str);
    }
}
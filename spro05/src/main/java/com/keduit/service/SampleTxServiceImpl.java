package com.keduit.service;

import com.keduit.mapper.Sample1Mapper;
import com.keduit.mapper.Sample2Mapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log4j
@Service
public class SampleTxServiceImpl implements SampleTxService{


    @Setter(onMethod_ = @Autowired)
    private Sample1Mapper sample1Mapper;

    @Setter(onMethod_ = @Autowired)
    private Sample2Mapper sample2Mapper;

    @Transactional
    @Override
    public void addData(String value) {
        log.info("....mapper1......");
        sample1Mapper.insertCol1(value);

        log.info("......mapper2....");
        sample2Mapper.insertCol2(value);

        log.info("......end........");
    }
}

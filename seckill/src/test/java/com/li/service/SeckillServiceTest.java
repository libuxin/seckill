package com.li.service;

import com.li.dto.Exposer;
import com.li.dto.SeckillExecution;
import com.li.entity.Seckill;
import com.li.exception.RepeatKillException;
import com.li.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception{
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void testGetById() throws Exception{
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testSeckillLogic() throws Exception{
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 1829088880L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution exception = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",exception);
            }catch (RepeatKillException e){
                logger.error(e.getMessage(),e);
            }catch (SeckillException e){
                logger.error(e.getMessage(),e);
            }
        }else {
            logger.warn("exposer={}",exposer);
        }
    }
}
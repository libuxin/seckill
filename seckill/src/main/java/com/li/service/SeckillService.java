package com.li.service;

/*
业务接口：站在 使用者 的角度设计接口
三个方面：方法定义粒度，参数，返回类型（return类型/异常）
*/

import com.li.dto.Exposer;
import com.li.dto.SeckillExecution;
import com.li.entity.Seckill;
import com.li.exception.RepeatKillException;
import com.li.exception.SeckillException;

import java.util.List;


public interface SeckillService {


    /**
     *  查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启是输出秒杀接口地址；
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀 操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillException;
}

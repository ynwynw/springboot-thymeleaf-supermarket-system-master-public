package com.yeqifu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeqifu.bus.entity.Num;
import com.yeqifu.bus.mapper.NumMapper;
import com.yeqifu.bus.service.INumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`) 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-10-10
 */
@Service
@Transactional
public class NumServiceImpl extends ServiceImpl<NumMapper, Num> implements INumService {

    @Override
    public boolean save(Num entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Num entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Num getById(Serializable id) {
        return super.getById(id);
    }
}

package com.yeqifu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.Pandian;
import com.yeqifu.bus.mapper.GoodsMapper;
import com.yeqifu.bus.mapper.PandianMapper;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.IPandianService;
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
public class PandianServiceImpl extends ServiceImpl<PandianMapper, Pandian> implements IPandianService {

    @Override
    public boolean save(Pandian entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Pandian entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Pandian getById(Serializable id) {
        return super.getById(id);
    }
}

package com.yeqifu.bus.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeqifu.bus.entity.Type;
import com.yeqifu.bus.mapper.TypeMapper;
import com.yeqifu.bus.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Override
    public boolean save(Type entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Type entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Type getById(Serializable id) {
        return super.getById(id);
    }
}

package com.yeqifu.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeqifu.sys.entity.Sal;
import com.yeqifu.sys.mapper.SalMapper;
import com.yeqifu.sys.service.ISalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-10-14
 */
@Service
@Transactional
public class SalServiceImpl extends ServiceImpl<SalMapper, Sal> implements ISalService {

}

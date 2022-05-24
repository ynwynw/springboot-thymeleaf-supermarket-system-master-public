package com.yeqifu.sys.service.impl;

import com.yeqifu.sys.entity.Notice;
import com.yeqifu.sys.mapper.NoticeMapper;
import com.yeqifu.sys.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}

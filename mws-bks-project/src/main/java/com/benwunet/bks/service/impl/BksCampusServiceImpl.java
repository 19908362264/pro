package com.benwunet.bks.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.bks.dao.BksCampusDao;
import com.benwunet.bks.model.BksCampus;
import com.benwunet.bks.service.BksCampusService;
import org.springframework.stereotype.Service;

@Service
public class BksCampusServiceImpl extends ServiceImpl<BksCampusDao, BksCampus> implements BksCampusService {
}

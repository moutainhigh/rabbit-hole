package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.com.biz.entity.BankInfo;
import in.hocg.rabbit.com.biz.mapper.BankInfoMapper;
import in.hocg.rabbit.com.biz.service.BankInfoService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [通用模块] 银行信息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-09-22
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BankInfoServiceImpl extends AbstractServiceImpl<BankInfoMapper, BankInfo> implements BankInfoService {

}

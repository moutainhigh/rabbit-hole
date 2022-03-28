package in.hocg.rabbit.mina.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.com.api.UniqueCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.mina.biz.convert.ShareContentConvert;
import in.hocg.rabbit.mina.biz.entity.ShareContent;
import in.hocg.rabbit.mina.biz.mapper.ShareContentMapper;
import in.hocg.rabbit.mina.biz.mapstruct.ShareContentMapping;
import in.hocg.rabbit.mina.biz.pojo.ro.ShareContentSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;
import in.hocg.rabbit.mina.biz.service.ShareContentService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [功能模块] 分享内容表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-03-16
 */
@Service
@UseConvert(ShareContentConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShareContentServiceImpl extends AbstractServiceImpl<ShareContentMapper, ShareContent> implements ShareContentService {
    private final UniqueCodeServiceApi codeServiceApi;
    private final ShareContentMapping mapping;

    @Override
    public ShareContentVo getByEncoding(String encoding) {
        return as(lambdaQuery().eq(ShareContent::getEncoding, encoding).one(), ShareContentVo.class);
    }

    @Override
    public String create(ShareContentSaveRo ro) {
        ShareContent entity = mapping.asShareContent(ro);
        entity.setEncoding(codeServiceApi.getUniqueCode(CodeType.ShareContent.getCodeStr()));
        baseMapper.insert(entity);
        return entity.getEncoding();
    }
}

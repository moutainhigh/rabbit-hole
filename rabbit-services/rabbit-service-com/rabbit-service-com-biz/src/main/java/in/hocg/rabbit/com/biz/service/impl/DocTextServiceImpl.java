package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.biz.convert.DocTextConvert;
import in.hocg.rabbit.com.biz.entity.DocText;
import in.hocg.rabbit.com.biz.mapper.DocTextMapper;
import in.hocg.rabbit.com.biz.pojo.vo.DocTextVo;
import in.hocg.rabbit.com.biz.service.DocTextService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.common.datadict.common.RefType;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [内容模块] 富文本内容表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocTextServiceImpl extends AbstractServiceImpl<DocTextMapper, DocText> implements DocTextService {
    private final DocTextConvert convert;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(PublishDocTextRo ro) {
        final Long refId = ro.getRefId();
        ValidUtils.notNull(refId, "上传失败，ID 错误");
        List<PublishDocTextRo.TextDto> texts = ro.getTexts();

        final RefType relType = ICode.ofThrow(ro.getRefType(), RefType.class);
        String refTypeCode = relType.getCodeStr();
        deleteByRefTypeAndRefId(refTypeCode, refId);

        if (CollUtil.isEmpty(texts)) {
            return;
        }

        TokenizerEngine engine = TokenizerUtil.createEngine();
        final List<DocText> list = texts.parallelStream()
            .map(item -> {
                String text = item.getText();
                Result result = engine.parse(text);
                return new DocText()
                    .setRefId(refId)
                    .setRefType(refTypeCode)
                    .setPriority(item.getPriority())
                    .setKeyword(IterUtil.join(result.iterator(), ";"))
                    .setText(text);
            }).collect(Collectors.toList());
        this.saveBatch(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DocTextVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId) {
        return listByRefTypeAndRefIdOrdered(refType, refId).stream()
            .map(convert::asDocTextVo)
            .collect(Collectors.toList());
    }

    private List<DocText> listByRefTypeAndRefIdOrdered(String refType, Long refId) {
        return lambdaQuery().eq(DocText::getRefType, refType).eq(DocText::getRefId, refId)
            .orderByDesc(DocText::getPriority, CommonEntity::getCreatedAt)
            .list();
    }

    private void deleteByRefTypeAndRefId(@NotNull String relType, @NotNull Long relId) {
        lambdaUpdate().eq(DocText::getRefType, relType).eq(DocText::getRefId, relId).remove();
    }
}

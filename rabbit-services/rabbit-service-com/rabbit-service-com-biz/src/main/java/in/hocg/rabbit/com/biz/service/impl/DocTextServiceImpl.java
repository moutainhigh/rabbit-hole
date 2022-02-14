package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.com.api.pojo.ro.BatchPublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.biz.convert.DocTextConvert;
import in.hocg.rabbit.com.biz.entity.DocText;
import in.hocg.rabbit.com.biz.mapper.DocTextMapper;
import in.hocg.rabbit.com.api.pojo.vo.DocTextVo;
import in.hocg.rabbit.com.biz.service.DocTextService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.common.datadict.common.RefType;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<String> tokenizer(String text) {
        TokenizerEngine engine = TokenizerUtil.createEngine();
        Result result = engine.parse(text);
        return StreamSupport.stream(result.spliterator(), false)
            .map(Word::getText)
            .map(StrUtil::trimToNull)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchPublish(BatchPublishDocTextRo ro) {
        final Long refId = ro.getRefId();
        ValidUtils.notNull(refId, "失败，ID 错误");
        List<BatchPublishDocTextRo.TextDto> texts = ro.getTexts();

        final RefType relType = ICode.ofThrow(ro.getRefType(), RefType.class);
        String refTypeCode = relType.getCodeStr();
        deleteByRefTypeAndRefId(refTypeCode, refId);

        if (CollUtil.isEmpty(texts)) {
            return;
        }

        final List<DocText> list = texts.parallelStream()
            .map(item -> new DocText()
                .setRefId(refId)
                .setRefType(refTypeCode)
                .setPriority(item.getPriority())
                .setDoctype(item.getDoctype())
                .setKeyword(IterUtil.join(tokenizer(item.getText()), ";"))
                .setText(item.getText())).collect(Collectors.toList());
        this.saveBatch(list);
    }

    @Override
    public Long publish(PublishDocTextRo ro) {
        final Long refId = ro.getRefId();
        ValidUtils.notNull(refId, "失败，ID 错误");
        final RefType relType = ICode.ofThrow(ro.getRefType(), RefType.class);
        String refTypeCode = relType.getCodeStr();
        String text = ro.getText();
        DocText entity = new DocText().setText(text)
            .setKeyword(IterUtil.join(tokenizer(text), ";"))
            .setRefType(refTypeCode)
            .setRefId(refId)
            .setDoctype(ro.getDoctype());
        this.save(entity);
        return entity.getId();
    }

    @Override
    public DocTextVo getDocTextById(Long id) {
        return convert.asDocTextVo(getById(id));
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

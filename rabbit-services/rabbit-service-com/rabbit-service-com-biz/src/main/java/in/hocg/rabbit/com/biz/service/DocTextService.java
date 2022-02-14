package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.biz.entity.DocText;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.com.api.pojo.vo.DocTextVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * [内容模块] 富文本内容表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-02-13
 */
public interface DocTextService extends AbstractService<DocText> {

    void publish(PublishDocTextRo ro);

    List<DocTextVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId);
}

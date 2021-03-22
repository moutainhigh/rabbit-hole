package com.github.lotus.com.biz.service.proxy;

import com.github.lotus.com.biz.entity.NoticeMessage;
import com.github.lotus.com.biz.mapstruct.NoticeMessageMapping;
import com.github.lotus.com.biz.pojo.vo.message.NoticeMessageComplexVo;
import com.github.lotus.com.biz.service.NoticeMessageProxyService;
import com.github.lotus.com.biz.service.NoticeMessageService;
import com.github.lotus.common.datadict.com.NoticeMessageRefType;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NoticeMessageProxyServiceImpl implements NoticeMessageProxyService {
    private final NoticeMessageService noticeMessageService;
    private final NoticeMessageMapping noticeMessageMapping;

    @Override
    public NoticeMessageComplexVo getById(Long id) {
        return this.convert(noticeMessageService.getById(id));
    }

    private NoticeMessageComplexVo convert(NoticeMessage entity) {
        Long refId = entity.getRefId();

        NoticeMessageComplexVo result = noticeMessageMapping.asComplex(entity);
        NoticeMessageComplexVo.RefObject refObject = new NoticeMessageComplexVo.RefObject();
        refObject.setId(refId);
        switch (ICode.ofThrow(entity.getRefType(), NoticeMessageRefType.class)) {
            case Comment:{
                // todo
                break;
            }
            default:
                throw new UnsupportedOperationException();
        }
        result.setRefObject(refObject);
        return result;
    }
}

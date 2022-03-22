package in.hocg.rabbit.com.biz.manager.impl;

import in.hocg.rabbit.com.biz.manager.CommentTargetProxyService;
import in.hocg.rabbit.com.biz.service.CommentTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2021/2/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentTargetProxyServiceImpl implements CommentTargetProxyService {
    private final CommentTargetService commentTargetService;


}

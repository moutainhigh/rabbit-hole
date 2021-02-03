package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.service.CommentTargetProxyService;
import com.github.lotus.com.biz.service.CommentTargetService;
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

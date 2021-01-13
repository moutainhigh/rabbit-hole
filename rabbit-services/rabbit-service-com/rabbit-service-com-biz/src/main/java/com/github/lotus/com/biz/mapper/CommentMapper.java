package com.github.lotus.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.com.biz.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 * [通用模块] 评论表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<Comment> pagingRootCommend(@Param("targetId") Long targetId,
                                     @Param("enabled") Serializable enabled,
                                     Page page);

    IPage<Comment> pagingByRegexTreePath(@Param("regexTreePath") String regexTreePath, Page page);
}

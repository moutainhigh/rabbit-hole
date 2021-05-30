package com.github.lotus.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.com.biz.entity.MessageUserRef;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageStatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [消息模块] 用户接收的消息表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Mapper
public interface MessageUserRefMapper extends BaseMapper<MessageUserRef> {

    IPage<MessageUserRef> paging(@Param("ro") MessagePagingRo ro, @Param("page") Page page);

}
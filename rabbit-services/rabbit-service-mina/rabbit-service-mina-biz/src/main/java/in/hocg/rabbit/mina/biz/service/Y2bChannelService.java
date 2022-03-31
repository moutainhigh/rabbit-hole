package in.hocg.rabbit.mina.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.mina.biz.entity.Y2bChannel;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelPageRo;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;

import java.util.List;

/**
 * <p>
 * [YouTube] YouTube频道表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-03-30
 */
public interface Y2bChannelService extends AbstractService<Y2bChannel> {

    Long rebind(String clientId, in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel youTubeChannel);

    List<Y2bChannelCompleteVo> complete(YouTubeChannelCompleteRo ro);

    IPage<Y2bChannelCompleteVo> paging(YouTubeChannelPageRo ro);

}

package in.hocg.rabbit.mina.biz.service;

import in.hocg.boot.youtube.autoconfiguration.utils.data.CredentialChannel;
import in.hocg.boot.youtube.autoconfiguration.utils.data.YouTubeChannel;
import in.hocg.rabbit.mina.biz.entity.Channel;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

/**
 * <p>
 * [YouTube] YouTube频道表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-03-30
 */
public interface ChannelService extends AbstractService<Channel> {

    Long rebind(String clientId, YouTubeChannel youTubeChannel);
}

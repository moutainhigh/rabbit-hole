package in.hocg.rabbit.mina.biz.manager;

import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelInfoVo;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ProxyManager {

    ProxyChannelInfoVo getFrpChannel(ProxyChannel channel);
}

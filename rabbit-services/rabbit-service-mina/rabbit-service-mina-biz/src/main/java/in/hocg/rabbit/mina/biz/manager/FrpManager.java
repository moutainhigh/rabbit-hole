package in.hocg.rabbit.mina.biz.manager;


import in.hocg.rabbit.mina.biz.support.channel.frp.ro.*;
import in.hocg.rabbit.mina.biz.support.channel.frp.vo.FrpResult;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface FrpManager {

    /**
     * 用户登录操作信息
     *
     * @param ro
     * @return
     */
    FrpResult<?> login(LoginFrpRo ro);

    /**
     * 创建代理
     *
     * @param ro
     * @return
     */
    FrpResult<?> newProxy(NewProxyFrpRo ro);

    /**
     * 心跳
     *
     * @param ro
     * @return
     */
    FrpResult<?> ping(PingFrpRo ro);

    /**
     * 新建工作连接
     *
     * @param ro
     * @return
     */
    FrpResult<?> newWorkConn(NewWorkConnFrpRo ro);

    /**
     * 创建用户连接
     *
     * @param content
     * @return
     */
    FrpResult<?> newUserConn(NewUserConnFrpRo content);
}

package in.hocg.rabbit.mina.biz.service;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface CheckService {

    boolean checkMessage(String appid, String text);

    boolean checkImage(String appid, String imageUrl);
}

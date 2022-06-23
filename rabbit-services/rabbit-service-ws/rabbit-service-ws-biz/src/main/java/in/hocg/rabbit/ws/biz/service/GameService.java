package in.hocg.rabbit.ws.biz.service;

import in.hocg.rabbit.ws.biz.pojo.ro.GameCmdRo;
import in.hocg.rabbit.ws.biz.pojo.ro.RoomPeerRo;
import in.hocg.rabbit.ws.biz.pojo.ro.RoomSignalRo;

/**
 * Created by hocgin on 2022/6/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface GameService {
    void handleRoomRequest(GameCmdRo ro);

    void handleRoomSignal(RoomSignalRo ro, String username);

    void handleRoomPeer(String roomId, Object ro, String username);
}

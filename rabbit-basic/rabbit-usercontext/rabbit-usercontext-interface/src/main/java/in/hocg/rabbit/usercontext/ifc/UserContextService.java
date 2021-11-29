package in.hocg.rabbit.usercontext.ifc;

import in.hocg.rabbit.usercontext.ifc.vo.UserDetail;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface UserContextService {
    UserDetail getUserDetail(String username);
}

package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.boot.utils.enums.ICode;
import in.hocg.rabbit.com.api.UniqueCodeServiceApi;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.com.biz.manager.impl.UniqueCodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UniqueCodeServiceApiImpl implements UniqueCodeServiceApi {
    private final UniqueCodeServiceImpl uniqueCodeService;

    @Override
    public String getUniqueCode(String groupCode) {
        return uniqueCodeService.getUniqueCode(ICode.ofThrow(groupCode, CodeType.class));
    }
}

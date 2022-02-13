package in.hocg.rabbit.com.biz.convert;

import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.com.biz.entity.DocText;
import in.hocg.rabbit.com.biz.pojo.vo.DocTextVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocTextConvert {

    public DocTextVo asDocTextVo(DocText entity) {
        String text = entity.getText();
        List<String> keyword = StrUtil.splitTrim(text, ";");
        return new DocTextVo()
            .setKeyword(keyword)
            .setText(text);
    }
}

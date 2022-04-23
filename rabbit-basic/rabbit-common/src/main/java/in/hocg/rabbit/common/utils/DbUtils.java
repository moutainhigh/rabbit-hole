package in.hocg.rabbit.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Created by hocgin on 2022/4/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class DbUtils {

    /**
     * 字符串转换为数组
     *
     * @param str
     * @return
     */
    public List<String> toList(String str) {
        return StrUtil.split(str, ',', true, true);
    }

    /**
     * 字符串转换为数组
     *
     * @param list
     * @return
     */
    public String toString(List<String> list) {
        return ArrayUtil.join(list.toArray(), ",");
    }
}

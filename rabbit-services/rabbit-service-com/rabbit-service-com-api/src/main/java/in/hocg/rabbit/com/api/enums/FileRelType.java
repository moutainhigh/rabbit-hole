package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.FILE_REL_TYPE, description = "文件关联类型")
public enum FileRelType implements DataDictEnum {
    Unknown("unknown", "未知"),
    GameCard("game_card", "游戏卡带"),
    Product("product", "商品"),
    ;
    private final Serializable code;
    private final String name;
}

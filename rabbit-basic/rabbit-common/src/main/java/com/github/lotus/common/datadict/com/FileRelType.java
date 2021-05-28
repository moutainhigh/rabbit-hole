package com.github.lotus.common.datadict.com;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum FileRelType implements DataDictEnum {
    Unknown("unknown", "未知"),
    GameCard("game_card", "游戏卡带");
    private final Serializable code;
    private final String name;
}

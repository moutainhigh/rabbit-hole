package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.BANK_INFO_TYPE, description = "银行类型")
public enum BankInfoType implements DataDictEnum {
    SuperPayBankCode("super_pay_bank_code", "超级网银号"),
    BankCode("bank_code", "联行号"),
    ;
    private final Serializable code;
    private final String name;
}

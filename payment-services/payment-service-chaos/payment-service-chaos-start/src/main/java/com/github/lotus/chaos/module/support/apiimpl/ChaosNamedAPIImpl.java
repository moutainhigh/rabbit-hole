package com.github.lotus.chaos.module.support.apiimpl;

import com.github.lotus.chaos.module.com.service.DataDictService;
import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.modules.support.ChaosNamedAPI;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosNamedAPIImpl implements ChaosNamedAPI {
    private final DataDictService dataDictService;
    private final AccountService accountService;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        final String type = args.getArgs()[0];
        final List<KeyValue> result = dataDictService.listDataDictItemDtoByDictIdAndCode(type, args.getValues());
        return this.toMap(result, KeyValue::getValue, KeyValue::getKey);
    }

    @Override
    public Map<String, Object> loadByUserName(NamedArgs args) {
        final List<Account> result = accountService.listAccountByAccountId(args.getValues());
        return this.toMap(result, Account::getId, Account::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        final List<Account> result = accountService.listAccountByAccountId(args.getValues());
        return this.toMap(result, Account::getId, Account::getNickname);
    }

    private <K, V, Z> Map<String, Z> toMap(List<V> values,
                                           Function<? super V, K> keyFunction,
                                           Function<? super V, Z> valueFunction) {
        return LangUtils.toMap(values, v -> String.valueOf(keyFunction.apply(v)), valueFunction);
    }
}

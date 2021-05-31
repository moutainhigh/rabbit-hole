package com.github.lotus.common.utils;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by hocgin on 2021/5/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Rules<K> {
    private final Map<K, Supplier<?>> rules = Maps.newHashMap();
    private Supplier<?> defaultRule = UnsupportedOperationException::new;

    private Rules() {
    }

    public static <K> Rules<K> create() {
        return new Rules<>();
    }

    public Rules<K> rule(K type, Supplier<?> fs) {
        rules.put(type, fs);
        return this;
    }

    public Rules<K> defRule(Supplier<?> fs) {
        defaultRule = fs;
        return this;
    }

    public <R> Optional<R> of(K args)  {
        Supplier<?> rule = rules.getOrDefault(args, defaultRule);

        Object o = rule.get();
        if (o instanceof RuntimeException) {
            throw (RuntimeException) o;
        }
        return (Optional<R>) Optional.ofNullable(o);
    }

    public static <P> Supplier<P> Runnable(Runnable fun) {
        return () -> {
            fun.run();
            return null;
        };
    }

    public static <P> Supplier<P> Supplier(Supplier<P> fun) {
        return fun;
    }
}

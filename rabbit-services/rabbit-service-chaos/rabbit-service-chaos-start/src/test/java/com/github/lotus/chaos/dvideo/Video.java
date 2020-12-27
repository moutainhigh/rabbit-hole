package com.github.lotus.chaos.dvideo;

import com.github.lotus.chaos.dvideo.decoder.DouYinVideoDecoder;
import com.github.lotus.chaos.dvideo.decoder.KuaiShouVideoDecoder;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Video {
    private static final Map<String, VideoDecoder> maps = Maps.newHashMap();

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        DuoYin(DouYinVideoDecoder.class),
        KuaiShou(KuaiShouVideoDecoder.class),
        Unknown(null);
        private final Class<? extends VideoDecoder> decoderClass;
    }

    public static Type getType(String url) {
        return Type.DuoYin;
    }

    public static String decode(String url, Type type) throws Exception {
        return getOrCreate(type.getDecoderClass()).decoding(url);
    }

    private static VideoDecoder getOrCreate(Class<? extends VideoDecoder> decoderClass) {
        String key = decoderClass.getName();
        return maps.computeIfAbsent(key, s -> {
            try {
                return decoderClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
                return null;
            }
        });
    }
}

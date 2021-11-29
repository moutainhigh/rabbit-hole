package in.hocg.rabbit.mina.gathering;

import cn.hutool.crypto.SecureUtil;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface GetFilename {

    static String getFilename(String ds, String filename) {
        String prefix = "wallpaper";
        String datasource = SecureUtil.md5(ds.replace(".", "_"));
        return prefix + "/" + datasource + "/" + filename;
    }
}

package in.hocg.rabbit.chaos.dvideo;

import cn.hutool.extra.ftp.Ftp;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;
import com.emc.ecs.nfsclient.rpc.CredentialUnix;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Created by hocgin on 2022/3/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Tests {
    @SneakyThrows
    public static void main(String[] args) {
        Nfs3 nfs = new Nfs3("39.100.87.79", "/nfs/disk", new CredentialUnix(), 3);
        List<String> list = nfs.newFile("/").list();
        System.out.println(list);
    }
}

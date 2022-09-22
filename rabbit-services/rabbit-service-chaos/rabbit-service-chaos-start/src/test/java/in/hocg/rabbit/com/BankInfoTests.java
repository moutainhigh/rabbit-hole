package in.hocg.rabbit.com;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.util.StrUtil;
import com.google.api.client.util.Lists;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.com.api.enums.BankInfoType;
import in.hocg.rabbit.com.biz.entity.BankInfo;
import in.hocg.rabbit.com.biz.service.BankInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankInfoTests extends AbstractSpringBootTest {
    @Autowired
    BankInfoService bankInfoService;


    @Test
    public void testImportBankCode() {
        File svcFile = FileUtil.file("/Users/hocgin/Downloads/支付联行号和超级网银号/BankCode.txt");

        List<BankInfo> batchEntity = Lists.newArrayList();
        final int batchCount = 10000;
        AtomicInteger i = new AtomicInteger();
        FileUtil.readLines(svcFile, Charset.forName("GB2312"), (LineHandler) line -> {
            int count = i.getAndIncrement();
            if (count == 0) {
                return;
            }

            // 支付行号&行号状态&行别代码&城市代码&行名全称&清算行号&
            List<String> data = StrUtil.split(line, '&', 7);
            BankInfo entity = new BankInfo()
                .setType(BankInfoType.BankCode.getCodeStr())
                .setCode(data.get(0))
                .setBankStatus(data.get(1))
                .setBankCode(data.get(2))
                .setCityCode(data.get(3))
                .setFullName(data.get(4))
                .setSimpleName(data.get(4))
                .setClearingBankCode(data.get(5));
            batchEntity.add(entity);

            count = i.getAndIncrement();
            if (count % batchCount == 0 && CollUtil.isNotEmpty(batchEntity)) {
                bankInfoService.saveBatch(batchEntity);
                batchEntity.clear();
            }
        });
        if (CollUtil.isNotEmpty(batchEntity)) {
            bankInfoService.saveBatch(batchEntity);
            batchEntity.clear();
        }
    }

    @Test
    public void testImportSuperPayBankCode() {
        File svcFile = FileUtil.file("/Users/hocgin/Downloads/支付联行号和超级网银号/SuperBankCode.txt");

        List<BankInfo> batchEntity = Lists.newArrayList();
        final int batchCount = 10000;
        AtomicInteger i = new AtomicInteger();
        FileUtil.readLines(svcFile, Charset.forName("GB2312"), (LineHandler) line -> {
            int count = i.getAndIncrement();
            if (count == 0) {
                return;
            }

            // 超级网银行号&行号状态&行别代码&行名全称&清算行号&
            List<String> data = StrUtil.split(line, '&', 6);
            BankInfo entity = new BankInfo()
                .setType(BankInfoType.SuperPayBankCode.getCodeStr())
                .setCode(data.get(0))
                .setBankStatus(data.get(1))
                .setBankCode(data.get(2))
                .setFullName(data.get(3))
                .setSimpleName(data.get(3))
                .setClearingBankCode(data.get(4));
            batchEntity.add(entity);

            count = i.getAndIncrement();
            if (count % batchCount == 0 && CollUtil.isNotEmpty(batchEntity)) {
                bankInfoService.saveBatch(batchEntity);
                batchEntity.clear();
            }
        });
        if (CollUtil.isNotEmpty(batchEntity)) {
            bankInfoService.saveBatch(batchEntity);
            batchEntity.clear();
        }
    }
}

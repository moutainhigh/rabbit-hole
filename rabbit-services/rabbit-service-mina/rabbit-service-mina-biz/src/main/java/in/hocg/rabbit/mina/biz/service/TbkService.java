package in.hocg.rabbit.mina.biz.service;

import in.hocg.rabbit.mina.biz.pojo.ro.TbkBatchUrlRo;

import java.util.Map;

public interface TbkService {
    String getPrivilegeLink(String url);

    Map<String, String> getBatchPrivilegeLink(TbkBatchUrlRo ro);
}

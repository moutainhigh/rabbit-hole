package com.github.lotus.ums.api;

import com.github.lotus.common.constant.GlobalConstant;
import com.github.lotus.ums.api.pojo.ro.CreateAccountRo;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import com.github.lotus.ums.api.pojo.vo.GetLoginQrcodeVo;
import com.github.lotus.ums.api.pojo.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ServiceName.NAME)
public interface UserServiceApi {
    String CONTEXT_ID = "AccountServiceApi";

    @GetMapping(value = CONTEXT_ID + "/getUserByUsername", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getUserByUsername(@RequestParam("username") String username);

    @GetMapping(value = CONTEXT_ID + "/getUserByUsernameOrEmailOrPhone", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getUserByUsernameOrEmailOrPhone(@RequestParam("unique") String unique);

    @GetMapping(value = CONTEXT_ID + "/getUserByPhone", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getUserByPhone(@RequestParam("phone") String phone);

    @PostMapping(value = CONTEXT_ID + "/createAccount", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo createAccount(@Validated @RequestBody CreateAccountRo ro);

    @PostMapping(value = CONTEXT_ID + "/getUserToken", headers = GlobalConstant.FEIGN_HEADER)
    String getUserToken(@RequestParam("username") String username);

    @PostMapping(value = CONTEXT_ID + "/getUsername", headers = GlobalConstant.FEIGN_HEADER)
    String getUsername(@RequestParam("token") String token);

    @PostMapping(value = CONTEXT_ID + "/listAccountByAccountId", headers = GlobalConstant.FEIGN_HEADER)
    List<AccountVo> listAccountVoById(@RequestParam("id") List<Long> id);

    @PostMapping(value = CONTEXT_ID + "/getById", headers = GlobalConstant.FEIGN_HEADER)
    AccountVo getById(@RequestParam("id") Long id);

    @PostMapping(value = CONTEXT_ID + "/getUserDetailVoByUsername", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getUserDetailVoByUsername(@RequestParam("username") String username);

    @GetMapping(value = CONTEXT_ID + "/getUserByIdFlag", headers = GlobalConstant.FEIGN_HEADER)
    UserDetailVo getUserByIdFlag(@RequestParam("idFlag") String idFlag);

    @GetMapping(value = CONTEXT_ID + "/getLoginQrcode", headers = GlobalConstant.FEIGN_HEADER)
    GetLoginQrcodeVo getLoginQrcode();

    @GetMapping(value = CONTEXT_ID + "/confirmQrcode", headers = GlobalConstant.FEIGN_HEADER)
    void confirmQrcode(@RequestParam("idFlag") String idFlag, @RequestParam("userId") Long userId);
}

package com.github.lotus.ums.biz.controller;


import com.github.lotus.ums.biz.entity.Account;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountEmailRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountPhoneRo;
import com.github.lotus.ums.biz.pojo.ro.UpdateAccountRo;
import com.github.lotus.ums.biz.pojo.vo.AccountComplexVo;
import com.github.lotus.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import com.github.lotus.ums.biz.service.AccountService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import in.hocg.boot.web.utils.web.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * [用户模块] 账号表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-10-06
 */
@Api(tags = "ums::账号")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @UseLogger("获取用户头像")
    @ApiOperation("获取用户头像")
    @GetMapping("/{username}:avatar")
    @ResponseBody
    public ResponseEntity<?> getAvatarUrl(@PathVariable String username) {
        return ResponseUtils.preview(service.getAccountByUsernameOrEmailOrPhone(username)
            .map(Account::getAvatar).orElse(null));
    }

    @UseLogger("获取当前用户信息")
    @ApiOperation("获取当前用户信息")
    @GetMapping
    @ResponseBody
    public Result<AccountComplexVo> getCurrentAccount() {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.getComplexById(userId));
    }

    @UseLogger("获取当前用户权限")
    @ApiOperation("获取当前用户权限")
    @GetMapping("/authority")
    @ResponseBody
    public Result<List<AuthorityTreeNodeVo>> getCurrentAuthority() {
        AuthorityTreeNodeVo nodeVo = new AuthorityTreeNodeVo();
        nodeVo.setAuthorityCode("index");
        nodeVo.setTitle("首页");
        return Result.success(Collections.singletonList(nodeVo));
    }

    @UseLogger("账号信息 - 修改")
    @ApiOperation("账号信息 - 修改")
    @PostMapping
    @ResponseBody
    public Result<Long> updateAccount(@Validated @RequestBody UpdateAccountRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updateAccount(userId, ro));
    }

    @UseLogger("账号手机号 - 修改")
    @ApiOperation("账号手机号 - 修改")
    @PostMapping("/phone")
    @ResponseBody
    public Result<Long> updatePhone(@Validated @RequestBody UpdateAccountPhoneRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updatePhone(ro));
    }

    @UseLogger("账号邮箱 - 修改")
    @ApiOperation("账号邮箱 - 修改")
    @PostMapping("/email")
    @ResponseBody
    public Result<Long> updateEmail(@Validated @RequestBody UpdateAccountEmailRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setId(userId);
        ro.setUpdaterId(userId);
        return Result.success(service.updateEmail(ro));
    }


}


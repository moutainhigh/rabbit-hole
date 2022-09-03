package in.hocg.rabbit.ums.biz.controller;


import in.hocg.boot.utils.exception.UnAuthenticationException;
import in.hocg.rabbit.ums.api.pojo.ro.ForgotRo;
import in.hocg.rabbit.ums.api.pojo.ro.RegisterRo;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.pojo.ro.*;
import in.hocg.rabbit.ums.biz.pojo.vo.AccountComplexVo;
import in.hocg.rabbit.ums.biz.pojo.vo.UserInfoMeVo;
import in.hocg.rabbit.ums.biz.pojo.vo.AuthorityTreeNodeVo;
import in.hocg.rabbit.ums.biz.service.UserService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    private final UserService service;

    @UseLogger("获取用户头像")
    @ApiOperation("获取用户头像")
    @GetMapping("/{username}:avatar")
    @ResponseBody
    public ResponseEntity<?> getAvatarUrl(@ApiParam("用户名") @PathVariable String username) {
        return ResponseUtils.preview(service.getByUsernameOrEmailOrPhone(username).map(User::getAvatarUrl).orElse(null));
    }

    @UseLogger
    @ResponseBody
    @GetMapping("/me")
    @ApiOperation("获取当前用户信息")
    public Result<UserInfoMeVo> getMeUserInfo(@RequestParam(value = "force", defaultValue = "true") Boolean force) {
        Optional<Long> userIdOpt = force ? Optional.ofNullable(UserContextHolder.getUserIdThrow()) : UserContextHolder.getUserId();
        return Result.success(userIdOpt.map(service::getMeUserInfoById).orElse(null));
    }

    @UseLogger("获取当前用户信息")
    @ApiOperation("获取当前用户信息")
    @GetMapping
    @ResponseBody
    @Deprecated(/**⚠️即将废弃⚠️ 请考虑使用 getMeUserInfo **/)
    public Result<AccountComplexVo> getCurrentAccount(@RequestParam(value = "force", defaultValue = "true") Boolean force) {
        Optional<Long> userOpt;
        if (force) {
            userOpt = Optional.ofNullable(UserContextHolder.getUserIdThrow());
        } else {
            userOpt = UserContextHolder.getUserId();
        }
        return Result.success(userOpt.map(service::getComplexById).orElse(null));
    }

    @UseLogger("获取当前用户权限")
    @ApiOperation("获取当前用户权限")
    @GetMapping("/authority/tree")
    @ResponseBody
    public Result<List<AuthorityTreeNodeVo>> listTreeCurrentAuthority(@ApiParam("项目编号") @RequestParam(value = "project", required = false) String projectSn) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.listTreeCurrentAuthority(projectSn, userId));
    }

    @UseLogger("获取当前用户权限编码")
    @ApiOperation("获取当前用户权限编码")
    @GetMapping("/authority")
    @ResponseBody
    public Result<List<String>> listCurrentAuthorityCode(@ApiParam("项目编号") @RequestParam(value = "project", required = false) String projectSn) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.listCurrentAuthorityCode(projectSn, userId));
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

    @ApiOperation(value = "忘记密码", notes = "免登陆")
    @PostMapping("/forgot")
    @ResponseBody
    public Result<Void> forgot(@Validated @RequestBody ForgotRo ro) {
        service.forgot(ro);
        return Result.success();
    }

    @ApiOperation(value = "注册", notes = "免登陆")
    @PostMapping("/register")
    @ResponseBody
    public Result<Void> register(@Validated @RequestBody RegisterRo ro) {
        service.register(ro);
        return Result.success();
    }

    @ApiOperation(value = "获取当前账户 Token")
    @GetMapping("/token")
    public Result<String> getToken() {
        return Result.success(service.getToken(UserContextHolder.getUsername().orElseThrow(UnAuthenticationException::new)));
    }
}


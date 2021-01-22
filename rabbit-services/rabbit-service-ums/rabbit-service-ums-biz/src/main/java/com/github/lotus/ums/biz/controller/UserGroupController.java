package com.github.lotus.ums.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.ums.biz.pojo.ro.AssignUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.SaveUserGroupRo;
import com.github.lotus.ums.biz.pojo.ro.UserGroupPagingRo;
import com.github.lotus.ums.biz.pojo.vo.UserGroupComplexVo;
import com.github.lotus.ums.biz.service.UserGroupService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.validation.autoconfigure.group.Insert;
import in.hocg.boot.validation.autoconfigure.group.Update;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限模块] 用户组表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Api(tags = "ums::用户组")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user-group")
public class UserGroupController {
    private final UserGroupService service;

    @ApiOperation("查询详情 - 用户组")
    @GetMapping("/{id}")
    public Result<UserGroupComplexVo> get(@ApiParam(value = "用户组", required = true) @PathVariable Long id) {
        return Result.success(service.getUserGroup(id));
    }


    @ApiOperation("新增用户组 - 用户组")
    @PostMapping
    public Result<Long> insertOne(@Validated({Insert.class}) @RequestBody SaveUserGroupRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("分页查询用户组 - 用户组")
    @PostMapping("/_paging")
    public Result<IPage<UserGroupComplexVo>> paging(@Validated @RequestBody UserGroupPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("修改用户组 - 用户组")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "用户组", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody SaveUserGroupRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("删除用户组 - 用户组")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "用户组", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @ApiOperation("分配用户 - 用户组")
    @PostMapping("/{id}/assign")
    public Result<Void> assignUserGroup(@ApiParam(value = "用户组", required = true) @PathVariable Long id,
                                        @Validated @RequestBody AssignUserGroupRo ro) {
        service.assignUserGroup(id, ro);
        return Result.success();
    }
}


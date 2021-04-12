package com.atguigu.guli.service.edu.feign;

import com.atguigu.guli.common.base.result.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通过服务名和api路径可以确定一个服务
 */
//服务名
@Service
@FeignClient("service-oss")
public interface OssFileService {
    @ApiOperation(value = "测试")
    @GetMapping("/admin/oss/file/test")
     R test();

}

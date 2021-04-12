package com.atguigu.guli.service.oss.controller.admin;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;
import com.atguigu.guli.service.base.exception.MyException;
import com.atguigu.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value = "文件",required = true)
                    @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "模块",required = true)
                    @RequestParam("modu;e") String module)  {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFileName = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFileName);
            return R.ok().message(("文件上传成功")).data("url",uploadUrl);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
           throw  new MyException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test(){
        log.info("oss被调用");
        return R.ok();
    }
}

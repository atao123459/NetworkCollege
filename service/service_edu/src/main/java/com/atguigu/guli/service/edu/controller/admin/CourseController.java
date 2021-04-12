package com.atguigu.guli.service.edu.controller.admin;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.form.CourseInfoForm;
import com.atguigu.guli.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author centao
 * @since 2021-03-16
 */
@CrossOrigin
@Api(description = "课琛管理")
@RestController
@RequestMapping("/admin/edu/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("保存课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程信息",required = true)
            @RequestBody CourseInfoForm courseInfoForm){
      String courseId = courseService.saveCourseInfo(courseInfoForm);
      return R.ok().data("courseId",courseId).message("保存课程信息成功");

    }
}


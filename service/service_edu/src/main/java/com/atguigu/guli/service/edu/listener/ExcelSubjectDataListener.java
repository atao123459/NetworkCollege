package com.atguigu.guli.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.guli.service.edu.entity.Subject;
import com.atguigu.guli.service.edu.entity.excel.ExcelSubjectData;
import com.atguigu.guli.service.edu.mapper.SubjectMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        log.info("读取数据:{}", excelSubjectData);
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        log.info("一级标题:{}", levelOneTitle);
        log.info("二级标题:{}", levelTwoTitle);

        //组装数据存入数据库
        //判断数据是否存在
        Subject subjectLevaOne = this.getByTitle(levelOneTitle);
        String parentId = null;
        if(subjectLevaOne == null) {
            //组装一级标题
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            //存入数据库
            subjectMapper.insert(subject);
            parentId  = subject.getId();
        }else{
            parentId = subjectLevaOne.getId();
        }

        Subject subjectLevelTwo = this.getsubByTitle(levelTwoTitle, parentId);
        //组装二级类别
        if(subjectLevelTwo == null){
            Subject subject = new Subject();
            subject.setTitle(levelTwoTitle);
            subject.setParentId(parentId);
            subjectMapper.insert(subject);
        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("读取完毕");
    }

    /**
     * 根据标题判断数据是否存在，避免出现重复的一级标题
     * @param title
     * @return
     */
    private Subject getByTitle(String title){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",title);
        subjectQueryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(subjectQueryWrapper);

    }

    /**
     * 根据一级标题和二级标题判断数据是否存在，避免出现重复的一级标题
     * @param title
     * @return
     */
    private Subject getsubByTitle(String title,String parentId){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",title);
        subjectQueryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(subjectQueryWrapper);

    }
}

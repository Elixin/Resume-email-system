package com.example.email.service;

import com.example.email.pojo.ResumeEmaillPojo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IResumeEmaillService {
    /**
     * 查询全部
     * @return
     */
    List<ResumeEmaillPojo> findAll();

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    ResumeEmaillPojo findOneName(String name);

    /**
     * 根据学历查询查询
     * @param education
     * @return
     */
    List<ResumeEmaillPojo> findeducation(String education);

    /**
     * 根据职位查询查询
     * @param postion
     * @return
     */
    ResumeEmaillPojo findpostion(String postion);

    /**
     * 插入一条新的数据
     * @param resumeEmaillPojo
     */
    void installnewResume(ResumeEmaillPojo resumeEmaillPojo);
}

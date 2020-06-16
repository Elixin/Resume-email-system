package com.example.email.service.impl;

import com.example.email.dao.ResumeEmailMapper;
import com.example.email.pojo.ResumeEmaillPojo;
import com.example.email.service.IResumeEmaillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResumeEmaillServiceimpl implements IResumeEmaillService {

    @Autowired
    ResumeEmailMapper resumeEmailMapper;

    @Override
    public List<ResumeEmaillPojo> findAll() {

        return resumeEmailMapper.findAll();
    }

    @Override
    public ResumeEmaillPojo findOneName(String name) {
        return null;
    }

    @Override
    public List<ResumeEmaillPojo> findeducation(String education) {
        return null;
    }

    @Override
    public ResumeEmaillPojo findpostion(String postion) {
        return null;
    }

    @Override
    public void installnewResume(ResumeEmaillPojo resumeEmaillPojo) {
        resumeEmailMapper.installnewResume(resumeEmaillPojo);
    }
}

package com.example.email;

import com.example.email.pojo.ResumeEmaillPojo;
import com.example.email.service.IEmailService;
import com.example.email.service.IResumeEmaillService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmailApplicationTests {
    @Autowired
    IResumeEmaillService iResumeEmaillService;
    @Autowired
    IEmailService iEmailService;
    @Test
    void contextLoads() {
        List<ResumeEmaillPojo> all = iResumeEmaillService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).toString());
        }
    }

    @Test
    void contextLoads2() {
        ResumeEmaillPojo resumeEmaillPojo = new ResumeEmaillPojo();
        resumeEmaillPojo.setName("王五");
        resumeEmaillPojo.setEducation("专科");
        resumeEmaillPojo.setPosition("软件开发工程师");
        resumeEmaillPojo.setRusumeName("王五的简历.pdf");
        iResumeEmaillService.installnewResume(resumeEmaillPojo);
        List<ResumeEmaillPojo> all = iResumeEmaillService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).toString());
        }
    }
    @Test
    void testEmail() throws Exception {
        iEmailService.readEmail();
    }
    @Test
    void testEmail2() throws Exception {
        iEmailService.sendMaill("2499537131@qq.com","测试","测试测试");
    }

}

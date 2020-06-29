package com.example.email.cotrller;

import com.example.email.pojo.ResumeEmaillPojo;
import com.example.email.service.impl.ResumeEmaillServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Controller
@RequestMapping("/resumepeople")
public class ResumeEmaillCotrller {
    @Autowired
    ResumeEmaillServiceimpl resumeEmaillServiceimpl;
//    @GetMapping
//    public String index(Model model){
//        return "index";
//    }


    @GetMapping
    public String all(Model model){
        List<ResumeEmaillPojo> all = resumeEmaillServiceimpl.findAll();
        model.addAttribute("NewResume",all);
        model.addAttribute("size",all.size());
        return"index";
    }

}

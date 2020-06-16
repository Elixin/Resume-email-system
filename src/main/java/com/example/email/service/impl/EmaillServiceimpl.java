package com.example.email.service.impl;

import com.example.email.pojo.ResumeEmaillPojo;
import com.example.email.service.IEmailService;
import com.example.email.service.IResumeEmaillService;
import com.example.email.util.ContextSeparate;
import com.example.email.util.ReciveMail;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmaillServiceimpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ResumeEmaillPojo resumeEmaillPojo;
    @Autowired
    private ContextSeparate contextSeparate;
    @Autowired
    private IResumeEmaillService iResumeEmaillService;
    //通过application传入用户名
    @Value("${mail.username}")
    private String username;
    //通过application密码
    @Value("${mail.password}")
    private String password;
    @Value("${mail.fromMail.addr}")
    private String from;
    private ReciveMail reciveMail;

    //定义两个正则用于匹配标题是否是求职信
    private final String typejl = ".*简历.*";
    private final String typeqz = ".*求职.*";

    @Override
    public void sendMaill(String to, String titlem, String context) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(titlem);
        simpleMailMessage.setText(context);
        try {
            javaMailSender.send(simpleMailMessage);
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }
    }

    @Override
    public void readEmail() throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "imap.qq.com");
        props.setProperty("mail.imap.port", "143");
        // 创建Session实例对象
        Session session = Session.getInstance(props);
        // 创建IMAP协议的Store对象
        Store store = session.getStore("imap");
        // 连接邮件服务器
//        store.connect("2499537131@qq.com", "vgbdfcaalklpdhie");
        store.connect(username, password);
        // 获得收件箱
        Folder folder = store.getFolder("INBOX");
        if (folder != null) {
            folder.open(Folder.READ_WRITE);
        }
        FetchProfile profile = new FetchProfile();
        profile.add(FetchProfile.Item.ENVELOPE);
        //false代表未读，true代表已读
        FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        //根据设置好的条件获取message
        Message[] messages = folder.search(ft);
        System.out.println("收件箱中共" + messages.length + "封未读邮件!");
        /**
         * 这里有个bug当没有收件人地址时会抛出异常
         * javax.mail.MessagingException: Failed to load IMAP envelope
         * 处理办法是在邮箱中设置规则,过滤掉没有收件人地址的邮件,这里重点批评qq音乐,这个异常就是QQ音乐跑出来的
         */
        for (int i = 0; i < messages.length; i++) {
            Message a = messages[i];
            String subject = a.getSubject();
            if (Pattern.matches(typejl, subject) || Pattern.matches(typeqz, subject)) {
                reciveMail = new ReciveMail((MimeMessage) a);
                String cc = reciveMail.getMailAddress("cc");
                reciveMail.getMailContent(a);
                if (reciveMail.isContainAttach(a)) {
                    reciveMail.saveAttachMent(a);
                }
                contextSeparate.setText(reciveMail.getBodyText());
                List<String> requestvalues = contextSeparate.getRequestvalues();
//                requestvalues.forEach(s -> System.out.println(s));
                resumeEmaillPojo.setName(requestvalues.get(0));
                resumeEmaillPojo.setEducation(requestvalues.get(1));
                resumeEmaillPojo.setPosition(requestvalues.get(2));
                resumeEmaillPojo.setRusumeName(reciveMail.getFilename());
                iResumeEmaillService.installnewResume(resumeEmaillPojo);
            }
        }
        folder.close(false);
        store.close();
    }

}

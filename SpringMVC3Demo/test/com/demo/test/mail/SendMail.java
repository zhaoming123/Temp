package com.demo.test.mail;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail
{
    //收件人邮箱地址
    private String to; 
    //发件人邮箱地址
    private String from; 
    //SMTP服务器地址
    private String smtpServer; 
    //登录SMTP服务器的用户名
    private String username ;
    //登录SMTP服务器的密码
    private String password ;
    //邮件主题
    private String subject; 
    //邮件正文
    private String content; 
    //记录所有附件文件的集合
    List<String> attachments 
        = new ArrayList<String>();
    //无参数的构造器
    public SendMail()
    {
    }
    
    public SendMail(String to , String from , String smtpServer 
        , String username , String password 
        , String subject , String content)
    { 
        this.to = to;
        this.from = from;
        this.smtpServer = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }
    //to属性的setter方法
    public void setTo(String to)
    {
        this.to = to;
    }
    //from属性的setter方法
    public void setFrom(String from)
    {
        this.from = from;
    }
    //smtpServer属性的setter方法
    public void setSmtpServer(String smtpServer)
    {
        this.smtpServer = smtpServer;
    }
    //username属性的setter方法
    public void setUsername(String username)
    {
        this.username = username;
    }
    //password属性的setter方法
    public void setPassword(String password)
    {
        this.password = password;
    }
    //subject属性的setter方法
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    //content属性的setter方法
    public void setContent(String content)
    {
        this.content = content;
    }
    //把邮件主题转换为中文
    public String transferChinese(String strText)
    {
        try
        {
            strText = MimeUtility.encodeText(
                new String(strText.getBytes()
                , "UTF-8"), "UTF-8", "B");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return strText;
    }
    //增加附件，将附件文件名添加的List集合中
    public void attachfile(String fname)
    {
        attachments.add(fname);
    }
    //发送邮件
    public boolean send()
    {
        //创建邮件Session所需的Properties对象
        Properties props = new Properties();
        props.put("mail.smtp.host" , smtpServer);
        props.put("mail.smtp.auth" , "true");
        //创建Session对象
        Session session = Session.getDefaultInstance(props
            //以匿名内部类的形式创建登录服务器的认证对象
            , new Authenticator()
            {
                public PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(username,password); 
                }
            });
        try
        {
            //构造MimeMessage并设置相关属性值
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(from));
            //设置收件人
            InternetAddress[] addresses = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO , addresses);
            //设置邮件主题
            subject = transferChinese(subject);
            msg.setSubject(subject);    
            //构造Multipart
            Multipart mp = new MimeMultipart();
            //向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setText(content);
            //将BodyPart添加到MultiPart中
            mp.addBodyPart(mbpContent);
            //向Multipart添加附件
            //遍历附件列表，将所有文件添加到邮件消息里
            for(String efile : attachments)
            {
                MimeBodyPart mbpFile = new MimeBodyPart();
                //以文件名创建FileDataSource对象
                FileDataSource fds = new FileDataSource(efile);
                //处理附件
                mbpFile.setDataHandler(new DataHandler(fds));
                mbpFile.setFileName(fds.getName());
                //将BodyPart添加到MultiPart中
                mp.addBodyPart(mbpFile);
            }
            //清空附件列表
            attachments.clear();
            //向Multipart添加MimeMessage
            msg.setContent(mp);
            //设置发送日期
            msg.setSentDate(new Date());
            //发送邮件
            Transport.send(msg);
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        SendMail sendMail = new SendMail();
        sendMail.setSmtpServer("smtp.163.com");
        //此处设置登录的用户名
        sendMail.setUsername("zhaomingzhu198911@163.com");
        //此处设置登录的密码
        sendMail.setPassword("zmz726757200");
        //设置收件人的地址
        sendMail.setTo("839435517@qq.com");
        //设置发送人地址
        sendMail.setFrom("zhaomingzhu198911@163.com");
        //设置标题
        sendMail.setSubject("测试邮件标题！");
        //设置内容
        sendMail.setContent("你好这LALALLALALLA件！"); 
        //粘贴附件
        sendMail.attachfile("F:/Demo/images1/01.jpg");
        sendMail.attachfile("F:/Demo/images1/02.jpg");
        if (sendMail.send())
        {
            System.out.println("---邮件发送成功---");
        }
    }
}
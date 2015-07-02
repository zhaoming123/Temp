package com.demo.utils;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class EmailUtils {

	public static void sendMessage(String to,String subject, String body) throws Exception{
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.host", CustomizedProperty.getContextProperty("mail.smtp.host",""));
			properties.put("mail.smtp.auth", "true");
			MyAuthenticator mAuthenticator=new MyAuthenticator(CustomizedProperty.getContextProperty("email_from"), CustomizedProperty.getContextProperty("email_pwd"));
		    Session session=Session.getDefaultInstance(properties, mAuthenticator);
		    MimeMessage message=new MimeMessage(session);
		    MimeMessageHelper mHelper=new MimeMessageHelper(message);
		    mHelper.setFrom(CustomizedProperty.getContextProperty("email_from"));
		    mHelper.setTo(to);
		    mHelper.setSubject(subject);
		    mHelper.setText(body);
		    Transport.send(message);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public static void sendMessage(String filePath,String to,String subject, String body) throws Exception{
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.host", CustomizedProperty.getContextProperty("mail.smtp.host",""));
			properties.put("mail.smtp.auth", "true");
			MyAuthenticator mAuthenticator=new MyAuthenticator(CustomizedProperty.getContextProperty("email_from"), CustomizedProperty.getContextProperty("email_pwd"));
		    Session session=Session.getDefaultInstance(properties, mAuthenticator);
		    MimeMessage message=new MimeMessage(session);
		    MimeMessageHelper mHelper=new MimeMessageHelper(message);
		    mHelper.setFrom(CustomizedProperty.getContextProperty("email_from"));
		    mHelper.setTo(to);
		    mHelper.setSubject(subject);
		    mHelper.setText(body);
		    mHelper.addAttachment(MimeUtility.encodeText(new File(filePath).getName(),"UTF-8","B"), new File(filePath));
		    Transport.send(message);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}	public static void sendMessage(List<String> filePaths,String to,String subject, String body) throws Exception{
		try {
			Properties properties=new Properties();
			properties.put("mail.smtp.host", CustomizedProperty.getContextProperty("mail.smtp.host",""));
			properties.put("mail.smtp.auth", "true");
			MyAuthenticator mAuthenticator=new MyAuthenticator(CustomizedProperty.getContextProperty("email_from"), CustomizedProperty.getContextProperty("email_pwd"));
		    Session session=Session.getDefaultInstance(properties, mAuthenticator);
		    MimeMessage message=new MimeMessage(session);
		    MimeMessageHelper mHelper=new MimeMessageHelper(message);
		    mHelper.setFrom(CustomizedProperty.getContextProperty("email_from"));
		    mHelper.setTo(to);
		    mHelper.setSubject(subject);
		    mHelper.setText(body);
		    for (String filePath : filePaths) {
			    mHelper.addAttachment(MimeUtility.encodeText(new File(filePath).getName(),"UTF-8","B"), new File(filePath));
			}
		    Transport.send(message);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
	  EmailUtils emailUtils=new EmailUtils();
	        try {
	          System.out.println(CustomizedProperty.getContextProperty("mail.smtp.host",""));
				emailUtils.sendMessage("839435517@qq.com", "323232", "oppppppppp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}

package com.dnight.boot.email.service;

import javax.mail.MessagingException;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
public interface MailService {

    /**
     * 发送文本邮件
     *
     * @param to       收件人地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param cc       抄送地址
     */
    void sendSimpleMail(String to, String subject, String content, String... cc);

    void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

    void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) throws MessagingException;

    void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException;
}

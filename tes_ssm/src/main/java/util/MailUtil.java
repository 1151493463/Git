package util;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	private Properties props;
	private String sender;
	private String password;
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	/**
	 * 
	 * @param receiver 接收者
	 * @param content 内容
	 * @param subject 主题
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void sendMail(String receiver, String content,String subject)
            throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session

        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sender)); // 设置发送者

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver)); // 设置发送方式与接收者

        message.setSubject(subject);
        //message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

        message.setContent(content, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送

        Transport.send(message);
    }
}


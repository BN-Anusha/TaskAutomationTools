

import java.util.Properties;
import java.io.*;
import javax.mail.*;
import java.net.*;

public class SendMailTLS {

	public static void main(String[] args) {
		try {
				//get IP address of current machine
				InetAddress ip = InetAddress.getLocalHost();
				String ipAddr = ip.getHostAddress();
				System.out.println("IP address of this system is :" +ip.getHostAddress());	
				String currentIp = ip.getHostAddress();

				//Read previous IP from file
				String fileName = "D:\\Learning\\java\\Mail\\ip.txt";
				File file = new File(fileName);
				FileReader fileReader = new FileReader(file);
				BufferedReader br = new BufferedReader(fileReader);
				StringBuffer sb = new StringBuffer();
				String line;
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				
				System.out.println("Contents of file: "+sb.toString());
				String prevIp = sb.toString();

				//TODO add comment here 
				
				//Write new IP to the same file from where it read
				//String fileName = "D:\\Learning\\java\\Mail\\ip.txt";
				File newFile = new File(fileName);
				FileWriter fileWriter = new FileWriter(newFile);
				//InetAddress ipNew = InetAddress.getLocalHost();
				fileWriter.write(currentIp);
				
				if (prevIp.equals(currentIp)){
					System.out.println("IP address are same");
					
				}else{
					final String username = "xxxx@gmail.com";
					final String password = "xxxxxxx";
			
					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");
					props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
					Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress("xxxx@gmail.com"));
						message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse("yyyy@gmail.com"));
						message.setSubject("IP Address change alert!!");
						//message.setText("Your IP address is" +ip.getHostAddress());
						message.setText("Your previous IP address is " +sb.toString()+ "\n\n"+
										"Your current IP address is " +ip.getHostAddress());
			
						Transport.send(message);
			
						System.out.println("Done");
						}

						fileReader.close();
						fileWriter.flush();
						fileWriter.close();	
			} 
			catch (MessagingException|IOException e) {
				throw new RuntimeException(e);
				//e.printStackTrace();
			}
	}
}


package org.unibl.etf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataSource;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/sendEmailServlet")
@MultipartConfig(
		   fileSizeThreshold = 1024 * 1024,  // 1 MB
		   maxFileSize = 5 * 1024 * 1024,     // 5 MB
		   maxRequestSize = 5 * 5 * 1024 * 1024  // 25 MB
		)
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Get user input from the form
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String msg = request.getParameter("content");

        // Get the uploaded file
        Part filePart = request.getPart("attachment");

        final String username = "manastiri1389@gmail.com"; 
        final String password = "zzcokloxmqoitfjd"; 
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // User's email address
            MimeBodyPart textPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            String final_Text = msg;
            textPart.setText(final_Text);
            message.setSubject(subject);
            multipart.addBodyPart(textPart);

            // If file is uploaded, add it to the email
            if (filePart != null && filePart.getSize() > 0) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(filePart.getInputStream(), filePart.getContentType());
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(extractFileName(filePart));
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);
            Transport.send(message);
            out.println("<center><h1 style='color:green;'>Email Sent Successfully.</h1>");
            out.println("<a href=\"inbox.jsp\"><h3>Go back to inbox</h3></a></center>");
        } catch (Exception e) {
            out.println(e);
        }
    }

}

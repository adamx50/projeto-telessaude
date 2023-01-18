package br.ufg.medicina.tele.Util;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.ufg.medicina.tele.entidades.NewsletterEntidade;

public abstract class EmailUtil {

	static String servidor = "10.232.1.1";
	
	private static String usuario = "contato@telemedicina.med.br";
	private static String senha = "#tele@med#mail";
	
	private static String remetente = "contato@telemedicina.med.br";
	
	
	/**
	 * Envia email no formato HTML
	 * @throws EmailException
	 * @throws MalformedURLException
	 * @author adao
	 */
	public static void enviaEmailFormatoHtml(NewsletterEntidade newsletterEntidade, String destinatario) throws EmailException, MalformedURLException {
		
		// Criar a mensagem de e-mail
		HtmlEmail email = new HtmlEmail ();
		
		configuraEmail(newsletterEntidade, destinatario, email);
		
		// embed the image and get the content id
		//URL url = new URL("http://localhost:8080/newsletterProjeto/img/imagemNewsletter/" + newsletterEntidade.getNomeImagem()); 
		URL url = new URL(Constantes.urlDiretorioImgImagemNewsletter + newsletterEntidade.getNomeImagem()); 
		String cid = email.embed(url, "Newsletter logo");
		
		
		//set the html message
		email.setHtmlMsg("<html><center><a href="+ newsletterEntidade.getLink()+"><img src=\"cid:"+cid+"\"></a></center></html>");
		
		// Configura a mensagem alternativa
		email.setTextMsg ("Seu cliente de e-mail não suporta mensagem HTML!");
		
		// Envia o e-mail
		email.send(); 
		
	}
	
	/**
	 * 
	 * Configura email para envio
	 * 
	 * @author adao
	 * */
	private static void configuraEmail(final NewsletterEntidade newsletterEntidade , String destinatario, Email email) {
		
		try {
			
			email.setHostName(servidor); // O servidor SMTP para envio do e-mail
			email.setAuthentication(usuario, senha);
			
			email.setFrom(remetente, "Newsletter Telessaúde"); // remetente
			email.addTo(destinatario, ""); // destinatário
			
			email.setSubject(newsletterEntidade.getTitulo()); // assunto do e-mail
			
			email.setMsg("Newsletter Telessaúde"); // conteudo do e-mail
			email.setSmtpPort(25);
			//email.setSSL(true);
			//email.setTLS(true);
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		
	}

}

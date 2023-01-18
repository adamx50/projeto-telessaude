package br.ufg.medicina.tele.BO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.ufg.medicina.tele.DAO.DAO;
import br.ufg.medicina.tele.DAO.NewsletterDAO;
import br.ufg.medicina.tele.DAOImp.NewsletterDAOImp;
import br.ufg.medicina.tele.Util.Constantes;
import br.ufg.medicina.tele.Util.EmailUtil;
import br.ufg.medicina.tele.entidades.NewsletterEntidade;

public class NewsletterBO extends BO<NewsletterEntidade, DAO<NewsletterEntidade>>{
	
	/**
	 * Atributo DAO
	 */
	private NewsletterDAO DAO;
	
	
	/**
	 * Método responsável por enviar newsletter para vários usuários
	 * @throws EmailException 
	 * @throws MalformedURLException 
	 */
	public void enviarNewsletter(final NewsletterEntidade newsletterEntidade, String enderecoEmail) throws MalformedURLException, EmailException {
		
		EmailUtil.enviaEmailFormatoHtml(newsletterEntidade, enderecoEmail);
	}
	
	/**
	 * Método responsavel por trazer o arquivo para o servidor
	 * 
	 * @param event
	 * 
	 * @throws IOException 
	 */
	public String fazerUpload(FileUploadEvent event) throws IOException{
		
		String nomeArquivo = null;
		
		try {
			
			UploadedFile arquivo = event.getFile();
			
			
			InputStream in = new BufferedInputStream(arquivo.getInputstream());
			nomeArquivo = arquivo.getFileName().replaceAll(" ", "_");
			
			String caminho = Constantes.diretorioImgImagemNewsletter + nomeArquivo;
			
			File file = new File(caminho);
			
			FileOutputStream fout = new FileOutputStream(file);
			
			while(in.available() != 0){
				
				fout.write(in.read());
			}
			
			fout.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw e;
		}
		
		return nomeArquivo;
	}
	
	/**
	 * @param fitro
	 * @return List<NewsletterEntidade>
	 * 
	 * Método responsável por filtrar newsletter
	 */
	public List<NewsletterEntidade> filtrarNewsletter(final String fitro) {
		
		return this.getDAO().filtrarNewsletter(fitro);
	}
	
	
	@Override
	protected NewsletterDAO getDAO() {
		
		if(this.DAO == null){
			
			this.DAO = new NewsletterDAOImp();
		}
		
		return this.DAO;
	}

}

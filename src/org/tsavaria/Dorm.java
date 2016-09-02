/**
 * 
 */
package org.tsavaria;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author tommy
 *
 */
public class Dorm {
	private static final int MAX_CONSOMMATION = 60;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection conn = Jsoup.connect("http://www.usherbrooke.ca/consommation-reseau-residences/consommation");
			Document doc = conn.get();
			
			Elements e = doc.getElementsByTag("title");
			String title = null;
			if(e.size() > 0)
				title = e.get(0).html();
			
			if("Service central d'authentification – Université de Sherbrooke".equals(title))
			{
				CredentialsDialog dialog = new CredentialsDialog();
				
				dialog.setVisible(true);
				while(!dialog.isDone());
				dialog.setVisible(false);
				
				HashMap<String, String> attributes = new HashMap<String, String>();
				
				Element f = doc.getElementById("authentification");
				
				Element lt = f.getElementsByAttributeValueMatching("name", "lt").get(0);
				Element execution = f.getElementsByAttributeValueMatching("name", "execution").get(0);
				
				attributes.put("username", dialog.getUsername());
				attributes.put("password", dialog.getPassword());
				attributes.put("lt", lt.attr("value"));
				attributes.put("execution", execution.attr("value"));
				attributes.put("_eventId", "submit");
				
				doc = Jsoup.connect("https://cas.usherbrooke.ca" + f.attr("action")).data(attributes).post();
			}
			
			String[] words = doc.getElementsByTag("h2").get(0).html().split(" ");
			float gigabytes = Float.parseFloat(words[words.length - 2].replace(",",  "."));
			ResultDialog result = new ResultDialog(gigabytes, MAX_CONSOMMATION);
			result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			result.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

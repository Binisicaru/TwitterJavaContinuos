/**
 * 
 */
package org.adesales.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * @author usuario
 *
 */
public class AccesoPrimario {
	HttpClient cliente;
	static InputStream stream ;
	InputStreamReader isr;
	BufferedReader br;
	static Properties prop;
	
	static{
		stream = AccesoPrimario.class.getResourceAsStream("./../../../config.properties");
		prop = new Properties();
		try {
			prop.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		HttpClient cliente;
		//consumidorAuth = new 	
		getAuthorization();
	}
	
	public static AccessToken getAuthorization(){
		
		System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "9666");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "9666");
		
		Twitter twitter = TwitterFactory.getSingleton();
		RequestToken rToken= null;//new RequestToken(token, tokenSecret);
		AccessToken aToken= new AccessToken("791406443593281536-OtsUKWaQnnq9DCgO9By3JMi5EsrbAZl", 
				"SMuxxwqSVZ6JgzIXSVorMawttBJ68HkZ5PXfnqkSc0HG8");
		twitter.setOAuthConsumer("hNP1ujYMaZhKSHGHo7T8dRyFd", "bNG9l06IJKu305UkLln1sLWm6OJiYoAYce9uYsCl83VISspjZN");
		
		System.setProperty("twitter4j.oauth.consumerKey", prop.getProperty("consumerKey"));
    	System.setProperty("twitter4j.oauth.consumerSecret", prop.getProperty("consumerSecret"));
		
		try{
			rToken = twitter.getOAuthRequestToken();
		}catch(TwitterException te){
			System.out.println("Se produjo una excepción en la clase" + te.getStatusCode() + " " +te.getErrorMessage());
			te.printStackTrace();
		}catch(Exception ex){
			System.out.println("Se produjo una excepción en la clase" + ex.getMessage());
			ex.printStackTrace();
		}
		
		InputStreamReader iSReader = new InputStreamReader(System.in); 
		BufferedReader br = new BufferedReader(iSReader);
		
		/*
		System.out.println("Open the following URL and grant access to your account:");
        System.out.println(rToken.getAuthorizationURL());
        System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		
        String pin = null;
        
        try{
        	pin = br.readLine();
        }catch(Exception e){
        	e.printStackTrace();
        }*/
        
        try{
        	/*if(pin.length()>0){
        		aToken = twitter.getOAuthAccessToken(rToken, pin);
        	}else{
        		aToken = twitter.getOAuthAccessToken();
        	}*/
        	//twitter.
        	twitter.setOAuthAccessToken(aToken);
        	twitter.sendDirectMessage("jessydecsi1", "This is a test message from my App, Cheers. Anabel.");
        	System.out.println("Saliendo después de enviar mi mensaje");// .getFollowersIDs(prop.getProperty("twitterId"), 1l).toString());
        	//WakeupAnabel
        }catch(TwitterException tw){
        	
        	if(401==tw.getStatusCode()){
        		System.out.println("Unable to get the access token.");
        	}else{
        		tw.printStackTrace();
        	}
        	//tw.printStackTrace();
        }
        
		return aToken;
	}

}

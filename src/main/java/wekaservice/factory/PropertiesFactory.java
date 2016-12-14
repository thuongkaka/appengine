package wekaservice.factory;

import java.io.*;
import java.util.Properties;

public class PropertiesFactory {
	public static PropertiesFactory getInstance(){
		return new PropertiesFactory();
	}
	
	public Properties getProperties() throws IOException{
		Properties props = new Properties();
		try(InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
		    props.load(resourceStream);
		}
		return props;
	}
}

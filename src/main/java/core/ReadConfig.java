package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	private static Properties prop;
	
	public static void loadProperty(String env) throws Exception {
		String propFileLoc = System.getProperty("user.dir")+"//src//test//resources//config//";
		FileInputStream fs = new FileInputStream(new File(propFileLoc+"config."+env+".properties"));
		prop = new Properties();
		prop.load(fs);
	}
	
	public static String getProperty(String propName) {
		if(prop.containsKey(propName)) {
			return prop.getProperty(propName);
		}
		return null;
	}
	
	

}

package neodream.sample;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.text.StrSubstitutor;

public class CommonUtil {

	public static void saveProperties() throws ConfigurationException {

		PropertiesConfiguration config = new PropertiesConfiguration("K:\\conf\\usergui.properties");
		config.setProperty("colors.background", "#000000");
		config.setProperty("alias", "jeusadmin -host ${DAS_URL} -port ${DAS_PORT} -u ${DAS_ID} -p ${DAS_PWD}");
		config.save();
	}

	public static void main(String[] args) throws Exception {
		saveProperties();
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.setIOFactory(new WhitespaceIOFactory());
		config.setFileName("K:\\conf\\usergui.properties");  // set the file to be loaded
		config.load();
		System.out.println(config.getFileName());
		System.out.println(config.getString("alias"));
		System.out.println(config.getString("colors.background"));
		String propAlias = config.getString("alias");
		String str = StrSubstitutor.replaceSystemProperties(
			      "You are running with java.version = ${java.version} and os.name = ${os.name}.");
		System.out.println(str);
		Map<String,String> valueMap = new HashMap<String,String>();
		valueMap.put("DAS_URL", "192.168.1.0");
		valueMap.put("DAS_PORT", "9736");
		valueMap.put("DAS_ID", "administrator");
		valueMap.put("DAS_pwd", "jeusadmin");
		
		String varPrefix = "${";
	    String varSuffix = "}";
		
		String alias = StrSubstitutor.replace(propAlias, valueMap, varPrefix, varSuffix  );
		System.out.println(alias);
		//strSubTest();

	}
	public static void strSubTest(){
		Map<String, Object> valueMap = new HashMap<String, Object>();
	    valueMap.put("state", "Andhra Pradesh");
	    valueMap.put("capital", "Hyderabad");
	    
	    String varPrefix = "#{";
	    String varSuffix = "}";
	    String template = "The capital of #{state} is #{capital}";
	    System.out.println(StrSubstitutor.replace(template, valueMap, varPrefix, varSuffix));
	    
	    varPrefix = "<<";
	    varSuffix = ">>";
	    template = "The capital of <<state>> is <<capital>>";
	    System.out.println(StrSubstitutor.replace(template, valueMap, varPrefix, varSuffix));
	}

}

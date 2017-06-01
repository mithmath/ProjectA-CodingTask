package projectA.Codingtask.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import projectA.Codingtask.utility.AppConstants;

public class Configuration {

	static final Logger LOGGER = Logger.getLogger(Configuration.class);
	Properties props;
	InputStream input;

	public Configuration() throws IOException {
		props = new Properties();
		input = getClass().getClassLoader().getResourceAsStream(AppConstants.PROPERTIES_FILENAME);
		if (input == null) {
			LOGGER.error("Configuration File Not Found:" + input);
		} else {
			props.load(input);
		}

	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}

}

package projectA.Codingtask.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projectA.Codingtask.App.Configuration;
import projectA.Codingtask.domain.Address;
import projectA.Codingtask.utility.AppConstants;
@Component
public class CSVFileReader implements IFileReader {

	@Autowired
	Configuration config;
	static final Logger LOGGER = Logger.getLogger(CSVFileReader.class);
	private BufferedReader bufferedReader;
	
	public boolean readEachLineFromFile(ArrayBlockingQueue<Address> addressDatQueue)
			throws IOException, InterruptedException {
		//Configuration configuration = new Configuration();
		String FILENAME = config.getProperty(AppConstants.INPUT_FILE);
		bufferedReader = new BufferedReader(new FileReader(FILENAME));
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			String[] data = line.split(",");

			Address emailAddress = new Address();
			emailAddress.setEmail(data[0]);
			emailAddress.setFirstName(data[1]);
			emailAddress.setLastName(data[2]);
			emailAddress.setDone(false);
			System.out.println("Produced:"+emailAddress.getEmail());
			addressDatQueue.put(emailAddress);
		}
		Address emailAddress = new Address();
		emailAddress.setDone(true);
		addressDatQueue.put(emailAddress);
		LOGGER.info("Reading CSV File Completed !!!");

		return true;

	}

}

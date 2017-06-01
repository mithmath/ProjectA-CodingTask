package projectA.Codingtask.UnitTest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import projectA.Codingtask.App.Configuration;
import projectA.Codingtask.Service.CSVFileReader;
import projectA.Codingtask.domain.Address;

@RunWith(JMockit.class)
public class CSVFileReaderUnitTest {
	
	@Tested
	CSVFileReader fileReader;
	
	@Injectable
	Configuration configuration;
	
	@Test
	public void ShouldInsertAllTheLinesFromCSVfileToLinkedQueue() throws IOException, InterruptedException{
		ArrayBlockingQueue<Address> addressDatQueue = new ArrayBlockingQueue<Address> (10);
		
		new Expectations() {
			
			{
				configuration.getProperty("InputFileName");
				returns("./src/test/resources/testdata3.csv");
			}
			
		};
		
		fileReader.readEachLineFromFile(addressDatQueue);
		assertEquals(3,addressDatQueue.size());
	}
	
}

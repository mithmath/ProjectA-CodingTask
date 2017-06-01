package projectA.Codingtask.UnitTest;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import projectA.Codingtask.App.Configuration;
import projectA.Codingtask.App.ThreadPoolManager;
import projectA.Codingtask.domain.Address;

@RunWith(JMockit.class)
public class ThreadPoolManagerUnitTest {

	@Tested
	ThreadPoolManager threadPoolManager;

	@Injectable
	Configuration configuration;

	@Test
	public void ShouldAllStepsOfThredPoolManagerExecutingProperly() throws IOException, InterruptedException {
		Address address = new Address();
		address.setEmail("mithmath@gmail.com");
		new Expectations() {

			{
				configuration.getProperty("ThreadCount");
				returns("1");
			}

		};

		threadPoolManager.readAndSentThreadPoolManagement();
	}
}

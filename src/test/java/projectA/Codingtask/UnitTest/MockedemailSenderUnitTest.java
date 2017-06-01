package projectA.Codingtask.UnitTest;

import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;
import projectA.Codingtask.Service.MockedEmailSender;
import projectA.Codingtask.domain.Address;

@RunWith(JMockit.class)
public class MockedemailSenderUnitTest {

	@Test
	public void ShouldExecuteMockedEmailSentMethodByReadingLinkedQueue() throws InterruptedException{
		MockedEmailSender emailSender = new MockedEmailSender();
		ArrayBlockingQueue<Address> addressDatQueue = new ArrayBlockingQueue<Address> (10);
		Address address1 = new Address();
		address1.setEmail("mithunvmathew@gmail.com");
		address1.setFirstName("mithun");
		address1.setLastName("mathew");
		address1.setDone(false);
		Address address2 = new Address();
		address2.setDone(true);
		addressDatQueue.put(address1);
		addressDatQueue.put(address2);
		Deencapsulation.invoke(emailSender, "sentEmail", addressDatQueue);
		
	}
	
}

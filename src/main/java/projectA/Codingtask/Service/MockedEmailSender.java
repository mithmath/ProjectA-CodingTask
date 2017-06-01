package projectA.Codingtask.Service;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import projectA.Codingtask.domain.Address;

public class MockedEmailSender implements IEmailSender {
	private static boolean isCSVReadereDone = false;
	static final Logger LOGGER = Logger.getLogger(MockedEmailSender.class);

	@Override
	public void sentEmail(ArrayBlockingQueue<Address> fileReadDataQueue) throws InterruptedException {
		while (!isCSVReadereDone || (isCSVReadereDone && !fileReadDataQueue.isEmpty())) {
			Address consumerAddress = fileReadDataQueue.take();

			if (consumerAddress.isDone()) {
				isCSVReadereDone = true;
				break;
			} else {
				LOGGER.info("Email sent to :" + consumerAddress.getEmail());
				// waiting time for Email sending (Mocked Email sender)
				Thread.sleep(500);
			}
		}
	}

}

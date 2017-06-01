package projectA.Codingtask.Service;

import java.util.concurrent.ArrayBlockingQueue;

import projectA.Codingtask.domain.Address;

public interface IEmailSender {

	void sentEmail(ArrayBlockingQueue<Address> fileReadDataQueue) throws InterruptedException;
}

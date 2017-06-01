package projectA.Codingtask.Service;

import java.util.concurrent.ArrayBlockingQueue;

import projectA.Codingtask.domain.Address;

public class EmailSenderImpl {

	IEmailSender emailSender;

	public IEmailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void sentEmail(ArrayBlockingQueue<Address> filereaddataqueue) throws InterruptedException {
		emailSender.sentEmail(filereaddataqueue);

	}
}

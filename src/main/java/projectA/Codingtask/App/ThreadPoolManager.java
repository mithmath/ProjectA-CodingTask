package projectA.Codingtask.App;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import projectA.Codingtask.Service.EmailSenderImpl;
import projectA.Codingtask.Service.FileReaderImpl;
import projectA.Codingtask.domain.Address;
import projectA.Codingtask.utility.AppConstants;

public class ThreadPoolManager implements Runnable {

	static final Logger LOGGER = Logger.getLogger(ThreadPoolManager.class);
	private final static ArrayBlockingQueue<Address> fileReadDataQueue = new ArrayBlockingQueue<Address>(1000000);

	private boolean isConsumer = false;

	@Autowired
	private FileReaderImpl fileReader;

	@Autowired
	EmailSenderImpl emailSender;

	@Autowired
	Configuration config;

	public ThreadPoolManager() {
	}

	public ThreadPoolManager(boolean consumer) {
		isConsumer = consumer;
	}

	public ThreadPoolManager(boolean consumer, FileReaderImpl fileReader) {
		isConsumer = consumer;
		this.fileReader = fileReader;
	}

	public ThreadPoolManager(boolean consumer, EmailSenderImpl emailSender) {
		isConsumer = consumer;
		this.emailSender = emailSender;
	}

	public void readAndSentThreadPoolManagement() throws IOException {

		int CONSUMER_COUNT = Integer.parseInt(config.getProperty(AppConstants.THREAD_COUNT));
		long startTime = System.currentTimeMillis();
		LOGGER.info("Process StartTime:" + startTime);

		// Thread for CSV FileReader
		ExecutorService CSVReaderPool = Executors.newFixedThreadPool(1);
		CSVReaderPool.submit(new ThreadPoolManager(false, fileReader)); // call
																		// run
																		// method

		// Thread Pool for Email Sender
		ExecutorService emailSenterPool = Executors.newFixedThreadPool(CONSUMER_COUNT);

		for (int i = 0; i < CONSUMER_COUNT; i++) {
			emailSenterPool.submit(new ThreadPoolManager(true, emailSender)); // run
																		// called
		}
		// Shutting down each pool , if assigned task is over.
		CSVReaderPool.shutdown();
		emailSenterPool.shutdown();

		while (!CSVReaderPool.isTerminated() || !emailSenterPool.isTerminated()) {
		}
		long endTime = System.currentTimeMillis();
		LOGGER.info("endTime:" + endTime);
		long elapsedTime = endTime - startTime;
		LOGGER.info("Total elapsed time: " + elapsedTime + " ms");
	}

	public void run() {
		if (isConsumer) {
			try {
				emailSender.sentEmail(fileReadDataQueue);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}

		} else {
			try {
				fileReader.readEachLineFromFile(fileReadDataQueue);
			} catch (IOException | InterruptedException e) {
				LOGGER.error(e);
			}
		}

	}
}

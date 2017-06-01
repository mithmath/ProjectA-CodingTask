package projectA.Codingtask.App;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import projectA.Codingtask.utility.AppConstants;

public class App {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("./EmailSender-context.xml");
		ThreadPoolManager manager = (ThreadPoolManager) context.getBean(AppConstants.BEAN_THREADPOOL);
		manager.readAndSentThreadPoolManagement();
		((ConfigurableApplicationContext) context).close();
	}
}

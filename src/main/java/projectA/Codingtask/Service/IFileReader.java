package projectA.Codingtask.Service;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import projectA.Codingtask.domain.Address;

public interface IFileReader {

	boolean readEachLineFromFile(ArrayBlockingQueue<Address> addressDatQueue) throws IOException, InterruptedException;
}

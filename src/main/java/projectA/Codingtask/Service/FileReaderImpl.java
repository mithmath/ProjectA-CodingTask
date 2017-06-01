package projectA.Codingtask.Service;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import projectA.Codingtask.domain.Address;

public class FileReaderImpl {

	IFileReader fileReader;

	public IFileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(IFileReader fileReader) {
		this.fileReader = fileReader;
	}

	public void readEachLineFromFile(ArrayBlockingQueue<Address> filereaddataqueue)
			throws IOException, InterruptedException {

		fileReader.readEachLineFromFile(filereaddataqueue);
	}

}

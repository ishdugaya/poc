package Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mocipoc.model.OrderModel;

public class Main {
	static String sourceFile="C:/Users/manish.dugaya/Desktop/sample_data_ordered.txt";
	public static void main(String[] args) throws IOException {
		Stream<String> orderFileStream=readSourceFile(sourceFile);
		orderFileStream.skip(1)
		.map(orderLine -> orderLine.split("\\s"))
		.map(orderArray -> new OrderModel(orderArray[0], orderArray[1]) )
		.sorted()
		.collect(Collectors.toList());
	}

	public static Stream<String> readSourceFile(String sourceFile) throws IOException {

		try  {
			Stream<String> stream = Files.lines(Paths.get(sourceFile));
			return stream;
		}finally {
			System.out.println("");
		}
	}
}

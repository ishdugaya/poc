package com.mocipoc.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mocipoc.dao.FileReadWriteDao;
import com.mocipoc.model.OrderModel;
import com.mocipoc.utils.Constants;

/**
 * @author manish.dugaya
 *
 */
/**
 * @author manish.dugaya
 *
 */
@Service
public class FileHandlingService {

	public static final Logger log = LoggerFactory.getLogger(FileHandlingService.class);

	@Autowired
	private FileReadWriteDao fileReadWriteDao;

	
	/**
	 * @param sourceFile: Source File location 
	 * @param destinationFileLocation :Destination File Location
	 * @return Status 
	 * @throws IOException
	 */
	public boolean readSourceFile(String sourceFile,String destinationFileLocation) throws IOException {
		List<OrderModel> sourceContentList=null;
		
		Stream<String> orderFileStream = fileReadWriteDao.readSourceFile(sourceFile);
		
		sourceContentList=	orderFileStream.skip(1)
								.map(orderLine -> orderLine.split("\\s"))
								.map(orderArray -> new OrderModel(orderArray[0], orderArray[1]/*getDatetimeFromEpoc(Long.parseLong(orderArray[1]))*/ ))
								//.sorted()
								.collect(Collectors.toList());
		orderFileStream.close();
		
		log.debug("****sourceContentList**** " + sourceContentList);
		
		
		return fileReadWriteDao.writeFile(prepareDestinationFileContent(sourceContentList),destinationFileLocation);
	}

	
	/**
	 * convert epoch to specified time format
	 * 
	 * @param epoc
	 * @return date in yyyy-MM-dd HH:mm:ss
	 */
	private String getDatetimeFromEpoc(Long epoc) {
		log.info("****epoc**** " + epoc);
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(epoc * 1000));

		} catch (Exception e) {
			log.error("****getDatetimeFromEpoc***" + e.getMessage());
			return  Long.toString(epoc);
		}

	}

	

	/**
	 * Prepare String for destination file
	 * 
	 * @param orderList
	 * @return Output file's content string 
	 */
	private  String prepareDestinationFileContent(List<OrderModel> orderList) {
		log.info("order List  "+orderList);
		StringBuilder orderString = new StringBuilder();
		//List<String> tags = Arrays.asList("Given Pizza Orders with time are : ", " Ordered ", " at :");
		orderList.stream().map(order->Constants.ORDERED_TAG+order.getOrderType()+Constants.AT_TAG+order.getOrderTimeStamp()+"\r\n").forEach(orderString::append);
		
		log.info("****contnet****"+orderString.insert(0, Constants.GIVEN_PIZZA_TAG+"\r\n"));
		 return orderString.toString();

	}

}

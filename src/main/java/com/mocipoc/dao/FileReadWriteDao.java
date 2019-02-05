package com.mocipoc.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FileReadWriteDao {
	public static final Logger log = LoggerFactory.getLogger(FileReadWriteDao.class);

	/**
	 * @param fileConent
	 * @param destinationFileLocation
	 * @return status
	 * @throws IOException
	 */
	public boolean writeFile(String fileConent, String destinationFileLocation) throws IOException {
		boolean result = false;
		log.info("**inside write File***" + fileConent);
		log.info("**DestinationFile location***" + destinationFileLocation);
		Path path = Paths.get(destinationFileLocation);
		// Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(fileConent);
			result = true;
		}
		return result;
	}

	/**
	 * @param sourceFile
	 * @return Stream of file
	 * @throws IOException
	 */
	public Stream<String> readSourceFile(String sourceFile) throws IOException {

		log.info("****sourceFile Location**** " + sourceFile);

		Stream<String> stream = Files.lines(Paths.get(sourceFile));
		
		return stream;

	}

}

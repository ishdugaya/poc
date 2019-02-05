package com.mocipoc.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mocipoc.service.FileHandlingService;

@RestController
public class FileHandlingController {

	public static final Logger log=LoggerFactory.getLogger(FileHandlingController.class);
	@Autowired
	private FileHandlingService fileHandlingService;
	
	
	/**
	 * @param sourceFilelocation
	 * @param destinationFilelocation
	 * @return Response Entity
	 * @throws IOException
	 */
	@PostMapping(value="/sourceFile")
	public ResponseEntity<String> xyz(@RequestParam (required=true)String sourceFilelocation ,@RequestParam(required=false , defaultValue="${defalutDestinationFileLocation}") String destinationFilelocation ) throws IOException{
		log.info("**Inside file controller**sourceFilelocation ** "+sourceFilelocation);
		log.info("**Inside file controller**destinationFilelocation ** "+destinationFilelocation);
		if(null==destinationFilelocation){
			return new ResponseEntity<String>("Please provide source file in request param..!",HttpStatus.EXPECTATION_FAILED);
		}
		boolean status=fileHandlingService.readSourceFile(sourceFilelocation,destinationFilelocation);
		 if(status){
			 return new ResponseEntity<String>("File Created Successfully..!",HttpStatus.CREATED);
		 }
		 	return new ResponseEntity<String>("File not Created..!",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.mocipoc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mocipoc.service.FileHandlingService;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(value = {FileHandlingController.class}, secure = false)
@PrepareForTest({FileHandlingService.class})
public class FileHandlingControllerTest2 {
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private FileHandlingService fileHandlingService;
	
	@Value("${sourceFile}")
	private String sourceFile;
	
	@Value("${destinationFile}")
	private String destinationFile;
	
	@Before
	public void setup(){
		//mockmvc = MockMvcBuilders.standaloneSetup(fileHandlingController).setControllerAdvice(new ExceptionController()).build();
		//Mockito.when(fileHandlingService.readSourceFile(Matchers.anyString(), Matchers.anyString())).thenReturn(HttpStatus.CREATED);
		//powermocki
	}
	
	
	
	@Test
	public void testFileHandlingControllerFileCreated() throws Exception{
		Mockito.when(fileHandlingService.readSourceFile(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/sourceFile").accept(
				MediaType.APPLICATION_JSON).param("sourceFilelocation", sourceFile)
				.param("destinationFilelocation",destinationFile);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		Assert.assertEquals("File Created Successfully..!", result.getResponse().getContentAsString());		
		Assert.assertEquals(201,result.getResponse().getStatus());
		
	}
	@Test
	public void test1FileHandlingControllerFileNotCreated() throws Exception{
		Mockito.when(fileHandlingService.readSourceFile(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.FALSE);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/sourceFile").accept(
				MediaType.APPLICATION_JSON).param("sourceFilelocation", sourceFile)
				.param("destinationFilelocation",destinationFile);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		Assert.assertEquals("File not Created..!", result.getResponse().getContentAsString());
		Assert.assertEquals(500,result.getResponse().getStatus());
		
		
	}
	@Test
	public void testFileHandlingControllerWithMissingPara() throws Exception {
		String destinationFile=null ;
		Mockito.when(fileHandlingService.readSourceFile(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.FALSE);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/sourceFile").accept(
				MediaType.APPLICATION_JSON)
				.param("destinationFilelocation", destinationFile);
				

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getStatus());
		Assert.assertEquals("oops..!!!  Request Parameter is not correct.", result.getResponse().getContentAsString());
		Assert.assertEquals(400,result.getResponse().getStatus());
		
		
	}
}

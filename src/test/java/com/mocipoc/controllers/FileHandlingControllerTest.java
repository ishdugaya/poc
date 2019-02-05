/*package com.mocipoc.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mocipoc.exception.ExceptionController;
import com.mocipoc.service.FileHandlingService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FileHandlingController.class)
@WebAppConfiguration
public class FileHandlingControllerTest {
	
	private MockMvc mockmvc;
	
	@Mock
	private FileHandlingService fileHandlingService;
	
	@InjectMocks
	private FileHandlingController fileHandlingController; 
	@Before
	public void setup(){
		mockmvc = MockMvcBuilders.standaloneSetup(fileHandlingController).setControllerAdvice(new ExceptionController()).build();
		Mockito.when(fileHandlingService.readSourceFile(Matchers.anyString(), Matchers.anyString())).thenReturn(HttpStatus.CREATED);
	}
	
	
	
	@Test
	public void testFileHandlingController() throws Exception{
		String sourceFile = "C:/Users/manish.dugaya/Desktop/sample_data_ordered.txt";
		String destinationFile ="C:/Users/manish.dugaya/Desktop/out_data_ordered.txt";
		mockmvc.perform(post("/sourceFile")
				.contentType(MediaType.APPLICATION_JSON)
				.param("sourceFilelocation", sourceFile)
				.param("destinationFilelocation",destinationFile)
			).andDo(print())
				.andExpect(status().isCreated());
	}
}
*/
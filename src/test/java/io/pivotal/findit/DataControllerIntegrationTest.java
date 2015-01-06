package io.pivotal.findit;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.findit.domain.NameValue;
import io.pivotal.findit.web.DataController;
import io.pivotal.findit.*;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

	@RunWith(SpringJUnit4ClassRunner.class)
	@ActiveProfiles(value = "test")
	@Transactional
	@TransactionConfiguration(defaultRollback = true)
	@SpringApplicationConfiguration(classes = Application.class)
	@WebAppConfiguration
	public class DataControllerIntegrationTest {

		
		//	@IntegrationTest("server.port=9000")
//		@Autowired
//		private WebApplicationContext wac;
//		private MockMvc mockMvc;
//		
//	    private RestTemplate restTemplate = new TestRestTemplate();
//	    @Before
//	    public void setUp() throws Exception {
//	        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	    }
//	    
//	    @Test
//	    public void retrieveMock() {
//	    	 mockMvc.perform(get("/retrieve/Mallory"))
//             .andExpect(status().isCreated());
//	    }
	    
		@Autowired private DataController dataController;
		
		@Before
		public void setup() {
			dataController.store("Mallory","Pharmex");
			dataController.store("MTim","Pivotal");
			dataController.store("Mahout","Pyrex");
		}
		
		@Test
		public void query() {
			List<NameValue> nameValueList = dataController.query("M");
			
			Assert.assertNotNull(nameValueList);
			Assert.assertEquals(3, nameValueList.size());
		}
	    @Test
	    public void retrieve() {
	    	NameValue returnedName = dataController.retrieve("Mallory");
	    	

	        Assert.assertEquals(returnedName.getName(), "Mallory");
	        Assert.assertEquals(returnedName.getValue(), "Pharmex");
	    }

	    /***
	    	//setWebEnvironment(false);
	    	

	     * //	        ResponseEntity<NameValue> entity = 
//	                restTemplate.getForEntity("http://localhost:9000/retrieve/Mallory", NameValue.class);
//	        Assert.assertNotNull(entity);
//	        Assert.assertNotNull(entity.getBody());
//	        NameValue returnedName = (NameValue)entity.getBody();
	        //Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());
	     */
	}
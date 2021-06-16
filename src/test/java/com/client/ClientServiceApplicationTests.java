package com.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.client.model.Client;
import com.client.service.ClientService;
@SpringBootTest
class ClientServiceApplicationTests {

	@Autowired
	ClientService service;


		@Test
		public void testSaveClientInfo() {
			Client clt = new Client(1234567890, "Kamal", "Surya", "+918838002341","AP  ,AP , India");
			assertEquals(service.saveClientInfo(clt),"Please enter valid id");
			
			Client cl = new Client(1235677881, "Kumar V", "Vel", "+918838002241","Chennai  ,Tamilnadu , India");
			service.saveClientInfo(cl);
			assertEquals(service.saveClientInfo(cl),"Record is already exist");
			
			Client cl1 = new Client(1235677881, "Kumaran", "Raj", "+918838002241","Chennai  ,Tamilnadu , India");
			assertEquals(service.saveClientInfo(cl1),"Mobile Number is invalid");
			
			Client cl2 = new Client(123567798, "Balan", "Baski", "+918838002241","Chennai  ,Tamilnadu , India");
			assertEquals(service.saveClientInfo(cl2),"MobileNumber is already in use");
			
			
			
		}

		 @Test
	     public void testGetAllClient() {
	    	 List<Client> list = new ArrayList<Client>();
	    	 Client cl = new Client(1621212222, "Kumar V", "Vel", "+918838002243","Chennai  ,Tamilnadu , India");
	    	 Client cl1 = new Client(123567787, "Raj", "Sam", "+918838002242","Chennai  ,Tamilnadu , India");
	         list.add(cl);
	         list.add(cl1);
	         
	         service.saveClientInfo(cl);
	         service.saveClientInfo(cl1);
	          
	         //test
	         List<Client> clList = service.getAll();
	          
	         //test size equals or not
	         assertEquals(clList.size(),3);
	    }
		 
		 @Test
		 public void tesGetClientById() {
			 Client client =  service.getClientById(123567789);
			 assertEquals(client.getFirstName(),"Kumar V");
		 }
		 
		 @Test
		 public void tesUpdateClientById() {
			 Client client =  service.getClientById(123567788);
			 client.setFirstName("Kishore");
			 assertEquals(service.updateClientInfo(client),"Record updated successully");
			 
		 }
		 

}

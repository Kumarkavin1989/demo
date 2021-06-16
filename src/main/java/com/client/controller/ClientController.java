package com.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.model.Client;
import com.client.service.ClientService;
@RequestMapping("/ClientService")
@RestController
public class ClientController {
	
@Autowired
ClientService service;
	
@PostMapping("/saveClientInfo")
public String saveClientInfo(@RequestBody Client client) {
	return service.saveClientInfo(client);
}

@GetMapping("/getClientById/{id}")
public Client getClientById(@PathVariable  long id) {
	return service.getClientById(id);
}


@GetMapping("/all")
public List<Client> getAllClient() {
	return service.getAll();
}


@PutMapping("/updateClientInfo")
public String updateClientInfo(@RequestBody Client client) {
	return service.updateClientInfo(client);
}

@DeleteMapping("/deleteById/{id}")
public String deleteById(@PathVariable  long id) {
	return service.deleteById(id);
}

@GetMapping("/searchByfirstName/{firstName}")
public Client searchByfirstName(@PathVariable  String firstName) {
	return service.searchByfirstName(firstName);
}

@GetMapping("/searchByMobileNumber/{mobileNumber}")
public Client searchByMobileNumber(@PathVariable  String mobileNumber) {
	return service.searchByMobileNumber(mobileNumber);
}


}

package com.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.client.exception.RecordNotFoundException;
import com.client.model.Client;

@Service
public class ClientService {
	HashMap<Long,Client> map = new HashMap<>();
	public String saveClientInfo(Client client) {
	if(validateId(client)) {
	if(validateMobileNumber(client.getMobileNumber())) {
		if(!map.containsKey(client.getId())) {
			return save(client);
		}else {
			return "Record is already exist";
		}
		
	  }else {
		return "Mobile Number is invalid";
	}
	
	}else {
		return "Please enter valid id";
	}
 }


	private String save(Client client) {
		Optional<Entry<Long, Client>> st = map.entrySet().stream().filter(m->m.getValue().getMobileNumber().equals(client.getMobileNumber())).findAny();
		if(st.isEmpty()) {
			map.put(client.getId(),client);
			return "Record inserted successully";
		}else {
			return "MobileNumber is already in use";
		}
		
		
	}


	private boolean validateMobileNumber(String mobileNumber) {
		Pattern p = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$"); 
        Matcher m = p.matcher(mobileNumber); 
        return (m.find() && m.group().equals(mobileNumber));
	}

	public Client getClientById(long id) {
		if(map.containsKey(id)) {
			return map.get(id);
		}else {
			 throw new RecordNotFoundException("Record not foud");
		}
	}

	public List<Client> getAll() {
		return map.entrySet().stream().map(m->m.getValue()).collect(Collectors.toList());
	}

	public String updateClientInfo(Client client) {
		if(validateId(client)) {
			if(validateMobileNumber(client.getMobileNumber())) {
				return update(client);
			  }else {
				return "Mobile Number is invalid";
			}
			
			}else {
				return "Please enter valid id";
			}
	}
	

	private String update(Client client) {
		if(map.containsKey(client.getId())) {
			if(isMobileNumberExist(client)) {
				map.put(client.getId(), client);
				return "Record updated successfully";
			}else {
				return "MobileNumber is already in use";
			}
		}else {
			 throw new RecordNotFoundException("Record not foud");
		}
	}


	private boolean isMobileNumberExist(Client client) {
		List<Entry<Long, Client>> st = map.entrySet().stream().filter(v->v.getValue().getMobileNumber().equals(client.getMobileNumber()) && v.getValue().getId()!=client.getId())
				.collect(Collectors.toList());
		return st.size()==1?false:true;
	}


	public String deleteById(long id) {
		if(map.containsKey(id)) {
			 map.remove(id);
		}else {
			 throw new RecordNotFoundException("Record not foud");
		}
		return "Record Deleted successully";
	}

	public Client searchByfirstName(String firstName) {
		List<Client> st = map.entrySet().stream().map(m->m.getValue()).collect(Collectors.toList());
		if(st.size()>0) {
			return st.stream().filter(c->c.getFirstName().equals(firstName)).findAny().orElseThrow(()-> new RecordNotFoundException("Record not foud"));
		}
		return null;
	}

	public Client searchByMobileNumber(String mobileNumber) {
		List<Client> st = map.entrySet().stream().map(m->m.getValue()).collect(Collectors.toList());
		if(st.size()>0) {
			return st.stream().filter(c->c.getMobileNumber().equals(mobileNumber)).findAny().orElseThrow(()-> new RecordNotFoundException("Record not foud"));
		}
		return null;
	}
	
	private boolean validateId(Client client) {
		String regex = "\\d{13}";
		boolean result = String.valueOf(client.getId()).matches(regex);
		if(result) {
	         return true;
	      } else {
	         return false;
	      }
	}
	
}
				
				
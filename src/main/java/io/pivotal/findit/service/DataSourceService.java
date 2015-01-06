package io.pivotal.findit.service;

import io.pivotal.findit.domain.NameValue;
import io.pivotal.findit.domain.NameValueRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("DataSourceService")
public class DataSourceService {

	private static final String NO_VALUE = "Not set.";

	Logger logger = LoggerFactory.getLogger(DataSourceService.class);

	@Autowired
	NameValueRepository nameValueRepository;
	  
	private final static String viewTemplate = "Name: %s";
	private final static String saveTemplate = "Name: %s Value: %s";
    
	public boolean deleteValue(Long id) {
		if ( id < 0) {
			return false;
		}
		nameValueRepository.delete(id);
		return true;
	}
	
	public boolean deleteValue(NameValue nameValue) {
		if ( nameValue == null) {
			return false;
		}
		nameValueRepository.delete(nameValue);
		return true;
	}
	public NameValue storeValue(String keyName, String value )
	{
		return storeValue(new NameValue(keyName, value));
	}
	
	public NameValue storeValue(NameValue nameValue) {
		if (nameValue == null || nameValue.getName() == null || nameValue.getName().trim().length() <= 0 || 
				nameValue.getValue() == null || nameValue.getValue().trim().length() <=0 ) {
			return null;
		}

		logger.error(String.format(saveTemplate, nameValue.toString()));

		return nameValueRepository.save(nameValue);
	}

	// default to empty
	public NameValue defaultValue() {
		return new NameValue("","");
	}
	// default to name only
	public NameValue defaultValue(String keyName) {
		return new NameValue(keyName, keyName);
	}
	
  //  @HystrixCommand(fallbackMethod = "defaultValue")
	public NameValue retrieveValue(String keyName) {
		logger.error(String.format(viewTemplate, keyName));

		List<NameValue> nameValues = nameValueRepository.findByName(keyName);
		
		if(nameValues == null || nameValues.size() <= 0) {
			return new NameValue(keyName, NO_VALUE);
		}
		return nameValues.get(0);
	}

	public List<NameValue> search(String query) {
		if ( query == null) { return null; }
		
		List<NameValue> nameValues = nameValueRepository.findByQuery(query); // + "%s");
		logger.error("Size=" + nameValues.size());

		return nameValues;
	}

	public Iterable<NameValue> findAll() {
		return nameValueRepository.findAll();
	} 
}

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
}
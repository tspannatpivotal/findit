package io.pivotal.findit.web;

import io.pivotal.findit.domain.NameValue;
import io.pivotal.findit.service.DataSourceService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class DataController {

	public static HttpServletRequest getCurrentRequest() {
	     RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	     Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
	     Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
	     HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
	     Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
	     return servletRequest;
	 }
	
	Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	private DataSourceService dataSourceService;
	
    private static final String templateView = "Name %s Value %s";
    
    @RequestMapping("/retrieve/{name}")
    public NameValue retrieve(
    		@PathVariable(value="name") String name) 
    {
    	NameValue value = dataSourceService.retrieveValue(name);
    	String returnValue = String.format(templateView, name, value.getValue());
    	logger.error(returnValue);
    	final String userIpAddress = getCurrentRequest().getRemoteAddr();
    	final String userAgent = getCurrentRequest().getHeader("user-agent");
    	final String userDisplay = String.format("IP:%s Browser:%s", userIpAddress, userAgent);
    	logger.error(userDisplay);
        return value;
    }

    @RequestMapping("/query/{query}")
    public List<NameValue> query(
    		@PathVariable(value="query") String query) 
    {
    	List<NameValue> value = dataSourceService.search(query);
    	final String userIpAddress = getCurrentRequest().getRemoteAddr();
    	final String userAgent = getCurrentRequest().getHeader("user-agent");
    	final String userDisplay = String.format("Query:%s,IP:%s Browser:%s", query, userIpAddress, userAgent);
    	logger.error(userDisplay);
        return value;
    }

    @RequestMapping("/list")
    public Iterable<NameValue> list() {
        return dataSourceService.findAll();
    }
 
    @RequestMapping("/store")
    public @ResponseBody NameValue store(
            @RequestParam(value="name", required=false, defaultValue="name") String name,
            @RequestParam(value="value", required=false, defaultValue="value") String value) 
    {
		NameValue returnValue = dataSourceService.storeValue(name, value);
    	final String userIpAddress = getCurrentRequest().getRemoteAddr();
    	final String userAgent = getCurrentRequest().getHeader("user-agent");
    	final String userDisplay = String.format("Name/Value:%s/%s,IP:%s Browser:%s", name, value, userIpAddress, userAgent);
    	logger.error(userDisplay);
    	return returnValue;
    }
}
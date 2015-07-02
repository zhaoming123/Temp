package com.demo.test.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.Before;  
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.demo.service.interfaces.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config-test.xml")
@TransactionConfiguration(transactionManager = "h4TManager", defaultRollback = true)
public class commontest extends AbstractJUnit4SpringContextTests {
	
	@Before  
    public void setUp() throws Exception  
    {  
    }  
	
	@Resource
    private IRoleService roleService;
	
	@Resource
    private IAuthorityService authorityService;

	@Test
	public void testChain(){
		
	}	
	@Ignore  
    public void testOtherSpringObject()  
    {  
        
    }  

}


/**
 * 
 */
package com.demo.scripts;

import java.util.Hashtable;



import main.java.com.demo.buisinessLibrary.Demo_Buisiness_Methods;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gallop.utilities.TestUtil;




public class Login_Test extends Demo_Buisiness_Methods {
	
	
	@Test(groups={"smoke"})
	public void Login_Test() throws Throwable {
	
		boolean flag = true;
		Hashtable<String,String> data = TestUtil.getData("Login_Test","WM");
		try{
			
			if(!ExistingUserSignin(data))
				flag=false;
				
		   }catch (Exception e) {
			         e.printStackTrace();
		   }
	}

}

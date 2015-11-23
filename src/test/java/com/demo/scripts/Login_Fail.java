/**
 * 
 */
package com.demo.scripts;

import java.util.Hashtable;



import main.java.com.demo.buisinessLibrary.Demo_Buisiness_Methods;
import org.testng.annotations.Test;

import com.gallop.utilities.TestUtil;




public class Login_Fail extends Demo_Buisiness_Methods {
	

	
	@Test(groups={"smoke"})
	public void Login_Test_fail() throws Throwable {
	
		boolean flag = true;
		Hashtable<String,String> data = TestUtil.getData("Login_Test","WM");
		
			
			if(!ExistingUserSignin_fail(data))
				flag=false;
				
		   
	}
}

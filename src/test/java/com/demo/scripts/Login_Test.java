/**
 * 
 */
package com.demo.scripts;
import java.util.Hashtable;
import main.java.com.demo.buisinessLibrary.Demo_Buisiness_Methods;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import com.gallop.utilities.TestUtil;

public class Login_Test extends Demo_Buisiness_Methods {
	
	@Test(groups={"smoke"})
	public void Import_SampleDB() throws Throwable {
		Hashtable<String,String> data = TestUtil.getData("Login_Test","WM");
		try{
			if(!verifySampleDB(data))
				flag=false;
			
		   }catch (Exception e) {
			     e.printStackTrace();
		   }
	}
}

/**
 * 
 * 
 */
package com.wavemakerstudio.tests;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.studio.utilities.TestUtil;
import com.wavemakerstudio.teststeps.Business_Logic_Methods;

public class Login_Fail_Test extends Business_Logic_Methods {
	@Test(groups={"smoke"})
	public void Login_Test_fail() throws Throwable {
		Hashtable<String,String> data = TestUtil.getData("Login_Test","WM");
		if(!ExistingUserSignin_fail(data))
			flag=false;
		}
}
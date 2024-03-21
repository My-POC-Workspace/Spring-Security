package com.jwt.customexception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class UserNotFound extends Exception{
	
	public UserNotFound(String str) {
		super(str);
	}

}

package es.uc3m.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

/* Simulates DB */

public class DataStore {
	
	// HashMap key = email = BD primary key
	private static Map<String, UserDataModelBean> usersDataMap;
	
	public DataStore() {
		usersDataMap = new HashMap();
	}
	
	public UserDataModelBean getInfo(String email) {
	    return (UserDataModelBean) usersDataMap.get(email);
	}
	
	public void saveInfo(UserDataModelBean newUserInfo) {
	    usersDataMap.put(newUserInfo.getEmail(), newUserInfo);
	}
	
}

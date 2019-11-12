package es.uc3m.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

/* Simulates DB */

public class DataStore {
	
	// HashMap key = email = BD primary key
	private  Map<String, UserDataModelBean> usersDataMap;
	
	public DataStore() {
		usersDataMap = new HashMap<String, UserDataModelBean>();
		usersDataMap.put("a@a.com", new UserDataModelBean("a","b","c","1234","a@a.com","a"));
	}
	
	public UserDataModelBean getInfo(String email) {
	    return (UserDataModelBean) usersDataMap.get(email);
	}
	
	public void saveInfo(UserDataModelBean newUserInfo) {
	    usersDataMap.put(newUserInfo.getEmail(), newUserInfo);
	}
	
	public boolean containsInfo(String email) {
		return usersDataMap.containsKey(email);
	}
	
}
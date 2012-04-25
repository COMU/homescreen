package com.comu.android;

import java.util.LinkedList;

public class SessionEvents {
	
	private static LinkedList<AuthListener> mAuthListeners = new LinkedList<AuthListener>();
	private static LinkedList<LogoutListener> mLogoutListeners = new LinkedList<LogoutListener>();

	public static void addAuthListener(AuthListener listener) {
	    mAuthListeners.add(listener);
	}

	public static void removeAuthListener(AuthListener listener) {
	    mAuthListeners.remove(listener);
	}

	public static void addLogoutListener(LogoutListener listener) {
	    mLogoutListeners.add(listener);
	}
	
	public static void removeLogoutListener(LogoutListener listener) {
	    mLogoutListeners.remove(listener);
	}
	    
	public static void onLoginSuccess() {
	    for (AuthListener listener : mAuthListeners) {
	        listener.onAuthSucceed();
	    }
	}
	    
	    public static void onLoginError(String error) {
	        for (AuthListener listener : mAuthListeners) {
	            listener.onAuthFail(error);
	        }
	    }
	    
	    public static void onLogoutBegin() {
	        for (LogoutListener l : mLogoutListeners) {
	            l.onLogoutBegin();
	        }
	    }
	    
	    public static void onLogoutFinish() {
	        for (LogoutListener l : mLogoutListeners) {
	            l.onLogoutFinish();
	        }   
	    }
	    
	    public static interface AuthListener {

	        public void onAuthSucceed();
	        public void onAuthFail(String error);
	    }
	    
	    public static interface LogoutListener {

	        public void onLogoutBegin();
	        public void onLogoutFinish();
	    }
	    

}

/**
 * 
 */
package me.jmll.model;

import java.io.Serializable;

/**
 * @author josem
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String fullName;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String fullName){
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String toString(){
		return String.format("UserName: %s;Pass: %s;FullName: %s",
				this.getUsername(), 
				this.getPassword(),
				this.getFullName());
	}
}

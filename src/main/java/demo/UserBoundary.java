package demo;

public class UserBoundary {

	private UserName username;
	private String email;
	private String password;
	private String birthday;
	private String role;

	public UserBoundary() {

	}

	public UserBoundary(UserName username, String email,
			String password, String birthday, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserName getUsername() {
		return this.username;
	}

	public void setUsername(UserName username) {
		this.username = username;
	}

}
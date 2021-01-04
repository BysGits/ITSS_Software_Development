package entity.account;


public class Account {

	private int id;
	private String username;
	private String password;
	private String owner;
	private String age;
	private String gender;
	private String cardCode;
	private boolean isAdmin;
	private boolean isUsing;
	
	public Account() {}
	
	public Account(int id, String username, String owner, String age, String gender, String cardCode) {
		this.id = id;
		this.username = username;
		this.owner = owner;
		this.cardCode = cardCode;
		this.gender = gender;
		this.age = age;
	}
	
	// getter and setter
	
	public int getId() {
		return id;
	}
	
	public Account setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getOwner() {
		return owner;
	}

	public Account setOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public String getAge() {
		return age;
	}

	public Account setAge(String age) {
		this.age = age;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public Account setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getCardCode() {
		return cardCode;
	}

	public Account setCardCode(String cardCode) {
		this.cardCode = cardCode;
		return this;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Account setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Account setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isUsing() {
		return isUsing;
	}

	public Account setUsing(boolean isUsing) {
		this.isUsing = isUsing;
		return this;
	}
	
	
	
	
	
	
	
	
	
	
}

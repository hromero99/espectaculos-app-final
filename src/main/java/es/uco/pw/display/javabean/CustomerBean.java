package es.uco.pw.display.javabean;



public class CustomerBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String emailUser;
	private Boolean administrator;
	private String name;
	private String surnames;
	private String nick;
	private String password;
	
	public CustomerBean() {
		
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}
	public Boolean getAdministrator(){
		return this.administrator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

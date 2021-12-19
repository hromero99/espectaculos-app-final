package es.uco.pw.business.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The type User.
 */
public class UserDTO {

    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Surnames.
     */
    private String surnames;
    /**
     * The Nick.
     */
    private String nick;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Administrator.
     */
    private boolean administrator;
    
    private String password;
    
    private java.util.Date creationDate;

    /**
     * Instantiates a new User.
     *
     * @param name          the name
     * @param surnames      the surnames
     * @param nick          the nick
     * @param email         the email
     * @param administrator the administrator
     */
    public UserDTO(int id,String name, String surnames, String nick, String email, boolean administrator){
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.nick = nick;
        this.email = email;
        this.administrator=administrator;
    }
    public UserDTO(int id,String name, String surnames, String nick, String email, boolean administrator, String password){
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.nick = nick;
        this.email = email;
        this.administrator=administrator;
        this.password = password;
    }

    public UserDTO(int id,String name, String surnames, String nick, String email, boolean administrator,String password, java.util.Date creationDate){
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.nick = nick;
        this.email = email;
        this.administrator=administrator;
        this.password = password;
        this.creationDate = creationDate;
    }

    /**
     * Instantiates a new User.
     *
     * @param csv_line the csv line
     */
    public UserDTO(String csv_line){
        String[] data = csv_line.split(",");
        this.name = data[0];
        this.surnames = data[1];
        this.nick = data[2];
        this.email = data[3];
        this.administrator = Boolean.parseBoolean(data[4]);
        this.setPassword(data[5]);
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets nick.
     *
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets surnames.
     *
     * @return the surnames
     */
    public String getSurnames() {
        return surnames;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets nick.
     *
     * @param nick the nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Sets surnames.
     *
     * @param surnames the surnames
     */
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        return String.format("%s,%s,%s,%s,%b,%s,%s", this.name,this.surnames,this.nick,this.email,this.administrator,this.password,dateFormat.format(this.creationDate));
    }

    /**
     * Is administrator boolean.
     *
     * @return the boolean
     */
    public boolean isAdministrator() {return this.administrator;}

    public String getPassword() {return this.password;}
    
    public void setPassword(String password) {this.password = password;}

	public java.util.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
}

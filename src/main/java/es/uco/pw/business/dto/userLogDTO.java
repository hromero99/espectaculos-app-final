package es.uco.pw.business.dto;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class userLogDTO{
	
	private Date loggingDate;
	private int user_id;
	private int id;
	public userLogDTO(int id, int user_id,Date fecha) {
		this.id=id;
		this.user_id=user_id;
		this.loggingDate=fecha;
	
	}
	public void setFecha(String fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
		  this.loggingDate = formatter.parse(fecha);
		} catch (ParseException e) {
		            e.printStackTrace();
        }
		    
	}
	public Date getFecha() {
		return this.loggingDate;
	}
	
	public int getUser() {
		return this.user_id;
	}
	public void setUser(int user) {
		this.user_id = user;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
}
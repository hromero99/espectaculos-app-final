package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import es.uco.pw.business.dto.UserDTO;
import es.uco.pw.business.dto.userLogDTO;
import es.uco.pw.data.common.DBConnection;
import es.uco.pw.data.common.DBUtils;

public class userDAO implements IDAO<UserDTO> {

	private String url;
	private String user;
	private String password;
	private DBUtils query;
	
	public userDAO(String url, String user, String password, java.util.Properties prop) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.query = new DBUtils(prop);
	}
   /**
    * Get the user ID search with user Email
    * @param userEmail The email of the user to search
    * @return UserDto if object exists or null
   */
	
   public UserDTO searchUserByEmail(String userEmail){
        DBConnection dbCon = new DBConnection(this.url, this.user, this.password);
        Connection con = dbCon.getConnection();
        UserDTO user = null;
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("searchUserEmail"));
            stmnt.setString(1,userEmail);
            ResultSet results = stmnt.executeQuery();
            if (results.next()){
                user = this.getById(results.getInt("id"));
                dbCon.closeConnection();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        dbCon.closeConnection();
        return user;
   }

    @Override
    public ArrayList<UserDTO> getAll() {
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();
        try{
            DBConnection connection = new DBConnection(this.url, this.user, this.password);
            Connection con = connection.getConnection();
            Statement statement = con.createStatement();
            ResultSet results = (ResultSet) statement.executeQuery(this.query.getSqlQuery("allUsers"));
            while(results.next()){
                users.add(new UserDTO(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("surnames"),
                        results.getString("username"),
                        results.getString("email"),
                        results.getBoolean("administrator"),
                        results.getString("password"),
                        results.getDate("creation_date")
                ));
            }
            connection.closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserDTO getById(int id) {
       DBConnection DbCon = new DBConnection(this.url, this.user, this.password);
       Connection con = DbCon.getConnection();
       try {
           PreparedStatement statmnt = con.prepareStatement(this.query.getSqlQuery("userQueryId"));
           statmnt.setInt(1,id);
           ResultSet rs = statmnt.executeQuery();
           if (rs.next()){
               return new UserDTO(
                       rs.getInt("id"),
                       rs.getString("name"),
                       rs.getString("surnames"),
                       rs.getString("username"),
                       rs.getString("email"),
                       rs.getBoolean("administrator"),
                       rs.getString("password"),
                       rs.getDate("creation_date")
                       );
           }
       }
       catch (SQLException e){
           e.printStackTrace();
       }
       DbCon.closeConnection();
       return null;
    }

    @Override
    public int create(String objectToSave) {
        UserDTO user = new UserDTO(objectToSave);
        DBConnection DBCon = new DBConnection(this.url, this.user, this.password);
        Connection con = DBCon.getConnection();
        UserDTO userCheck = this.searchUserByEmail(user.getEmail());
        if (userCheck != null) {
        	return -2;
        }
        try {
        	java.util.Date current = new java.util.Date();
        	PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("insertUser"));
            stmnt.setString(1,user.getNick());
            stmnt.setString(2,user.getEmail());
            stmnt.setString(3,user.getName());
            stmnt.setString(4,user.getSurnames());
            stmnt.setString(5, user.getPassword());
            stmnt.setDate(6, new java.sql.Date(current.getTime()));   
            stmnt.executeUpdate();
            UserDTO u =this.searchUserByEmail(user.getEmail());
            return u.getId();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
       
        DBCon.closeConnection();
        return -1;
    }

    @Override
    public int update(int idOriginal, UserDTO newObjectInformation) {
       DBConnection db = new DBConnection(this.url, this.user, this.password);
       Connection con = db.getConnection();
       try {
           PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("updateUser"));
           stmnt.setString(1,newObjectInformation.getNick());
           stmnt.setString(2,newObjectInformation.getEmail());
           stmnt.setString(3,newObjectInformation.getName());
           stmnt.setString(4,newObjectInformation.getSurnames());
           stmnt.setInt(5,idOriginal);
           int status =  stmnt.executeUpdate();
           return  status;
       }
       catch (SQLException e){
           e.printStackTrace();
       }

       return 0;
    }

    @Override
    public boolean delete(int idToDelete) {
        boolean deleted = false;
        DBConnection dbCon = new DBConnection(this.url, this.user, this.password);
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(this.query.getSqlQuery("deleteUserId"));
            st.setInt(1,idToDelete);
            st.executeUpdate();
            deleted = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        dbCon.closeConnection();
        return deleted;
    }

    public ArrayList<userLogDTO> getLogByUser(int user_id){
    	  ArrayList<userLogDTO> list = new ArrayList<userLogDTO>();
          DBConnection dbCon = new DBConnection(this.url, this.user, this.password);
          Connection con = dbCon.getConnection();
          try {
              PreparedStatement st = con.prepareStatement(this.query.getSqlQuery("getUserLog"));
              st.setInt(1,user_id);
              ResultSet rs = st.executeQuery();
              while (rs.next()) {
            	  list.add(new userLogDTO(rs.getInt("id"),rs.getInt("user_id"),rs.getDate("date")));
              }
          }
          catch (SQLException e){
              e.printStackTrace();
          }
          dbCon.closeConnection();
          return list;
    }
    
    public void addLogDate(int user_id) {
    	 DBConnection dbCon = new DBConnection(this.url, this.user, this.password);
         Connection con = dbCon.getConnection();
         try {
        	 java.util.Date utilDate = new java.util.Date();
        	 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             PreparedStatement st = con.prepareStatement(this.query.getSqlQuery("setUserLog"));
             st.setInt(1,user_id);
             st.setDate(2,sqlDate);
             st.executeUpdate();
            
         }
         catch (SQLException e){
             e.printStackTrace();
         }
         dbCon.closeConnection();
    }
    
    public String getLastLog(int user_id) {
    	 String userLastLog = "El usuario no ha accedido";
    	 DBConnection dbCon = new DBConnection(this.url, this.user, this.password);
         Connection con = dbCon.getConnection();
         try {
          	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
             PreparedStatement st = con.prepareStatement(this.query.getSqlQuery("getUserLog"));
             st.setInt(1,user_id);
             ResultSet results = st.executeQuery();
             if (results.next()) {
            	   results.last();
                   return dateFormat.format(results.getDate("date"));
             }
          
                                 
         }
         catch (SQLException e){
             e.printStackTrace();
         }
         dbCon.closeConnection();
         return userLastLog;
    	
    }
}

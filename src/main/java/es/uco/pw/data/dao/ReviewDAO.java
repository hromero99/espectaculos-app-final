package es.uco.pw.data.dao;

import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.data.common.DBUtils;
import es.uco.pw.data.common.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements IDAO<ReviewDTO> {
	private String url;
	private String user;
	private String password;
	private DBUtils query;
	java.util.Properties prop;
	private DBConnection DBConnection;
	
	public ReviewDAO(String url, String user, String password, java.util.Properties prop) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.prop =prop;
		this.query = new DBUtils(this.prop);
		this.DBConnection = new DBConnection(this.url,this.user,this.password);
	}

    @Override
    public ArrayList<ReviewDTO> getAll() {
        Connection con = this.DBConnection.getConnection();
        ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
        try {
            //TODO: Here change to get espectaculo title from table instead return ID
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(this.query.getSqlQuery("getReviews"));
            while(rs.next()){
                reviews.add(new ReviewDTO(
                        rs.getInt("id"),
                        rs.getInt("espectaculo_id"),
                        rs.getString("content"),
                        rs.getInt("user_id")
                ));
            }
            this.DBConnection.closeConnection();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public ReviewDTO getById(int id) {

        Connection con = this.DBConnection.getConnection();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("reviewByID"));
            stmnt.setInt(1,id);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                return new ReviewDTO(
                        rs.getInt("id"),
                        rs.getInt("espectaculo_id"),
                        rs.getString("content"),
                        rs.getInt("user_id")
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(String objectToSave) {
        Connection con = this.DBConnection.getConnection();
        ReviewDTO newReview = new ReviewDTO(objectToSave);
        try{
        	
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("createReview"));
            stmnt.setInt(1,newReview.getCreator());
            stmnt.setInt(2,newReview.getEspectacle());
            stmnt.setString(3,newReview.getText());
            stmnt.executeUpdate();
            // Return the current review created
            for (ReviewDTO it: this.getReviewForUser(newReview.getCreator())) {
            	if (it.getText().equals(newReview.getText()) && it.getEspectacle() == newReview.getEspectacle()) {
            		return it.getId();
            	}
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        this.DBConnection.closeConnection();
        
        return 0;
    }

    @Override
    public int update(int idOriginal, ReviewDTO newObjectInformation) {

        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.getConnection();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("updateReview"));
            stmnt.setString(1,newObjectInformation.getText());
            stmnt.setInt(2,idOriginal);
            stmnt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        dbConnection.closeConnection();
        return 0;
    }

    @Override
    public boolean delete(int idToDelete) {
        Connection con = this.DBConnection.getConnection();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("deleteReview"));
            stmnt.setInt(1,idToDelete);
            stmnt.executeUpdate();
            this.DBConnection.closeConnection();
            return true;
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        this.DBConnection.closeConnection();
        return false;
    }

    public List<ReviewDTO> getReviewForUser(int id) {
        Connection con = this.DBConnection.getConnection();
        List<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("ReviewForUser"));
            stmnt.setInt(1,id);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                reviews.add(new ReviewDTO(
                        rs.getInt("id"),
                        rs.getInt("espectaculo_id"),
                        rs.getString("content"),
                        rs.getInt("user_id")
                        )
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        this.DBConnection.closeConnection();
        return reviews;
    }
    public List<ReviewDTO> getReviewForEspectaculo(int espectaculoID){
        List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
        Connection con = this.DBConnection.getConnection();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("ReviewByEspectaculo"));
            stmnt.setInt(1,espectaculoID);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                reviewList.add(
                        new ReviewDTO(
                                rs.getInt("id"),
                                rs.getInt("espectaculo_id"),
                                rs.getString("content"),
                                rs.getInt("user_id")
                        )
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        this.DBConnection.closeConnection();

        return reviewList;
    }

    public void evaluateReview(int userId, int points, int review_id,int espectaculo_id) {
        Connection con = this.DBConnection.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("reviewEvaluate"));
            stmnt.setInt(1,espectaculo_id);
            stmnt.setInt(2,userId);
            stmnt.setInt(3,points);
            stmnt.setInt(4,review_id);
            stmnt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

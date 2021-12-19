package es.uco.pw.data.dao;

import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.business.dto.PaseEspectaculoDTO;
import es.uco.pw.data.common.DBConnection;
import es.uco.pw.data.common.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EspectaculoDAO implements IDAO<EspectaculoDTO>{
	private String url;
	private String user;
	private String password;
	private DBUtils query;
	private DBConnection DbConnection;
	
	public EspectaculoDAO(String url, String user, String password, java.util.Properties prop) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.query = new DBUtils(prop);
		this.DbConnection = new DBConnection(this.url,this.user,this.password);
	}
	
    public EspectaculoDAO() {
	}

	public EspectaculoDTO searchEspectaculoByTitle(String espectaculoTitle){
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("searchEspectaculoTitle"));
            stmnt.setString(1,espectaculoTitle);
            ResultSet rs = stmnt.executeQuery();
            // Check if the query don't return matches, in that case return empty espectaculo object
            if (!rs.next()){
                return null;
            }
            return new EspectaculoDTO(rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getString("category"),null,rs.getInt("localidades"));

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<EspectaculoDTO> getAll() {
        ArrayList<EspectaculoDTO> espectaulos = new ArrayList<EspectaculoDTO>();
        Connection con = this.DbConnection.getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(this.query.getSqlQuery("allEspectaculos"));
            while (rs.next()){
                espectaulos.add(new EspectaculoDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        null,
                        rs.getInt("localidades")
                ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return espectaulos;
    }

    @Override
    public EspectaculoDTO getById(int id) {
        Connection con = this.DbConnection.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("searchEspectaculoId"));
            stmnt.setInt(1,id);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                return new EspectaculoDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        null,
                        rs.getInt("localidades")
                );
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public EspectaculoDTO getByTitle(String title) {
        EspectaculoDTO espec = null;
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("searchEspectaculoTitle"));
            stmnt.setString(1,title);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                espec = this.getById(rs.getInt("id"));
                dbCon.closeConnection();

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return espec;
    }

    @Override
    public int create(String objectToSave) {
        return 0;
    }

    public int create(EspectaculoDTO objectToSave) {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("createEspectaculo"));
            stmnt.setString(1,objectToSave.getTitulo());
            stmnt.setString(2,objectToSave.getDescripcion());
            stmnt.setString(3,objectToSave.getCategoria());
            stmnt.setInt(4,objectToSave.getLocalidades());
            stmnt.executeUpdate();
            dbCon.closeConnection();
            return this.getByTitle(objectToSave.getTitulo()).getId();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return -1;
            //e.printStackTrace();
        }
    }

    @Override
    public int update(int idOriginal, EspectaculoDTO newObjectInformation) {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("updateEspectaculo"));
            stmnt.setString(1,newObjectInformation.getTitulo());
            stmnt.setString(2,newObjectInformation.getDescripcion());
            stmnt.setString(3,newObjectInformation.getCategoria());
            stmnt.setInt(4,newObjectInformation.getLocalidades());
            stmnt.setInt(5,idOriginal);
            stmnt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean delete(int idToDelete) {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try{
            PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("deletePases"));
            stmnt.setInt(1,idToDelete);
            stmnt.executeUpdate();
            PreparedStatement stmnt2=con.prepareStatement(this.query.getSqlQuery("deleteEspectaculo"));
            stmnt2.setInt(1,idToDelete);
            stmnt2.executeUpdate();
            dbCon.closeConnection();
            return true;
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }

    public int createEspectaculoPase(int IdEspectaculo, PaseEspectaculoDTO pase){
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try (PreparedStatement stmnt = con.prepareStatement(this.query.getSqlQuery("createPaseEspectaculo"))) {
            stmnt.setInt(1,IdEspectaculo);
            stmnt.setDate(2, new java.sql.Date(pase.getFecha().getTime()));
            stmnt.setTime(3, new java.sql.Time(pase.getHora().getTime()));
            stmnt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<EspectaculoDTO> searchEspectaculoByCategory(String espectaculoCategory) {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        List<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
        try {
            String query = this.query.getSqlQuery("getEspectaculoCategory");
            PreparedStatement stmnt = con.prepareStatement(query);
            stmnt.setString(1,espectaculoCategory);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                espectaculos.add(
                        new EspectaculoDTO(
                                rs.getInt("id"),
                            rs.getString("title"), rs.getString("description"),
                                rs.getString("category"),
                                null,
                                rs.getInt("localidades")
                        )
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return espectaculos;
    }

    public List<EspectaculoDTO> searchNextEspectaculo(Optional<String> category){
        List<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        ResultSet rs = null;
        try {
            if (category.isPresent()){
                List<EspectaculoDTO> espectaculosToSearch = this.searchEspectaculoByCategory(String.valueOf(category));
                for (EspectaculoDTO it: espectaculosToSearch){
                    String query = this.query.getSqlQuery("getNextEspectaculoPase");
                    PreparedStatement stmnt = con.prepareStatement(query);
                    stmnt.setInt(1, it.getId());
                    rs = stmnt.executeQuery();
                    if (rs.next()){
                        espectaculos.add(new EspectaculoDTO(it.toCsv()));
                    }
                }
                String query = this.query.getSqlQuery("getNextEspectaculosCategory");
            }else {
                List<EspectaculoDTO> espectaculosToSearch = this.getAll();
                for (EspectaculoDTO it: espectaculosToSearch){
                    String query = this.query.getSqlQuery("getNextEspectaculoPase");
                    PreparedStatement stmnt = con.prepareStatement(query);
                    stmnt.setInt(1, it.getId());
                    rs = stmnt.executeQuery();
                    if (rs.next()){
                        espectaculos.add(new EspectaculoDTO(it.toCsv()));
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        dbCon.closeConnection();
        return espectaculos;
    }
    
    public List<EspectaculoDTO> getEspectaculosCelerbated(){
    	
    	List<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>();
        Connection con = this.DbConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(this.query.getSqlQuery("pasesEspectaculoCelebrated"));
            while(rs.next()){
                // For each pase need to search the espectaculo from id
            	espectaculos.add(this.getById(rs.getInt("espectaculo_id")));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    	return espectaculos;
    }
}


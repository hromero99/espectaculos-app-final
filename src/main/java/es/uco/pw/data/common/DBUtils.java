package es.uco.pw.data.common;

public class DBUtils {

	private java.util.Properties querys;
	public DBUtils (java.util.Properties prop) {
		this.querys=prop;
	}
    public String getSqlQuery(String key){
            try {
                return (String) this.querys.get(key);
            }
            catch (NullPointerException KeyNotFoundError){
                return "null";
            }
       
    }
}

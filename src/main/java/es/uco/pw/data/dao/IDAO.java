package es.uco.pw.data.dao;
import java.util.ArrayList;

public interface IDAO<T> {

    ArrayList<T> getAll();
    T getById(int id);
    int create(String objectToSave);
    int update(int idOriginal, T newObjectInformation);
    boolean delete(int idToDelete);


}

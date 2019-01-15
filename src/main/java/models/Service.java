package models;

import java.util.List;

public interface Service {

    /*
    public Model getById(long id);
    public List<Model> getAll();
    public boolean deleteById(long id);
    public boolean deleteAll();
    public boolean update(Model model);
    public List<Model> getWhere(String attribute, String value);
    */


    public boolean tableExists();
    public boolean createTable();
}

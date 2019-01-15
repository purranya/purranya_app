package application;

import data_util.DatabaseService;
import data_util.HomeFolderService;
import data_util.OperationSystemData;

import java.sql.Connection;

public class Main_dbtests {
    public static void main(String[] args) throws Exception
    {
        DatabaseService dbs = new DatabaseService();
        //tworzenie bazy
        //dbs.createDatabase("testcreate");
        //połączenie
        //Connection c = dbs.getDatabaseConnection("testcreate");
        //c.close();
        //usuwanie
        //dbs.deleteDatabase("testcreate");
    }
}

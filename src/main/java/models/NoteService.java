package models;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NoteService /*implements Service*/{

    private Connection conn;

    PreparedStatement getByIdPstm;

    NoteService(Connection conn)
    {
        this.conn = conn;
    }

    public Model getById(long id)
    {
        return null;
    }

}

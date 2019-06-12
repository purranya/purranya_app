package api_client;

import app.GlobalOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import models.db_models.Calendar;
import models.db_models.Event;
import models.db_models.Label;
import models.db_models.Note;
import models.transfer_models.OperationStatus;
import models.transfer_models.*;

import java.util.HashMap;

public class Server
{

    public static boolean logIn(String username, String password)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);

        String response = null;
        try
        {
            params.put("login",om.writeValueAsString(l));
            response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"/login",params);
        } catch (Exception e)
        {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            return false;
        }

        OperationStatus ls = null;
        try
        {
            ls = om.readValue(response, OperationStatus.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return false;
        }

        return ls.equals(OperationStatus.OPERATION_SUCCESS);
    }

    public static boolean register(String username, String password)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);

        String response = null;
        try
        {
            params.put("login",om.writeValueAsString(l));
            response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"/register",params);
        } catch (Exception e)
        {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            return false;
        }

        OperationStatus answer = null;
        try
        {
            answer = om.readValue(response, OperationStatus.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return false;
        }

        return answer.equals(OperationStatus.OPERATION_SUCCESS);
    }

    public static UserCalendarIndex getCalendarIndex(String username, String password)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);

        String response = null;
        try
        {
            params.put("login",om.writeValueAsString(l));
            response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//calendar/getindex",params);
        } catch (Exception e)
        {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            return null;
        }

        UserCalendarIndex index = null;

        try
        {
            index = om.readValue(response, UserCalendarIndex.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return null;
        }

        return index;
    }

    public static UserCalendar getCalendar(String username, String password ,Calendar calendar)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);

        String response = null;
        try
        {
            params.put("model",om.writeValueAsString(calendar));
            params.put("login",om.writeValueAsString(l));
            response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//calendar/get",params);
        } catch (Exception e)
        {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            return null;
        }

        UserCalendar userCalendar = null;

        try
        {
            userCalendar = om.readValue(response, UserCalendar.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return null;
        }

        return userCalendar;
    }

    public static UserNoteIndex getNoteIndex(String username, String password)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);

        String response = null;
        try
        {
            params.put("login",om.writeValueAsString(l));
            response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//note/getindex",params);
        } catch (Exception e)
        {
            System.err.println("Could not connect to server");
            e.printStackTrace();
            return null;
        }

        UserNoteIndex index = null;

        try
        {
            index = om.readValue(response, UserNoteIndex.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return null;
        }

        return index;
    }

    public static <T> boolean modelAction(String username, String password, T model, ModelAction action)
    {
        GlobalOptions options = new GlobalOptions();
        HashMap<String,String> params = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        Login l = new Login(username,password);
        String response = null;

        if(model instanceof Calendar)
        {
            Calendar calendar = (Calendar)model;
            try
            {
                params.put("login", om.writeValueAsString(l));
                params.put("model", om.writeValueAsString(calendar));
                response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//calendar/" + action.toString(), params);
            } catch (Exception e)
            {
                System.err.println("Could not connect to server");
                e.printStackTrace();
                return false;
            }
        }
        else if(model instanceof Note)
        {
            Note note = (Note)model;
            try
            {
                params.put("login", om.writeValueAsString(l));
                params.put("model", om.writeValueAsString(note));
                response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//note/" + action.toString(), params);
            } catch (Exception e)
            {
                System.err.println("Could not connect to server");
                e.printStackTrace();
                return false;
            }
        }
        else if(model instanceof Event)
        {
            Event event = (Event)model;
            try
            {
                params.put("login", om.writeValueAsString(l));
                params.put("model", om.writeValueAsString(event));
                response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//event/" + action.toString(), params);
            } catch (Exception e)
            {
                System.err.println("Could not connect to server");
                e.printStackTrace();
                return false;
            }
        }
        else if(model instanceof Label)
        {
            Label label = (Label)model;
            try
            {
                params.put("login", om.writeValueAsString(l));
                params.put("model", om.writeValueAsString(label));
                response = new HTTPSClient().post("https://"+ options.get("api.host")+":"+options.get("api.port")+"//label/" + action.toString(), params);
            } catch (Exception e)
            {
                System.err.println("Could not connect to server");
                e.printStackTrace();
                return false;
            }
        }

        OperationStatus answer = null;

        try
        {
            answer = om.readValue(response, OperationStatus.class);
        } catch ( Exception e )
        {
            System.err.println("Server response parsing failed");
            e.printStackTrace();
            return false;
        }

        return answer.equals(OperationStatus.OPERATION_SUCCESS);
    }
}

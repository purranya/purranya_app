package app;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class GlobalOptions {
    HashMap<String,String> options = new HashMap<>();


    public GlobalOptions()
    {
        setDefault();
        try
        {
            load();
        } catch ( IOException e )
        {
            if(e instanceof FileNotFoundException)
                try
                {
                    save();
                } catch (IOException e2)
                {
                    Logging.logError("Cannot save global options\n" + e2.toString());
                }
            else
                System.err.println("Loading global options failed");
        }
    }

    private void setDefault()
    {
        options.put("date.format","yyyy/MM/dd HH:mm:ss");
        options.put("date.format.log","yyyy-MM-dd-HH-mm-ss");

        options.put("stylesheet","dark");

        options.put("logging.file","true");
        options.put("logging.console","true");
    }

    private void load() throws IOException
    {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File f = new HomeFolderManager().getPath("options").resolve("options.cfg").toFile();

        options = om.readValue(f,new TypeReference<HashMap<String,String>>(){});
    }

    private void save() throws IOException
    {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File f = new HomeFolderManager().getPath("options").resolve("options.cfg").toFile();

        om.writeValue(f,options);
    }

    public String get(String option)
    {
        return options.get(option);
    }

    public void set(String option, String value)
    {
        options.put(option,value);
        try
        {
            save();
        } catch (IOException e)
        {
            Logging.logError("Cannot save global options\n" + e.toString());
        }
    }
}

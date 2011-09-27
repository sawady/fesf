package ar.edu.fesf.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.joda.time.DateTime;

public class LibraryConfiguration {

    public static void main(final String[] args) throws IOException, InterruptedException {
        // create and load default properties
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("defaultProperties.properties");
        defaultProps.load(in);
        in.close();

        // create application properties with default
        Properties applicationProps = new Properties(defaultProps);

        // now load properties from last invocation
        in = new FileInputStream("appProperties.properties");
        applicationProps.load(in);
        in.close();

        applicationProps.put("lastRegisteredDate", new DateTime().toString());

        System.out.println(DateTime.parse((String) applicationProps.get("lastRegisteredDate")).toString());

        OutputStream out = new FileOutputStream("appProperties.properties");
        applicationProps.store(out, "estamos haciendo una prueba loco");
        out.close();

    }

}

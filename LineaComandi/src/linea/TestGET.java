package linea;
import java.io.*;
import java.net.*;
/**
 *
 * @author luca
 */
public class TestGET 
{

public TestGET(String QryString)
{
    try 
      {
        URL u = new URL(QryString);
        URLConnection uc = u.openConnection();
      
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);
        int c;
        while ((c = reader.read()) != -1) 
        {
          System.out.print((char) c);
        }
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
}
}

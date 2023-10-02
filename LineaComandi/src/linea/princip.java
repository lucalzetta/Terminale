package linea;
import java.io.*;
import java.net.*;

public class princip 
{
    public static void main(String[] args)throws IOException
    {
            /**
             * Codice di test per il debug dell'applicazione
             */
//            System.out.println("Hello world!");
//            Argos ag = new Argos(args);
            /**
             * Inseriamo del codice per testare un URLConnection
             * Questo tratto è destinato ad una classe specifica per ottenere
             * le pagine grezze dai siti
             */
            
    if  (args.length > 0) 
    {
        
      try 
      {
        // Open the URLConnection for reading
        URL u = new URL(args[0]);
        URLConnection uc = u.openConnection();
        try (InputStream raw = uc.getInputStream()) 
        { // autoclose
          InputStream buffer = new BufferedInputStream(raw);
          // chain the InputStream to a Reader
          Reader reader = new InputStreamReader(buffer);
          int c;
          while ((c = reader.read()) != -1) 
          {
            System.out.print((char) c);
          }
        }
      }
      catch (MalformedURLException ex) 
      {
        System.err.println(args[0] + " is not a parseable URL");
      }
      catch (IOException ex) 
      {
        System.err.println(ex);
      }
    }
  }
  }

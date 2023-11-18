package linea;
import java.io.*;
import java.net.*;

/**
 *
 * @author luca
 * Questa classe si occupa di scaricare le pagine di un sito indicato nella riga di comando
 * dalla sua pagina principale, tipicamente index.html.
 * la pagina viene poi salvata nella directory radice passata come argomento e da lì
 * analizzata alla ricerca dei suoi collegamenti interni per scaricare tutti gli elementi del sito.
 */
public class TestGET 
{
private String SITO;
private String ROOT_DIR;

public TestGET()
{
    try 
      {
        URL u = new URL(SITO);
        URLConnection uc = u.openConnection();
        StringBuilder sb = new StringBuilder();
        SalvaPagine sp = new SalvaPagine(this.ROOT_DIR);
      
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);
        int c;
        while ((c = reader.read()) != -1) 
        {
          sb.append((char)c);
          System.out.print((char) c);
        }
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
}
/**
 * Questo costruttore in realtà è un costruttore di default per testare la possibilità 
 * di scaricare dal sito le sue pagine costruttive, se il test funziona con la pagina
 * passata come argomento dalla linea di comando si può procedere al download completo
 * sfruttando l'impostazione della prima pagina e della root directory di destinazione
 */

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

public void setSite(String site)
{
this.SITO = site;
}

public void setRoot(String root)
{
this.ROOT_DIR = root;
}
}

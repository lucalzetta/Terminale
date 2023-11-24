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
private VariabiliGlobali VG;

public TestGET()
{
    VG = new VariabiliGlobali();
    ROOT_DIR = VG.get_root();
    SITO = VG.get_sito().toString();
}
/**
 * Questo costruttore in realtà è un costruttore di default per testare la possibilità 
 * di scaricare dal sito le sue pagine costruttive, se il test funziona con la pagina
 * passata come argomento dalla linea di comando si può procedere al download completo
 * sfruttando l'impostazione della prima pagina e della root directory di destinazione
 * @param QryString questa variabile può esere impostata manualmente per recuperare una
 * specifica pagina di un sito.
 */

public TestGET(String QryString)
{
    try 
      {
        URL u = VG.get_sito();
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

public String getSite()
{
return VG.get_sito().toString();
}

public String getRoot()
{
return VG.get_root();
}

public void OttieniPagina()
{
    try 
      {
        URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
        StringBuilder sb = new StringBuilder();
        StringBuilder file = new StringBuilder();
        SalvaPagine sp = new SalvaPagine(this.ROOT_DIR);
        ArchivioURLS au = new ArchivioURLS();
        String page = u.getFile();
        String dir = ROOT_DIR;
      System.out.printf("La pagina: %s, verrà salvata in %s%n",page, dir);
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);
        
        /**
         * Questa funzionalità è da migliorare per poterla estendere a tutti 
         * i tipi di file
         */
        
        int c;
        while ((c = reader.read()) != -1) 
        {
          sb.append((char)c);
          //System.out.print((char) c);
        }
        /**
         * Il codice seguente serve a estrarre i riferimenti alle altre pagine del sito
         */
        int i = 0;
        c = 0;
        int f = sb.lastIndexOf("href=\"");
        while (i < f)
            {
                c++;
                i = sb.indexOf("href=\"", i );
                i = i + 6;
                while(sb.charAt(i)!='\"')
                    {
                        file.append(sb.charAt(i));
                        i++;
                    }
                file.append("\n");
                au.set_origin(file);
                au.scrivi_su_file();
                System.out.printf("Trovata l'occorrenza n° %d di <href=\" in posizione: %d\t%s",c,i,file.toString());
                file.delete(0, file.length());
                i++;
            }
         /**
         * Il codice seguente serve a estrarre i riferimenti alle immagini presenti nella pagina
         */
        i = 0;
        c = 0;
        f = sb.lastIndexOf("src=\"");
        while (i < f)
            {
                c++;
                i = sb.indexOf("src=\"", i );
                i = i + 5;
                while((sb.charAt(i)!=';')&((sb.charAt(i)!='?'))&((sb.charAt(i)!='>'))&((sb.charAt(i)!='"')))
                    {
                        file.append(sb.charAt(i));
                        i++;
                    }
                file.append("\n");
                au.set_origin(file);
                au.scrivi_su_file();
                file.delete(0, file.length());
                System.out.printf("Trovata l'occorrenza n° %d di 'src=' in posizione: %d\t%s%n",c,i,file.toString());
                i++;
            }
        
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
}
}

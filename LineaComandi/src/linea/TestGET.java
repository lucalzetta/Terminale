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
private VariabiliGlobali VG = new VariabiliGlobali();

public TestGET()throws IOException
{
    ROOT_DIR = VG.get_root();
    SITO = VG.get_sito().toString();
    //riga di test per provare le nuove classi, decommentare le classi interessate
    TrovaDirs td = new TrovaDirs();
    td.cerca_dire();
}
/**
 * Questo costruttore in realtà è un costruttore di default per testare la possibilità 
 * di scaricare dal sito le sue pagine costruttive, se il test funziona con la pagina
 * passata come argomento dalla linea di comando si può procedere al download completo
 * sfruttando l'impostazione della prima pagina e della root directory di destinazione
 * @param scarica_dati questa variabile può esere impostata manualmente per recuperare una
 * specifica pagina di un sito.
 */

public TestGET(boolean scarica_dati)
{
if(! scarica_dati)
{
    /**
     * Questo if, valuta la richiesta di scaricare i dati e se non è impostata
     * replica il comportamento di default usato come test.
     */
    ROOT_DIR = VG.get_root();
    SITO = VG.get_sito().toString();
    //riga di test per provare le nuove classi, decommentare le classi interessate
    try
        {
            TrovaDirs td = new TrovaDirs();
            td.cerca_dire();
        }
    catch(IOException ioe)
        {
            System.err.printf("Errore nel costruttore con argomenti della classe "
                    + "TestGET(): ", ioe);
        }

}
else
{
    try 
      {
        URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
      
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);
       
        OttieniPagina();
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
} 
}

public void OttieniPagina()throws IOException
{
    try 
      {
        String dir = VG.get_destinazione_files_sito();
        URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
        StringBuilder sb = new StringBuilder();
        StringBuilder file = new StringBuilder();
        ArchivioURLS au = new ArchivioURLS();
        String page = u.getFile();
        //Ci si può trovare nella situazione in cui il nome della pagina da scaricare 
        //non sia ammesso come nome valido, in questo caso verrà convertito in index.html
        switch(page)
            {
            case "/":
                page = "index.html";
            break;    
            }
        SalvaPagine sp = new SalvaPagine(dir, page);
        System.out.printf("La pagina: %s, verrà salvata in %s%n",page, dir);
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);
        
        /**
         * Questa funzionalità è da migliorare per poterla estendere a tutti 
         * i tipi di file
         */
/*********************************************************************************/        
        /**
         * Questo ciclo legge i byte che il server emette per la pagina richiesta.
         */        
        int c;
        while ((c = reader.read()) != -1) 
        {
          sb.append((char)c);
          //System.out.print((char) c);
        }
/*********************************************************************************/        
        /**
         * Il codice seguente serve a estrarre i riferimenti alle altre pagine del sito.
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
                //System.out.printf("Trovata l'occorrenza n° %d di <href=\" in posizione: %d\t%s",c,i,file.toString());
                file.delete(0, file.length());
                i++;
            }
/*********************************************************************************/        
         /**
         * Il codice seguente serve a estrarre i riferimenti alle immagini presenti nella pagina.
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
                //System.out.printf("Trovata l'occorrenza n° %d di 'src=' in posizione: %d\t%s%n",c,i,file.toString());
                i++;
            }
/*********************************************************************************/        
        /**
         * Il codice seguente serve a scrivere la pagina in un file nella directory locale
         * di replica del sito.
         */
        sp.scrivi();
/*********************************************************************************/
        /**
         * Il codice seguente valuta le directory presenti nel file ListaURLS
         * e crea l'albero delle directory nel file system locale.
         */
        //prima ripuliamo i files dai duplicati
        /*System.out.printf("%nControllo passato a PuliziaFiles%n");
        PuliziaFiles pf = new PuliziaFiles();
        pf.del_URLS_doppi();*/
        //commentate le seguenti tre righe a scopo di debug
        System.out.printf("%nControllo passato a TrovaDirs%n");
        TrovaDirs td = new TrovaDirs();
        td.cerca_dire();
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
}
}

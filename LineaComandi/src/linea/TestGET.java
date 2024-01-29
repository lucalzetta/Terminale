package linea;
import java.io.*;
import java.net.*;
//import java.util.Set;

/**
 *
 * @author luca
 * Questa classe si occupa di scaricare le pagine di un sito indicato nella riga di comando
 * dalla sua pagina principale, tipicamente index.html.
 * la pagina viene poi salvata nella directory radice passata come argomento e da lì
 * analizzata alla ricerca dei suoi collegamenti interni per scaricare tutti gli elementi del sito.
 * 08/12/2023 finita una prima fase di crescita complessa e disordinata della classe,
 * ho deciso di limitarla all'ottenimento della pagina richiesta che viene memorizzata
 * in una variabile globale di tipo stringa 'PAGINA'
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
    System.out.printf("Siamo nel costruttore di default della classe TestGET, sono state raccolte le "
            + "varibili ROO_DIR e SITO che valgono: %n%s%n%s", ROOT_DIR, SITO);
    //riga di test per provare le nuove classi, decommentare le classi interessate
    //TrovaDirs td = new TrovaDirs();
    //td.cerca_dire();
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
        /*URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
      
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream theHTML = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(theHTML);*/
       
        OttieniPagina();
       }
      catch (IOException ex) 
       {
            System.err.printf("%s dovuto a un errore della classe TestGet.",ex);
       }
} 
}

private void OttieniPagina()throws IOException
{
    try 
      {
        String dir = VG.get_destinazione_files_sito();
        URL u = VG.get_sito();
        //il metodo get_file dell'oggetto URL non si rivela sempre affidabile
        //perciò ricorriamo ad un ciclo sulla stringa che lo compone
        String page = u.toString();
        String nome = "";
        int stop = page.length();
        int start = page.lastIndexOf("/");
        start = start +1;
        while(start < stop)
            {
                nome = nome + page.charAt(start);
                start++;
            }
        System.out.printf("Nome della pagina da salvare: %s%n", nome);
       //Ci si può trovare nella situazione in cui il nome della pagina da scaricare 
        //non sia ammesso come nome valido, in questo caso verrà convertito in index.html
        switch(nome)
            {
            case "/":
                if (VG.get_conter() == 0)
                    {
                        nome = "index.html";
                        VG.set_conter();
                    }
                else
                    {
                        nome="pagina_nominata_default(" + VG.get_conter() + ").html";
                        VG.set_conter();
                    }
            break;
            case "":
                if (VG.get_conter() == 0)
                    {
                        nome = "index.html";
                        VG.set_conter();
                    }
                else
                    {
                        nome="pagina_nominata_default(" + VG.get_conter() + ").html";
                        VG.set_conter();
                    }
            break;    
            }
        System.out.printf("Nome della pagina da salvare (dopo l'if): %s%n", nome);
        
        SalvaPagine sp = new SalvaPagine(dir, nome);//Questa riga salva la pagina richiesta nel file locale
                                                             //corrispondente
        System.out.printf("La pagina: %s, verrà salvata in %s%n",nome, dir);
        sp.scrivi();
/*********************************************************************************/        

        /**
         * Il codice seguente valuta le directory presenti nella lista LISTA_URLS_LIST
         * e crea l'albero delle directory nel file system locale.
         */
        //System.out.printf("%nControllo passato a TrovaDirs%n");
/*        EstraiLinks el = new EstraiLinks(nome);
        TrovaDirs td = new TrovaDirs();
        td.cerca_dire();*/
       }
      catch (IOException ex) 
       {
            System.err.println(ex);
       }
    finally
        {
            //System.out.printf("Clausola finally del try di TestGet.OttieniPagina()");
        }
}
}

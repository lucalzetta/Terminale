package linea;
import java.io.*;
import java.net.*;
//import java.util.Set;

/**
 *
 * @author luca
 * Questa classe si occupa di scaricare le pagine di un sito indicato nella riga di comando
 * dalla sua pagina principale, tipicamente index.html.
 * la pagina viene poi salvata nella directory passata come argomento e da lì
 * analizzata alla ricerca dei suoi collegamenti interni per scaricare tutti gli elementi del sito.
 * 08/12/2023 finita una prima fase di crescita complessa e disordinata della classe,
 * ho deciso di limitarla all'ottenimento della pagina richiesta che viene memorizzata
 * in una variabile globale di tipo stringa 'PAGINA'
 * 09/02/2024 ulteriore modifica: oltre al salvataggio della pagina come stream di byte
 * la classe si occuperà anche di decidere se analizzare il testo, nel caso di file
 * riconducibili a un flusso testuale o salvare la pagina così com'è nell'eventualità
 * che si tratti di immagini, pdf, o altri tipi di file multimediali.
 */
public class TestGET 
{
private String SITO;
private String ROOT_DIR;
private String SUBDIR;
private String NOME_FILE;
private VariabiliGlobali VG = new VariabiliGlobali();

public TestGET()throws IOException
{
//System.out.printf("%nCLASSE TestGet, costruttore di default.%n");
    ROOT_DIR = VG.get_root();
    SITO = VG.get_sito().toString();
    SUBDIR = VG.get_subdir();
    NOME_FILE = VG.get_name_page();
    System.out.printf("Siamo nel costruttore di default della classe TestGET, sono state raccolte le "
            + "varibili ROO_DIR, SITO, SUBDIR e NOME_FILE che valgono: %n%s%n%s%n%s%n%s", ROOT_DIR, SITO, SUBDIR, NOME_FILE);
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
System.out.printf("%nCLASSE TestGet, costruttore con parametri.%n");
System.out.println();
    for (int i = 0; i < 50; i++)
        System.out.printf("&#$");

    ROOT_DIR = VG.get_root();
    System.out.printf("%nTestGet, valore delle variabili iniziali della classe.%nROOT_DIR: %s", ROOT_DIR);
    
    SITO = VG.get_sito().toString();
        System.out.printf("%nTestGet, valore delle variabili iniziali della classe.%nSITO: %s", SITO);
    
    SUBDIR = VG.get_subdir();
        System.out.printf("%nTestGet, valore delle variabili iniziali della classe.%nSUBDIR: %s", SUBDIR);

    NOME_FILE = VG.get_name_page();
        System.out.printf("%nTestGet, valore delle variabili iniziali della classe.%nNOME_FILE: %s", NOME_FILE);

    System.out.println();
    for (int i = 0; i < 50; i++)
        System.out.printf("&8$");
    System.out.println();

    
if(! scarica_dati)
{
    /**
     * Questo if, valuta la richiesta di scaricare i dati e se non è impostata
     * replica il comportamento di default usato come test.
     */
    //Seguono alcuni output di debug per il controllo delle variabili
    
    
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
            TrovaDirs td = new TrovaDirs();
            td.cerca_dire();
            td.imposta_sito(VG.get_subdir() + NOME_FILE);
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
System.out.printf("%nCLASSE TestGet, metodo OttieniPagina().%n");
    try 
      {
        String dir = VG.get_root();
        URL u = VG.get_sito();
        URL sito_base;
        //il metodo get_file dell'oggetto URL non si rivela sempre affidabile
        //perciò ricorriamo ad un ciclo sulla stringa che lo compone
        String page = u.toString();
        String nome = "";
        String nome_base = "";
        int stop = page.length();
        int start = page.lastIndexOf("/");
        ValutaFile VF;
        start = start +1;
        while(start < stop)
            {
                nome = nome + page.charAt(start);
                start++;
            }
        System.out.printf("Nome della pagina da salvare: %s%n", nome);
        VG.set_name_page(nome);
        
        stop = page.lastIndexOf("/");
        start = 0;
               while(start < stop)
            {
                nome_base = nome_base + page.charAt(start);
                start++;
            }
        System.out.printf("Nome del sito da esplorare: %s%n", nome_base);
        //Si rende necessario depurare il nome del sito dal nome della 
        //pagina per la gestione delle pagine successive
        sito_base = new URL(nome_base);
        VG.set_sito_ridotto(sito_base);
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
                        int cont = VG.get_conter();
                        VG.set_conter();//Il metodo incrementa da sè il valore del contatore
                    }
            break;    
            }
        System.out.printf("Nome della pagina da salvare (dopo l'if): %s%n", nome);
        
        VF = new ValutaFile(nome);
        VG.set_testo(VF.visitabile());
        
        SalvaPagine sp = new SalvaPagine(dir, nome);//Questa riga salva la pagina richiesta nel file locale
                                                             //corrispondente
        System.out.printf("La pagina: %s, verrà salvata in %s%n"
                + "con l'opzione testo = %b%n",nome, dir, VG.get_testo());
        sp.scrivi(VG.get_testo());
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

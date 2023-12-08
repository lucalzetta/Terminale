package linea;
import java.io.*;
import java.net.*;
import java.util.Set;

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
//        URLConnection uc = u.openConnection();
//        StringBuilder sb = new StringBuilder();
//        StringBuilder file = new StringBuilder();
//        StringBuilder riga = new StringBuilder();
//        ArchivioURLS au = new ArchivioURLS();
        String page = u.getFile();
//        Set<String> pagine = VG.get_set_pagina();
//        Set<String> immagini = VG.get_set_immagini();
        //Ci si può trovare nella situazione in cui il nome della pagina da scaricare 
        //non sia ammesso come nome valido, in questo caso verrà convertito in index.html
        switch(page)
            {
            case "/":
                if (VG.get_conter() == 0)
                    {
                        page = "index.html";
                        VG.set_conter();
                    }
                else
                    {
                        page="pagina_nominata_default(" + VG.get_conter() + ").html";
                        VG.set_conter();
                    }
            break;    
            }
        SalvaPagine sp = new SalvaPagine(dir, page);//Questa riga salva la pagina richiesta nel file locale
                                                             //corrispondente
        System.out.printf("La pagina: %s, verrà salvata in %s%n",page, dir);
        sp.scrivi();
/*********************************************************************************/        
        /**
         * Il codice seguente serve a estrarre i riferimenti alle altre pagine del sito.
         */
        int i = 0;
        c = 0;
        int f = sb.lastIndexOf("href=\"");
        file.delete(0, file.length());
        while (i < f)
            {
                c++;
                i = sb.indexOf("href=\"", i );
                i = i + 6;
                riga.delete(0, riga.length());
                while(sb.charAt(i)!='\"')
                    {
                        file.append(sb.charAt(i));
                        riga.append(sb.charAt(i));
                        i++;
                    }
                file.append("\n");
                pagine.add(riga.toString() + "\n");
                au.set_origin(file);
                au.scrivi_su_file();
                //System.out.printf("Trovata l'occorrenza n° %d di <href=\" in posizione: %d\t%s",c,i,file.toString());
                file.delete(0, file.length());
                i++;
            }
        /**righe di debug, commentabili 07/12/2023***********************/
                System.out.printf("%n%nElenco delle pagine presenti nel sito%n%n");
                for(String g : pagine)
                    {
                        System.out.printf("%s", g);
                    }
                /****************************************************************/
/*********************************************************************************/        
         /**
         * Il codice seguente serve a estrarre i riferimenti alle immagini presenti nella pagina.
         */
        i = 0;
        c = 0;
        f = sb.lastIndexOf("src=\"");
        file.delete(0, file.length());
        while ((i <= f) & (f != -1))
            {
                c++;
                i = sb.indexOf("src=\"", i );
                i = i + 5;
                riga.delete(0, riga.length());
                while((sb.charAt(i)!=';')&((sb.charAt(i)!='?'))&((sb.charAt(i)!='>'))&((sb.charAt(i)!='"')))
                    {
                        file.append(sb.charAt(i));
                        riga.append(sb.charAt(i));
                        i++;
                    }
                file.append("\n");
                immagini.add(riga.toString() + "\n");
                au.set_origin(file);
                au.scrivi_su_file();
                file.delete(0, file.length());
                //System.out.printf("Trovata l'occorrenza n° %d di 'src=' in posizione: %d\t%s%n",c,i,file.toString());
                i++;
            }
        /**righe di debug, commentabili 07/12/2023***********************/
                System.out.printf("%n%nElenco delle immagini presenti nel sito%n%n");
                for(String g : immagini)
                    {
                        System.out.printf("%s", g);
                    }
                /****************************************************************/
        /**
         * Il codice seguente valuta le directory presenti nella lista LISTA_URLS_LIST
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

package linea;

/**
 *
 * @author luca
 * Questa classe estrae tutti i collegamenti a cui fa riferimento la pagina e li
 * archivia in un TreeSet.
 */
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class EstraiLinks 
{
private final String PAGINA;
private final VariabiliGlobali VG = new VariabiliGlobali();
private final CheckerVariabili CV = new CheckerVariabili();
private final ErrorsClasse EC = new ErrorsClasse();
private Set<String> SET_LINKS;
private Set<String> SET_SITI;
private Set<String> SET_PG;
private Set<String> SET_IMG;
private File LOG_F;
private FileOutputStream FOUT;
private static String MSG;

public EstraiLinks(String pagina)
{
//System.out.printf("%nCLASSE EstraiLinks, costruttore di default.%n");
PAGINA = pagina;
LOG_F = VG.get_file_LOG();
MSG = "";
try
    {
        FOUT = new FileOutputStream(LOG_F, true);
    }
catch(FileNotFoundException fnf)
    {
        MSG = "\nClasse EstraiLinks, costruttore con parametri, "
                + "si è verificato un errore nella generazione del file "
                + fnf + "\n";
        EC.set_msg(MSG);
        EC.scrivi_su_file();
    }

}

public void links (boolean testo)
{
//System.out.printf("%nCLASSE EstraiLinks, metodo links().%n");    
    /**
     * È possibile passare stringhe diverse per implementare i possibili
     * link presenti nelle pagie html, xml, xhtml e così via
     */
    if( ! testo)
        {
            //System.out.printf("%nIl file %s non è un file di testo, verrà salvato"
                    //+ " senza ulteriori analisi%n", VG.get_name_page());
        }
    else
        {
            int i = 0;
            Set <String> global_links = VG.get_set_collegamenti();
            Set <String> global_www = VG.get_set_sitiext();
            Set <String> global_pag = VG.get_set_pagina();
            Set <String> global_img = VG.get_set_immagini();
            
            global_links.addAll(ciclo("href=", SET_LINKS));
            global_pag.addAll(ciclo("href=", SET_LINKS));
            
            global_links.addAll(ciclo("src=", SET_IMG));
            global_img.addAll(ciclo("src=", SET_IMG));

            global_www.addAll(ciclo("http:", this.SET_SITI));
            global_www.addAll(ciclo("https:", this.SET_SITI));
            global_www.addAll(ciclo("www:", this.SET_SITI));

        }
}

private Set<String> ciclo(String par, Set<String> set)
{
//System.out.printf("%nCLASSE EstraiLinks, metodo ciclo().%n");    
/**
 * 14/12/2023
 * Questo ciclo generalizza la ricerca delle righe contenenti i parametri indicati
 * dalla stringa 'par'.
 * Tutto verrà aggiunto a global_links, dopo di che penseremo a separare le immagini
 * dagli altri tipi di file.
 */
 SET_LINKS = set;
        SET_LINKS = new TreeSet<>();
        int i = 0;
        int c = 0;
        int old_ref = 0;
        int f = PAGINA.length();
        byte[] b_msg;
        String riga;
        String msg = "";
        /**
         * Con un primo ciclo estraiamo i riferimenti degli attributi 'href'
         */
        while (i < f)
            {
                c++;
                i = PAGINA.indexOf(par, i );
                //System.out.printf("Trovata l'occorrenza n° %d di '%s' in posizione: %d\t%s%n",c,par,i,"Primo conrtrollo di TEST");
                if(i <= old_ref)
                    {
                        break;
                    }
                else
                    {   
                        i = i + par.length();
                        riga = "";
                        /**
                         * È necessario escludere il primo carattere virgolette doppie dalla 
                         * specificazione del parametro visto che html lascia la libertà di 
                         * usarle come pure di non usarle.
                         */
                        if (PAGINA.charAt(i) == '\"')
                            {
                                i = i + 1;
                            }
                        /**
                         * 13/12/2023 È necessario estendere i caratteri di fine espressione per comprendere
                         * tutti i casi possibili. 
                         */
                        
                        while((PAGINA.charAt(i)!=';')&((PAGINA.charAt(i)!='?'))&((PAGINA.charAt(i)!='>'))
                             &((PAGINA.charAt(i)!='\"'))&((PAGINA.charAt(i)!='&')) &((PAGINA.charAt(i)!='\\')))
                            {
                                riga = riga + PAGINA.charAt(i);
                                i++;
                            }
                        //riga A QUESTO PUNTO VA DEPURATA di alcuni caratteri non ammessi
                        riga = pulisci_link(riga);
                        //e se non è vuota o nulla la aggiungiamo al set 
                        //delle pagine del sito
                        if((riga != null) & (riga != ""))
                            {
                                SET_LINKS.add(riga);
                            }
                        msg = "Trovata l'occorrenza n° " + c + 
                                " di " + par + " in posizione: " + i + " nel file " + VG.get_name_page() + "\n";
                        b_msg = msg.getBytes();
                        try
                            {
                                FOUT.write(b_msg);
                            }
                        catch(IOException ioe)
                            {
                                MSG = "\nClasse EstraiLinks, metodo ciclo(), "
                                + "si è verificato un errore nella scrittura del file"
                                + " di Log."
                                + ioe + "\n";
                                EC.set_msg(MSG);
                                EC.scrivi_su_file();
                            }

                        //System.out.printf("Trovata l'occorrenza n° %d di '%s' in posizione: %d\t%s%n",c,par, i,riga);
                        i++;
                        old_ref = PAGINA.indexOf(par, 0 );//segna la prima occorrenza della 
                                                                      //stringa ricercata per bloccare
                                                                      //la ripetizione della ricerca dopo
                                                                      //la fine del file
                    }
            }
        /**righe di debug, commentabili 07/12/2023***********************/
return SET_LINKS;        
}

private String pulisci_link(String link)
{
String new_link = link;
String par;
int start;
//eliminiamo le nidificazioni dlle cartelle fino al sesto livello
//questo codice si può irrobustire con un loop su un char array
// di '.', '.', '/'
par = "../";

if(link.startsWith(par))
    {
        new_link = link.substring(3);
    }

par = "../../";

if(link.startsWith(par))
    {
        new_link = link.substring(6);
    }

par = "../../../";

if(link.startsWith(par))
    {
        new_link = link.substring(9);
    }

par = "../../../../";

if(link.startsWith(par))
    {
        new_link = link.substring(12);
    }

par = "../../../../../";

if(link.startsWith(par))
    {
        new_link = link.substring(15);
    }

par = "../../../../../../";

if(link.startsWith(par))
    {
        new_link = link.substring(16);
    }

par = "//";

if(link.startsWith(par))
    {
        new_link = link.substring(2);
    }

par = "/";

if(link.startsWith(par))
    {
        new_link = link.substring(1);
    }

return new_link;
}

public void filtra_links()
{
/**
 * 08/03/2024
 * Questo metodo si occupa di estrarre dalla lista dei links quelli consideraati
 * inutili e i riferimenti a pagine esterne al sito.
 * Questi link non verranno eliminati ma riposti in liste alternative
 */

Set <String> global_links = VG.get_set_collegamenti();
Set <String> global_www = VG.get_set_sitiext();
Set <String> global_pag = VG.get_set_pagina();
Set <String> global_pag_scart = VG.get_set_pagine_scartate();
Set <String> global_img = VG.get_set_immagini();
Iterator i = global_links.iterator();
String riga = "";
String cerca = "";
CharSequence ch_cerca;
int start = 0;

while (i.hasNext())
    {
        riga = i.next().toString();
        
        cerca = "javascript";
        
        if(riga.contains(cerca))
            {
                global_pag_scart.add(riga);
                i.remove();
                if(i.hasNext())
                    {
                        i.next();
                    }
            }
        
        cerca = "phpThumb";
        
        if(riga.contains(cerca))
            {
                global_pag_scart.add(riga);
                i.remove();
            }                        

        cerca = "http";
        
        if(riga.contains(cerca))
            {
                global_www.add(riga);
                i.remove();
            }
        
        if(riga == "")
            {
                System.out.printf("%nTrovata una stringa vuota nella lista dei "
                        + "collegamenti.%n");
                i.remove();
            }
        
        if(riga == null)
            {
                System.out.printf("%nTrovata un valore null nella lista dei "
                        + "collegamenti.%n");
                i.remove();
            }
        
        start = riga.lastIndexOf(".");
        
        if ( start != -1)
            {
                cerca = riga.substring(start, riga.length());
                switch (cerca)
                    {
                        case ".jpg":
                        global_img.add(riga);
                        break;
            
                        case ".jpeg":
                        global_img.add(riga);
                        break;

                        case ".png":
                        global_img.add(riga);
                        break;

                    }
            }
        
    }
}

}

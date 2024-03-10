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

public EstraiLinks(String pagina)
{
//System.out.printf("%nCLASSE EstraiLinks, costruttore di default.%n");
PAGINA = pagina;

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
            global_pag.addAll(SET_LINKS);
            
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
        //int f = PAGINA.lastIndexOf("href=\"");
                int f = PAGINA.length();
        String riga;
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
                             &((PAGINA.charAt(i)!='\"'))&((PAGINA.charAt(i)!='&')))
                            {
                                riga = riga + PAGINA.charAt(i);
                                i++;
                            }
                        //riga A QUESTO PUNTO VA DEPURATA di alcuni caratteri non ammessi
                        riga = pulisci_link(riga);
                        
                        SET_LINKS.add(riga);
                        
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
Set <String> global_img = VG.get_set_immagini();
Iterator i = global_links.iterator();
String riga = "";

while (i.hasNext())
    {
        riga = i.next().toString();
        switch (riga)
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

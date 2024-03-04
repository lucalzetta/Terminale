package linea;

/**
 *
 * @author luca
 * Questa classe estrae tutti i collegamenti a cui fa riferimento la pagina e li
 * archivia in un TreeSet.
 */
import java.util.Set;
import java.util.TreeSet;

public class EstraiLinks 
{
private final String PAGINA;
private final VariabiliGlobali VG = new VariabiliGlobali();
private Set<String> SET_LINKS;

public EstraiLinks(String pagina)
{
//System.out.printf("%nCLASSE EstraiLinks, costruttore di default.%n");
PAGINA = pagina;
SET_LINKS = new TreeSet<>();
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
            ciclo("href=");
            ciclo("src=");
            //i cicli seguenti i n realtà sono eliminabili ma temporaneamente li
            //lasciamo funzionare a scopo di test
            //ciclo("http:");
            //ciclo("https:");
            //ciclo("www.");
            global_links.addAll(SET_LINKS);
            System.out.printf("%n%nElenco dei link presenti nel sito%n%n");
                for(String g : global_links)
                    {
                        System.out.printf("%s%n", g);
                        i++;
                        //System.out.printf(" %n%d ", i);
                    }
            System.out.printf("%n%40s%n", "£");
        }
}

private void ciclo(String par)
{
//System.out.printf("%nCLASSE EstraiLinks, metodo ciclo().%n");    
/**
 * 14/12/2023
 * Questo ciclo generalizza la ricerca delle righe contenenti i parametri indicati
 * dalla stringa 'par'.
 * Tutto verrà aggiunto a global_links, dopo di che penseremo a separare le immagini
 * dagli altri tipi di file.
 */
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
                        
                        while((PAGINA.charAt(i)!=';')&((PAGINA.charAt(i)!='?'))&((PAGINA.charAt(i)!='>'))&((PAGINA.charAt(i)!='\"')))
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
 
}

private String pulisci_link(String link)
{
String new_link = link;
String par;
int start;

par = "../";

if(link.startsWith(par))
    {
        new_link = link.substring(3);
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
}

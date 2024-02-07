package linea;

/**
 *
 * @author luca
 * Questa classe estrae tutti i collegamenti a cui fa riferimento la pagina e li
 * archivia in u TreeSet.
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
PAGINA = pagina;
SET_LINKS = new TreeSet<>();
}

public void links ()
{
    /**
     * È possibile passare stringhe diverse per implementare i possibili
     * link presenti nelle pagie html, xml, xhtml e così via
     */
    int i = 0;
    Set <String> global_links = VG.get_set_collegamenti();
    
    ciclo("href=");
    ciclo("src=");
    ciclo("http:");
    ciclo("https:");
    System.out.printf("%n%nElenco dei link presenti nel sito%n%n");
        for(String g : SET_LINKS)
            {
                System.out.printf("%s%n", g);
                i++;
                //System.out.printf(" %n%d ", i);
            }
        
    global_links.addAll(SET_LINKS);
    
    System.out.printf("%n%nElenco dei link presenti nel set LISTA_URLS_SET delle variabiliGlobali "
            + "dopo il passaggio in EstraiLinks%n%n");
        for(String g : VG.get_set_collegamenti())
            {
                System.out.printf("%s%n", g);
                i++;
                //System.out.printf(" %n%d ", i);
            }
}

private void ciclo(String par)
{
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
                        SET_LINKS.add(riga);
                        System.out.printf("Trovata l'occorrenza n° %d di '%s' in posizione: %d\t%s%n",c,par, i,riga);
                        i++;
                        old_ref = PAGINA.indexOf(par, 0 );//segna la prima occorrenza della 
                                                                      //stringa ricercata per bloccare
                                                                      //la ripetizione della ricerca dopo
                                                                      //la fine del file
                    }
            }
        /**righe di debug, commentabili 07/12/2023***********************/
 
}
}

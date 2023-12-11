/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

/**
 *
 * @author luca
 * Questa classe estrae tutti i collegamenti a cui fa riferimento la pagina e li
 * archivia in u TreeSet<>.
 */
import java.util.Set;
import java.util.TreeSet;

public class EstraiLinks 
{
private final String PAGINA;
private final VariabiliGlobali VG = new VariabiliGlobali();
    
public EstraiLinks(String pagina)
{
PAGINA = pagina;
}

public void links ()
{
        /**
         * Il codice seguente serve a estrarre i riferimenti alle altre pagine del sito.
         * dopo di che li aggiunge al set<> globale
         */
        int i = 0;
        int c = 0;
        int f = PAGINA.lastIndexOf("href=\"");
        String riga;
        Set <String> global_links = VG.get_set_collegamenti();
        Set <String> global_img = VG.get_set_immagini();
        Set <String> set_page = new TreeSet<>();
        /**
         * Con un primo ciclo estraiamo i riferimenti degli attributi 'href'
         */
        while (i < f)
            {
                c++;
                i = PAGINA.indexOf("href=\"", i );
                i = i + 6;
                riga = "";
                while(PAGINA.charAt(i)!='\"')
                    {
                        riga = riga + (char)i;
                        i++;
                    }
                set_page.add(riga);
                //System.out.printf("Trovata l'occorrenza n° %d di <href=\" in posizione: %d\t%s",c,i,file.toString());
                i++;
            }
        /**righe di debug, commentabili 07/12/2023***********************/
                System.out.printf("%n%nElenco delle pagine presenti nel sito%n%n");
                for(String g : set_page)
                    {
                        System.out.printf("%s", g);
                    }
                /****************************************************************/
global_links.addAll(set_page);   
set_page.clear();
        /**
         * Facciamo seguire un loop separato per l'estrazione degli attributi 'src' che 
         * si suppone puntino alle immagini, possono seguire altri loop per ricerche
         * specifiche di altri attributi
         */ 
/*********************************************************************************/        
         /**
         * Il codice seguente serve a estrarre i riferimenti alle immagini presenti nella pagina.
         */
        i = 0;
        c = 0;
        f = PAGINA.lastIndexOf("src=\"");
        
        while ((i <= f) & (f != -1))
            {
                c++;
                i = PAGINA.indexOf("src=\"", i );
                i = i + 5;
                riga = "";
                while((PAGINA.charAt(i)!=';')&((PAGINA.charAt(i)!='?'))&((PAGINA.charAt(i)!='>'))&((PAGINA.charAt(i)!='"')))
                    {
                        riga = riga + (PAGINA.charAt(i));
                        i++;
                    }
                set_page.add(riga);
                //System.out.printf("Trovata l'occorrenza n° %d di 'src=' in posizione: %d\t%s%n",c,i,file.toString());
                i++;
            }
        /**righe di debug, commentabili 07/12/2023***********************/
                System.out.printf("%n%nElenco delle immagini presenti nel sito%n%n");
                for(String g : set_page)
                    {
                        System.out.printf("%s", g);
                    } 
global_img.addAll(set_page);
global_links.addAll(set_page);   
set_page.clear();

}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

/**
 *
 * @author lucaamministratore
 * Questa classe si occuperà di ciclare attraverso le altre classi 
 * fino a che non verranno esauriti tutti i link di tutte le pagine
 * Dovrà implementare i thread per la programmazione concorrente 
 * e offrire la possibilità di scaricare più siti simultaneamente.
 */
import java.io.IOException;
import java.util.Set;
import java.util.Iterator;

public class Loopper
{
private final VariabiliGlobali VG;
private Set<String> SET_LINKS_VISITATI;
private Set<String> SET_LINKS;
private static String STRINGA = "";
private static int INT = 0;
//private final PrimoStep PS;

public Loopper()throws IOException
{
VG = new VariabiliGlobali();
SET_LINKS_VISITATI = VG.get_set_scaricati();
SET_LINKS = VG.get_set_collegamenti();

passo();
risultati();//linea di debug per la visualizzazione a console dei collegamenti
cicloSito();
risultati();
/**
 * A questo punto la pagina è salvata, la lista dei collegamenti è aggiornata
 * resta da caricare il link tra quelli scaricati, fare il confronto con quelli 
 * da scaricare, impostare le variabili per il file successivo e ricominciare.
 * Quando il confronto fra la lista degli urls e quella degli urls scaricati
 * dà esito di eguaglianza il ciclo è finito.
 */

}

private void cicloSito()
{
boolean interr = false;
while(! interr)
    {
        passo();
        if(VG.get_set_collegamenti().equals(VG.get_set_scaricati()))interr = true;
    }

risultati();//linea di debug per la visualizzazione a console dei collegamenti

}

private void passo()
{
TestGET TG = new TestGET(true);
EstraiLinks el = new EstraiLinks(VG.get_page());
el.links();
STRINGA = VG.get_root() + "/" + VG.get_name_page();

System.out.printf("%nClasse Loopper metodo passo().%nAggiunta "
        + "della pagina visitata: %s.%n" , STRINGA);

SET_LINKS_VISITATI.add(STRINGA);
SET_LINKS.add(STRINGA);


//cicloSito();
/**
 * A questo punto la pagina è salvata, la lista dei collegamenti è aggiornata
 * resta da caricare il link tra quelli scaricati, fare il confronto con quelli 
 * da scaricare, impostare le variabili per il file successivo e ricominciare.
 * Quando il confronto fra la lista degli urls e quella degli urls scaricati
 * dà esito di eguaglianza il ciclo è finito.
 */
//impostazione nuova pagina

}

private void risultati()
{
/**
 * Questo è un metodo di debug che visualizza il contenuto dei set<>
 * di archiviazione dei link che verranno salvati sucessivamente nei 
 * file di log del programma
 */
    
//stampiamo un test dei set<> globali contenenti i link da visualizzare a console
System.out.printf("%n========================================================%n"
        + "Lista dei collegamenti presenti nel sito\tLista dei collegamenti visitati%n");
STRINGA = "";
String spazi = "";
//Iterator it = SET_LINKS_VISITATI.iterator();
Iterator it = VG.get_set_scaricati().iterator();
//for (String st : SET_LINKS)
for (String st : VG.get_set_collegamenti())
    {
        INT = 40 - st.length();
        if (40 > 0)
            {
                while (INT > 0)
                    {
                    spazi = spazi + " ";
                    INT--;
                    }
            }
        STRINGA = STRINGA + st + spazi + "\t";
        spazi = "";
        if(it.hasNext())
            {
                STRINGA = STRINGA + it.next() + "\n";
            }
        else
            {
                STRINGA = STRINGA + "\n";
            }
    }
System.out.printf("%s", STRINGA);
}
}

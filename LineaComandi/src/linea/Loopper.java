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
import java.io.File;
import java.util.Set;
import java.util.Iterator;
import java.net.URL;
import java.net.MalformedURLException;

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
//risultati();//linea di debug per la visualizzazione a console dei collegamenti
cicloSito();
risultati();//linea di debug per la visualizzazione a console dei collegamenti
/**
 * A questo punto la pagina è salvata, la lista dei collegamenti è aggiornata
 * resta da caricare il link tra quelli scaricati, fare il confronto con quelli 
 * da scaricare, impostare le variabili per il file successivo e ricominciare.
 * Quando il confronto fra la lista degli urls e quella degli urls scaricati
 * dà esito di eguaglianza il ciclo è finito.
 */

}

private void cicloSito()throws IOException
{
boolean interr = false;
int control = 0;
//while(! interr)//riga per l'uso normale del programma
while (control < 2)//a scopo di debug limitiamo il numero di cicli
    {
        passo();
        if(VG.get_set_collegamenti().equals(VG.get_set_scaricati()))interr = true;
        control ++;
    }

risultati();//linea di debug per la visualizzazione a console dei collegamenti

}

private void passo()throws IOException
{
VG.set_root(VG.get_subdir());
TestGET TG = new TestGET(true);
EstraiLinks el = new EstraiLinks(VG.get_page());
el.links(VG.get_testo());

if(VG.get_testo())
    {
        System.out.printf("%nClasse Loopper metodo passo().%nTesto "
        + "della pagina visitata:%n%s%n" , VG.get_page());
    }

//STRINGA = STRINGA + VG.get_root();
//STRINGA = STRINGA + "/";
STRINGA = STRINGA + VG.get_name_page();

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
nuova_pagina();
}

private void nuova_pagina()
{
/**
 * Questo metodo trova una nuova pagina da visitare tra i link presenti nel sito 
 * per ripetere a ciclo le operazioni fatte con la precedente
 */
Iterator i_links = VG.get_set_collegamenti().iterator();
Iterator i_scaricati = VG.get_set_scaricati().iterator();
boolean t = false;
boolean u = false;
String to_visit = "";
String visitato = "";
String tmp = "";
String n_file = "";
String sub_dir = "/";
int contatore_e = 0;
int contatore_i = 0;
URL perc;
URL nuovo_perc;
File page;

while ((! t) & (i_scaricati.hasNext()))
    {
        visitato = i_scaricati.next().toString();
        while((! u) & (i_links.hasNext()))
            {
                to_visit = i_links.next().toString();
                if(visitato == to_visit)
                    {
                        
                    }
                else
                    {
                        u = true;
                    }
                contatore_i ++;
                System.out.printf("%nCiclo %d interno, classe Loopper metodo nuova_pagina. "
                        + "Valore di pagina %s%n", contatore_i, to_visit);
            }
        contatore_e ++;
        System.out.printf("%n\tCiclo %d esterno, classe Loopper metodo nuova_pagina."
                + " Valore di pagina %s%n", contatore_e, visitato);
    }


try
    {
        perc = new URL(VG.get_sito_ridotto().toString());
        tmp = tmp + perc.getProtocol();
        tmp = tmp + "://";
        tmp = tmp + perc.getAuthority();
        tmp = tmp + "/";
        tmp = tmp + to_visit;
        nuovo_perc = new URL(tmp);
        contatore_e = to_visit.lastIndexOf("/");
        contatore_i = 0;
        
        while(contatore_i < contatore_e)
            {
                sub_dir = sub_dir + to_visit.charAt(contatore_i);
                contatore_i ++;
            }
        sub_dir = sub_dir +"/";
        contatore_i = to_visit.length();
        contatore_e ++;
        
        while(contatore_e < contatore_i)
            {
                n_file = n_file + to_visit.charAt(contatore_e);
                contatore_e ++;
            }
       
        VG.set_subdir(VG.get_root() + sub_dir);
        VG.set_name_page(n_file);
        VG.set_sito(nuovo_perc);
        
        System.out.printf("Prossima pagina da visitare: %s%nclasse Loopper metodo nuova_pagina.", tmp);
        System.out.printf("Scaricheremo il file %s%nNella directory %s%n", n_file, VG.get_subdir());
    }
catch(MalformedURLException mue)
    {
        System.err.printf("Il percorso %s non è valido, si è verificato un errore: %s%n", to_visit, mue);
    }
catch( IOException IOE)
    {
        System.err.printf("Il file %s non è valido, si è verificato un errore: %s%n", to_visit, IOE);
    }
}

private void risultati()
{
/**
 * Questo è un metodo di debug che visualizza il contenuto dei set<>
 * di archiviazione dei link che verranno salvati sucessivamente nei 
 * file di log del programma
 */
    
//stampiamo un test dei set<> globali contenenti i link da visualizzare a console
System.out.printf("%n=============================================================================================%n"
                  + "Lista dei collegamenti presenti nel sito                      Lista dei collegamenti visitati%n");
STRINGA = "";
String spazi = "";
String sub = "";
//Iterator it = SET_LINKS_VISITATI.iterator();
Iterator it = VG.get_set_scaricati().iterator();
//for (String st : SET_LINKS)
for (String st : VG.get_set_collegamenti())
    {
        INT = 60 - st.length();
        if (INT > 0)
            {
                while (INT > 0)
                    {
                    spazi = spazi + " ";
                    INT--;
                    }
            }
        STRINGA = STRINGA + st;
        STRINGA = STRINGA + spazi;
        STRINGA = STRINGA + "\t";
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

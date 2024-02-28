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
private static CheckerVariabili CV = new CheckerVariabili();
//private final PrimoStep PS;

public Loopper()throws IOException
{
//System.out.printf("%nCLASSE Loopper, costruttore di default.%n");
VG = new VariabiliGlobali();
SET_LINKS_VISITATI = VG.get_set_scaricati();
SET_LINKS = VG.get_set_collegamenti();

passo();
//risultati();//linea di debug per la visualizzazione a console dei collegamenti
cicloSito();
//risultati();//linea di debug per la visualizzazione a console dei collegamenti
/**
 * A questo punto la pagina è salvata, la lista dei collegamenti è aggiornata
 * resta da caricare il link tra quelli scaricati, fare il confronto con quelli 
 * da scaricare, impostare le variabili per il file successivo e ricominciare.
 * Quando il confronto fra la lista degli urls e quella degli urls scaricati
 * dà esito di eguaglianza il ciclo è finito.
 */
/**
 */

}

private void cicloSito()throws IOException
{
//System.out.printf("%nCLASSE Loopper, metodo cicloSito().%n");
boolean interr = false;
int control = 0;
while(! interr)//riga per l'uso normale del programma
//while (control < 4)//a scopo di debug limitiamo il numero di cicli
    {   
        /**
         * Area di azzeramento delle variabili, finito il primo passo,
         * invocato dal costruttore, è necessario resettare alcune variabili
         * globali per reimpostarle a valori coerenti con il ciclo
         * successivo. 17/02/2024
         * 
         */
        
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        System.out.printf("%nArea di azzeramento delle variabili.%n%n");

        for (int i = 0; i < 90; i++)
            {
                System.out.printf("I");
            }
        System.out.println();
        STRINGA = "";
        VG.set_page("");
        if(VG.get_pagina_builder() != null)
            {
                VG.set_page_builder(VG.get_pagina_builder().delete(0, VG.get_pagina_builder().length()));
            }
        //VG.set_name_page("");
/*        CV.get_NOME_PAGINA();
        CV.get_ROOT_DEST();
        CV.get_CARTELLA_SITO();
        CV.get_CONTATORE();
        CV.get_SUBDIR();*/
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII        
        passo();
        risultati();
        
        if(VG.get_set_collegamenti().equals(VG.get_set_scaricati()))
            {
                System.out.printf("Lavoro ultimato! Tutti i links sono stati visitati e scaricati.");
                interr = true;
            }
        
        System.out.printf("%nClasse Loopper, metodo ciclo, "
                + "variabile di interruzione del loop: "
                + "%s%nContatore di cicli: %d%n", interr, control);
        control ++;
    }

risultati();//linea di debug per la visualizzazione a console dei collegamenti

}

private void passo()throws IOException
{
//System.out.printf("%nCLASSE Loopper, metodo passo().%n");
//VG.set_root(VG.get_subdir());
System.out.printf("%nClasse Loopper, metodo passo,%n"
        + "valore della root passato a TestGet: %s%n"
        + "valore di nome pagina al momento della chiamata di TestGET: %s%n"
        , VG.get_root(), VG.get_name_page());
CV.get_URL_SITO();
TestGET TG = new TestGET(true);
//TrovaDirs TD = new TrovaDirs();
EstraiLinks el = new EstraiLinks(VG.get_page());
el.links(VG.get_testo());
//TD.cerca_dire();//già chiamato da TestGet
//TD.imposta_sito(VG.get_subdir() + VG.get_name_page());
}

private void risultati()
{
System.out.printf("%nCLASSE Loopper, metodo risultati().%n");
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

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

//while(! interr)//riga per l'uso normale del programma
while (control < 200)//a scopo di debug limitiamo il numero di cicli
    {   
        /**
         * Area di azzeramento delle variabili, finito il primo passo,
         * invocato dal costruttore, è necessario resettare alcune variabili
         * globali per reimpostarle a valori coerenti con il ciclo
         * successivo. 17/02/2024
         * 
         */
        
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
        /*System.out.printf("%nArea di azzeramento delle variabili.%n%n");

        for (int i = 0; i < 90; i++)
            {
                System.out.printf("I");
            }
        System.out.println();*/
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
        //risultati();
        
        if(VG.get_set_collegamenti().equals(VG.get_set_scaricati()))
            {
                System.out.printf("Lavoro ultimato! Tutti i links sono stati visitati e scaricati.");
                interr = true;
            }
        else
            {
                int coll = SET_LINKS.size();
                int visit = SET_LINKS_VISITATI.size();
                System.out.printf("Links presenti: %d%nLinks visitati %d%n", coll, visit);            
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
/*System.out.printf("%nClasse Loopper, metodo passo,%n"
        + "valore della root passato a TestGet: %s%n"
        + "valore di nome pagina al momento della chiamata di TestGET: %s%n"
        , VG.get_root(), VG.get_name_page());
CV.get_URL_SITO();*/
TestGET TG = new TestGET(true);
//TrovaDirs TD = new TrovaDirs();
EstraiLinks el = new EstraiLinks(VG.get_page());
el.filtra_links();
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
FileOutputStream wr = null;
FileOutputStream page_wr = null;
FileOutputStream page_visit_wr = null;
FileOutputStream page_scart_wr = null;
FileOutputStream fnf_wr = null;
FileOutputStream www_wr = null;
FileOutputStream img_wr = null;
Set<String> setVuoto = null;
try
    {
        wr = new FileOutputStream(VG.get_file_DIRS());
        page_wr = new FileOutputStream(VG.get_file_PAG());
        page_visit_wr = new FileOutputStream(VG.get_file_PAG_VISIT());
        page_scart_wr = new FileOutputStream(VG.get_file_PAG_SCART());
        fnf_wr = new FileOutputStream(VG.get_file_FNF());
        www_wr = new FileOutputStream(VG.get_file_URLS());
        img_wr = new FileOutputStream(VG.get_file_IMG());
        //stampiamo un test dei set<> globali contenenti i link da visualizzare a console
        String Tmp = "";
        String separatore = "\n=============================================================================================\n";
        Tmp = separatore + "Lista dei collegamenti presenti nel sito                                                         \n";
        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_collegamenti(), setVuoto);
        
        byte [] i_str = new byte[STRINGA.length()];
        i_str = STRINGA.getBytes();
        try
            { 
            for(int i = 0; i < i_str.length; i++)
                {
                    wr.write(i_str[i]);
                }
            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
    
        Tmp = "";
        Tmp = separatore + "Lista delle pagine presenti nel sito\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_pagina(), setVuoto);
        byte [] i_str_pag = new byte[STRINGA.length()];
        i_str_pag = STRINGA.getBytes();
        try
            {
            for(int i = 0; i < i_str_pag.length; i++)
                {
                    page_wr.write(i_str_pag[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
                
        Tmp = "";
        Tmp = separatore + "Lista delle pagine visitate, sono comprese le pagine che hanno generato un errore di pagina non trovata\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_scaricati(), setVuoto);
        byte [] i_str_pag_visit = new byte[STRINGA.length()];
        i_str_pag_visit = STRINGA.getBytes();
        try
            {
            
            for(int i = 0; i < i_str_pag_visit.length; i++)
                {
                    page_visit_wr.write(i_str_pag_visit[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
        
        Tmp = "";
        Tmp = separatore + "Lista delle pagine scartate in quanto non scaricabili\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_pagine_scartate(), setVuoto);
        byte [] i_str_pag_scart = new byte[STRINGA.length()];
        i_str_pag_scart = STRINGA.getBytes();
        try
            {
            
            for(int i = 0; i < i_str_pag_scart.length; i++)
                {
                    page_scart_wr.write(i_str_pag_scart[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }

        Tmp = "";
        Tmp = separatore + "Lista delle pagine esterne al sito\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_sitiext(), setVuoto);
        byte [] www_str_pagnf = new byte[STRINGA.length()];
        www_str_pagnf = STRINGA.getBytes();
        try
            {
            for(int i = 0; i < www_str_pagnf.length; i++)
                {
                    www_wr.write(www_str_pagnf[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
        
        Tmp = "";
        Tmp = separatore + "Lista delle pagine non trovate\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_fnf(), setVuoto);
        byte [] pagnf = new byte[STRINGA.length()];
        pagnf = STRINGA.getBytes();
        try
            {
            for(int i = 0; i < pagnf.length; i++)
                {
                    fnf_wr.write(pagnf[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
        
        Tmp = "";
        Tmp = separatore + "Lista delle immagini presenti nel sito\n";

        STRINGA = "";
        STRINGA = STRINGA + str_ris(Tmp, VG.get_set_immagini(), setVuoto);
        byte [] img_str_pagnf = new byte[STRINGA.length()];
        img_str_pagnf = STRINGA.getBytes();
        try
            {
            for(int i = 0; i < img_str_pagnf.length; i++)
                {
                    img_wr.write(img_str_pagnf[i]);
                }

            }
        catch(IOException ioe)
            {
                System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati():%n"
                        + "%s%n", ioe);
            }
    }
catch (FileNotFoundException fnf)
    {
                System.err.printf("%nErrore di FNF nella classe Loopper metodo Risultati():%n"
                        + "%s%n", fnf);
    }
try
    {
    if (wr != null)
        wr.close();
    if (page_wr != null)
        page_wr.close();
    if (page_visit_wr != null)
        page_visit_wr.close();
    if (fnf_wr != null)
        fnf_wr.close();
    if (www_wr != null)
        www_wr.close();
    if (img_wr != null)
        img_wr.close();

    }
catch (IOException ioe)
    {
            System.err.printf("%nErrore di IO nella classe Loopper metodo Risultati()"
                    + " fase di chiusura degli stream:%n"
                        + "%s%n", ioe);
    }
}
private String str_ris(String Rich, Set<String> set_1, Set<String> set_2)
{
String ris = "";
STRINGA = Rich;
String spazi = "";
String sub = "";
Iterator it = null;
if(set_2 != null)
    {
        it = set_2.iterator();
    }

    for (String st : set_1)
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
                if(it != null)
                    if (it.hasNext())
                        {
                            STRINGA = STRINGA + it.next() + "\n";
                        }
                    else
                        {
                            STRINGA = STRINGA + "\n";
                        }
                else
                        {
                            STRINGA = STRINGA + "\n";
                        }
            }
//        System.out.printf("%s", STRINGA);
return STRINGA;
}
}

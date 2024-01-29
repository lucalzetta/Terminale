package linea;

/**
 *
 * @author luca
 * Questa classe compie un operazione preliminare e carica la 
 * pagina principale, oggetto della ricerca del sito da scaricare
 * nella lista degli url globale e in quella dei file scaricati.
 * Quindi invoca il Loopper
 * ****************************************************************************
 * 15/12/2023, forse questa classe si può evitare, candidata all'eliminazione.*
 * ****************************************************************************
 */
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;

public class PrimoStep 
{
private final VariabiliGlobali VG;
private Set<String> urls;
private Set<String> urls_scaricati;
private Loopper lp;

public PrimoStep()
{
VG = new VariabiliGlobali();
urls = new TreeSet<>(VG.get_set_collegamenti());
urls_scaricati = new TreeSet<>(VG.get_set_scaricati());
try
    {
        lp = new Loopper();
    }
catch (IOException ioe)
    {
        System.err.printf("Classe PrimoStep, inizializzazione, errore di Input Output %s%n", ioe);
    }
}
}

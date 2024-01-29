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

public class Loopper
{
private final VariabiliGlobali VG;
//private final PrimoStep PS;

public Loopper()throws IOException
{
//PS = new PrimoStep();    
TestGET TG = new TestGET(true);
VG = new VariabiliGlobali();
System.out.printf("Costruttore della classe Loopper. Passaggio a EstraiLinks.");
EstraiLinks el = new EstraiLinks(VG.get_page());
el.links();
/**
 * A questo punto la pagina è salvata, la lista dei collegamenti è aggiornata
 * resta da caricare il link tra quelli scaricati, fare il confronto con quelli 
 * da scaricare, impostare le variabili per il file successivo e ricominciare.
 * Quando il confronto fra la lista degli urls e quella degli urls scaricati
 * dà esito di eguaglianza il ciclo è finito.
 */
}
}

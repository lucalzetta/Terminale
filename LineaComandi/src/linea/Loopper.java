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
 */
import java.io.IOException;

public class Loopper
{
private VariabiliGlobali VG = new VariabiliGlobali();

public Loopper()throws IOException
{
TestGET TG = new TestGET(true);
System.out.printf("Costruttore della classe Loopper. Passaggio a EstraiLinks.");
EstraiLinks el = new EstraiLinks(VG.get_page());
el.links();
}
}

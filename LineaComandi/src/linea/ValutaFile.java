/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

import java.io.File;

/**
 *
 * @author lucaamministratore
 * Questa classe valuterà il tipo di file che le verrà passato come argomento per 
 * stabilire se si tratta di un flusso di testo su cui si possono effettuare 
 * operazioni sul testo oppure di un qualche altro tipo di file che non potrà
 * essere visitato allo stesso modo.
 * 09/02/2024 In questa prima fase ci affideremo all'estensione del nome del file
 * per stabilire se si tratta di testo o meno, in seguito tenteremo una lettura dei 
 * metadati.
 */
public class ValutaFile 
{
private static boolean TESTO;    

public ValutaFile(String pagina)
{
//Prendiamo l'estensione del file e decidiamo se si tratta di un tipo di testo
//o meno.
String ext = "";
int start = pagina.lastIndexOf(".");
if (start != -1)
    {
        int lunghezza = pagina.length()-start;
        for (int i = 0; i < lunghezza; i++)
            {
                ext = ext + pagina.charAt(i);
            }
        text(ext);
    }
else
    {
        System.out.println("Classe ValutaFile:\nImpossibile determinare l'estensione del file!");
    }
}

private boolean text(String estensione)
{
boolean t = false;
switch(estensione)
{
    case ".txt":
           t = true;
    case ".html":
           t = true;
    case ".xhtml":
           t = true;
    case ".xml":
           t = true;
    case ".css":
           t = true;
    case ".php":
           t = true;           
    default:
            t = false;
}
TESTO = t;
return TESTO;
}

public boolean visitabile()
{
return TESTO;
}
}

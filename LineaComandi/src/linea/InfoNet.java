package linea;

/**
 *Questa classe gestirà le informazioi che il programma si propone di restituire,
 * le più dettagliate possibili, a partire da un nome di dominio, un indirizzo 
 * IP o cos'altro è possibile.
 * 
 * @author luca
 */

import java.net.*;

public class InfoNet 
{
private final String NOMEDOMINIO;
private final String IPDOMINIO;

public InfoNet()
{
this.NOMEDOMINIO = "";
this.IPDOMINIO = "";
}

public InfoNet(String nomeDominio)
{
this.NOMEDOMINIO = nomeDominio;
this.IPDOMINIO = "";
}

public InfoNet(String nomeDominio, String IPDominio)
{
this.NOMEDOMINIO = nomeDominio;
this.IPDOMINIO = IPDominio;
}

public String genericInfos()
{
String inf = "";
try 
    {
    InetAddress adr = InetAddress.getByName(NOMEDOMINIO);
    inf = adr.toString();
    }
catch (UnknownHostException uh)
    {
    System.err.printf("Nome di dominio non riconosciuto: ", uh);
    }
return inf;
}
}

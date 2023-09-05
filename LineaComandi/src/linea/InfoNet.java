package linea;

/**
 *Questa classe gestirà le informazioi che il programma si propone di restituire,
 * le più dettagliate possibili, a partire da un nome di dominio, un indirizzo 
 * IP o cos'altro è possibile.
 * 
 * @author luca
 */

import java.net.*;
import java.util.*;

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

public String localInfos()throws SocketException
{
String inf="";
Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
while(interfaces.hasMoreElements())
    {
        NetworkInterface ni = interfaces.nextElement();
        inf = inf + interfaces.nextElement().toString() + "\n";
        System.out.printf("%s", ni);
    }
System.out.printf("%s", inf);

return inf;
}

public String mieInfos()
{
String inf = "";

    try 
    {
      InetAddress me = InetAddress.getLocalHost();
      inf = me.getHostAddress();
      System.out.println("Il mio indirizzo è: " + inf);
    } 
    catch (UnknownHostException ex) 
        {
      System.out.println("Non riesco a trovare il mio indirizzo.");
        }
return inf;
}
}

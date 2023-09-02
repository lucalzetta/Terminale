package linea;

public class Argos 
{
private String[] ARGOMENTI;
private byte[] CARATTERI;

public Argos()
{
this.ARGOMENTI = new String[0];
this.CARATTERI = new byte[0];
System.out.println("Costruttore di default della classe Argos");
}

public Argos(String[] argomenti)
{
this.ARGOMENTI = argomenti;
int l = ARGOMENTI.length;
System.out.printf("Numero di argomenti passati dalla riga di comando: %d%n",l);
}
}

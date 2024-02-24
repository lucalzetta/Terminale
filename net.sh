#istruzione per la compilazione dei file del programma per il controllo da terminale

#IL SEGUENTE PERCORSO VALE PER Il computer DELL con installazione Linux mint.
#javac -d /home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/build/classes/ /home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/src/linea/*.java
#cd ~/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/build/classes

#IL SEGUENTE PERCORSO VALE PER Il computer ATA Informatica sul disco con SO Linux Debian.
javac -d /home/lucaamministratore/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/build/classes/ /home/lucaamministratore/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/src/linea/*.java

cd /home/lucaamministratore/GDrive/Luca/Programmazione/JAVA/Terminale/LineaComandi/build/classes

#l'esecuzione del programma 'linea' ha il la main class nel file princip e accetta 
#un'opzione e un parametro per l'esecuzione, il test lo facciamo con l'opzione 
#-s che permette di scaricare un sito e un indirizzo web valido come parametro.
java linea.princip -s http://www.scuoladipoliticamaniago.it/index.html


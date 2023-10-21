import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
public class CaesarVerschlüsselung {
    public static void main (String[] args) 
            { 
                System.out.print("Herzlich Willkommen zum Verschlüsselungsprogramm. ");
                System.out.println("Ziel des Programms ist es, den Benutzer bei der Caeser Verschlüsselung zu unterstützen!");
                //print gibt die Strings aus verschiedenen Print anweisungen in einer Zeile aus. Println gibt jede print Anweisung in einer Zeile aus (println=printline)
                //ein Java Dokument beinhaltet immer eine Klasse(definiert mit "public class [Name der Klasse]"), in der widerum eine Hauptmethode namens main mit "public static void main (String[] args){}" festgelegt wird.
                //am Ende jeder Anweisungszeile innerhalb einer Methode steht ein ";" und bereiche von z.B. Klassen werden mit "{" angefangen und mit "}" beendet.
                String Originaltext;
                Scanner scanner = new Scanner(System.in);
                System.out.print("Geben sie das zu Kryptografierende Wort ein: ");
                Originaltext= scanner.nextLine();
                System.out.print("Geben sie die Verschiebung im Alphabet in ganzen Zahlen an: ");
                int Verschiebung = scanner.nextInt();
                scanner.close();
                String Ergebnis = "";
                for (int i = 0; i < Originaltext.length(); i++) 
                {
                    Ergebnis= Ergebnis + Verschluesselung(Originaltext.charAt(i), Verschiebung);
                }
                System.out.println ("Das Verschlüsselte Wort ist:" + Ergebnis);
            }
                public static char Verschluesselung (char Originaltext, int Verschiebung)
                    {
                        char Ergebnis;
                        char UnterBearbeitung;
                        UnterBearbeitung = Originaltext;
                        UnterBearbeitung = Character.toLowerCase(UnterBearbeitung);
                        List<Character> listAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
                        int IndexUnterBearbeitung= listAlphabet.indexOf(UnterBearbeitung);
                        int IndexErgebnis = IndexUnterBearbeitung + Verschiebung;
                        if (IndexErgebnis > 25) {
                            IndexErgebnis = IndexErgebnis - 26;
                        }
                        Ergebnis = listAlphabet.get(IndexErgebnis);
                        return(Ergebnis);
                    }
}

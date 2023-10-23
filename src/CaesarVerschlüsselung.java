import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
public class CaesarVerschlüsselung {


    public static void main (String[] args) 
            { 
                System.out.print("Herzlich Willkommen zum Verschlüsselungsprogramm. ");
                System.out.println("\nZiel des Programms ist es, den Benutzer bei der Caeser Verschlüsselung zu unterstützen!\nWenn sie 0 als Verschlüsselung eingeben, wird die zu kryptografierende Zeichenkette mit allen 25 Verschiebungen angezeigt.");
                //print gibt die Strings aus verschiedenen Print anweisungen in einer Zeile aus. Println gibt jede print Anweisung in einer Zeile aus (println=printline)
                //ein Java Dokument beinhaltet immer eine Klasse(definiert mit "public class [Name der Klasse]"), in der widerum eine Hauptmethode namens main mit "public static void main (String[] args){}" festgelegt wird.
                //am Ende jeder Anweisungszeile innerhalb einer Methode steht ein ";" und bereiche von z.B. Klassen werden mit "{" angefangen und mit "}" beendet.
                Scanner scanner = new Scanner(System.in);
                String Originaltext= StringScanner("Geben sie die zu kryptografierende Zeichenkette ein: ", scanner);
                String VerschiebungString = StringScanner("Geben sie die Verschiebung im Alphabet in ganzen Zahlen an: ", scanner);
                int Verschiebung = StringInInt(VerschiebungString, scanner);
                System.out.println ("Kryptografierte Zeichenkette: " + Ergebnisberechnung(Originaltext, Verschiebung));
                scanner.close();
            }


    public static int StringInInt(String VerschiebungsString, Scanner scanner)
        {
            int Verschiebung = 0;
            Boolean Minus = false;
            if (VerschiebungsString.contains("-"))
            {
                VerschiebungsString = VerschiebungsString.replace("-", "");
                Minus = true;
            }
            if (VerschiebungsString.matches("\\d+"))
                {   
                    Verschiebung = Integer.parseInt(VerschiebungsString);
                    if (Minus)
                        {
                            Verschiebung = Verschiebung * -1;
                        }
                    return Verschiebung;
                }
            int i = 0; 
            while (false == VerschiebungsString.matches("\\d+") && i <= 3)
                {
                    i++;
                    System.out.println("Bitte geben sie als Verschiebung eine ganze Zahl an!");
                    VerschiebungsString = StringScanner("Geben sie die Verschiebung im Alphabet in ganzen Zahlen an: ", scanner);
                    if (VerschiebungsString.contains("-"))
                        {
                            VerschiebungsString = VerschiebungsString.replace("-", "");
                            Minus = true;
                        }
                    if (VerschiebungsString.matches("\\d+"))
                        {   
                            Verschiebung = Integer.parseInt(VerschiebungsString);
                            if (Minus)
                                {
                                    Verschiebung = Verschiebung * -1;
                                }
                            return Verschiebung;
                        }
                }
            if (i > 3)
                {
                    System.out.println("Bitte geben sie als Verschiebung eine ganze Zahl an und starten sie das Programm erneut!");
                    scanner.close();
                    System.exit(0);
                }
            return (Verschiebung);
        }


    public static char Verschluesselung (char OriginalChar, int Verschiebung) //TODO Verschlüsselung aufteilen und Code in Cleancode verwandeln 
        {
            char ErgebnisChar;
            char UnterBearbeitung;
            UnterBearbeitung = OriginalChar;
            UnterBearbeitung = Character.toLowerCase(UnterBearbeitung);
            List<Character> listAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
            int IndexUnterBearbeitung= listAlphabet.indexOf(UnterBearbeitung);
            if (IndexUnterBearbeitung == -1 )
                {
                    return (UnterBearbeitung);
                }
            int IndexErgebnis = IndexUnterBearbeitung + Verschiebung;
            while (IndexErgebnis < 0 || IndexErgebnis > 25)
                {
                    if (IndexErgebnis > 25) 
                        {
                            IndexErgebnis = IndexErgebnis - 26;
                        }
                    if (IndexErgebnis < 0) 
                        {
                            IndexErgebnis = 26 + IndexErgebnis;
                        }
                
                }
            ErgebnisChar = listAlphabet.get(IndexErgebnis);
            return(ErgebnisChar);
        }


    public static String StringScanner (String Text, Scanner scanner)
        {
            System.out.print(Text);
            String input = scanner.nextLine();
            return(input);
        }


    public static String Ergebnisberechnung (String Originaltext, int Verschiebung)
        {
            String ErgebnisWort = "";
            String Ergebnis = "";
            if (Verschiebung == 0)
            {
                for (int V = 0;V < 26; V++)
                {
                    for (int i = 0; i < Originaltext.length(); i++) 
                        {
                            char OriginalChar = Originaltext.charAt(i);
                            ErgebnisWort= ErgebnisWort + UpperLower(OriginalChar, V);
                        }
                    Ergebnis =Ergebnis + "\n" + (V + 1) + ". " + ErgebnisWort  ;
                    ErgebnisWort = "";
                }
                return (Ergebnis);
            }
            for (int i = 0; i < Originaltext.length(); i++) 
            {
                char OriginalChar = Originaltext.charAt(i);
                Ergebnis= Ergebnis + UpperLower(OriginalChar, Verschiebung);
            }
            return (Ergebnis);
        }


    public static char UpperLower (char OriginalChar, int Verschiebung)
    {   
        if (Character.isUpperCase(OriginalChar))
            {
                OriginalChar = Character.toUpperCase(Verschluesselung(OriginalChar, Verschiebung));
            }
        else
            {
                OriginalChar = Verschluesselung(OriginalChar, Verschiebung);
            }
        return (OriginalChar);
    }
}

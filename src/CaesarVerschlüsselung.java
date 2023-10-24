import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class CaesarVerschlüsselung {

    public static void main(String[] args) {
        System.out.print("Herzlich Willkommen zum Verschlüsselungsprogramm. ");
        System.out.println(
                "\nZiel des Programms ist es, den Benutzer bei der Caeser Verschlüsselung zu unterstützen!\nWenn sie 0 als Verschlüsselung eingeben, wird die zu kryptografierende Zeichenkette mit allen 25 Verschiebungen angezeigt.");
        /**
         * print gibt die Strings aus verschiedenen Print anweisungen in einer Zeile
         * aus. Println gibt jede print Anweisung in einer Zeile aus (println=printline)
         * ein Java Dokument beinhaltet immer eine Klasse(definiert mit "public class
         * [Name der
         * Klasse]"), in der widerum eine Hauptmethode namens main mit "public
         * static void main (String[] args){
         * }" festgelegt wird. Am Ende jeder Anweisungszeile innerhalb einer Methode steht ein "
         * ;" und
         * bereiche von z.B. Klassen werden mit "{" angefangen und mit "}" beendet.
         */
        Scanner scanner = new Scanner(System.in);
        String Originaltext = StringScanner("Geben sie die zu kryptografierende Zeichenkette ein: ", scanner);
        String VerschiebungString = StringScanner("Geben sie die Verschiebung im Alphabet in ganzen Zahlen an: ",
                scanner);
        int Verschiebung = PrüfungMinus(VerschiebungString, scanner);
        System.out.println("Kryptografierte Zeichenkette: " + ErgebnisBerechnung(Originaltext, Verschiebung));
        scanner.close();
    }

    public static int PrüfungMinus(String VerschiebungsString, Scanner scanner) {
        Boolean Minus = false;
        if (VerschiebungsString.contains("-")) {
            VerschiebungsString = VerschiebungsString.replace("-", "");
            Minus = true;
        }
        return (StringInInt(VerschiebungsString, scanner, Minus));
    }

    public static int StringInInt(String VerschiebungsString, Scanner scanner, Boolean Minus) {
        int Verschiebung = 0;
        if (VerschiebungsString.matches("\\d+") && VerschiebungsString.contains(",") == false) {
            Verschiebung = Integer.parseInt(VerschiebungsString);
            if (Minus) {
                Verschiebung = Verschiebung * -1;
            }
            return Verschiebung;
        } else {
            return (StringInIntBeiBuchstabeOderKomma(VerschiebungsString, scanner, Minus));
        }
    }

    public static int StringInIntBeiBuchstabeOderKomma(String VerschiebungsString, Scanner scanner, Boolean Minus) {
        int i = 4;
        int Verschiebung = 0;
        while ((false == VerschiebungsString.matches("\\d+") || VerschiebungsString.contains(",")) && i >= 1) {
            i = i - 1;
            System.out.println("Bitte geben sie als Verschiebung eine ganze Zahl an! Sie haben noch " + (i + 1)
                    + " Versuch(e), dannach müssen sie das Programm neu starten!");
            VerschiebungsString = StringScanner("Geben sie die Verschiebung im Alphabet in ganzen Zahlen an: ",
                    scanner);
            if (VerschiebungsString.contains("-")) {
                VerschiebungsString = VerschiebungsString.replace("-", "");
                Minus = true;
            }
            if (VerschiebungsString.matches("\\d+")) {
                Verschiebung = Integer.parseInt(VerschiebungsString);
                if (Minus) {
                    Verschiebung = Verschiebung * -1;
                }
                return Verschiebung;
            }
        }
        if (i < 1) {
            System.out.println(
                    "Bitte geben sie als Verschiebung eine ganze Zahl an und starten sie das Programm erneut!");
            scanner.close();
            System.exit(0);
        }
        return (Verschiebung);
    }

    public static char KryptografierungChar(char OriginalChar, int Verschiebung)
    {
        char ErgebnisChar;
        char UnterBearbeitung;
        UnterBearbeitung = OriginalChar;
        UnterBearbeitung = Character.toLowerCase(UnterBearbeitung);
        List<Character> listAlphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        int IndexUnterBearbeitung = listAlphabet.indexOf(UnterBearbeitung);
        if (IndexUnterBearbeitung == -1) {
            return (UnterBearbeitung);
        }
        int IndexErgebnis = IndexUnterBearbeitung + Verschiebung;
        while (IndexErgebnis < 0 || IndexErgebnis > 25) {
            if (IndexErgebnis > 25) {
                IndexErgebnis = IndexErgebnis - 26;
            }
            if (IndexErgebnis < 0) {
                IndexErgebnis = 26 + IndexErgebnis;
            }

        }
        ErgebnisChar = listAlphabet.get(IndexErgebnis);
        return (ErgebnisChar);
    }

    public static String StringScanner(String Text, Scanner scanner) {
        System.out.print(Text);
        String input = scanner.nextLine();
        return (input);
    }

    public static String ErgebnisBerechnung(String Originaltext, int Verschiebung)
    {
        String ErgebnisWort = "";
        String Ergebnis = "";
        if (Verschiebung == 0 || 0 == Verschiebung % 26) {
            for (int V = 0; V < 26; V++) {
                for (int i = 0; i < Originaltext.length(); i++) {
                    char OriginalChar = Originaltext.charAt(i);
                    ErgebnisWort = ErgebnisWort + UpperLower(OriginalChar, V);
                }
                Ergebnis = Ergebnis + "\n" + (V + 1) + ". " + ErgebnisWort;
                ErgebnisWort = "";
            }
            return (Ergebnis);
        }
        for (int i = 0; i < Originaltext.length(); i++) {
            char OriginalChar = Originaltext.charAt(i);
            Ergebnis = Ergebnis + UpperLower(OriginalChar, Verschiebung);
        }
        return (Ergebnis);
    }

    public static char UpperLower(char OriginalChar, int Verschiebung) {
        if (Character.isUpperCase(OriginalChar)) {
            OriginalChar = Character.toUpperCase(KryptografierungChar(OriginalChar, Verschiebung));
        } else {
            OriginalChar = KryptografierungChar(OriginalChar, Verschiebung);
        }
        return (OriginalChar);
    }
}
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalc {

    private final static int MAX = 1000;
    private final static int MIN = 0;

    public int add(String str) {

        // Code automatisch formatieren lassen mit Ctrl+Alt+L

        String multiDelimiter = ",";
        char delimiterBasic = ',';

        if (str.isEmpty()) {
            return 0; // da du hier ein return machst bräuchtest du keinen else Block, das spart dir Einrückung und
            // dann würde ich die empty-Abfrage auch ganz an Methodenbeginn ziehen.
        } else {
            int result = 0;
            if (str.startsWith("//") && str.charAt(2) == '[') {
                // Pattern.compile an zentrale Stelle auslagern (statische Variable), ansonsten geht auch Pattern.matches,
                // aber wenn du die Regex häufig ausführst ist es viel performanter, die Regex ein einziges Mal
                // zu kompilieren und dann nur noch zu verwenden.
                // Außerdem lokale Variablen immer final machen, wenn die Referenz nicht neu zugewiesen wird.
                // Daduch kannst Du sie von denen unterscheiden, die erneut zugewiesen werden,
                // was bei Refactoring / Lesen von Code sehr hilft, auch wenn es erstmal geschwätziger ist.
                Pattern multiDelimiterFormat = Pattern
                    .compile("\\[([^A-Za-z0-9]+?)\\]{1,}?");        //mit "trägem regex quantifizierer"
                Matcher multiDelimiterMatcher = multiDelimiterFormat.matcher(str);

                // man sollte nie dem Methodenparameter einen neuen Wert zuweisen, diesen stattdessen in eine
                // lokale temporäre Variable speichern und dann mit dieser weiterarbeiten.
                // Methodenparameter final machen, dann passiert das auch nicht aus Versehen, weil dann Compilefehler auftritt.
                // In diesem guten Online Buch mehr Details und Beispiel:
                // https://ncrcoe.gitbooks.io/java-for-small-teams/content/specifics/200_do_not_reassign_parameters.html
                // das Buch ist auf jeden Fall empfehlenswert
                str = str.substring(2);

                while (multiDelimiterMatcher.find()) {
                    multiDelimiter = multiDelimiter.concat(multiDelimiterMatcher.group(1));
                    int len = multiDelimiterMatcher.group(1).length();
                    str = str.substring(len + 2);
                    multiDelimiterMatcher = multiDelimiterFormat.matcher(str);
                }

            } else {
                if (!Character.isDigit(str.charAt(0))) {
                    delimiterBasic = str.charAt(2);
                    str = str.substring(3);
                }
            }

            List<Integer> list = Stream.of(str.split("[," + delimiterBasic + multiDelimiter + "]"))
                .map(elem -> elem.replaceAll("\n", ""))
                .filter(item -> !item.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            for (Integer currentValue : list) {
                if (currentValue < MIN) {
                    System.out.println("negatives not allowed: " + currentValue);
                }
                if (currentValue <= MAX) {
                    result += currentValue;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }

}

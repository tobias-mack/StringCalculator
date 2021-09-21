import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalc {
    private final static int MAX = 1000;
    private final static int MIN = 0;

    public int add(String str){

        String multiDelimiter = ",";
        char delimiterBasic = ',';

        if(str.isEmpty()){
            return 0;
        }
        else {
            int result = 0;
            if(str.startsWith("//") && str.charAt(2) == '['){
                Pattern multiDelimiterFormat = Pattern.compile("\\[([^A-Za-z0-9]+?)\\]{1,}?");        //mit "trägem regex quantifizierer"
                Matcher multiDelimiterMatcher = multiDelimiterFormat.matcher(str);
                str = str.substring(2);

                while(multiDelimiterMatcher.find()){
                    multiDelimiter = multiDelimiter.concat(multiDelimiterMatcher.group(1));
                    int len = multiDelimiterMatcher.group(1).length();
                    str = str.substring(len+2);
                    multiDelimiterMatcher = multiDelimiterFormat.matcher(str);
                }

            }else {
                if (!Character.isDigit(str.charAt(0))) {
                    delimiterBasic = str.charAt(2);
                    str = str.substring(3);
                }
            }

            List<Integer> list = Stream.of(str.split("[,"+ delimiterBasic + multiDelimiter +"]"))
                        .map (elem -> elem.replaceAll("\n",""))
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

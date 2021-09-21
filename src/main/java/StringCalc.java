import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalc {
    private final static int MAX = 1000;
    private final static int MIN = 0;

    public int add(String str){

        if(str.isEmpty()){
            return 0;
        }
        else {
            int result = 0;
            char delimiter = ',';
            String delimiter2 = ",";

            Pattern pat = Pattern.compile("\\/\\/\\[([^A-Za-z0-9]+)\\]");
            Matcher matcher = pat.matcher(str);

            if (matcher.find()) {
                System.out.println("Delimiter : " + matcher.group(1));
                delimiter2 = matcher.group(1);
                str = str.substring(3+delimiter2.length()+2);
                System.out.println(str);
            } else {
                System.out.println("No match" +str);
                if(str.startsWith("//")){
                    delimiter = str.charAt(2);
                    str = str.substring(3);
                }
            }



            List<Integer> list = Stream.of(str.split("[," + delimiter + delimiter2 + "]"))
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
       //String str = "//[***]\n1***2***3";
       //Pattern pat = Pattern.compile("\\/\\/\\[([^A-Za-z0-9]+)\\]");
       //Matcher matcher = pat.matcher(str);

       //if (matcher.find()) {
       //    System.out.println("Delimiter : " + matcher.group(1));
       //}else{
       //    System.out.println("no match");
       //}

        String s = "3**1";
        String[] a = s.split("[" +"*" + '1'+ "]");
        System.out.println(Arrays.toString(a));

    }

}

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalc {

    public int add(String str){

        if(str.isEmpty()){
            return 0;
        }
        else {
            int result = 0;
            char delimiter = ',';
            //Stack<Character> theStack = new Stack<Character>();
            //theStack.push()

            if(str.startsWith("//")){
                delimiter = str.charAt(2);
                str = str.substring(3);
            }

            List<Integer> list = Stream.of(str.split("[," + delimiter + "]"))
                    .map (elem -> elem.replaceAll("\n","")  )
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                Integer currentValue = list.get(i);
                if(currentValue < 0){
                    System.out.println("negatives not allowed: " + currentValue );
                }
                result += currentValue;
            }
            return result;
        }

    }

}

import java.util.stream.IntStream;

public class FizzBuzz {

    final static private int FIZZ = 3;
    final static private int BUZZ = 5;
    final static private int RANGE = 10000000;

    public static void main(String[] args) {


        long startTime = System.nanoTime();

        IntStream.rangeClosed(1, RANGE)
                .mapToObj(i -> i % FIZZ == 0 ? (i % BUZZ == 0 ? "FizzBuzz" : "Fizz") : (i % BUZZ == 0 ? "Buzz" : i))
                .forEach(System.out::println);

        long estimatedTime = System.nanoTime() - startTime;

        //################SECOND FIZZBUZZ################################################

        long startTime2 = System.nanoTime();

        for (int i = 1; i <= RANGE; i++) {
            if(i%FIZZ == 0 && i%BUZZ == 0) {
                System.out.println("FizzBuzz");
            } else if(i%FIZZ == 0){
                    System.out.println("Fizz");
            } else if(i%BUZZ == 0){
                System.out.println("Buzz");
            } else{
                System.out.println(i);
            }
        }

        long estimatedTime2 = System.nanoTime() - startTime2;

        System.out.println("Time taken with stream: "+ estimatedTime/ 1000000 +" milliseconds");
        System.out.println("Time taken: "+ estimatedTime2/ 1000000 +" milliseconds");    }



}

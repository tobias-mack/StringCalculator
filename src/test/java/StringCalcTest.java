
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StringCalcTest {

    private final StringCalc stringCalc = new StringCalc();

    @Nested
    @DisplayName("basic")
    class MyNested {

        @DisplayName("accept string with 0 params")
        @Test
        public void stringWith0Params() {
            String zeroParams = "";

            //test for empty String
            assertThat(stringCalc.add(zeroParams))
                    .isEqualTo(0);

        }

        @DisplayName("accept string with 1 params")
        @Test
        public void stringWith1Param() {
            String oneParam = "2";

            //test for 1 param String
            assertThat(stringCalc.add(oneParam))
                    .isEqualTo(2);

        }

        @DisplayName("accept string with 2 params")
        @Test
        public void stringWith2Params() {
            String twoParams = "3,5";

            //test for 2 param String
            assertThat(stringCalc.add(twoParams))
                    .isEqualTo(8);
        }

        @DisplayName("accept string with many params")
        @Test
        public void stringWithManyParams() {
            String Params = "3,5,22,100,70";

            //test for 2 param String
            assertThat(stringCalc.add(Params))
                    .isEqualTo(200);
        }
    }

    @DisplayName("accept string with new line")
    @Test
    public void stringWithNewLine() {
        String Params = "3,\n12";

        //test for 2 param String
        assertThat(stringCalc.add(Params))
                .isEqualTo(15);
    }

    @DisplayName("accept string with delimiter")
    @Test
    public void strWithDelimiter() {
        String Params = "//;\n3;12";

        //test for 2 param String
        assertThat(stringCalc.add(Params))
                .isEqualTo(15);
    }

    @DisplayName("dont accept negatives")
    @Test
    public void negativeNumberDetection() {
        String Params = "//.\n-3.-12";

        //test for 2 param String
        assertThat(stringCalc.add(Params))
                .isEqualTo(-15);
    }

}

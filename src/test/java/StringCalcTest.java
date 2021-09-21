
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StringCalcTest {

    private final StringCalc stringCalc = new StringCalc();

    @Nested
    @DisplayName("accepting basic input")
    class MyNested {

        @DisplayName("accept string with 0 params")
        @Test
        public void stringWith0Params() {
            String zeroParams = "";
            assertThat(stringCalc.add(zeroParams))
                    .isEqualTo(0);

        }

        @DisplayName("accept string with 1 params")
        @Test
        public void stringWith1Param() {
            String oneParam = "2";
            assertThat(stringCalc.add(oneParam))
                    .isEqualTo(2);

        }

        @DisplayName("accept string with 2 params")
        @Test
        public void stringWith2Params() {
            String twoParams = "3,5";
            assertThat(stringCalc.add(twoParams))
                    .isEqualTo(8);
        }

        @DisplayName("accept string with many params")
        @Test
        public void stringWithManyParams() {
            String Params = "3,5,22,100,70";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(200);
        }
    }
    @Nested
    @DisplayName("accepting more specific stuff")
    class test2 {
        @DisplayName("accept string with new line")
        @Test
        public void stringWithNewLine() {
            String Params = "3,\n12";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(15);
        }

        @DisplayName("accept string with delimiter")
        @Test
        public void strWithDelimiter() {
            String Params = "//;\n3;12";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(15);
        }

        @DisplayName("dont accept negatives")
        @Test
        public void negativeNumberDetection() {
            String Params = "//.\n-3.-12";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(-15);
        }

        @DisplayName("dont accept values bigger than 1000")
        @Test
        public void ignoreBigNumbers() {
            String Params = "//.\n1001.12.2";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(14);
        }
    }

    @Nested
    @DisplayName("accepting different delimiter formats")
    class Delimiter {
        @DisplayName("Delimiter of any length with specific format")
        @Test
        public void specificDelimiterFormat() {
            String Params = "//[***]\n1***2***3";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(6);
        }
        @DisplayName("multiple delimiters")
        @Test
        public void multipleDelimiterFormat() {
            String Params = "//[*][%]\n1*2%3";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(6);
        }
        @DisplayName("multiple delimiters with different length")
        @Test
        public void multipleDelimiters() {
            String Params = "//[**][%][???]\n1**2%3???4";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(10);
        }

    }
}

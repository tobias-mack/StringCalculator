
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
            // laut Aufgabenstellung sollte hier eine Exception fliegen: " Schritt 5
            // Calling Add with a negative number will throw an exception “negatives not allowed” -
            // and the negative that was passed.if there are multiple negatives, show all of them in the exception message"
//            assertThat(stringCalc.add(Params))
//                    .isEqualTo(-15);

            // FIXME verwende hierzu, siehe AssertJ Dokumentation: Assertions.assertThatThrownBy(...)...


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
            // lokale Variablen schreibt man in Java normalerweise klein: "String params = ..."
            String Params = "//[**][%][???]\n1**2%3???4";
            assertThat(stringCalc.add(Params))
                    .isEqualTo(10);
        }


        // FIXME nachfolgende beide Tests schlagen fehl, sollten aber - wenn ich mich bei der Aufgabenstellung nicht verlesen habe - grün sein
        @Test
        public void multipleDelimitersDifferentLength_allFeatures() {
            String Params = "//[***][@][???]\n1***5\n2000,2***3@2";
            assertThat(stringCalc.add(Params))
                .isEqualTo(13);
        }
        @Test
        public void multipleDelimitersDifferentLength_allFeatures2() {
            String Params = "//[***][@][x]\n1***5\n2000,2***3@2x5";
            assertThat(stringCalc.add(Params))
                .isEqualTo(18);
        }



        @Test
        public void multiNewline() {
            String Params = "\n1\n\n\n5\n,\n3,5";
            assertThat(stringCalc.add(Params))
                .isEqualTo(13);
        }


        // ob dieser Test grün sein sollte oder fehlschlagen sollte ist Interpretationssache
        @Test
        public void shouldThisReallyBeGreen() {
            String Params = "1,,5";
            assertThat(stringCalc.add(Params))
                .isEqualTo(6);
        }

        // FIXME dieser Test sollte m.E. grün sein, jedenfalls nicht Ergebnis 15
        @Test
        public void twoNewlinesBetweenNumbers() {
            String Params = "1\n\n5";
            assertThat(stringCalc.add(Params))
                .isEqualTo(6);
        }


    }
}

package wang.zhongpin.demo;

import java.util.List;

public class RomanArabicConverter {
    public static int romanToArabic(String input) throws IllegalArgumentException {
        // TODO: 1 Uncomment to add check for empty string, run fuzz test again
        // if (input.equals("")) {
        //     throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        // }

        // TODO: 2.1 Uncomment to add check for empty string and invalid input
        // if (isInvalidRoman(input)) {
        //     throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        // }

        // TODO: 2.2 Remove the code in TODO 1 as it is covered by the regex check

        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    // TODO: 2.3 Uncomment this part, run fuzz test again
    // // Roman numerals only go up to 3999 (MMMCMXCIX)
    // private static final String ROMAN_NUMERAL_REGEX = "^(?=[MDCLXVI])(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    // private static boolean isInvalidRoman(String romanNumeral) {
    //     return !romanNumeral.matches(ROMAN_NUMERAL_REGEX);
    // }
}

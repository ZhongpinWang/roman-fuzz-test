import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.junit.jupiter.api.Test;
import wang.zhongpin.demo.RomanArabicConverter;

import static org.assertj.core.api.Assertions.*;

public class RomanArabicConverterTest {
    @Test
    void given2018Roman_WhenConvertingToArabic_ThenReturn2018() {
        String roman2018 = "MMXVIII";
        int result = RomanArabicConverter.romanToArabic(roman2018);
        assertThat(result).isEqualTo(2018);
    }

    @Test
    void given1999Arabic_WhenConvertingToRoman_ThenReturnMCMXCIX() {
        int arabic1999 = 1999;
        String result = RomanArabicConverter.arabicToRoman(arabic1999);
        assertThat(result).isEqualTo("MCMXCIX");
    }

    @FuzzTest
    void fuzzTest(FuzzedDataProvider data) {
        String s = data.consumeRemainingAsString();
        try {
            assertThat(RomanArabicConverter.arabicToRoman(RomanArabicConverter.romanToArabic(s))).isEqualTo(s);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("cannot be converted to a Roman Numeral");
        }
    }
}

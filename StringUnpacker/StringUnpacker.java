import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * StringUnpacker Class provides a means for unpacking strings of the type
 *
 * "number[number[substring]substring]]string",
 *
 * where the 'number' is the number of repetitions following substring
 *
 * Example:
 *
 * "2[3[x]y]" is unpacked into "xxxyxxxy"
 * "3[xyz]4[xy]z" is unpacked into "xyzxyzxyzxyxyxyxyz"
 */
public class StringUnpacker {

    public String unpack(String string) {
        if (!isValid(string)) {
            return "Incorrect input string format";
        }

        Stack<Integer> repetitionsNumber = new Stack<>();
        StringBuilder unpackedString = new StringBuilder();
        StringBuilder stringPart = new StringBuilder();
        string = string.replace("[", "");

        for (char currentChar : string.toCharArray()) {
            if (Character.isDigit(currentChar)) {
                repetitionsNumber.push(Integer.parseInt(String.valueOf(currentChar)));
                continue;
            }
            if (Character.isAlphabetic(currentChar)) {
                stringPart.append(currentChar);
                continue;
            }
            assert currentChar == ']';
            String currentString = stringPart.toString().repeat(repetitionsNumber.pop());
            stringPart.setLength(0);
            if (repetitionsNumber.size() > 0) {
                stringPart.append(currentString);
            } else {
                unpackedString.append(currentString);
            }
        }

        return unpackedString.append(stringPart.toString()).toString();
    }

    public boolean isValid(String string) {
        String allowedSymbols = "^((\\d\\[)+[a-zA-Z]+]+[a-zA-Z\\]]*)+$";
        if (!Pattern.matches(allowedSymbols, string)) {
            return false;
        }

        int opensBracketCount = 0;
        string = string.replaceAll("[^\\[\\]]", "");

        for (char currentChar : string.toCharArray()) {
            if (currentChar == ']' && opensBracketCount < 1) {
                return false;
            } else if (currentChar == ']') {
                opensBracketCount--;
            } else {
                opensBracketCount++;
            }
        }

        return opensBracketCount == 0;
    }
}


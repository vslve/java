import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter string to unpack:");
        String stringToUnpack = new Scanner(System.in).nextLine();
        System.out.println("Unpacked string: ");
        System.out.println(new StringUnpacker().unpack(stringToUnpack));
    }
}

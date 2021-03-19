import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUnpackerTests {
    
    StringUnpacker stringUnpacker = new StringUnpacker();
    
    @Test
    public void unpackTest() {
        Assertions.assertEquals("xxxyxxxy", stringUnpacker.unpack("2[3[x]y]"));
        Assertions.assertEquals("xyzxyzxyzxyxyxyxyz", stringUnpacker.unpack("3[xyz]4[xy]z"));
        Assertions.assertEquals("aaxaaxaaxyaaxaaxaaxy", stringUnpacker.unpack("2[3[2[a]x]y]"));
        Assertions.assertEquals("aaxaaxaaxyaaxaaxaaxyzczczczczcd", stringUnpacker.unpack("2[3[2[a]x]y]5[zc]d"));
        Assertions.assertEquals("aZ", stringUnpacker.unpack("1[a]Z"));
        Assertions.assertEquals("xxxyxxxy", stringUnpacker.unpack("2[3[0[a]x]y]"));
        Assertions.assertEquals("", stringUnpacker.unpack("0[xy]"));
    }

    @Test
    public void isValidTest() {
        Assertions.assertTrue(stringUnpacker.isValid("2[3[x]y]"));
        Assertions.assertTrue(stringUnpacker.isValid("3[xyz]4[xy]z"));
        Assertions.assertTrue(stringUnpacker.isValid("2[3[2[a]x]y]"));
        Assertions.assertTrue(stringUnpacker.isValid("2[3[2[a]x]y]5[zc]d"));
        Assertions.assertTrue(stringUnpacker.isValid("1[a]Z"));
        Assertions.assertTrue(stringUnpacker.isValid("2[3[0[a]x]y]"));
        Assertions.assertTrue(stringUnpacker.isValid("0[xy]"));
        Assertions.assertFalse(stringUnpacker.isValid(""));
        Assertions.assertFalse(stringUnpacker.isValid(" "));
        Assertions.assertFalse(stringUnpacker.isValid("2[3[x2[z]]y]"));
        Assertions.assertFalse(stringUnpacker.isValid("2[3[x]y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("a2[3[x]y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("2[3[[]x]y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("2[3{x}y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("2[ 3[x]y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("2[3[абв]y]5"));
        Assertions.assertFalse(stringUnpacker.isValid("2[3[x]y]_"));
    }
}

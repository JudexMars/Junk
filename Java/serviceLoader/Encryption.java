package serviceLoader;

/**
 * Encryption - is the interface that defines a class that can encrypt and decrypt any information using
 * some algorithm (Caesar, etc.)
 * @author Danny Brante
 * @version 1.0
 */
public interface Encryption {
    byte[] encrypt(byte[] source, byte[] key);
    byte[] decrypt(byte[] source, byte[] key);
    int strength();
}
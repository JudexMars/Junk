package serviceLoader.impl;

import serviceLoader.Encryption;

/**
 * An implementation of Encryption interface that provides Caesar's cipher
 * @author Danny Brante
 * @version 1.0
 */
public class CaesarCipher implements Encryption {
    /**
     * Encrypts a certain information
     * @param source Information that has to be encrypted
     * @param key The key which will be used to encrypt the information
     * @return The encrypted information
     */
    public byte[] encrypt(byte[] source, byte[] key)
    {
        var result = new byte[source.length];
        for (int i = 0; i < source.length; i++)
        result[i] = (byte)(source[i] + key[0]);
        return result;
    }
    /**
     * Decrypts a certain information
     * @param source Information that has to be decrypted
     * @param key The key which was used to encrypt the information
     * @return The decrypted information
     */
    public byte[] decrypt(byte[] source, byte[] key)
    {
        return encrypt(source, new byte[] { (byte) -key[0] });
    }
    /**
     * @return Strength of the Caesar's cipher
     */
    public int strength() {
        return 1;
    }
}

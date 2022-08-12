package serviceLoader;
import java.io.UnsupportedEncodingException;
import java.util.*;

// This shit doesnt work with anything but Red hat extension, fuck it

public class SLoader {
    public static ServiceLoader<Encryption> crypt = ServiceLoader.load(Encryption.class);

    public static Encryption getCipher(int minStrength)
    {
        for (Encryption cipher : crypt) // implicitly calls cipherLoader.iterator()
        {
            if (cipher.strength() >= minStrength) return cipher;
        }
        return null;
    }

    public static void main(String ... args) throws UnsupportedEncodingException{
        System.out.println("The program is running");
        Encryption cipher = getCipher(1);
        System.out.println("Strength: " + cipher.strength() + "; Name: " + cipher.getClass().getName());
    }
}


package cwiczenie1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CIPHER CODER/DECODER: ");

        System.out.print("Enter the text to encode: ");
        String text = scanner.nextLine();

        System.out.print("Enter the shift key: ");
        int shift = scanner.nextInt();
        scanner.nextLine();

        String encodedText = cipherEncode(text, shift);
        System.out.println("Encoded text: " + encodedText);

        String decodedText = CipherDecode(encodedText, shift);
        System.out.println("Decoded text: " + decodedText);

        System.out.println("VIGENERE CODER/DECODER: ");

        System.out.println("Enter the text to encode: ");
        text = scanner.nextLine();

        System.out.println("Enter the key: ");
        String key = scanner.nextLine();

        encodedText = vigenereEncode(text, key);
        System.out.println("Encoded text: " + encodedText);

        decodedText = vigenereDecode(encodedText, key);
        System.out.println("Decoded text: " + decodedText);

        System.out.println("Break Cipher: ");

        System.out.println("Enter text to break: ");
        encodedText = scanner.nextLine();

        breakCipher(encodedText);

        scanner.close();
    }

    public static String cipherEncode(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char shiftedChar = (char) (((ch - (Character.isUpperCase(ch) ? 'A' : 'a')) + shift) % 26 + (Character.isUpperCase(ch) ? 'A' : 'a'));
                result.append(shiftedChar);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String CipherDecode(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char shiftedChar = (char) (((ch - (Character.isUpperCase(ch) ? 'A' : 'a')) - shift + 26) % 26 + (Character.isUpperCase(ch) ? 'A' : 'a'));
                result.append(shiftedChar);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String vigenereEncode(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char shiftedChar = (char) ((Character.toLowerCase(ch) - 'a' +
                        Character.toLowerCase(key.charAt(j % key.length())) - 'a') % 26 + 'a');
                if (Character.isUpperCase(ch)) {
                    shiftedChar = Character.toUpperCase(shiftedChar);
                }
                result.append(shiftedChar);

                j++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String vigenereDecode(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char shiftedChar = (char) ((Character.toLowerCase(ch) - 'a' -
                        Character.toLowerCase(key.charAt(j % key.length())) + 'a') % 26 + 'a');
                if (Character.isUpperCase(ch)) {
                    shiftedChar = Character.toUpperCase(shiftedChar);
                }
                result.append(shiftedChar);

                j++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void breakCipher(String ciphertext) {
        for (int i = 1; i <= 26; i++) {
            String plaintext = decrypt(ciphertext, i);
            System.out.println("Key = " + i + ": " + plaintext);
        }
    }

    private static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) ((c - key - 'A' + 26) % 26 + 'A');
                plaintext.append(shifted);
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }

}
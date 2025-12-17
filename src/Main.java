import java.io.*;
import java.util.Scanner;

public class Main {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        shift = ((shift % 26) + 26) % 26;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char newChar = (char) ((c - base + shift) % 26 + base);
                result.append(newChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        shift = ((shift % 26) + 26) % 26;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char newChar = (char) ((c - base - shift + 26) % 26 + base);
                result.append(newChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void allPossible(String text) {
        for (int i = 1; i <= 25; i++) {
            String result = decrypt(text, i);

            System.out.println("Shift " + i + ": " + result);
        }
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }

        br.close();
        return result.toString();
    }

    public static void writeFile(String fileName, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(content);
        bw.close();
    }

    public static void encryptFile(String fileName, String outputFile, int shift) throws IOException {
        String text = readFile(fileName);
        String result = encrypt(text, shift);
        writeFile(outputFile, result);
    }

    public static void decryptFile(String fileName, String outputFile, int shift) throws IOException {
        String text = readFile(fileName);
        String result = decrypt(text, shift);
        writeFile(outputFile, result);
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("---Text Encrypter---");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Find Decrypted Text");
            System.out.println("4. Encrypt File");
            System.out.println("5. Decrypt File");
            System.out.println("6. EXIT");

            System.out.print("Please enter your option (1, 2, 3, 4, 5, 6): ");
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    scan.nextLine();
                    System.out.print("Enter Text: ");
                    String text = scan.nextLine();
                    System.out.print("Please enter your shift value (0-25): ");
                    int shift = scan.nextInt();

                    String encrypted = encrypt(text, shift);
                    System.out.println("Encrypted text: " + encrypted);
                    System.out.println("\n");
                    break;

                case 2:
                    scan.nextLine();
                    System.out.print("Enter Encrypted Text: ");
                    String text2 = scan.nextLine();
                    System.out.print("Please enter your shift value (0-25): ");
                    int shift2 = scan.nextInt();

                    String decrypted = decrypt(text2, shift2);
                    System.out.println("Decrypted text: " + decrypted);
                    System.out.println("\n");
                    break;

                case 3:
                    scan.nextLine();
                    System.out.print("Enter Encrypted Text: ");
                    String text3 = scan.nextLine();
                    allPossible(text3);
                    System.out.println("\n");
                    break;

                case 4:
                    scan.nextLine();
                    System.out.print("Enter Input File Name: ");
                    String fileName = scan.nextLine();

                    System.out.print("Enter Output File Name: ");
                    String outputFileName = scan.nextLine();

                    System.out.print("Please enter your shift value (0-25): ");
                    int shift4 = scan.nextInt();

                    encryptFile(fileName, outputFileName, shift4);
                    System.out.println("File Encrypted.");
                    System.out.println("\n");
                    break;

                case 5:
                    scan.nextLine();
                    System.out.print("Enter Input File Name: ");
                    String fileNamed = scan.nextLine();

                    System.out.print("Enter Output File Name: ");
                    String outputFileNamed = scan.nextLine();

                    System.out.print("Please enter your shift value (0-25): ");
                    int shift5 = scan.nextInt();

                    decryptFile(fileNamed, outputFileNamed, shift5);
                    System.out.println("File Decrypted.");
                    System.out.println("\n");
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
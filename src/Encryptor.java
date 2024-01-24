import java.io.*;
import java.util.Scanner;

public class Encryptor {
    private final Alphabet alphabet;

    public Encryptor(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    private int setKey(){
        System.out.println("выберите ключь шифрования");
        Scanner scanner =new Scanner(System.in);
        return scanner.nextInt();
    }
    public void encrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {


            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String encryptedContent = encrypt(content.toString(), setKey());

            System.out.print("Введите путь к  результатирующему файлу: ");

            String outputFilePath = scanner.nextLine();
            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write(encryptedContent);
                System.out.println("Файл успешно зашифрован и сохранен по пути: " + outputFilePath);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }


    private String encrypt(String input, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch) || ch == ' ' || ch == '\n') {
                for (int i = 0; i < alphabet.getRussianAlphabet().length; i++) {
                    if (Character.toLowerCase(ch) == alphabet.getRussianAlphabet()[i]) {
                        int encryptedIndex = (i + shift) % alphabet.getRussianAlphabet().length;
                        char encryptedChar = (Character.isUpperCase(ch)) ? Character.toUpperCase(alphabet.getRussianAlphabet()[encryptedIndex]) : alphabet.getRussianAlphabet()[encryptedIndex];
                        result.append(encryptedChar);
                        break;
                    }
                }
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }


}

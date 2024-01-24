import java.io.*;
import java.util.Scanner;


public class DecryptionMain {
    private final Alphabet alphabet;

    public DecryptionMain(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    private int setKey(){
        System.out.println("выберите ключь шифрования");
        Scanner scanner =new Scanner(System.in);
        return scanner.nextInt();
    }

        public void decryptor() {
            System.out.print("Введите путь к файлу для дешифровки: ");
            String filePath = new Scanner(System.in).nextLine();

            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }


                String decryptedContent = encrypt(content.toString(), -setKey());
                System.out.print("Введите путь к результатирующему файлу: ");


                String outputFilePath = new Scanner(System.in).nextLine();
                try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                    fileWriter.write(decryptedContent);
                    System.out.println("Файл успешно дешифрован и сохранен по пути: " + outputFilePath);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String encrypt(String input, int shift) {
            StringBuilder result = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (Character.isLetter(ch)) {
                    for (int i = 0; i <  alphabet.getRussianAlphabet().length; i++) {
                        if (Character.toLowerCase(ch) ==  alphabet.getRussianAlphabet()[i]) {
                            int decryptedIndex = (i - shift +  alphabet.getRussianAlphabet().length) %  alphabet.getRussianAlphabet().length;
                            char decryptedChar = (Character.isUpperCase(ch)) ? Character.toUpperCase( alphabet.getRussianAlphabet()[decryptedIndex]) :  alphabet.getRussianAlphabet()[decryptedIndex];
                            result.append(decryptedChar);
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



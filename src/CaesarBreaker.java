import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

    public class CaesarBreaker {
        private final Alphabet alphabet;

        public CaesarBreaker(Alphabet alphabet) {
            this.alphabet = alphabet;
        }
        public void bruteforce() {
            System.out.print("Введите путь к файлу для взлома: ");
            String filePath = new Scanner(System.in).nextLine();

            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }


                for (int shift = 0; shift < alphabet.getRussianAlphabet().length; shift++) {
                    String decryptedContent = decrypt(content.toString(), shift);
                    System.out.println("Попытка с ключом " + shift + ":");
                    System.out.println(decryptedContent);
                    System.out.println("-------------------------------");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String decrypt(String input, int shift) {
            StringBuilder result = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (Character.isLetter(ch) || ch == ' ' || ch == '\n') {
                    for (int i = 0; i < alphabet.getRussianAlphabet().length; i++) {
                        if (Character.toLowerCase(ch) == alphabet.getRussianAlphabet()[i]) {
                            int decryptedIndex = (i - shift + alphabet.getRussianAlphabet().length) % alphabet.getRussianAlphabet().length;
                            char decryptedChar = (Character.isUpperCase(ch)) ? Character.toUpperCase(alphabet.getRussianAlphabet()[decryptedIndex]) : alphabet.getRussianAlphabet()[decryptedIndex];
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



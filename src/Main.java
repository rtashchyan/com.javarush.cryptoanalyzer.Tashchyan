import java.util.Scanner;


        public class Main{


 
            public static void main(String[] args) {
                System.out.println("выберите опцию"+ "\n"+ "1.шифрование"+ "\n" + "2.дешифрование" +"\n" + "3.брутфорс" + "\n"+ "0 чтобы выйти");


                Alphabet alphabet = new Alphabet();


                Scanner scanner = new Scanner(System.in);
                int command = scanner.nextInt();
                while (true) {
                    if (command == 1) {
                        Encryptor encryptor = new Encryptor(alphabet);

                        encryptor.encrypt();
                        break;
                    } else if (command ==2) {
                        DecryptionMain decryptionMain = new DecryptionMain(alphabet);
                        decryptionMain.decryptor();
                        break;
                    } else if(command  == 3) {
                        CaesarBreaker caesarBreaker = new  CaesarBreaker(alphabet);
                        caesarBreaker.bruteforce();
                        break;
                    } else if (command == 0) {
                        System.out.println("программа завершена");
                        break;

                    } else{
                        throw new RuntimeException("неверная команда");
                    }
                }







            }
        }




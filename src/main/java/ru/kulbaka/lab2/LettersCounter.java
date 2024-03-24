package ru.kulbaka.lab2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LettersCounter {
    public static void countLetters(String inputFileName, String outputFileName){
        File inputFile = new File(inputFileName);
        if (!inputFile.exists()){
            System.out.println("Файл для чтения не существует!");
            return;
        }
        Map<Character, Integer> alphabet = readLetters(inputFile);
        File outputFile = new File(outputFileName);
        if (!outputFile.exists()){
            System.out.println("Файл для записи не существует!");
            return;
        }
        writeLetters(alphabet, outputFile);
    }

    private static Map<Character, Integer> readLetters(File inputFile){
        Map<Character, Integer> alphabet = new HashMap<>();
        try (FileReader fileReader = new FileReader(inputFile)) {
            int symbol = fileReader.read();
            while (symbol != -1){
                if (alphabet.containsKey((char)symbol)){
                    int oldLetterCount = alphabet.get((char)symbol);
                    alphabet.replace((char)symbol, ++oldLetterCount);
                } else {
                    alphabet.put((char)symbol, 1);
                }
                symbol = fileReader.read();
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла!");
        }
        return alphabet;
    }

    private static void writeLetters(Map<Character, Integer> alphabet, File outputFile){
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (char key = 'a'; key <= 'z'; key++) {
                int count = alphabet.getOrDefault(key, 0);
                writer.write(key + ": " + count + "\n");
            }
            for (char key = 'A'; key <= 'Z'; key++) {
                int count = alphabet.getOrDefault(key, 0);
                writer.write(key + ": " + count + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }
}

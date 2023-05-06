package util;

import convert.Converter;
import convert.impl.JsonConverter;
import convert.impl.XmlConverter;
import dataFormat.DataFormatFactory;
import dataFormat.factoryImpl.JsonDataFormatFactory;
import dataFormat.factoryImpl.XmlDataFormatFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Scanner;

public class UserInterface {
    private final DataFormatFactory jsonFactory;
    private final DataFormatFactory xmlFactory;

    public UserInterface() {
        jsonFactory = new JsonDataFormatFactory();
        xmlFactory = new XmlDataFormatFactory();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Виводимо доступні формати
        System.out.println("Доступні формати:");
        System.out.println("1. JSON");
        System.out.println("2. XML");

        // Вибираємо вхідний формат
        System.out.print("Введіть номер вхідного формату: ");
        int inputFormat = scanner.nextInt();

        // Вибираємо вихідний формат
        System.out.print("Введіть номер вихідного формату: ");
        int outputFormat = scanner.nextInt();

        // Ініціалізуємо конвертер залежно від вибраних форматів
        Converter converter;
        if (inputFormat == 1 && outputFormat == 2) {
            converter = new JsonConverter(xmlFactory);
        } else if (inputFormat == 2 && outputFormat == 1) {
            converter = new XmlConverter(jsonFactory);
        } else {
            System.out.println("Невірно вибрані формати!");
            return;
        }

        // Вводимо шлях до вхідного файлу
        System.out.print("Введіть шлях до вхідного файлу: ");
        String inputFilepath = scanner.next();

        // Вводимо шлях до вихідного файлу
        System.out.print("Введіть шлях до вихідного файлу: ");
        String outputFilepath = scanner.next();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilepath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilepath))) {

            // Зчитуємо дані з вхідного файлу
            StringBuilder inputData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputData.append(line);
            }

            // Конвертуємо дані та записуємо результат в вихідний файл
            String outputData = converter.convert(String.valueOf(inputData));
            writer.write(outputData);

            System.out.println("Конвертація завершена!");
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}

package util;

import convert.Converter;
import convert.impl.JsonConverter;
import convert.impl.XmlConverter;
import dataFormat.DataFormatFactory;
import validation.DataFormatValidatorVisitor;
import validation.ValidatorVisitor;
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
    private final ValidatorVisitor validatorVisitor;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        validatorVisitor = new DataFormatValidatorVisitor();
        jsonFactory = new JsonDataFormatFactory();
        xmlFactory = new XmlDataFormatFactory();
    }
    public void convertFiles(int inputFormat, int outputFormat, String inputFilepath, String outputFilepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilepath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilepath))) {
            // Зчитуємо дані з вхідного файлу
            StringBuilder inputData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputData.append(line);
            }
            // Конвертуємо дані та записуємо результат в вихідний файл
            Converter converter = getConverter(inputFormat, outputFormat);
            String outputData = converter.convert(String.valueOf(inputData));
            writer.write(outputData);

            System.out.println("Конвертація завершена!");
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public Converter getConverter(int inputFormat, int outputFormat) {
        Converter converter;
        if (inputFormat == 1 && outputFormat == 2) {
            converter = new JsonConverter(xmlFactory, validatorVisitor);
        } else if (inputFormat == 2 && outputFormat == 1) {
            converter = new XmlConverter(jsonFactory, validatorVisitor);
        } else {
            throw new IllegalArgumentException("Невірно вибрані формати!");
        }
        return converter;
    }

    public void printAvailableFormats() {
        System.out.println("Доступні формати:");
        System.out.println("1. JSON");
        System.out.println("2. XML");
    }

    public void promptInputFormat() {
        System.out.print("Введіть номер вхідного формату: ");
    }

    public void promptOutputFormat() {
        System.out.print("Введіть номер вихідного формату: ");
    }

    public void promptInputFilePath() {
        System.out.print("Введіть шлях до вхідного файлу: ");
    }

    public void promptOutputFilePath() {
        System.out.print("Введіть шлях до вихідного файлу: ");
    }

    public int getInputFormat(){
        return scanner.nextInt();
    }
    public int getOutputFormat(){
        return scanner.nextInt();
    }
    public String getOutputFile(){
        return scanner.next();
    }
    public String getInputFile(){
        return scanner.next();
    }
}

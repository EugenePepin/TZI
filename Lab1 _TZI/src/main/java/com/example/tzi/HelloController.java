package com.example.tzi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;
    @FXML
    private TextField shiftField;
    @FXML
    private TextArea inputArea;
    @FXML
    private TextArea outputArea;
    @FXML
    private Button loadButton;
    @FXML
    private Label error;

    private File selectedFile;

    @FXML
    private void handleLoadButtonAction() {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                inputArea.setText(fileContent.toString());
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleEncryptButtonAction() {
        error.setText(""); // Очистка повідомлення про помилку перед новою операцією
        try {
            int shift = Integer.parseInt(shiftField.getText());
            String inputText = inputArea.getText();
            String outputText = caesarCipher(inputText, shift); // Шифрування з позитивним shift
            outputArea.setText(outputText);
        } catch (NumberFormatException e) {
            error.setText("Ви залишили поле пустим, або ввели букву. Спробуйте ще раз...");
        }
    }

    @FXML
    private void handleDecryptButtonAction() {
        error.setText(""); // Очистка повідомлення про помилку перед новою операцією
        try {
            int shift = Integer.parseInt(shiftField.getText());
            String inputText = inputArea.getText();
            String outputText = caesarCipher(inputText, -shift); // Розшифрування з негативним shift
            outputArea.setText(outputText);
        } catch (NumberFormatException e) {
            error.setText("Ви залишили поле пустим, або ввели букву. Спробуйте ще раз...");
        }
    }

    private String caesarCipher(String input, int shift) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                int base = Character.isUpperCase(c) ? 'A' : 'a';
                output.append((char) ((c - base + shift + 26) % 26 + base));
            } else {
                output.append(c); // Додати символ без змін, якщо це не буква
            }
        }
        return output.toString();
    }
}

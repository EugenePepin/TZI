package com.example.lab2_tzi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloController {
    @FXML
    private final String alphabet = "абвгґдеєжзииїйклмнопрстуфхцчшщьюя";

    @FXML
    private TextField keyField;
    @FXML
    private TextArea inputArea;
    @FXML
    private TextArea outputArea;
    @FXML
    private Button loadButton;
    @FXML
    private void clearButton(){
        inputArea.setText("");
    }




    @FXML
    private void handleLoadButtonAction() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
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
    private void encrypt() {
        String key = keyField.getText();
        String inputText = inputArea.getText();

        if (!inputText.isEmpty() && !key.isEmpty()) {
            String encryptedText = vigenerEncrypt(inputText, key);
            outputArea.setText(encryptedText);
        }
    }

    @FXML
    private void decrypt() {
        String key = keyField.getText();
        String inputText = inputArea.getText();

        if (!inputText.isEmpty() && !key.isEmpty()) {
            String decryptedText = vigenerDecrypt(inputText, key);
            outputArea.setText(decryptedText);
        }
    }

    private String vigenerEncrypt(String inputText, String key) {
        StringBuilder sb = new StringBuilder();
        int keyIndex = 0;
        for (char c : inputText.toCharArray()) {
            int plainCharIndex = alphabet.indexOf(c);
            if (plainCharIndex != -1) {
                int keyCharIndex = alphabet.indexOf(key.charAt(keyIndex));
                int cipherCharIndex = (plainCharIndex + keyCharIndex) % alphabet.length();
                char cipherChar = alphabet.charAt(cipherCharIndex);
                sb.append(cipherChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }

    private String vigenerDecrypt(String inputText, String key) {
        StringBuilder sb = new StringBuilder();
        int keyIndex = 0;
        for (char c : inputText.toCharArray()) {
            int cipherCharIndex = alphabet.indexOf(c);
            if (cipherCharIndex != -1) {
                int keyCharIndex = alphabet.indexOf(key.charAt(keyIndex));
                int plainCharIndex = (cipherCharIndex - keyCharIndex + alphabet.length()) % alphabet.length();
                char plainChar = alphabet.charAt(plainCharIndex);
                sb.append(plainChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }

}

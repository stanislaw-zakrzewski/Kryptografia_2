package sample;

import data.SaveToFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import streamCipher.StreamCipher;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Controller {
    private File file;
    private SaveToFile saveToFile;
    private String end;

    @FXML
    public TextArea prime, g, seed, messages, selectedFile;
    @FXML
    public Button browse, cipher;

    public Controller() {
        saveToFile = new SaveToFile();
    }

    public void pressBrowse(ActionEvent event) {
        JFileChooser fc = new JFileChooser(new File("").getAbsolutePath());
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            selectedFile.setText("Selected File: " + file.getPath());
            messages.setText("File succesfuly loaded.");
            String s = file.getName();
            char[] pom = s.toCharArray();
            int pom2 = pom.length-1;
            while (pom[pom2] != '.') {
                pom2--;
            }
            end = s.substring(pom2);
        }
    }

    public void pressCipher() {
        long pL = Long.parseLong(prime.getText());
        long gL = Long.parseLong(g.getText());
        long sL = Long.parseLong(seed.getText());
        String ciphered = StreamCipher.process(getBytesFromFile(), pL, gL, sL);
        saveToFile.save("output" +  end, ciphered);
        messages.setText("File succesfuly ciphered.");
    }

    private String getBytesFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            for(Byte b : bytes) {
                stringBuilder.append(numberTo8Bits(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static String numberTo8Bits(byte number) {
        StringBuilder stringBuilder = new StringBuilder();
        int val = number;
        for (int i = 0; i < 8; i++) {
            stringBuilder.append((byte)((val & 128) == 0 ? 0 : 1));
            val <<= 1;
        }
        return stringBuilder.toString();
    }
}

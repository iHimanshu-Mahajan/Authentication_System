package com.example;

// Copyright (©) 2023 Himanshu Mahajan

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {
    final static File fname = new File("Database.txt");
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private PasswordField passwordTxtField;
    @FXML
    private PasswordField confirmPasswordTxtField;

    @FXML
    protected void signup() {
        String name = nameTxtField.getText();

        String email = emailTxtField.getText();
        email = email.trim();

        String password = passwordTxtField.getText();
        password = password.trim();

        String rewritePassword = confirmPasswordTxtField.getText();
        rewritePassword = rewritePassword.trim();

        if (name.length() <= 0 || email.length() <= 0 || password.length() <= 0 || rewritePassword.length() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            displayErrorMessage(alert, "Information", "Fill All the Fields!", "Fill all the fields name, age, email, password and confirm password (Input Required!).");
        }
        else {
            if (existingEmail(fname, email)) {
                System.out.println("Email already exists...");
            }
            else if (checkName(name) && checkEmail(email) && checkLength(password) && checkNumber(password) && checkLowercase(password) && checkUppercase(password) && checkSpecialCharacter(password) && password.equals(rewritePassword)) {
                createFile(fname);
                email = display(email);
                String contents =  "Email: " + email + "\n" + "Password: " + password + "\n\n";
                writeFile(fname, contents);
                System.out.println("Redirecting to the main page...");
            }
            else {
                if (!checkName(name)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Invalid Name!", "Hey, name must only have characters nothing else.");
                }
                else if (!checkEmail(email)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Email not Found!", "Hey, name must end with '@gmail.com' or '@hotmail.com' or '@facebook.com' or '@yahoo.com'.");
                }
                else if (!checkLength(password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Invalid Password Length!", "Hey, the password length must be greater than or equal to 8 characters.");
                }
                else if (!checkLowercase(password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Password requires one Lowercase character!", "Hey, the password must have at least one lowercase character.");
                }
                else if (!checkUppercase(password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Password requires one Uppercase character!", "Hey, the password must have at least one uppercase character.");
                }
                else if (!checkNumber(password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Password requires one numeric number!", "Hey, the password must have at least one numeric number.");
                }
                else if (!checkSpecialCharacter(password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Password requires one special character!", "Hey, the password must have at least one special character.");
                }
                else if (!(password.equals(rewritePassword))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    displayErrorMessage(alert, "Error", "Password doesn't Match!", "Hey, the password doesn't match. NOTE: Password field and Confirm Password field must be same");
                }
                else {
                    System.out.println("Hey, this code will never be executed...");
                }
            }
        }
    }
    @FXML
    protected void signin(ActionEvent e) throws IOException {
        FXMLLoader secondScreen = new FXMLLoader(App.class.getResource("signin.fxml"));
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(secondScreen.load(), 700, 500));
        primaryStage.show();
    }
    @FXML
    protected boolean checkName(String name) {
        for (int index = 0; index < name.length(); index++) {
            char character = name.charAt(index);
            if (!(character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z')) {
                return false;
            }
        }
        return true;
    }


    @FXML
    protected boolean checkEmail(String email) {
        if (email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@facebook.com") || email.endsWith("@yahoo.com")) {
            return true;
        }
        return false;
    }
    @FXML
    protected String display(String email) {
        String mail = email.trim();
        mail = email.toLowerCase();
        return mail;
    }
    @FXML
    protected boolean checkLength(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        }
        return false;
    }
    @FXML
    protected boolean checkLowercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character >= 'a' && character <= 'z') {
                return true;
            }
        }
        return false;
    }
    @FXML
    protected boolean checkUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                return true;
            }
        }
        return false;
    }
    @FXML
    protected boolean checkNumber(String password) {
        for (int index = 0; index < password.length(); index++) {
            char character = password.charAt(index);
            for (int i = 0; i < password.length(); i++) {
                if (character >= '0' && character <= '9') {
                    return true;
                }
            }
        }
        return false;
    }
    @FXML
    protected boolean checkSpecialCharacter(String password) {
        String specialChar = "`~!@#$%^&*()-+=_{}[].><?/|,\"";
        for (int index = 0; index < password.length(); index++) {
            char character = password.charAt(index);
            for (int i = 0; i < specialChar.length(); i++) {
                if (character == specialChar.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void displayErrorMessage(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void createFile(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (Exception exception) {
            System.out.println("Sorry can't find the file.");
        }
    }
    @FXML
    protected void writeFile(File path, String contents) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(contents);
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("Sorry can't find the file.");
        }
    }

    @FXML
    protected boolean existingEmail(File file, String email) {
        try {
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String mail = read.nextLine();
                if (mail.startsWith("Email: ")) {
                    String emailExists = mail.substring("Email: ".length());
                    if (emailExists.equals(email)) {
                        read.close();
                        return true;
                    }
                }
            }
            read.close();
        }
        catch (Exception exception) {
            System.out.println("Sorry can't find the file");
        }
        return false;
    }
}
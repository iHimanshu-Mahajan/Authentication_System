package com.example;

// Copyright (©) 2023 Himanshu Mahajan

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class PrimaryController {
    final static File fname = new File("Database.txt");
    @FXML
    private TextField emailTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    protected void signIn() {
        String email = emailTxtField.getText();
        email = email.trim();
        email = email.toLowerCase();

        String password = passwordTxtField.getText();
        password = password.trim();


        if (email.length() <= 0 || password.length() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            displayErrorMessage(alert, "Information", "Fill both the Fields!", "Fill both the fields email and password (Input required!).");
        }
        else {
            if (authenticatingUser(fname, email, password)) {
                System.out.println("Redirecting to next page...");
            }
            else if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@facebook.com") || email.endsWith("@yahoo.com"))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                displayErrorMessage(alert, "Error", "Invalid Email Address", "The email must end with a '@gmail.com' or '@hotmail.com' or '@facebook.com' or '@yahoo.com'.");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                displayErrorMessage(alert, "Error", "Email not found.", "Wrong Password or Email. Couldn't find your Spotify Account");
            }
        }
    }
    @FXML
    protected void signUp(ActionEvent e) throws IOException {
        FXMLLoader secondScreen = new FXMLLoader(App.class.getResource("signup.fxml"));
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(secondScreen.load(), 700, 500));
        primaryStage.show();
    }
    @FXML
    protected void decorative() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        displayErrorMessage(alert, "Display Purposes", "Display Purposes", "Forget your Password?' is used solely to make the User Interface more appealing, giving the impression that the user is on an authorized app. However, in reality, this is not a functional 'Forgot Your Password' feature.");
    }
    @FXML
    protected boolean authenticatingUser(File file, String email, String password) {
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String emailLine = read.nextLine();
                if (emailLine.startsWith("Email: ")) {
                    String emailExists = emailLine.substring("Email: ".length());
                    if (emailExists.equals(email)) {
                        if (read.hasNextLine()) {
                            String passwordLine = read.nextLine();
                            if (passwordLine.startsWith("Password: ")) {
                                String checkingPassword = passwordLine.substring("Password: ".length());
                                if (checkingPassword.equals(password)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            read.close();
        }
        catch (Exception exception) {
            System.out.println("Sorry!!!");
        }
        return false;
    }
    @FXML
    protected void displayErrorMessage(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

# User Authentication Application

🔑 User Authentication App is a straight forward JavaFX application that offers user authentication, allowing users to either sign in or create a new account.

## Features

- **Sign In** 🔐: Existing users can log in using their registered email and password.
- **Sign Up** ✍️: New users can create an account with a name, email, and password.
- **Password Validation** 🔑: Passwords must meet specific criteria, including length, lowercase and uppercase characters, numbers, and special characters.
- **Email Validation** 📧: Email addresses are validated to ensure they end with supported domains.
- **User-Friendly Interface** 🖥️: The app features a user-friendly interface with visually appealing elements.

## Controllers

### PrimaryController

The `PrimaryController` handles the sign-in functionality.

#### Methods

- `signIn()` 🔓: Processes the sign-in attempt, validates user input, and checks for authentication.
- `signUp(ActionEvent e)` 🚀: Redirects users to the sign-up page.
- `decorative()` 🖼️: Displays a message for display purposes.
- `authenticatingUser(File file, String email, String password)` 🔑🔓: Authenticates the user using the provided email and password.
- `displayErrorMessage(Alert alert, String title, String header, String content)` ❗: Displays error messages.

### SecondaryController

The `SecondaryController` handles the sign-up functionality.

#### Methods

- `signup()` ✍️🚀: Processes the sign-up attempt, validates user input, and creates a new account if all criteria are met.
- `signin(ActionEvent e)` 🔓🚀: Redirects users to the sign-in page.
- `checkName(String name)` 📛: Validates the user's name.
- `checkEmail(String email)` 📧: Validates the user's email address.
- `display(String email)` 📧: Formats and returns the email address.
- `checkLength(String password)` 🔑: Validates the password length.
- `checkLowercase(String password)` 🔡: Validates the presence of lowercase characters in the password.
- `checkUppercase(String password)` 🔠: Validates the presence of uppercase characters in the password.
- `checkNumber(String password)` 🔢: Validates the presence of numeric digits in the password.
- `checkSpecialCharacter(String password)` 🎉: Validates the presence of special characters in the password.
- `createFile(File file)` 📂: Creates a data file if it does not exist.
- `writeFile(File path, String contents)` 📝: Writes user data to the data file.
- `existingEmail(File file, String email)` 📧🔍: Checks if the provided email already exists in the data file.

## FXMLs

### signin.fxml

The `signin.fxml` file represents the sign-in page.

### signup.fxml

The `signup.fxml` file represents the sign-up page.

## Usage

To run the Music App, simply open it in a JavaFX environment and follow the on-screen instructions.

## Author

- **Himanshu Mahajan**

## License

This project is licensed under **Himanshu Mahajan** [All Copyright &copy reserved].

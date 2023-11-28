package GuessTheNumberGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

/**
 *
 * @author sanchit
 */

public class GuessTheNumberGame extends Application {
    
    private int randomNumber;
    private int attempts;
    private Label messageLabel;
    private TextField guessTextField;
    private Button guessButton;
    private Button newNumberButton;
    private Button tryAgainButton;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;

        // Create UI elements
        Label titleLabel = new Label("Guess the Number Game");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        messageLabel = new Label("Enter a number between 1 and 100:");
        guessTextField = new TextField();
        guessButton = new Button("Guess");
        guessButton.setVisible(true);
        newNumberButton = new Button("Change Number");
        newNumberButton.setVisible(true);
        tryAgainButton = new Button("Try Again");
        tryAgainButton.setVisible(false);
        guessButton.setOnAction(e -> checkGuess());
        newNumberButton.setOnAction(e -> newNumber());
        tryAgainButton.setOnAction(e -> tryAgain());

        // Create a layout and add UI elements
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titleLabel, messageLabel, guessTextField, guessButton, newNumberButton, tryAgainButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Guess the Number");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (guess == randomNumber) {
                messageLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                guessTextField.setDisable(true);
                guessButton.setVisible(false);
                newNumberButton.setVisible(false);
                tryAgainButton.setVisible(true);
            } else if (guess < randomNumber) {
                messageLabel.setText("Too low. Try again:");
            } else {
                messageLabel.setText("Too high. Try again:");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid input. Enter a number between 1 and 100:");
        }
        guessTextField.clear();
    }
    
    private void newNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
    }
    
    private void tryAgain() {
        guessTextField.setDisable(false);
        guessButton.setVisible(true);
        newNumberButton.setVisible(true);
        tryAgainButton.setVisible(false);
        messageLabel.setText("Enter a number between 1 and 100:");
        guessTextField.clear();
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package prog3;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    /**
     * The method that will create a stage to launch the GUI
     * It's layout is assigned from .fxml file
     * @param primaryStage used as access tool to create a scene!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("prog3.fxml"));
        primaryStage.setTitle("Program 3 - Tuition Manager");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }

    /**
     * Main driver that gives details on what commands to use.
     * Calls launch() that will call the start() and launch the GUI.
     * @param args additional arguments unused.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

package prog4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Main class of the code. Has the starting stage of code
 * extends Application Class for making a Window Style Project
 * @author Krushn Gor, Jake Ippolito
 */
public class Main extends Application {

    /**
     * Main method- will call launch() method to launch the application in start() method!
     * @param args Additional arguments unused.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Override method from Application class
     * Use to pop up a window
     * @param primaryStage intakes a Stage object to pop up a new window
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage1;
        stage1 = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Build.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("Build.fxml"));
        Parent root1 = loader.load();

        BuildController obj = loader.getController();
        obj.setMainButton();
        stage1.setTitle("Make Your Pizza");

        Scene sceneStage1 = new Scene(root1, 801, 653);
        stage1.setScene(sceneStage1);

        stage1.show();


    }
}

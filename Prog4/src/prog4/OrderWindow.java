package prog4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This window has Order list printed on it!
 * @author Krushn Gor, Jake Ippolito
 */
public class OrderWindow {
    static Stage stage2;

    /**
     * Contains code to launch a window
     * @throws IOException if JavaFX throws an IOException
     */
    public void display() throws IOException {
        stage2 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Order.fxml"));
        //Parent root2 = FXMLLoader.load(OrderWindow.class.getResource("Order.fxml"));

        Parent root2 = loader.load();

        OrderController obj = loader.getController();
        obj.setOrderList();

        //Block input event with other windows until this window is closed!
        stage2.initModality(Modality.APPLICATION_MODAL);

        stage2.setTitle("Your Order");
        Scene sceneStage2 = new Scene(root2, 801, 653);
        stage2.setScene(sceneStage2);

        //Not the regular show! Will make you wait until the window is hidden/closed!
        stage2.showAndWait();

        //NOTE: showAndWait() and initModality() are two different concept!
    }
}


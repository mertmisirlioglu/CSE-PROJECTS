import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mail/login.fxml"));
        Scene firstScene = new Scene(root);

        primaryStage.setTitle("Mail Client");
        primaryStage.setScene(firstScene);
        primaryStage.show();


    }
}

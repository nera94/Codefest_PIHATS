package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root1=FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root1);
			//((Node)e.getSource()).getScene().getWindow().hide();
			Stage st1=new Stage();
			//st1.setScene(new Scene(root1));
			//st1.initStyle(StageStyle.UNDECORATED);
			st1.setMaximized(true);
			scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
			st1.setScene(scene);
			st1.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

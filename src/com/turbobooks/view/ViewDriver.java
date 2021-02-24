package com.turbobooks.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class ViewDriver extends Application {
    
    	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	@Override
	public void start(Stage primaryStage) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/turbobooksview.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		primaryStage.setTitle("TurboBooks");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.getScene().getStylesheets()
				.add(getClass().getClassLoader().getResource("css/main-style.css").toExternalForm());
		primaryStage.show();
	}

	public static void main(String[] args) {
            Log4JInit.initializeLog4J();
            log.info ("\n----------------");
            launch(args);
	}
}

package com.turbobooks.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.turbobooks.view.additem.TurboBooksAddItemController;
import com.turbobooks.view.checkout.TurboBooksCheckoutController;
import com.turbobooks.view.removeitem.TurboBooksRemoveItemController;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TurboBooksDrawerController {

	@FXML
	private JFXButton checkoutBtn;

	@FXML
	private JFXButton addItemBtn;

	@FXML
	private JFXButton removeBtn;

	private TurboBooksMainController controller;

	@FXML
	public void initialize() {

	}

	public void setMainController(TurboBooksMainController controller) {
		this.controller = controller;
	}

	@FXML
	void fireAddItem(MouseEvent event) {
		Parent itemView = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/additemview.fxml"));

			itemView = (AnchorPane) loader.load();

			TurboBooksAddItemController turboBooksItemManagementController = loader.getController();
			turboBooksItemManagementController.setMainController(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.getMainContentBox().getChildren().clear();
		controller.getMainContentBox().getChildren().add(itemView);
		transitionWindow(itemView);

		addItemBtn.setStyle("-fx-background-color: #c2c4c6;");
		checkoutBtn.setStyle("-fx-background-color: transparent;");
		removeBtn.setStyle("-fx-background-color: transparent;");
	}

	@FXML
	void fireCheckout(MouseEvent event) {
		Parent checkoutView = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/checkoutview.fxml"));

			checkoutView = (AnchorPane) loader.load();

			TurboBooksCheckoutController turboBooksCheckoutController = loader.getController();
			turboBooksCheckoutController.setMainController(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.getMainContentBox().getChildren().clear();
		controller.getMainContentBox().getChildren().add(checkoutView);
		transitionWindow(checkoutView);

		checkoutBtn.setStyle("-fx-background-color: #c2c4c6;");
		addItemBtn.setStyle("-fx-background-color: transparent;");
		removeBtn.setStyle("-fx-background-color: transparent;");
	}

	@FXML
	void fireRemoveItem(MouseEvent event) {
		Parent removeItemView = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/removeitemview.fxml"));

			removeItemView = (AnchorPane) loader.load();

			TurboBooksRemoveItemController turboBooksRemoveItemController = loader.getController();
			turboBooksRemoveItemController.setMainController(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.getMainContentBox().getChildren().clear();
		controller.getMainContentBox().getChildren().add(removeItemView);
		transitionWindow(removeItemView);

		removeBtn.setStyle("-fx-background-color: #c2c4c6;");
		addItemBtn.setStyle("-fx-background-color: transparent;");
		checkoutBtn.setStyle("-fx-background-color: transparent;");
	}

	private void transitionWindow(Parent view) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), view);
		fadeTransition.setFromValue(0.0f);
		fadeTransition.setToValue(1.0f);
		fadeTransition.setCycleCount(1);
		fadeTransition.setAutoReverse(true);

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), view);
		translateTransition.setFromX(0);
		translateTransition.setToX(180);
		translateTransition.setCycleCount(1);
		translateTransition.setAutoReverse(true);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
		parallelTransition.setCycleCount(1);
		parallelTransition.play();
	}
}

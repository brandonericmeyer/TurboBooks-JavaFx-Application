package com.turbobooks.view;

import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TurboBooksMainController {

	@FXML
	private AnchorPane turboPane;

	@FXML
	private VBox mainContent;

	@FXML
	private Button checkoutBtn;

	@FXML
	private Button manageBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXSpinner spinner;

	@FXML
	private ImageView logo;

	@FXML
	public void initialize() {

		spinner.setVisible(false);

		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/drawerContent.fxml"));
		VBox box;
		try {
			box = loader.load();
			TurboBooksDrawerController controller = loader.getController();
			controller.setMainController(this);
			drawer.setSidePane(box);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		initializeMenu();
		initializeLogo();
	}

	private void initializeLogo() {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), logo);
		fadeTransition.setFromValue(0.0f);
		fadeTransition.setToValue(1.0f);
		fadeTransition.setCycleCount(1);
		fadeTransition.setAutoReverse(true);

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), logo);
		translateTransition.setFromX(0);
		translateTransition.setToX(150);
		translateTransition.setCycleCount(1);
		translateTransition.setAutoReverse(true);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
		parallelTransition.setCycleCount(1);
		parallelTransition.play();
	}

	private void initializeMenu() {
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			if (drawer.isOpened()) {
				drawer.close();
			} else {
				drawer.open();
			}
		});
	}

	@FXML
	void sayHello(MouseEvent event) {
		System.out.println("FIRE!");
	}

	public VBox getMainContentBox() {
		return mainContent;
	}

	public AnchorPane getTurboPane() {
		return turboPane;
	}

	public JFXSpinner getSpinner() {
		return spinner;
	}
}

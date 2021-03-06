package com.turbobooks.view.removeitem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.turbobooks.model.business.manager.DAOManager;
import com.turbobooks.model.business.manager.TurbobooksManager;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.itemservice.IItemManagerService;
import com.turbobooks.view.TurboBooksMainController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TurboBooksRemoveItemController {

	@FXML
	private JFXTextField uuid;

	@FXML
	private JFXButton submitBtn;

	@FXML
	private VBox removeItemBox;

	@FXML
	private JFXSpinner spinner;

	private TurboBooksMainController controller;

	private JFXSnackbar message;

	// private TurbobooksManager turboManager;

	private DAOManager daoManager;

	@FXML
	public void initialize() {
		// turboManager = TurbobooksManager.getInstance();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		daoManager = (DAOManager) context.getBean("daoManager");
		// daoManager = DAOManager.getInstance();

		initListeners();
		setLoading(false);
	}

	private void initListeners() {
		uuid.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[a-zA-Z0-9_]*$")) {
					uuid.setText(newValue.replaceAll("[^A-Za-z0-9]", ""));
					uuid.setStyle("-jfx-focus-color: red;");

					message = new JFXSnackbar(controller.getTurboPane());
					message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Invalid Format!  Alpha numeric only.")));
				} else {
					uuid.setStyle("-jfx-focus-color: #0ec9ed;");
				}
			}
		});
	}

	private void setLoading(boolean isLoading) {
		removeItemBox.setDisable(isLoading);
		spinner.setVisible(isLoading);
	}

	private void removeItem() {
		Item item = daoManager.getItem(IItemManagerService.NAME, uuid.getText());

		if (item != null) {
			boolean action = daoManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, "");

			if (action) {
				message = new JFXSnackbar(controller.getTurboPane());
				message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Item has been successfully removed!")));
				return;
			}
		}

		message = new JFXSnackbar(controller.getTurboPane());
		message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Removal Failed!  Item not found.")));
	}

	private boolean isFilledOut() {
		if (uuid.getText().length() <= 0) {
			message = new JFXSnackbar(controller.getTurboPane());
			message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("All fields are required!")));
			return false;
		}

		return true;
	}

	@FXML
	void fireSubmit(ActionEvent event) {
		if (isFilledOut()) {
			final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> setLoading(true));
			final KeyFrame kf2 = new KeyFrame(Duration.seconds(1), e -> removeItem());
			final KeyFrame kf3 = new KeyFrame(Duration.seconds(2), e -> setLoading(false));
			final Timeline timeline = new Timeline(kf1, kf2, kf3);
			Platform.runLater(timeline::play);
		}
	}

	@FXML
	void fireReset(MouseEvent event) {
		uuid.setStyle("-jfx-focus-color: #0ec9ed;");
	}

	public void setMainController(TurboBooksMainController controller) {
		this.controller = controller;
	}

}

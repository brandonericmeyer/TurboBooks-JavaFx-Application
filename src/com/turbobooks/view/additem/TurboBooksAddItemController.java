package com.turbobooks.view.additem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.turbobooks.model.business.manager.DAOManager;
import com.turbobooks.model.business.manager.TurbobooksManager;
import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Catalog;
import com.turbobooks.model.domain.Item;
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

public class TurboBooksAddItemController {

	@FXML
	private JFXTextField firstname;

	@FXML
	private JFXTextField isbn;

	@FXML
	private JFXComboBox<ItemType> type;

	@FXML
	private JFXTextField title;

	@FXML
	private JFXTextField uuid;

	@FXML
	private JFXTextField lastname;

	@FXML
	private JFXButton submitBtn;

	@FXML
	private JFXSpinner spinner;

	@FXML
	private VBox addItemBox;

	private TurboBooksMainController controller;

	private JFXSnackbar message;

	private DAOManager daoManager;

	public enum ItemType {
		BOOK("BOOK"), DISC("DISC"), VIDEO("VIDEO");

		private ItemType(final String type) {
			this.type = type;
		}

		public String type;

		public String getLabel() {
			return type;
		}

	}

	@FXML
	public void initialize() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		daoManager = (DAOManager) context.getBean("daoManager");
		// daoManager = DAOManager.getInstance();
		type.getItems().addAll(ItemType.BOOK, ItemType.DISC, ItemType.VIDEO);
		type.setValue(ItemType.BOOK);
		initListeners();
		setLoading(false);
	}

	private void initListeners() {
		firstname.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[a-zA-Z]+")) {
					firstname.setText(newValue.replaceAll("[^a-zA-Z]+", ""));
					firstname.setStyle("-jfx-focus-color: red;");

					message = new JFXSnackbar(controller.getTurboPane());
					message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Invalid Format!  Letters only.")));
				} else {
					firstname.setStyle("-jfx-focus-color: #0ec9ed;");
				}
			}
		});

		lastname.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[a-zA-Z]+")) {
					lastname.setText(newValue.replaceAll("[^a-zA-Z]+", ""));
					lastname.setStyle("-jfx-focus-color: red;");

					message = new JFXSnackbar(controller.getTurboPane());
					message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Invalid Format!  Letters only.")));
				} else {
					lastname.setStyle("-jfx-focus-color: #0ec9ed;");
				}
			}
		});

		isbn.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					isbn.setText(newValue.replaceAll("[^\\d]", ""));
					isbn.setStyle("-jfx-focus-color: red;");

					message = new JFXSnackbar(controller.getTurboPane());
					message.fireEvent(
							new SnackbarEvent(new JFXSnackbarLayout("Invalid Format!  ISBN should be numeric.")));
				} else if (newValue.length() > 10) {
					Platform.runLater(() -> {
						isbn.clear();
					});
					isbn.setStyle("-jfx-focus-color: red;");

					message = new JFXSnackbar(controller.getTurboPane());
					message.fireEvent(
							new SnackbarEvent(new JFXSnackbarLayout("Max Limit!  ISBN should be 10 digits.")));
				} else {
					isbn.setStyle("-jfx-focus-color: #0ec9ed;");
				}
			}
		});

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

	@FXML
	void fireReset(MouseEvent event) {
		isbn.setStyle("-jfx-focus-color: #0ec9ed;");
		firstname.setStyle("-jfx-focus-color: #0ec9ed;");
		lastname.setStyle("-jfx-focus-color: #0ec9ed;");
		uuid.setStyle("-jfx-focus-color: #0ec9ed;");
	}

	private void setLoading(boolean isLoading) {
		addItemBox.setDisable(isLoading);
		spinner.setVisible(isLoading);
	}

	private void addItem() {
		Item item = null;
		Catalog catalog = new Catalog(type.getValue().getLabel());

		if (type.getValue() == ItemType.BOOK) {
			item = new Book(catalog, isbn.getText(), firstname.getText(), lastname.getText(), title.getText(),
					uuid.getText(), "", true);
		} else if (type.getValue() == ItemType.DISC) {
			item = new Book(catalog, isbn.getText(), firstname.getText(), lastname.getText(), title.getText(),
					uuid.getText(), "", true);
		} else if (type.getValue() == ItemType.VIDEO) {
			item = new Book(catalog, isbn.getText(), firstname.getText(), lastname.getText(), title.getText(),
					uuid.getText(), "", true);
		}

		boolean action = daoManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, "");

		if (action) {
			message = new JFXSnackbar(controller.getTurboPane());
			message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Item has been successfully added!")));
		} else {
			message = new JFXSnackbar(controller.getTurboPane());
			message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Item was not stored successfully!")));
		}
	}

	private boolean isFilledOut() {
		if (isbn.getText().length() <= 0 || title.getText().length() <= 0 || firstname.getText().length() <= 0
				|| lastname.getText().length() <= 0 || uuid.getText().length() <= 0) {
			message = new JFXSnackbar(controller.getTurboPane());
			message.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("All fields are required!")));
			return false;
		}

		return true;
	}

	@FXML
	void fireSubmit(ActionEvent event) {
		if (isFilledOut()) {
			// Timeline is used to create a small delay so you can see progress indicator
			final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> setLoading(true));
			final KeyFrame kf2 = new KeyFrame(Duration.seconds(1), e -> addItem());
			final KeyFrame kf3 = new KeyFrame(Duration.seconds(2), e -> setLoading(false));
			final Timeline timeline = new Timeline(kf1, kf2, kf3);
			Platform.runLater(timeline::play);
		}
	}

	@FXML
	void setItemType(ActionEvent event) {
		if (type.getValue() == ItemType.BOOK) {
			type.setValue(ItemType.BOOK);
		} else if (type.getValue() == ItemType.DISC) {
			type.setValue(ItemType.DISC);
		} else if (type.getValue() == ItemType.VIDEO) {
			type.setValue(ItemType.VIDEO);
		}
	}

	public void setMainController(TurboBooksMainController controller) {
		this.controller = controller;
	}
}

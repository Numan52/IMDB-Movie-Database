package com.example.movieapp.UI;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class PopUpMessage {
    Dialog<String> dialog;

    public PopUpMessage(String title, String message) {
        dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(message);

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

    }
    public void show() {
        dialog.showAndWait();
    }
}

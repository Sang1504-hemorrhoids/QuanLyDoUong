package quanly.douong.controller;

import quanly.douong.ui.LoginJDialog;
import quanly.douong.ui.WelcomeJDialog;
import quanly.douong.ui.manager.UserManagerJDialog;

import javax.swing.*;

public interface ManagerDrinkController {
    void init();

    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showUserManagerJDialog(JFrame frame) {
        this.showJDialog(new UserManagerJDialog(frame, true));
    }
}

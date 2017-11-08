package ua.edu.nulp.testyourself.core;

public interface LoadingUIHandler {

    void showLoadingDialog(String messageValue);

    void updateLoadingDialogMessage(String messageValue);

    void hideLoadingDialog();
}
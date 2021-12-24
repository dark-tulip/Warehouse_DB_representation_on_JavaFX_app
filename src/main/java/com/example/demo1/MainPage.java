package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/main/resources/sklad.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @FXML
    protected void onStaffButtonClick()  {
        //  При клике на кнопку загружать новую сцену - СОТРУДНИКИ
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("staff-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage stage = new Stage();
            stage.setTitle("Сотрудники");
            stage.setScene(scene);
            stage.show();

        } catch (IOException exc) {
            System.out.println( "Failed to create new Window." + exc);
        }
    }

    @FXML
    protected void onCategoryButtonClick() {
        //  При клике на кнопку Категории загружать сцену - Категории товаров
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("category-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("Категории");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exc) {
            System.out.println( "Failed to create new Window." + exc);
        }
    }

    @FXML
    protected void onProductButtonClick() {
        //  При клике на кнопку Продукты загружать сцену - Продукты
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("product-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setTitle("Товары");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exc) {
            System.out.println( "Failed to create new Window." + exc);
        }
    }

}
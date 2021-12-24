package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // ГЛАВНАЯ СТРАНИЦА ПРИЛОЖЕНИЯ
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("hello-view.fxml"));

        // Создаем сцену, про сцену https://habrastorage.org/r/w1560/webt/4g/e1/uo/4ge1uolcdkwkxcsdont6cnytgto.png
        Scene main_scene = new Scene(fxmlLoader.load(), 600, 400);

        // Заголовок документа
        stage.setTitle("База данных склад");

        // Загрузить сцену
        stage.setScene(main_scene);

        // Показать сцену
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
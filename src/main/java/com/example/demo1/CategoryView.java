package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class CategoryView {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    @FXML
    private TextField name_category, description_category;

    public int categoryID = 1;

    @FXML
    public void onNextButtonClick() throws IOException {

        String serverName = "DESKTOP-B32C0H6\\DEV";
        String dataBaseName = "rgr2_3";
        String connectionUrl = String.format("jdbc:sqlserver://%1s;databaseName=%2s;integratedSecurity=true", serverName, dataBaseName);

        try (
                Connection connection = DriverManager.getConnection(connectionUrl);
                Statement stmt = connection.createStatement();
        ) {
            System.out.println("CONNECTED TO DATABASE " + connectionUrl);

            String SQL = "SELECT * FROM [ProductSchema].[category] WHERE category_id=" + categoryID;
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            if (rs.next()) {

                categoryID = Integer.parseInt(rs.getString("category_id"));
                System.out.println(rs.getString("category_id") + " " + rs.getString("name_category"));

                name_category.setText(rs.getString("name_category"));
                description_category.setText(rs.getString("description_category"));

                name_category.setEditable(false);
                description_category.setEditable(false);

                categoryID += 1;

            } else {
                categoryID = 1;
                onNextButtonClick();
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

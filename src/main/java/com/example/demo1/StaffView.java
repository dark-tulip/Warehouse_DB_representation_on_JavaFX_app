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


public class StaffView {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    @FXML
    private TextField firstName, lastName, StaffPosition, StaffPhone;

    public int staffID = 1;

    @FXML
    public void onSubmitButtonClick() throws IOException {

        String serverName = "DESKTOP-B32C0H6\\DEV";
        String dataBaseName = "WarehouseDB";
        String connectionUrl = String.format("jdbc:sqlserver://%1s;databaseName=%2s;integratedSecurity=true", serverName, dataBaseName);

        try (
                Connection connection = DriverManager.getConnection(connectionUrl);
                Statement stmt = connection.createStatement();
        ) {
            System.out.println("CONNECTED TO DATABASE " + connectionUrl);

            String SQL = "SELECT * FROM [AccountingSchema].[staff] WHERE staff_id=" + staffID;
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            if (rs.next()) {

                staffID = Integer.parseInt(rs.getString("staff_id"));
                System.out.println(rs.getString("staff_id") + " " + rs.getString("staff_name"));

                firstName.setText(rs.getString("staff_name"));
                lastName.setText(rs.getString("staff_surname"));
                StaffPosition.setText(rs.getString("position"));
                StaffPhone.setText(rs.getString("telephone"));

                firstName.setEditable(false);
                lastName.setEditable(false);
                StaffPosition.setEditable(false);
                StaffPhone.setEditable(false);
                staffID += 1;

            } else {
                staffID = 1;
                onSubmitButtonClick();
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

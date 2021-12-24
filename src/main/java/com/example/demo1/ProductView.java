package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.sql.*;

public class ProductView {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    @FXML
    private TextArea result_set;

    public int productID = 1;
    String res = "";

    String serverName = "DESKTOP-B32C0H6\\DEV";
    String dataBaseName = "WarehouseDB";
    String connectionUrl = String.format("jdbc:sqlserver://%1s;databaseName=%2s;integratedSecurity=true", serverName, dataBaseName);

    @FXML
    public void onButtonClick() {

        res = "";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement())
        {
            System.out.println("CONNECTED TO DATABASE " + connectionUrl);

            String SQL = "SELECT * FROM ProductSchema.show_products" ;
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {

                //productID = Integer.parseInt(rs.getString("category_id"));
                //s.warehouse_id, c.name_category, p.name_product, p.price, sum(s.amount) as amount
                 res += rs.getString(1) + ", "
                        + rs.getString(2) + ", "
                        + rs.getString(3)+ "тг, "
                        + rs.getString(4)+ "шт\n";

                System.out.println(res);
                result_set.setText(res);

                result_set.setEditable(false);
                productID += 1;
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onButtonClickWarehouseOne() {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement())
        {
            System.out.println("CONNECTED TO DATABASE " + connectionUrl);

            String SQL = "SELECT * FROM ProductSchema.show_products_by_warehouse where warehouse_id = 1 ORDER BY 2 DESC";
            ResultSet rs = stmt.executeQuery(SQL);
            res = "";
            // Iterate through the data in the result set and display it.
            while (rs.next()) {

                //productID = Integer.parseInt(rs.getString("category_id"));
                //s.warehouse_id, c.name_category, p.name_product, p.price, sum(s.amount) as amount
                res += rs.getString(2) + ", "
                        + rs.getString(3) + ", "
                        + rs.getString(4)+ "тг, "
                        + rs.getString(5)+ "шт\n";

                System.out.println(res);
                result_set.setText(res);

                result_set.setEditable(false);
                productID += 1;
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void onButtonClickWarehouseTwo() {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement())
        {
            System.out.println("CONNECTED TO DATABASE " + connectionUrl);

            String SQL = "SELECT * FROM ProductSchema.show_products_by_warehouse where warehouse_id = 2";
            res = "";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {

                //productID = Integer.parseInt(rs.getString("category_id"));
                //s.warehouse_id, c.name_category, p.name_product, p.price, sum(s.amount) as amount
                res += rs.getString(2) + ", "
                        + rs.getString(3) + ", "
                        + rs.getString(4)+ "тг, "
                        + rs.getString(5)+ "шт\n";

                System.out.println(res);
                result_set.setText(res);

                result_set.setEditable(false);
                productID += 1;
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

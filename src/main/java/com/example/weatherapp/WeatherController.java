package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import org.json.*;

public class WeatherController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cityId"
    private TextField cityId; // Value injected by FXMLLoader

    @FXML // fx:id="felt"
    private Text felt; // Value injected by FXMLLoader

    @FXML // fx:id="getData"
    private Button getData; // Value injected by FXMLLoader

    @FXML // fx:id="pres"
    private Text pres; // Value injected by FXMLLoader

    @FXML // fx:id="temp"
    private Text temp; // Value injected by FXMLLoader

    @FXML // fx:id="tempMax"
    private Text tempMax; // Value injected by FXMLLoader

    @FXML // fx:id="tempMin"
    private Text tempMin; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        getData.setOnAction(actionEvent -> {
            String getCityId = cityId.getText().trim();
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getCityId + "&appid=99503b3d01e76216113c72a064ffef6d&cnt=5&units=metric&lang=en");
            if(!output.isEmpty()){
                JSONObject object = new JSONObject(output);
                temp.setText("Temperature: " + object.getJSONObject("main").getDouble("temp"));
                felt.setText("Felt: " + object.getJSONObject("main").getDouble("feels_like"));
                tempMax.setText("Max: " + object.getJSONObject("main").getDouble("temp_max"));
                tempMin.setText("Min: " + object.getJSONObject("main").getDouble("temp_min"));
                pres.setText("Pressure: " + object.getJSONObject("main").getDouble("pressure"));
            }
        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Город не найден!");
        }
        return content.toString();
    }

}

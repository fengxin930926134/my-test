package com.fengx.mytest.methodology.design_mode.observer;

/**
 * 观察者模式简单实践
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
    }
}
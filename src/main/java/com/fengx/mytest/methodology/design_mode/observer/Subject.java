package com.fengx.mytest.methodology.design_mode.observer;

public interface Subject {
    void resisterObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}
package org.example;

public class Menu {
    Button button;

    Menu(Window w){
        button = new Button(w);
    }

    void update(Window w){
        button.update(w);
    }

    void draw(Window w) {
        button.draw(w);
    }
}
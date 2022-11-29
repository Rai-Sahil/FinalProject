package org.example;

import processing.core.PConstants;

public class Button {

    int rectX, rectY;      // Position of square button
    int rectSize = 90;     // Diameter of rect
    boolean rectOver = false;
    Button(Window window) {
        rectX = window.width/2-rectSize-10;
        rectY = window.height/2-rectSize/2;
        //window.ellipseMode(window.CENTER);
    }

    public void draw(Window w) {
        update(w);

        w.stroke(255);
        w.strokeWeight(2);
        w.rect(rectX, rectY, rectSize, rectSize);
    }

    public void update(Window w) {
        if (overRect(rectX, rectY, w) ) {
            rectOver = true;
        } else {
            rectOver = false;
        }
    }

    public boolean overRect(int x, int y, Window w)  {
        if (w.mouseX >= x && w.mouseX <= x+(90) &&
                w.mouseY >= y && w.mouseY <= y+(90)) {
            return true;
        } else {
            return false;
        }
    }
}
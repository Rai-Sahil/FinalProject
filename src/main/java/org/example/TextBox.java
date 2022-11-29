package org.example;

import org.example.Window;

public class TextBox {

    public int textSize = 32;
    public int X = 0, Y = 0, H = 35, W = 200;
    public boolean BorderEnable = true;
    private boolean selected = true;
    public int TextLength = 0;
    public String Text = "";
    public int BorderWeight = 1;

    TextBox(){

    }

    TextBox(int x, int y, int w, int h) {
        X = x; Y = y; W = w; H = h;
    }

    void draw(Window w){
        if(selected){
            //w.background(160, 160, 160);
        } else{
            //w.background(140, 140, 140);
        }
//        w.strokeWeight(3);
//        w.stroke(30, 30, 30);
        w.noStroke();

        w.noFill();
        w.rect(X, Y, W, H);
        w.textSize(textSize);
        w.text(Text, X + (w.textWidth("a") / 2), Y + textSize);
    }

    boolean keyPressed(char key, int keyCode, Window w) {
        if (selected) {
            if (keyCode == 8) {
                BACKSPACE();
            } else if (keyCode == 32) {
                addText(' ', w);
            } else if (keyCode == 10) {
                return true;
            } else {
                // CHECK IF THE KEY IS A LETTER OR A NUMBER
                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key, w);
                }
            }
        }
        return false;
    }

    private void addText(char text, Window w) {
        // IF THE TEXT WIDTH IS IN BOUNDARIES OF THE TEXTBOX
        if (w.textWidth(Text + text) < W) {
            Text += text;
            TextLength++;
        }
    }

    private void BACKSPACE() {
        if (TextLength - 1 >= 0) {
            Text = Text.substring(0, TextLength - 1);
            TextLength--;
        }
    }

    private boolean overBox(int x, int y) {
        if (x >= X && x <= X + W) {
            if (y >= Y && y <= Y + H) {
                return true;
            }
        }
        return false;
    }

    void PRESSED(int x, int y) {
        if (overBox(x, y)) {
            selected = true;
        } else {
            selected = false;
        }
    }
}
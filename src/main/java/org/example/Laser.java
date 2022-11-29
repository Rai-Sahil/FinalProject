package org.example;

import processing.core.PImage;

public class Laser {
    PImage bot;
    float posX;
    float posY;
    float speed;
    float dir;
    float size;

    Laser(float px, float py, float pDir, Window window){
        posX = px;
        posY = py;
        speed = 5;
        dir = pDir - window.HALF_PI;
        posX += 25*window.cos(dir);
        posY += 25*window.sin(dir);
        size = 2;
        bot = window.loadImage("C:\\Users\\raisa\\IdeaProjects\\JavaProject\\src\\main\\java\\org\\example\\kisspng-light-download-color-yellow-glare-light-effects-5a99f9b9a49f01.5350402715200403776743.png");
    }

    void Update(Window window){
        posX += speed*window.cos(dir);
        posY += speed*window.sin(dir);
    }

    void Render(Window window){
        window.push();
        window.noFill();
        window.stroke(255);
        window.strokeWeight(1);
//        window.rect(posX, posY, size, size + 10f);
        window.image(bot, posX-7, posY-7, 20, 20);
        window.pop();
    }

    public boolean CheckCollision(Asteroid a, Window window){
        float d = window.dist(posX, posY, a.posX, a.posY);

        if(d < (a.size+size)*3/5){
            return true;
        }
        else {
            return false;
        }
    }
}
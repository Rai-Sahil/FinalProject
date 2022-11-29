package org.example;

public class Pixel {
    float posX;
    float posY;
    float size;
    float direction;
    float speed;
    float life;

    Pixel(float x, float y, Window window){
        posX = x;
        posY =  y;
        direction = window.random(0, window.TWO_PI);
        speed = window.random(1/10, 1/2);
        life = window.floor(window.random(100, 200));
        size = window.random(2, 5);
    }

    public boolean Update(Window window){
        posX += speed * window.cos(direction);
        posY += speed * window.sin(direction);
        life--;
        if(life <= 0)return true;
        else return false;
    }

    public void Render(Window window){
        window.push();
        if(life > 0){
            window.fill(255, 255, 255, life);
            window.rect(posX, posY, size, size);
        }
        window.pop();
    }
}
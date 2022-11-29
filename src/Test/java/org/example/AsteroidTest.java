package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidTest {
    Player player;

    Window window;

    PImage bot;

    PImage bot2;

    int asteroid;

    float posX;

    float posY;

    float size;

    float randomDir;

    float speed;

    boolean top = true;

    boolean left = true;

    @BeforeEach
    void setup(){
        window = new Window();
        player = new Player(window);
    }

    @Test
    void checkPosX1(){
        if(posX > window.width){
            assertEquals(0, posX);
        }
    }

    @Test
    void checkPosX2(){
        if (posX < 0){
            assertEquals(window.width, posX);
        }
    }

    @Test
    void checkPosY1(){
        if (posY > window.height){
            assertEquals(0, posY);
        }
    }

    @Test
    void checkPosY2(){
        if (posY < 0){
            assertEquals(window.height, posY);
        }
    }

    @Test
    void checkRandPos1(){
        if(window.floor(window.random(0, 2)) == 0){
            top = true;
        }
        assertEquals(true, top);
    }

    @Test
    void checkRandPos2(){
        if(window.floor(window.random(0, 2)) == 0){
            left = true;
        }
        assertEquals(true, left);
    }

    @Test
    void checkTopAndLeftX(){
        if (top && !left){
            posX = window.random(0, (window.height/2 - 100));
        }
        assertEquals(window.random(0, (window.height/2 - 100)), posX);
    }

    @Test
    void checkTopAndLeftY(){
        if (top && !left){
            posY = window.random(0, (window.height/2 - 100));
        }
        assertEquals(window.random(0, (window.height/2 - 100)), posY);
    }

    @Test
    void checkNotTopAndLeftX(){
        if(!top && left){
        posX = window.random(0, (window.width/2 - 100));
        }
        assertEquals(window.random(0, (window.width/2 - 100)), posX);
    }

    @Test
    void checkUpdate1(){
        posX += window.cos(randomDir)*speed;
        assertEquals(posX += window.cos(randomDir)*speed, posX);
    }

    @Test
    void checkUpdate2(){
        posY += window.sin(randomDir)*speed;
        assertEquals(posY += window.sin(randomDir)*speed, posY);
    }

}
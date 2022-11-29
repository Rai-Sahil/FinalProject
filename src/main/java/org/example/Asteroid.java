package org.example;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Asteroid {
    PImage bot;
    PImage bot2;
    int asteroid;
    float posX;
    float posY;
    float size;
    float randomDir;
    float speed;

  /** Sets asteroids in a random direction, in a random position at a set speed.
   *
   * @param window opens window for the user
   */
  Asteroid(Window window){
        randPos(window);
        size = 50;
        randomDir = window.random(0, PConstants.TWO_PI);
        speed = 2;
        bot = window.loadImage("C:\\Users\\raisa\\IdeaProjects\\JavaProject\\src\\main\\java\\org\\example\\asteroids.png");
        bot2 = window.loadImage("C:\\Users\\raisa\\IdeaProjects\\JavaProject\\src\\main\\java\\org\\example\\asteroid2.png");
        asteroid = (int) window.random(0, 2);
    }

  /** Updates the speed in which the direction of the asteroid is flying.
   *
   * @param window window for the user
   */
  public void Update(Window window){
        posX += window.cos(randomDir)*speed;
        posY += window.sin(randomDir)*speed;
        Edge(window);
    }

    public void Render(Window window){
        window.push();
        window.translate(posX, posY);
        window.noFill();
        window.stroke(255);
        window.strokeWeight(2);
        if(asteroid == 0){
            window.image(bot, -10, -10, size, size-10);
        } else {
            window.image(bot2, -10, -10, size, size-10);
        }
        window.pop();
    }

  /** Contains asteroids to inside the window instead of going out of bounds.
   * If the x or y position of an asteroid becomes greater than its width or height,
   * the positions are reset to 0,0 respectively and pop it over to the side of the screen.
   *
   * @param window window for the user
   */
    private void Edge(Window window){
        if(posX > window.width) posX = 0;
        if(posX < 0) posX = window.width;
        if(posY > window.height) posY = 0;
        if(posY < 0) posY = window.height;
    }

  /** Function creates a "wall" of +- 100 pixels on screen so when the asteroids spawn randomly,
   * they don't automatically spawn on top of the player and end the game before it starts.
   * Each quadrant of the screen is covered for the 100 pixels.
   *
   * @param window window for the user
   */
    private void randPos(Window window){
        boolean top = false;
        boolean left = false;
        if(window.floor(window.random(0, 2)) == 0) top = true;
        if(window.floor(window.random(0, 2)) == 0) left = true;

        if(top && left){
            posX = window.random(0, (window.width/2 - 100));
            posY = window.random(0, (window.height/2 - 100));
        }
        else if(top && !left){
            posX = window.random((window.width/2 + 100), window.width);
            posY = window.random(0, (window.height/2 - 100));
        }
        else if(!top && left){
            posX = window.random(0, (window.width/2 - 100));
            posY = window.random((window.height/2 + 100), window.height);
        } else{
            posX = window.random((window.width/2 + 100), window.width);
            posY = window.random((window.height/2 + 100), window.height);
        }
    }
}
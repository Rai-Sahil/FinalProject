package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  Player player;

  Window window;

  float posX;
  float posY;



  @BeforeEach
  void setup(){
    window = new Window();
    player = new Player(window);
  }

  @Test
  void checkPlayerPosition(){
    posX = window.width/2;
    posY = window.height/2;

//    if (player.posX < window.size(630, 350));

  }

  @Test
  void checkMaxSpeed(){
    if (player.MAXSPEED == window.PI/50){
      assertEquals(window.PI/50, player.maxSpeed(window));
    }
  }

  @Test
  void checkRotationRight(){
    boolean left = false;
    boolean right = false;
    if (player.isRotating == -1) right = true;
    assertFalse(player.checkRotationRight(window));
  }

  @Test
  void checkRotationLeft(){
    boolean left = false;
    boolean right = false;
    if (player.isRotating == -1) left = true;
    assertFalse(player.checkRotationLeft(window));
  }
}

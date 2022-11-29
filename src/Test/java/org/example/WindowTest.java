package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

  Window window;

  Player player;

  @BeforeEach
  void setup(){
    window = new Window();
    player = new Player(window);
  }

  // on line 322 in window class, added checkwindowsize function
  @Test
  void checkWindowSize(){
    window.setSize(569, 789);
    assertFalse(window.checkWindowSize(window));

    window.setSize(640, 360);
    assertTrue(window.checkWindowSize(window));
  }

}
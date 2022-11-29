package org.example;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;
import processing.data.IntList;

import java.util.ArrayList;

public class Window extends PApplet {

  PShape bot;
  PImage gameOver;
  Player player;
  ArrayList<TextBox> textboxes;
  ArrayList<Asteroid> asteroids;
  ArrayList<Star> stars;
  ArrayList<Pixel> pixels;
  ArrayList<Laser> lasers;
  StarsStraght[] menuStars = new StarsStraght[800];
  IntList astRemove;
  IntList laserRemove;
  IntList pixelRemove;
  int gameState; // 0 is start menu, 1 is the game, 2 is game over
  int score;
  int result = 0;
  int round; //Levels
  int roundTitleCounter;
  boolean notRoundOne;
  boolean send = false;
  float speed;
  String msg = "";
  String name = "";

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"Spacesaver"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }

  public void setup() {
    background(0);
    frameRate(60);
    InstantiateVariables();//This gets call whenever we restart the game...
    gameState = 0;
    bot = loadShape("C:\\Users\\raisa\\IdeaProjects\\Java-Project\\src\\main\\java\\org\\example\\rocket-3432.svg");
    gameOver = loadImage("C:\\Users\\raisa\\IdeaProjects\\JavaProject\\src\\main\\java\\org\\example\\Game-Over-PNG-Image.png");
  }

  //Used for setup of our game when we are running it.
  void InstantiateVariables(){
    player = new Player(this);
    asteroids = new ArrayList<Asteroid>();
    stars = new ArrayList<Star>();
    textboxes = new ArrayList<TextBox>();

    for (int i = 0; i < floor(random(6, 10)); i++) {
      asteroids.add(new Asteroid(this));
    }

    for (int i = 0; i < 100; i++){
      stars.add(new Star(this));
    }

    for (int i = 0; i < menuStars.length; i++){
      menuStars[i] = new StarsStraght(this);
    }

    TextBox message = new TextBox(0, 0, width, height);
    textboxes.add(message);

    lasers = new ArrayList<Laser>();
    pixels = new ArrayList<>();
    astRemove = new IntList();
    laserRemove = new IntList();
    pixelRemove = new IntList();

    score = 0;
    round = 1;
    notRoundOne = false;
    roundTitleCounter = 180;
  }

  public void settings() {
    size(800, 800);
  }

  public void draw() {
    switch (gameState) {
      case 0 -> {
        push();
        speed = map(mouseX, 0, width, 0, 20);
        background(0);
//                for (Star s : stars){
//                    s.display(this);
//                    PVector dir = new PVector(2, 0);
//                    s.move(dir, this);
//                }

        translate(width/2, height/2);

        for (int i = 0; i < menuStars.length; i++){
          menuStars[i].update(this);
          menuStars[i].show(this);
        }

        for(TextBox t : textboxes){
          t.draw(this);
        }

        if(send){
          text(msg, (width - textWidth(msg)) / 2, 260);
        }

        textSize(32);
        fill(255);
        //shape(bot, 200, 150);
        text("Press Enter", width * -.095f, height * .15f);
        pop();
        break;
      }
      case 1 -> {
        RoundUpdate();
        Update();
        Render();
        for (Star s : stars){
          s.display(this);
          PVector dir = new PVector(2, 0);
          s.move(dir, this);
        }
        result = score;
        break;
      }
      case 2 -> {
        push();
        background(0);
        for (Star s : stars){
          s.display(this);
          PVector dir = new PVector(2, 0);
          s.move(dir, this);
        }
        textSize(32);
        image(gameOver, width * .38f, height * .1f, 200, 200);
//                text("Game Over", width*.39f, height*.26f);
        text("You Scored: " + result, width * .35f, height * .5f);
        text("Press Enter to Play Again", width * .24f, height * .8f);
        pop();
        break;
      }
    }
  }

  void RoundUpdate(){
    if(roundTitleCounter > 0)roundTitleCounter--;//If there is any timer subtract the timer
    else {
      roundTitleCounter = 2;
      notRoundOne = true;
    }

    //ABout to finish a round but not in first round...
    if (roundTitleCounter == 1 && notRoundOne){
      for(int i = 0; i < floor(random(5, 8)); i++){
        asteroids.add(new Asteroid(this));
      }
    }

    if(roundTitleCounter == 0 && asteroids.size() == 0){
      roundTitleCounter = 180;
      round++;
    }
  }

  public void Render() {
    background(0);

    if(roundTitleCounter > 0){
      push();
      textSize(32);
      text("Round " + round, width*.4f, height*.4f);
      pop();
    }

    push();
    textSize(32);
    text("Score: " + score, width*.05f, height*.05f);
    text("Name: " + name, width*.75f, height*.05f);
    pop();
    player.Render(this);
    for (Asteroid asteroid : asteroids) {
      asteroid.Render(this);
    }

    for (Laser laser : lasers) {
      laser.Render(this);
    }

    for (Pixel pixel : pixels) {
      pixel.Render(this);
    }
  }

  public void death(int value){

  }

  public void Update() {
    player.Update();
    for (Asteroid asteroid : asteroids) {
      asteroid.Update(this);
      if (player.CheckCollision(asteroid, this)){
        gameState = 2;
      }
    }

    for (Laser laser : lasers) {
      laser.Update(this);
    }

    for (int l = 0; l < lasers.size(); l++) {
      for (int a = 0; a < asteroids.size(); a++) {
        if (lasers.get(l).CheckCollision(asteroids.get(a), this)) {
          laserRemove.append(l);
          astRemove.append(a);
          score += 100;
          for (int n = 0; n < 15; n++) {
            pixels.add(new Pixel(asteroids.get(a).posX, asteroids.get(a).posY, this));
          }
          break;
        }
      }
    }
    for (int i = 0; i < pixels.size(); i++) {
      if (pixels.get(i).Update(this)) {
        pixelRemove.append(i);
      }
    }

    //REMOVE
    for (int i = 0; i < laserRemove.size(); i++) {
      if (laserRemove.get(i) < lasers.size()) lasers.remove(laserRemove.get(i));
    }
    laserRemove.clear();
    for (int i = 0; i < astRemove.size(); i++) {
      if (astRemove.get(i) < asteroids.size()) asteroids.remove(astRemove.get(i));
    }
    astRemove.clear();
    for (int i = 0; i < pixelRemove.size(); i++) {
      if (pixelRemove.get(i) < pixels.size()) pixels.remove(pixelRemove.get(i));
    }
    pixelRemove.clear();
  }

  public void keyPressed() {
    switch (gameState) {
      case 0 -> {
        for (TextBox t : textboxes) {
          if (t.keyPressed(key, keyCode, this)) {
            send = true;
            msg = "Message is: " + textboxes.get(0).Text;
            name = textboxes.get(0).Text;
          }
        }
        if (keyCode == 10) gameState = 1;
        break;
      }
      case 1 -> {
        if (keyCode == 39) {//RIGHT
          player.isRotating = -1;
        } else if (keyCode == 37) {//LEFT
          player.isRotating = 1;
        }
        if (keyCode == 32) {
          if (!player.shotFired) {
            lasers.add(player.Fire(this));
            if(round > 5){
              lasers.add(player.Fire2(this));
            }
            player.shotFired = true;
          }
        }
        break;
      }
      case 2 -> {
        if (keyCode == 10) gameState = 1;
        InstantiateVariables();
        break;
      }
    }
  }

  public void keyReleased() {
    switch (gameState) {
      case 0 -> {

        break;
      }
      case 1 -> {
        if (keyCode == 39) {
          player.isRotating = 0;
        } else if (keyCode == 37) {
          player.isRotating = 0;
        }
        if (keyCode == 32) {
          if (player.shotFired) player.shotFired = false;
        }
        break;
      }
      case 2 -> {

        break;
      }
    }
  }

  public void mousePressed() {
    for (TextBox t : textboxes) {
      t.PRESSED(mouseX, mouseY);
    }
  }

  public boolean checkWindowSize(Window window){
    if ((window.height == 360)
        && (window.width == 640)){
      return true;
    } else {
      return false;
    }
  }
}

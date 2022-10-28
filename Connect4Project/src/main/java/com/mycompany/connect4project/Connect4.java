/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.connect4project;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Connect4 {

    @FXML
    Canvas canvasConnect4;
    @FXML
    GraphicsContext gc;
    
//Initialize the values for variables
//Creates gamestate: 0 = RedTurn, 1 = Blue Turn, 2 = GameEnded	
int gameState=0;

        
    @FXML
    private void initialize() {
        //create a graphics2D object
        gc = canvasConnect4.getGraphicsContext2D();
        createGameBackground();
        
    }


 
   


    @FXML
    void onMousePressedFunction(MouseEvent event) throws InterruptedException {
      //I belive the whole game intself runs based on mouse events alone. 
      //All we should need should be the mouseEvents and functions they rely on.
      
      //Create a switch that depends the action on gameState
      switch(gameState){
          
        case 0:
              
         //places piece on board and check for win    
              
            
            //Not sure how to make the logic that checks for a gameWin
             if (gameWin = true)
              gameState = 2;   
               
                     
                     
             gameState = 1; 
            break;
              
        case 1:
              
              
              
              
              
              
            break;
            
        case 2:
            
            
            
            
            
            
            break;

          
      }
    }

    @FXML
    void onMouseReleasedFunction() {
  
        
        
        
    }

    void createGameBackground() {
        gc.setFill(Color.BEIGE);
        
        
        
    }


    }


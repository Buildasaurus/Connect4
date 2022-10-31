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

/// TODO (PETER): migrate to GameController
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
      /*
        switch(gameState){
          
        case 0:
              
         //places piece on board and then checks for win    
              
            
            //Tjekker om man vinder på at lave det move
             if (gameWin = true)
              gameState = 2;   
               
                     
                     
             gameState = 1; 
            break;
              
        case 1:
              
              
              
          //Tjekker om man vinder på at lave det move
             if (gameWin == true)
              gameState = 2;      
              
              
            break;
            
        case 2:
            
          //Kør noget kode som genstartet spillet og viser en win message  
            
            
            
            
            break;

          
      }

       */
    }

    @FXML
   void onMouseReleasedFunction() {
  
        
        
        
    }

    void createGameBackground() {
        gc.setFill(Color.BEIGE);
        
        
        
    }

    
    void placePiece() {
        
        
        //Calculate the column number to place in
        int columnnumber = 0;
        columbnumber = canvasConnect4.getWidth()/5
        
        
        
        
        //Det her er kun grafik delen af setPiece
        //Sets color of piece based on gamestate
        if(gameState == 0)
        gc.setFill(Color.BLUE);
        else gc.setFill(Color.RED);
     
        
        //Princippet er at columnClicked skal være kolonnen man placerer i, og pieceNumberInRow skal være det nummer, som den er i kolonnen.
        //Altså at placeringen varierer afhængigt af om det er den første brik i kolonnen, eller der er nogen placeret før.
        // gc.fillOval(canvasConnect4.getWidth()/ColumnClicked,canvasConnect4.getHeight()/PieceNumberInRow,2,2);
        
        
    }

    }


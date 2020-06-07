/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author PC
 */

public class App extends Application {
    
    Game g = new Game();
    @Override
    
    public void start(Stage stagePrimary) {
        g.addNum();
        BorderPane root2 = new BorderPane(); //căn lề so với ô cửa sổ
        root2.getChildren().add(g.getImage());
        Scene scene = new Scene(root2, 1000, 600); // kích thước bảng là 1000x600
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            if(g.isOver()){
                root2.getChildren().remove(1);
                root2.getChildren().add(g.getGameoverImage());
            }else{
                if(event.getCode()== KeyCode.A)
                {
                    String olds = g.stateString();
                    g.pushup();
                    String news = g.stateString();
                    if(!olds.equals(news))
                        g.addNum();
                    //root2.getChildren().remove(1);
                    
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.S)
                {
                    String olds = g.stateString();
                    g.pushright();
                    String news = g.stateString();
                    if(!olds.equals(news))
                        g.addNum();
                    //root2.getChildren().remove(1);
                    
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.D)
                {
                    String olds = g.stateString();
                    g.pushdown();
                    String news = g.stateString();
                    if(!olds.equals(news))
                        g.addNum();
                   // root2.getChildren().remove(1);  
                   
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.W)
                {
                    String olds = g.stateString();
                    g.pushleft();
                    String news = g.stateString();
                    if(!olds.equals(news))
                        g.addNum();
                    //root2.getChildren().remove(1);
                    
                    root2.getChildren().add(g.getImage());
                }
            }
        });
        
        root2.getChildren().removeAll();
        stagePrimary.setTitle("Hello 2048 world ..");
        stagePrimary.setScene(scene);
        
        stagePrimary.show();
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }

}
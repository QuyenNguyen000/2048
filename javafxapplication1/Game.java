/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author PC
 */

public class Game {
    private final int[][] gameBoard;
    private final Random r = new Random();
    private final Color[] cs = {
        Color.rgb(205, 193, 180),
        Color.rgb(238, 228, 218),
        Color.rgb(237, 224, 200),
        Color.rgb(242, 177, 121),
        Color.rgb(245, 149, 99),
        Color.rgb(246, 124, 95),
        Color.rgb(246, 94, 29),
        Color.rgb(237, 207, 114),
        Color.rgb(237, 204, 97),
        Color.rgb(237, 200, 80),
        Color.rgb(237, 197, 63),
        Color.rgb(237, 194, 46),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50)
    };
    
    public Game()
    {
       gameBoard = new int [4][4];
    }
   
    public BorderPane getNode(int x, int y) {
        BorderPane b = new BorderPane();
        StackPane s = new StackPane();
        Rectangle rec = new Rectangle(99, 99);
        int j=0;
        
        for(int i = 1; Math.pow(2, i) <= this.gameBoard[x][y]; i++){
            j=i;
        }
        // Math.pow(x,y) là x mũ y
        
        rec.setFill(cs[j]);
            s.getChildren().add(rec);
        // chỉnh màu ô
        
        Text text = new Text(String.valueOf(this.gameBoard[x][y]));
        text.setFont(Font.font ("Arial", FontWeight.BOLD, 40));
        text.setFill(Color.GREY);
        // đặt font kích cỡ màu chữ
        
        if(this.gameBoard[x][y] != 0)
            s.getChildren().add(text);
        b.getChildren().add(s);
        b.setTranslateX(x*100+150);
        b.setTranslateY(y*100+150); // chỉnh 2048 nằm giữa cửa sổ
        return b;
    }
    
    public BorderPane getImage(){
        BorderPane b = new BorderPane();
        for (int i=0; i<4; i++)
            for(int j= 0; j<4; j++)
            {
                b.getChildren().add(getNode(i,j));
            }
        return b;
    }
    public BorderPane getGameoverImage(){
        BorderPane b = new BorderPane();
        for (int i=0; i<4; i++)
            for(int j= 0; j<4; j++)
            {
                b.getChildren().add(getNode(i,j));
            }
        Text text = new Text("Game over ..");
        text.setFont(Font.font("Arial",40));
        text.setTranslateX(600);
        text.setTranslateY(300);
        b.getChildren().add(text);
        return b;
    }
    
    // giao diện kết thúc
    
    public void addNum()
    {
        ArrayList<Integer> XX = new ArrayList<Integer>();
        ArrayList<Integer> YY = new ArrayList<Integer>();
        for(int x=0; x<4; x++)
        {
            for(int y=0; y<4; y++)
            {
                if(gameBoard[x][y]==0)
                {
                    XX.add(new Integer(x));
                    YY.add(new Integer(y));
                }
            }
        }
        int choice = r.nextInt(XX.size()); //xem
        int numchooser = r.nextInt(10); //value 0-9
        int newPopup = 2;
        if (numchooser == 0)
        {
                newPopup = 4;
        } //xem
        int X = XX.get(choice);
        int Y = YY.get(choice);
        gameBoard[X][Y]= newPopup;
    }
    
    public void pushup()
    {
        System.out.println("Pushing Up...");
        int topRow;
        for (int y=0; y<4; y++)
        {
            topRow=0;
            for (int x=0; x<4; x++)
            {
                if (topRow==x || gameBoard[x][y]==0) {}
                else if (gameBoard[x][y] == gameBoard[topRow][y])
                {
                    gameBoard[topRow][y] = gameBoard[topRow][y]*2;
                    gameBoard[x][y]=0;
                    topRow ++;
                }
                else 
                {
                    if (gameBoard[topRow][y]!=0) 
                    {
                        topRow++;
                    }
                    if(topRow!=x) 
                    {
			gameBoard[topRow][y]=gameBoard[x][y];
			gameBoard[x][y]=0;
                    }
                }
            }
	}
    }
    
    public void pushdown()
    {
        System.out.println("Pushing Down...");
        int lastRow;
        for(int y=0; y<4; y++)
        {
            lastRow=3;
            for(int x=3; x>=0; x--)
            {
                if(lastRow==x || gameBoard[x][y]==0)
                {
                    continue;
                }
                else if(gameBoard[x][y]==gameBoard[lastRow][y])
                {
                    gameBoard[lastRow][y]=gameBoard[lastRow][y]*2;
                    gameBoard[x][y]=0;
                    lastRow--;
                }
                else
                {
                    if(gameBoard[lastRow][y]!=0)
                    lastRow--;
                    if(lastRow!=x)
                    {
                        gameBoard[lastRow][y]=gameBoard[x][y];
                        gameBoard[x][y]=0;
                    }
                }
            }
        }
    }
    
    public void pushleft()
    {
        System.out.println("Pushing Left...");
        int lastleftCol;
        for(int x=0; x<4; x++)
        {
            lastleftCol=0;
            for(int y=0; y<4; y++)
            {
                if(lastleftCol==y || gameBoard[x][y]==0)
                {
                    
                }
                else if(gameBoard[x][y]==gameBoard[x][lastleftCol])
                {
                    gameBoard[x][lastleftCol]=gameBoard[x][lastleftCol]*2;
                    gameBoard[x][y]=0;
                }
                else
                {
                    if(gameBoard[x][lastleftCol]!=0)
                        lastleftCol++;
                    if(lastleftCol!=y)
                    {
                        gameBoard[x][lastleftCol] = gameBoard[x][y];
                        gameBoard[x][y]=0;
                    }
                }
            }
        }
    }
    
    public void pushright()
    {
        System.out.println("Pushing Right...");
        int lastrightCol;
        for(int x=0; x<4; x++)
        {
            lastrightCol=3;
            for(int y=3; y>=0; y--)
            {
                if(lastrightCol==y || gameBoard[x][y]==0)
                {
                    
                }
                else if(gameBoard[x][y]==gameBoard[x][lastrightCol])
                {
                    gameBoard[x][lastrightCol]=gameBoard[x][lastrightCol]*2;
                    gameBoard[x][y]=0;
                }
                else
                {
                    if(gameBoard[x][lastrightCol]!= 0)
                        lastrightCol--;
                    if(lastrightCol!=y)
                    {
                        gameBoard[x][lastrightCol]=gameBoard[x][y];
                        gameBoard[x][y]=0;
                    }
                }
            }
        }
    }
    
    public boolean isOver(){
        boolean check = true;
        for (int i=0; i<4;i++)
            for(int j=0; j<4; j++){
                if (this.gameBoard[i][j]==0)
                    check = false;
                if (i!= 3){
                    if(this.gameBoard[i][j]==this.gameBoard[i+1][j])
                        check = false;
                }
                if (j!= 3){
                    if(this.gameBoard[i][j]==this.gameBoard[i][j+1])
                        check = false;
                }
            }
        return check;
    }
    // điều kiện kết thúc
    
    public String stateString(){
        String str = "";
        for (int i=0; i<4; i++)
            for (int j=0; j<4;j++)
                str+= String.valueOf(this.gameBoard[i][j]);
        return str;
    }

}
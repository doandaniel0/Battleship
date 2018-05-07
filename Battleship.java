import java.util.*;
import java.io.*;

/**
 * Write a description of class Battleship here.
 * This is a simple single player version of Battleship with 3 ships
 * Daniel Doan 
 * 12/14/2016
 */
public class Battleship
{
    // The main method keeps the game running until you hit all 3 ships then prints to an output file stating how many attempts it took.
    public static void main(String [] args) throws IOException { 
        int x=0;
        int y=0;
        int n= 5;
        int shotsHit=0;
        int attempts =0;
        int [][] board = new int [n][n]; // This initializes the 2d array that is printed;
        int [][] ships= new int [n][n]; // THis initializes the 2d array that the ships sit in;
        PrintWriter output= new PrintWriter (new FileOutputStream("output.txt"));
        System.out.println("There are three ships that each take up one slot of the grid \ncall x and y coordinates to shoot\n~ is water , * is a miss and ! is a hit.");
        setShips(ships,n); // This sets 3 ships randomly on a 2d Array
        initBoard(board);// Sets the value 1 to each element in the 2d array
        drawBoard(board);// This draws out the 2d array based on what value each element has (-1,0 or 1)
        while(shotsHit!=3){
            //This if statements uses the method hit() to check if you hit a ship or not and increments the amount of shots hit and attempts taken.
            if(hit(ships,board ,x,y)){
                shotsHit++;
                attempts++;
            }else{
                attempts++;
            }
            //This nested loop updates the board after you've taken a shot.
            drawBoard(board);
        }
        System.out.println("Congratulations ! You sank all three ships in "+ attempts +  " attempts , woooo! *high five* =)");
        // These lines of code prints the number of attempts it takes to beat the game to an output file.
        output.println("Finished the game in " + attempts + " shots");
        output.close();
    }
    // This method initializes the board with -1 which drawBoard uses to insert "~".
    public static void initBoard(int[][]board){
        for(int i = 0; i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                board[i][j]=-1;
            }
        }
    }
    // This method creates a different 2d array to represent where the ships are and sets  3 random elements to 1.
    public static void setShips(int [][]ships, int n){
        /* This function uses the random class and gets random numbers 0 to n-1 for the indicies of the 2d array,the if 
        statement in the for loop also prevents a ship from being placed where a previous one has already been placed by checking if the new random index is the same as the last one*/
        Random random = new Random();
        int i = random.nextInt(n-1);
        int j = random.nextInt(n-1);
        ships[i][j]=1;
        int count =0;
        for ( i=0,j=0; count<=2;i++ ,j++){ //This loop only sets two more ships because one ship is already sey outside of the loop.
            if(random.nextInt(n-1)!=i && random.nextInt(n-1)!=j){
                i = random.nextInt(n-1);
                j = random.nextInt(n-1);
                ships[i][j]=1;
                count++;
            }
        }
       
    }
    // This method draws out the initial board and is used later in main to draw the modified boards;
    public static void drawBoard(int [][] board){
        for(int i = 0; i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                if(board[i][j]==-1){
                    System.out.print("\t ~ ");
                }else if(board[i][j]==0){
                    System.out.print("\t * ");
                }else if( board[i][j]==1){
                    System.out.print("\t ! ");
                }
            }
            System.out.println();
        }
        
    }
    /*This method determines takes an x and y coordinate from the user and compares it to the 
     coordinates of the array of ships to see if they match by checking if they both == 1 to determine
     if a ship was hit then modifies the board by setting it == to 1 if its a hit and 0 if it's a miss*/
    public static boolean hit(int [][]ships,int [][]board,int x,int y ){
        Scanner foo = new Scanner(System.in);
        System.out.println(" what x coordinate?");
        System.out.println(" what y coordinate?");
        x = foo.nextInt();
        y = foo.nextInt();
        if (ships[y-1][x-1]==1){// I use x-1 nad y-1 to keep the indices in the bounds of the array
            board[y-1][x-1]=1;
            return true;
        }else if(ships[y-1][x-1]!=1){
            board[y-1][x-1]=0;
        }
        return false;
    }
    
}

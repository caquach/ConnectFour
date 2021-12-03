/**
 * Program that simulates the popular board game, Connect 4.
 * This part of the program is what users interact with through user input.
 * @author Calvin Quach on April 4, 2019
 */
import java.util.Scanner;
public class ConnectFourGameRunner {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Welcome to Connect Four. \nPress p to start playing and type any other key to quit at any time! ");
        String input = in.next().toLowerCase();

        while(input.equals("p"))
        {
            ConnectFour game = new ConnectFour();
            String start = "GLHF to both players!\n" + game.toString() + "\n" + game.playerPrompt();
            System.out.print(start);
            int col = -1;
        
            while(in.hasNextInt())
            {
                col = Math.max(in.nextInt(),1);
                col = Math.min(col,7);
                col--;
            
                game.placeMarker(col);
                System.out.println(game.toString());

                if(game.fourConnected(col))
                {
                    System.out.println(game.playerWin());
                    break;
                }
            
                game.decrementHeight(col);
                game.switchPlayerTurn();

                System.out.print(game.playerPrompt());
            }
            System.out.print("Type p to start another game. ");
            input = in.next().toLowerCase();
        }
        System.out.print("Thank you for playing!");
        in.close();
    }
}
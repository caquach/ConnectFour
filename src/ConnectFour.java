/**
 * Program that simulates the popular board game, Connect 4.
 * This part of the program handles the board, placement, and match logic of the game.
 * @author Calvin Quach on April 4, 2019
 */
public class ConnectFour {
    private String marker;
    private String[][] board;
    private int[] height;

    /**
     * A connect four board is 6x7 (hxw)
     * Player 1 has marker "o", Player 2 has marker "x"
     * For each player, the goal of the game is to connect four of the same marker in a row
     * Matches can be horizontal,vertical, or diagonal.
     * A player can only make a move per turn.
     */
    public ConnectFour()
    {
        marker = "o";
        height = new int[] 
        {5,5,5,5,5,5,5};
        
        board = new String[][]
        {
            {"-","-","-","-","-","-","-"},
            {"-","-","-","-","-","-","-"},
            {"-","-","-","-","-","-","-"},
            {"-","-","-","-","-","-","-"},
            {"-","-","-","-","-","-","-"},
            {"-","-","-","-","-","-","-"}
        };
    }

    /**
     * Method to switch player turn.
     * If "o" (Player 1) was placed, switch marker to "x" (Player 2).
     */
    public void switchPlayerTurn()
    {
        if(marker.equals("x"))
        {
            marker = "o";
        }
        else
        {
            marker = "x";
        }        
    }

    /**
     * Method that decrements height from a given column.
     * @param c column to decrement from (0-6)
     * @throws IndexOutOfBoundsException if c is less than 0 or greater than 6
     */
    public void decrementHeight(int c)
    {
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        if(height[c] <= 0)
        {
            return;
        }
        height[c] -= 1;

    }

    /**
     * Method to get the height of the empty space at column c.
     * @param c column to access height from
     * @throws IndexOutOfBoundsException if c is less than 0 or greater than 6
     * @return the current empty space at c
     */
    public int getHeight(int c)
    {
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        return height[c];
    }

    /**
     * Method to access the current marker.
     * @return the current marker
     */
    public String getMarker()
    {
        return marker;
    }

    /**
     * Method to place the current player's marker.
     * @param c column to place marker
     * @throws IndexOutOfBoundsException if c is less than 0 or greater than 6
     */
    public void placeMarker(int c)
    {
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        if(!board[0][c].equals("-"))
        {
            switchPlayerTurn();
            System.out.println("Column " + c + " is already filled, pick another column.");
            return;
        }
        board[height[c]][c] = marker;
    }
    
    /**
     * Method to prompt the respective player to make a move.
     * @return a string prompting next plater to make a move
     */
    public String playerPrompt()
    {
        if(marker.equals("o"))
        {
            return "Player 1, pick a column from 1 to 7: ";
        }
        return "Player 2, pick a column from 1 to 7: ";
    }

    /**
     * Method to display winning message for player 1 or 2.
     * @return the winning message based on the current marker which denotes the player
     */
    public String playerWin()
    {
        String number = "";
        
        if(marker.equals("o"))
        {
            number = "1";
        }
        else
        {
            number = "2";
        }
        return "Player " + number + " is the winner. Congratulations!";
    }

    /**
     * Method to display the current state of the board.
     * @return the board in terms of a string.
     */
    public String toString()
    {
        String display = "1 2 3 4 5 6 7\n";
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(c == board[0].length - 1)
                {
                    display += board[r][c] + '\n';
                }
                else
                {
                    display += board[r][c] + " ";
                }
            }            
        }
        return display;
    } 
    
    /**
     * General method to check for connect fours in all directions.
     * @param c col to check
     * @throws IndexOutOfBoundsException if c is less than 0 or greater than 6
     * @return return if connect four exists, false otherwise
     */
    public boolean fourConnected(int c)
    {
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        return fourConnectedDiagonal(height[c], c) || fourConnectedHorizontal(height[c], c) || fourConnectedVertical(height[c], c);
    }

     /**
     * Method to check vertical connect fours 
     * @param r row to check
     * @param c col to check
     * @throws IndexOutOfBoundsException if r is less than 0 or greater than 5 or if c is less than 0 or greater than 6
     * @return return if connect four exists, false otherwise
     */
    public boolean fourConnectedVertical(int r, int c)
    {
        if (r < 0 || r > 5) {
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        boolean connected = false;
        
        int combo = 0;
        int temp = r + 3;

        if(r <= 2) //check below
        {
            for(int i = r; i <= temp; i++)
            {
                if((board[i][c].equals(marker)))
                {
                    combo++;
                }
            }
            if(combo == 4)
            {
                connected = true;
            }
        }

        return connected;
    }

    /**
     * Method to check horizontal connect fours 
     * @param r row to check
     * @param c col to check
     * @throws IndexOutOfBoundsException if r is less than 0 or greater than 5 or if c is less than 0 or greater than 6
     * @return return if connect four exists, false otherwise
     */
    public boolean fourConnectedHorizontal(int r, int c)
    {
        if (r < 0 || r > 5) {
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        boolean connected = false;
        
        int combo = 0;
        int temp = c - 3;

        if(c >= 3) //check left
        {
            for(int i = c; i >= temp; i--)
            {
                if((board[r][i].equals(marker)))
                {
                    combo++;
                }
            }
            if(combo == 4)
            {
                connected = true;
            }
        }

        combo = 0;
        temp = c + 3;

        if(c <= 3) //check right
        {
            for(int i = c; i < temp; i++)
            {
                if((board[r][i].equals(marker)))
                {
                    combo++;
                }
            }
            if(combo == 4)
            {
                connected = true;
            }
        }

        return connected;
    }

    /**
     * Method to check diagonal connect fours 
     * @param r row to check
     * @param c col to check
     * @throws IndexOutOfBoundsException if r is less than 0 or greater than 5 or if c is less than 0 or greater than 6
     * @return return if connect four exists, false otherwise
     */
    public boolean fourConnectedDiagonal(int r, int c)
    {
        if (r < 0 || r > 5) {
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if (c < 0 || c > 6) {
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        boolean connected = false;
        
        int combo = 0;
        int row = r;
        int col = c;

        if(r <= 2 && c >= 3) //check bottom left
        {
            for(int i = 0; i < 4; i++)
            {
                if((board[row][col].equals(marker)))
                {
                    combo++;
                }
                row++;
                col--;            
            }

            if(combo == 4)
            {
                connected = true;
            }
        }

        combo = 0;
        row = r;
        col = c;

        if(r <= 2 && c <= 3) //check bottom right
        {
            for(int i = 0; i < 4; i++)
            {
                if((board[row][col].equals(marker)))
                {
                    combo++;
                }
                row++;
                col++;      
            }

            if(combo == 4)
            {
                connected = true;
            }
        }

        combo = 0;
        row = r;
        col = c;

        if(r >= 3 && c >= 3) //check top left
        {
            for(int i = 0; i < 4; i++)
            {
                if((board[row][col].equals(marker)))
                {
                    combo++;
                }
                row--;
                col--;   
            }

            if(combo == 4)
            {
                connected = true;
            }
        }

        combo = 0;
        row = r;
        col = c;

        if(r >= 3 && c <= 3) //check top right
        {
            for(int i = 0; i < 4; i++)
            {
                if((board[row][col].equals(marker)))
                {
                    combo++;
                }
                row--;
                col++;           
            }

            if(combo == 4)
            {
                connected = true;
            }
        }

        return connected;
    }
}
import java.io.Console;

/**
* The class Tictactoe contains methods that allow Tictactoe to function.
*
* Tictactoe class has multiple different functions that together allow 
* game to function. 
*
* @author Mikko Salonen <address removed>
* @version 1.0
* @since Java 14
*/

public class Tictactoe {
    int width;
    int height;
    String[][] arena;
    int minPawns;


    /**
    * Main method for running program.
    *
    * This method calls for constructor to create new game.
    */
    public static void main(String [] args) {
        Tictactoe game = new Tictactoe();
    }

    /**
    * Board constructor
    *
    * Creates board and starts the game. Also resets roundcounter.
    */
    public Tictactoe() {
        
        createBoard();

        baseGame(0);
    }

    /**
    * Creating board.
    *
    * Before creation, program asks for board size.
    */
    public void createBoard() {
        try {
            
            Console c = System.console();
            System.out.println("\nInsert \"Noughts and crosses\" board size");
            int size = Integer.parseInt(c.readLine());
            System.out.println("\nInsert required number of adjacent pawns which lead to win.");
            int minPawns = Integer.parseInt(c.readLine());
            setSize(size, minPawns);
        }
        catch(IllegalArgumentException e) {
            cc();
            System.out.println("Given value is not valid. " + e.getMessage());
            createBoard();
        }
    }  

    /**
    * Returns board size.
    * 
    * Returns board size as int value. It also sets board height and width (if later on board is required to hold different height and width).
    * Requesting generateArena function to initiate arena creation.
    *
    * @param boardSize is the int variable that is being validated.
    * @param minPawns the number of adjacent chips which grant victory
    */
    public void setSize(int boardSize, int minPawns) {
        if(boardSize<3 || minPawns>boardSize || minPawns<3) {
            throw new IllegalArgumentException("Board size is too small or required number of adjacent pawns is too small (below 3) or too large for that arena.");
        } else {
            if(boardSize>=10 && minPawns<5) {
                throw new IllegalArgumentException("Required number of adjacent pawns is too low. Required adjacent pawn amount needs to be 5 or greater.");
            }
            this.minPawns = minPawns;
            this.width = boardSize;
            this.height = boardSize;
            arena = new String[boardSize][boardSize];
            generateArena(boardSize);
        }
    }
    /**
    * Arena creation
    *
    * Creates arena based on size that has been given previously.
    *
    * @param size is the amount of rows shall be created for arena.
    */
    public void generateArena(int size) {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                arena[i][j] = "[ ]";
            }
        }
    }
    

    /**
    * Checks every time when called that has anyone won the game.
    * 
    * Compares every single possible combination every time.
    * 
    * @param roundAmount checks that if there are still free slots in within game arena.
    */  
    public void checkGameStatus(int roundAmount) {
        int z = minPawns;
        for(int i = 0; i <= this.width -1; i++) { //Increasing vertical values
            for(int j = 0; j <= this.height -1; j++) { //Increasing horizontal values
                if(arena[i][j] == "[X]") { //Player pawn check
                    if(j <= this.width - z) { //Horizontal check, checks from left to right
                        int x = j;
                        int check = 0;     
                        for(int a = 1; a <= z; a++) {
                            if(arena[i][x] == "[X]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[i][x] == "[X]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Player has won!");
                                        System.exit(0);
                                    }
                                }           
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            x++;
                        }
                    }
                    if(i <= this.width - z) { //Vertical check, checks from top to bottom
                        int y = i;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][j] == "[X]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[y][j] == "[X]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Player has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            y++;
                        }
                    }
                    if(j <= this.width - z && i <= this.height - z) { // From left descending towards right. Increasing Y and X values together.
                        int y = i;
                        int x = j;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][x] == "[X]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[y][x] == "[X]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Player has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            y++; //Increasing Y and X values every round.
                            x++;
                        }
                    }

                    if(j >= this.width - this.width + z - 1 && i <= this.height - z) { // From right descending towards left. Increasing Y while decreasing X value.
                        int y = i;
                        int x = j;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][x] == "[X]") {
                                check++;//Check value increases every time it sees wanted pawn.
                                if(arena[y][x] == "[X]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Player has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            y++; //Increasing Y values and decreasing X values every round.
                            x--;
                        }
                    }
                }
                if(arena[i][j] == "[O]") { //Computer pawn check
                    if(j <= this.width - z) { //Horizontal check, checks from left to right
                        int x = j;
                        int check = 0;               
                        for(int a = 1; a <= z; a++) {
                            if(arena[i][x] == "[O]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[i][x] == "[O]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Computer has won!");
                                        System.exit(0);
                                    }
                                }           
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            x++;
                        }
                    }
                    if(i <= this.width - z) { //Vertical check, checks from top to bottom
                        int y = i;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][j] == "[O]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[y][j] == "[O]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Computer has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            y++;
                        }
                    }
                    if(j <= this.width - z && i <= this.height - z) { // From left descending towards right. Increasing Y and X values together.
                        int y = i;
                        int x = j;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][x] == "[O]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[y][x] == "[O]" && a <= z) {
                                    if (check == z) {  //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Computer has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0; //If value is something else than what is required, Check value shall reset.
                            }
                            y++; //Increasing Y and X values every round.
                            x++;
                        }
                    }

                    if(j >= this.width - this.width + z - 1 && i <= this.height - z) { // From right descending towards left. Increasing Y while decreasing X value.
                        int y = i;
                        int x = j;
                        int check = 0;
                        for(int a = 1; a <= z; a++) {
                            if(arena[y][x] == "[O]") {
                                check++; //Check value increases every time it sees wanted pawn.
                                if(arena[y][x] == "[O]" && a <= z) {
                                    if (check == z) { //If check value is as great as required pawns game will end.
                                        cc();
                                        printArena();
                                        System.out.println("Game has ended. Computer has won!");
                                        System.exit(0);
                                    }
                                }
                            }
                            else {
                                check = 0;  //If value is something else than what is required, Check value shall reset.
                            }
                            y++; //Increasing Y values and decreasing X values every round.
                            x--;
                        }
                    }
                }
            }
        }
        if(roundAmount>=this.width*this.height) { // Counts player turns. If value is greater or same as arena size (width multiplied by height), program will determinate that there is no free spots left. Program will end.
            cc();
            printArena();
            System.out.println("Game has ended. There was no successor this time!");
            System.exit(0);
        }
    }

    /**
    * Printing arena 
    *
    * Prints the arena for player, also shows pawns.
    */
    public void printArena() {
        String horizontalIndicator = "   ";
        for(int j = 0; j < this.width; j++) //horizontal row indicator number
        {
            horizontalIndicator = horizontalIndicator + " " + j + " ";
        }
        System.out.println(horizontalIndicator);
        for(int i = 0; i < this.height; i++)
        {
            System.out.print(" " + i + " ");
            for(int j = 0; j < this.width; j++)
            {
                System.out.print(arena[i][j]);
            }
            System.out.println();
        }
    }

    /**
    * Vertical row visualizer
    *
    * Arena visualizer shows player indicator of vertical row where user is putting users pawn.
    *
    * @param vertical Shows player what row player has selected.
    */  

    public void verticalDemo(int vertical) {
        cc();
        String horizontalIndicator = "   ";
        for(int j = 0; j < this.width; j++) //horizontal row indicator number
        {
            horizontalIndicator = horizontalIndicator + " " + j + " ";
        }
        System.out.println(horizontalIndicator);
        for(int i = 0; i < this.height; i++)
        {
            System.out.print(" " + i + " ");
            for(int j = 0; j < this.width; j++)
            {
                if(j == vertical) {
                    System.out.print("[#]");
                }
                else {
                    System.out.print(arena[i][j]);
                }
                
            }
            System.out.println();
        }   
    }
    /**
    * Vertical and horizontal row visualizer
    *
    * Arena visualizer shows player indicator of vertical and horizontal row where user is putting users pawn.
    *
    * @param vertical Shows clearly which vertical row player has selected
    * @param horizontal Shows what horizontal row player has selected.
    */ 

    public void arenaDemo (int vertical, int horizontal) {
        cc();
        String horizontalIndicator = "   ";
        for(int j = 0; j < this.width; j++) //horizontal row indicator number
        {
            horizontalIndicator = horizontalIndicator + " " + j + " ";
        }
        System.out.println(horizontalIndicator);
        for(int i = 0; i < this.height; i++)
        {
            System.out.print(" " + i + " ");
            for(int j = 0; j < this.width; j++)
            {
                if(j == vertical && i == horizontal) {
                    System.out.print("[#]");
                }
                else {
                    System.out.print(arena[i][j]);
                }
                
            }
            System.out.println();
        }
    }

    /**
    * Validates pawn location
    *
    * Checks if selected location is not occupied
    *
    * @param vert Vertical row indicator
    * @param horiz Horizontal row indicator
    */ 
    public void validateLocation(int vert, int horiz) {
        if (arena[horiz][vert] != "[ ]") {
            throw new IllegalArgumentException("Current position is already occupied. Please try another slot.");
        }
        else {
            arena[horiz][vert] = "[X]";
            cc();
            System.out.println("\n\n\nYour value has been added.\n");
        }
    }

    /**
    * Validates pawn location
    *
    * @param size If given value is greater than arena size, program will throw IllegalArgumentException.
    */ 
    public void validateSize(int size) {
        if (size > this.width - 1 || size < 0) {
            throw new IllegalArgumentException("Given value is out of bounds. Please give reasonable value."); 
        }
    }
  
    /**
    * Validates opponent pawn location
    *
    * Checks if selected location is not occupied
    *
    * @param vert Vertical row indicator
    * @param horiz Horizontal row indicator
    * @param roundAmount Counts turns
    */
    public void validateOpponentLocation(int vert, int horiz, int roundAmount) {
        if (arena[horiz][vert] != "[ ]") {
            opponentTurn(roundAmount); //Opponent is trying to place pawn! Position occupied! Trying again!
        }
        else {
            arena[horiz][vert] = "[O]";
            System.out.println("\nOpponent » Pawn has been placed on board.\n\nYour turn!\n"); 
        }
    }



    /**
    * Opponents turn!
    * 
    * Generates two randomly generated numbers, width and height.
    * 
    * @param roundAmount Counts turns between computer and player
    */ 
    public void opponentTurn(int roundAmount) {
        double width = Math.random() + roundAmount;
        width = (width - roundAmount) * this.width;
        double height = Math.random() + roundAmount;
        height = (height - roundAmount) * this.height;
        int x = (int)width;
        int y = (int)height;
        validateOpponentLocation(x, y, roundAmount);
        roundAmount++;
        checkGameStatus(roundAmount);
        baseGame(roundAmount);
    }


    /**
    * Clearing console
    *
    * ClearConsole, cc for short. Clears console. It removes extra lines and tries to keep console as tidy as possible!
    */
    public void cc() {
        for(int i = 0; i <= 20; i++) {
            System.out.println();
        }
    }
    
    /**
    * Player interface
    *
    * Player can interact with program only within baseGame method. Program asks position from user 
    * where user wants to put users pawn. After telling vertical row program shows user's selected row with verticalDemo -method. 
    * Same goes with horizontal rows. Only exception is that method is then arenaDemo. Every value is being validated by program.
    * Player can not select already occupied slots or select outside playable area.
    *
    * @param roundAmount Counts player turns between player and computer
    */
    public void baseGame(int roundAmount) {
        try {           
            Console c = System.console();
            printArena();
            System.out.println("Where would you like to place your chip? (Vertical value first)");
            int vertical = Integer.parseInt(c.readLine());
            validateSize(vertical);
            verticalDemo(vertical);
            System.out.println("A bit more specific? (Insert horizontal value!)");
            int horizontal = Integer.parseInt(c.readLine());
            validateSize(horizontal);
            arenaDemo(vertical, horizontal);
            validateLocation(vertical, horizontal);
            roundAmount++;
            checkGameStatus(roundAmount);
            System.out.println("Opponent's turn!");
            opponentTurn(roundAmount);
        }
        catch(IllegalArgumentException e) {
            cc();
            System.out.println("Given value is not valid. " + e.getMessage());
            baseGame(roundAmount);
        }
    }
}

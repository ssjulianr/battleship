import java.util.*;

public class BattleshipGame {
    // ArrayList to store Battleship objects and GameHelper instance.
    private final ArrayList<Battleship> battleships = new ArrayList<>();
    private final GameHelper helper = new GameHelper();
    private int numberOfGuesses = 0;

    // Method to set up the game by creating battleships and placing them.
    public void initializeGame() {
        // Create Battleship objects and set their enemy names.
        Battleship enemy1 = new Battleship();
        enemy1.setEnemyName("Kraven The Hunter");
        Battleship enemy2 = new Battleship();
        enemy2.setEnemyName("Mysterio");
        Battleship enemy3 = new Battleship();
        enemy3.setEnemyName("Electro");

        // Add battleships to the ArrayList.
        battleships.add(enemy1);
        battleships.add(enemy2);
        battleships.add(enemy3);

        // Display introductory messages.
        System.out.println("Doc Ock has recently escaped prison and is assembling the Sinister Six!");
        System.out.println("Enemies: Kraven The Hunter, Mysterio, Electro");
        System.out.println("Use your Spidey-Senses and defeat the villains before they unite!");

        // Place battleships on the grid.
        for (Battleship battleship : battleships) {
            ArrayList<String> newLocation = helper.placeInitialPosition(3);
            battleship.setShipPositions(newLocation);
        }
    }

    // Method to start playing the game.
    public void startGame() {
        // Continue playing until all battleships are defeated.
        while (!battleships.isEmpty()) {
            String userGuess = helper.getPlayerInput("Take a shot"); // Get user's guess.
            checkUserGuess(userGuess); // Check if the guess hits a battleship.
        }
        endGame(); // All battleships are defeated, end the game.
    }

    // Method to check if the user's guess hit a battleship.
    private void checkUserGuess(String userGuess) {
        numberOfGuesses++; // Increment the number of guesses.

        String result = "Missed!"; // Default result is a miss.

        // Iterate through battleships to check if the guess hits any.
        for (Battleship battleship : battleships) {
            result = battleship.checkHitOrMiss(userGuess); // Check if the guess hits a battleship.

            if (result.equals("Hit!")) {
                break; // Stop checking if a battleship is hit.
            } else if (result.equals("Destroyed!")) {
                battleships.remove(battleship); // Remove a destroyed battleship from the list.
                break; // Stop checking if a battleship is destroyed.
            }
        }
        System.out.println(result); // Display the result of the guess (Hit, Miss, or Destroyed).
    }

    // Method to display the end game message.
    private void endGame() {
        System.out.println("Congratulations! The enemies are defeated. Doc Ock has no allies left!");
        System.out.println("You sunk all the battleships in " + numberOfGuesses + " attempts!");
    }

    // Main method to start the game.
    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame(); // Create an instance of the BattleshipGame class.
        game.initializeGame(); // Set up the game by placing battleships.
        game.startGame(); // Start playing the game.
    }
}

// Class representing a battleship.
class Battleship {
    private String enemyName;
    private ArrayList<String> shipPositions;

    // Method to set the positions of the battleship's cells.
    public void setShipPositions(ArrayList<String> positions) {
        shipPositions = positions;
    }

    // Method to set the name of the battleship's enemy.
    public void setEnemyName(String name) {
        this.enemyName = name;
    }

    // Method to check if a user's guess hit the battleship.
    public String checkHitOrMiss(String userInput) {
        String result = "Missed!"; // Default result is a miss.
        int index = shipPositions.indexOf(userInput); // Check if the guess matches any cell.

        if (index >= 0) {
            shipPositions.remove(index); // If hit, remove the cell from the battleship's position.

            if (shipPositions.isEmpty()) {
                result = "Destroyed!"; // If all cells are hit, battleship is destroyed.
                System.out.println("You've destroyed " + enemyName + "!");
            } else {
                result = "Hit!"; // If not all cells are hit, it's a hit.
            }
        }

        return result; // Return the result of the guess (Miss, Hit, or Destroyed).
    }
}

// Class to assist with game logic and user input.
class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;

    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int shipCount = 0;

    // Method to get user input.
    public String getPlayerInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    // Method to place battleships' initial positions on the grid.
    public ArrayList<String> placeInitialPosition(int size) {
        int[] shipCoords = new int[size];
        int attempts = 0;
        boolean success = false;

        shipCount++;
        int increment = getIncrement();

        // Try to place battleships while meeting conditions.
        while (!success && attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE);

            for (int i = 0; i < shipCoords.length; i++) {
                shipCoords[i] = location;
                location += increment;
            }

            if (fitsOnGrid(shipCoords, increment)) {
                success = availableCoords(shipCoords);
            }
        }

        savePosition(shipCoords);

        return convertToAlphaFormat(shipCoords);
    }

    // Check if battleship coordinates fit within the grid.
    boolean fitsOnGrid(int[] shipCoords, int increment) {
        int lastLocation = shipCoords[shipCoords.length - 1];
        if (increment == 1) {
            return getRowFromIndex(shipCoords[0]) == getRowFromIndex(lastLocation);
        } else {
            return lastLocation < GRID_SIZE;
        }
    }

    // Check if coordinates are available on the grid.
    boolean availableCoords(int[] shipCoords) {
        for (int coord : shipCoords) {
            if (grid[coord] != 0) {
                return false;
            }
        }
        return true;
    }

    // Mark positions on the grid as used.
    void savePosition(int[] shipCoords) {
        for (int index : shipCoords) {
            grid[index] = 1;
        }
    }

    // Convert numeric coordinates to "a0"-style coordinates.
    private ArrayList<String> convertToAlphaFormat(int[] shipCoords) {
        ArrayList<String> alphaCells = new ArrayList<>();
        for (int index : shipCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }

    // Convert numeric index to "a0"-style coordinates.
    String getAlphaCoordsFromIndex(int index) {
        int row = getRowFromIndex(index);
        int column = index % GRID_LENGTH;
        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    }

    // Get the row number from a numeric index.
    private int getRowFromIndex(int index) {
        return index / GRID_LENGTH;
    }

    // Determine the increment value for placing ships.
    private int getIncrement() {
        if (shipCount % 2 == 0) {
            return 1;
        } else {
            return GRID_LENGTH;
        }
    }
}

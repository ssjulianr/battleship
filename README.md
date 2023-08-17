# Battleship Game - Readme

## Introduction
Welcome to the Peter Parker's Battleship, a project developed by Julian Robinson. This Java-based game lets you take on the role of a Spider-Man, battling members of the Sinister Six in a classic battleship-style game. Your mission is to defeat all the enemies by accurately guessing their positions on the grid and sinking their battleships before they unite!

## How It Works
The Battleship Game is a turn-based game where you, as the player, take shots at the grid to locate and sink the hidden battleships of the villains. Here's a breakdown of how the game works:

1. **Initialization**: The game starts by creating battleships with different names such as "Kraven The Hunter," "Mysterio," and "Electro." Each battleship occupies three consecutive positions on the grid. The grid is represented as a 7x7 matrix.

2. **Gameplay Loop**: The game continues in a loop until all the battleships are defeated. In each turn, you input your guess for a grid position to hit. The grid positions are represented using a combination of a letter (A to G) and a number (0 to 6), e.g., "a2" or "d5."

3. **Guess Evaluation**: After each guess, the game checks if your guess hits any battleship. If you hit a battleship, you'll be notified with a "Hit!" message. If you successfully sink a battleship by hitting all its positions, you'll get a "Destroyed!" message.

4. **Victory Condition**: The game ends when you've successfully sunk all the battleships. You'll receive a victory message, along with the number of attempts it took you to defeat the enemies.

## How to Use
To play the Battleship Game, follow these steps:

1. **Compile**: Make sure you have the Java Development Kit (JDK) installed on your computer. Compile the source code using a Java compiler, such as `javac`. Open your terminal or command prompt and navigate to the directory containing the source code files. Run the following command to compile the code:

javac BattleshipGame.java

2. **Run the Game**: After compiling, run the game by executing the compiled `.class` file. Use the following command:

java BattleshipGame

3. **Gameplay**: Follow the on-screen instructions to play the game. Input your guesses for grid positions when prompted. If you hit a battleship or sink it, you'll receive relevant messages. Continue playing until you defeat all the battleships.

4. **Victory**: Once you've sunk all the battleships, the game will display a victory message, along with the number of attempts you took to win.

## Game Rules
- The grid is a 7x7 matrix, with columns labeled from A to G and rows numbered from 0 to 6.
- Battleships occupy three consecutive positions either horizontally or vertically on the grid.
- You win the game by sinking all battleships before they unite.


For any questions or feedback, please contact Julian Robinson at [ssjulianr@gmail.com](mailto:ssjulianr@gmail.com).

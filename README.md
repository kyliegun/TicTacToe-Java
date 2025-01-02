# TicTacToe-Java
### Final Project for Object Oriented Programming Class (SFWRENG 2OP3)

Developed a command-line TicTacToe game in Java utilizing Object Oriented Programming principles and design. 

This game allows for three different modes of play:
- Player vs Player
- Player vs Computer
- Computer vs Computer

## Features
- Player vs Player: Two players can take turns playing the game locally on the same machine.
- Player vs Computer: A player can compete against a computer opponent with basic AI.
- Computer vs Computer: Watch two AI-controlled computers play against each other.
- Text-based Interface: The game runs entirely in the terminal with a simple text interface.
- Game Logic: Includes winning conditions, turns, and a reset function.

## Installation
1. Clone the repository.

2. Install Maven if you do not already have it.
- Windows: Download from Maven Official Website
- MacOS: Use Homebrew:
brew install maven
- Linux: Use package manager, for Ubuntu:
sudo apt-get install maven

3. Build the project.
mvn package

## Running the Application
To run the application, use the following command:

java -cp target/[YOUR_JAR_FILE].jar [MainClassWithPackageName]
Replace [YOUR_JAR_FILE] with the name of the generated JAR file and [MainClassWithPackageName] with the fully qualified name of your main class.
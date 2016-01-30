package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.Console;

public class Game{
	final static int MAX_GUESSES=8;
	private static Game instance = new Game();
//	int playerTurn;
	String guessPhrase;
	HashSet<Character> guesses;  
	ArrayList<Character> wrongGuesses; 
		
	private Game() {
		guesses = new HashSet<Character>();
		wrongGuesses = new ArrayList<Character>();
	}
	
	public static Game getInstance() {
		return instance;
	}
	public void playGame() {
		getPhrase();
		boolean isOver = false;
		while (!isOver){
			makeGuess();
			isOver = updateStatus();
		}
	}
	private void getPhrase() {
		System.out.println("Please enter the phrase: ");
		Console c = System.console();
		do { 
			char arr[] = c.readPassword();
			guessPhrase = new String(arr).toUpperCase();
		}
		while(!isValidPhrase(guessPhrase));
		
	}
	private void makeGuess() {
		System.out.println("Enter guess:");
		Scanner input = new Scanner(System.in);
		char guess;
		do {
			guess = Character.toUpperCase(input.next().charAt(0));
		}
		while (!isValidGuess(guess));
		guesses.add(guess);
		boolean exists = guessPhrase.indexOf(guess) != -1;
		if(!exists) {
			wrongGuesses.add(guess);
		}
		System.out.println("Your guess was " + (exists ? "correct" : "incorrect" ) + ". You have " + (MAX_GUESSES - wrongGuesses.size()) + " guesses left.");
	}
	private boolean updateStatus() {
		StringBuilder toPrint = new StringBuilder();
		boolean victory = true;
		boolean defeat = false;
		for (char c : guessPhrase.toCharArray()){
			if (guesses.contains(c)){
				toPrint.append(c);
			}
			else {
				victory = false;
				if (c == ' ') {
					toPrint.append(c);
				}
				else {
					toPrint.append('_');
				}
			}
		}
		System.out.println(toPrint.toString());
		if (victory) {
			System.out.println ("Congrats! You win!");
		}
		else{ 
			defeat = wrongGuesses.size() >= MAX_GUESSES;
			if(defeat) { 
				System.out.println("Game Over! You lose.");
			}
		}
		return victory || defeat;	
	}
	
	private boolean isValidGuess(char c) {
		if (guesses.contains(c)){
			System.out.println("Invalid. You already guessed " + c);
			return false;
		}
		if (!Character.isAlphabetic(c)) {
			System.out.println("Invalid. Please enter an alphabetic character.");
			return false;
		}
		return true;
	}
	private boolean isValidPhrase(String str) {
		for (char c : str.toCharArray()){
			if(!Character.isAlphabetic(c) && c != ' ') {
				System.out.println("Please only use alphabetic characters or spaces.");
				return false;
			}
		}
		return true;
	}
}

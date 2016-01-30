package game;

public class HangmanLauncher {

	public static void main(String[] args) {
		Game hangman = Game.getInstance();
		hangman.playGame();
	}

}

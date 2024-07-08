import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generate a number between 1 and 100
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Welcome to the Number Game!");
            System.out.println("I have generated a number between 1 and 100. Try to guess it!");
            System.out.println("You have a maximum of 10 attempts.");

            while (attempts < 10 && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    hasGuessedCorrectly = true;
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all 10 attempts. The number was: " + numberToGuess);
            }

            totalScore += 10 - attempts; // Calculate score based on attempts taken
            roundsPlayed++;

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Game over! You played " + roundsPlayed + " rounds.");
        System.out.println("Your total score is: " + totalScore);

        scanner.close();
    }
}


/********************************************************/
//	     		Guessing game with leaderboard			//
//	 Author: Liam English-Birge		Date: 04/25/2019	//
/********************************************************/

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class guessingGame {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);//initialize scanner as "scan"
		int guessNumber, target, guessCount = 0, numberGames = 0;//initialize integer variables
		Boolean guessing = true;//boolean variables that keep loops looping
		Random rand = new Random();//create random object
		target = rand.nextInt(101 - 0);//generate random integer between and including 1 and 100
		int scores[] = new int[100];//create integer scoreboard with 100 slots
		
		System.out.println("--Guess My Number--");//title
		System.out.println("I'm thinking of a number between 1 and 100 -- Enter '404' to give up.");//info and exit command
		
		BREAK_LOOP1://break label 
		while (guessing) {//main game loop
			System.out.print("Enter your guess: ");//prompt for user input
			guessNumber = scan.nextInt();//scan user input
			if (guessNumber > 100) {//if the input is more than 100:
				if (guessNumber == 404) {//if the input equals 404: exit program after displaying victory taunt
					System.out.println("Looks like I win! You show excellent self control for a carbon-based lifeform.");
					guessing = false;//stop main loop
					break BREAK_LOOP1;//break loop and go to break label
				} else {//if input isn't 404 tell user that their number was out of range
					System.out.println("That number is not in the range specified, please choose a number less than 100.");
				}
			} else if (guessNumber < 1) {//if input is less than 1 tell user their number was out of range
				System.out.println("That number is not in the range specified, please choose a number greater than 1.");
			}else if (guessNumber > target) {//if input is greater than the target number:
				guessCount++;//increase guess counter by 1
				if (((target + 5) > guessNumber) && (guessNumber > (target - 5))) {//if input is within a 10 number difference of target number but still larger:
					System.out.println("Too high, you're getting close!");//tell the user they were close
				} else {//otherwise:
					System.out.println("Too high, guess again.");//just tell them they were too high
				}
			} else if (guessNumber < target) {//if input was less than the target:
				guessCount++;//increase guess counter by 1
				if (((target + 10) > guessNumber) && (guessNumber > (target - 10))) {//if input is within a 10 number difference of target number but still less:
					System.out.println("Too low, you're getting close!");//tell the user they were close
				} else {//otherwise:
					System.out.println("Too low, guess again.");//just tell them they were too high
				}
			} else if (guessNumber == target) {//if the input equals the target number:
				guessCount++;//increase guess counter by 1
				numberGames++;
				if (guessCount == 1) {//if the user guessed the target in one try, print special message
					System.out.println("You got my number in a single guess, have you been outside today?");
				} else {//otherwise:
					System.out.println("You guessed my number in " + guessCount + " tries, Good job!");//display number of guesses
				}
				System.out.print("Would you like to play again? <y/n>: ");//ask if user would like to play again
				char contChoice = scan.next().charAt(0);//read first character inputed by user
				if (contChoice == 'y' || contChoice == 'Y') {//if character equals y/Y:
					scores[numberGames] = guessCount;//add current game score to score list
					target = rand.nextInt(101 - 0);//choose new target number
					guessCount = 0;//reset guess counter to 0
				} else {//if character is anything besides y/Y display leaderboard then exit the program
					scores[numberGames] = guessCount;//add current game score to score list
					System.out.println("--Leaderboard--");//title
					for (int i = 1; i < scores.length; i++) {//loop through score list
						if (scores[i] == 0) {//if score equals zero (game not played)
							scores[i] = 888888888;//set score equal to a arbitrarily large place-holder
						}
					}
					Arrays.sort(scores);//sort score list in descending order
					for (int i = 1; i <= 10; i++) {//loop through top 10 scores
						if (i == 10) {//if the score's placement is tenth:
							System.out.println(i + ". " + scores[i]);//print line with only one space after period
						} else {//otherwise
							System.out.println(i + ".  " + scores[i]);//print line with 2 spaces after period
						}
					}
					wait4Enter();//wait for user to press enter/display prompt message (see bellow)
					guessing = false;//stop main loop
					break BREAK_LOOP1;//break to break label
				}
			}
		}
		System.out.println("Shutting down...");//notify that the program is shutting down
	}
	public static void wait4Enter() {//new method
		Scanner s = new Scanner(System.in);//initialize scanner
		System.out.println("Press enter to quit.");//prompt user
		s.nextLine();//read for any input including enter key
	}
}


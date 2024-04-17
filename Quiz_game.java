package task_04_quiz_Application;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
	private String questionText;
	private String[] options;
	private int correctAnswerIndex;

	public QuizQuestion(String questionText, String[] options, int correctAnswerIndex) {
		this.questionText = questionText;
		this.options = options;
		this.correctAnswerIndex = correctAnswerIndex;
	}

	public String getQuestionText() {
		return questionText;
	}

	public String[] getOptions() {
		return options;
	}

	public boolean isCorrectAnswer(int userAnswerIndex) {
		return userAnswerIndex == correctAnswerIndex;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}
}

public class Quiz_game {
	private static Scanner scanner = new Scanner(System.in);
	private static Timer questionTimer;

	public static void main(String[] args) {
		QuizQuestion[] questions = setupQuiz();
		int score = playQuiz(questions);
		displayResult(score, questions.length,questions);
		scanner.close();
	}

	private static QuizQuestion[] setupQuiz() {
		// Define your quiz questions here
		QuizQuestion[] questions = new QuizQuestion[5];

		questions[0] = new QuizQuestion("What is the capital of India?",
				new String[] { "A. Chennai", "B. Mumbai", "C.goa ", "D. Delhi" }, 3);

		questions[1] = new QuizQuestion("Which planet is known as the Red Planet?",
				new String[] { "A. Jupiter", "B. Mars", "C. Venus", "D. Saturn" }, 0);

		questions[2] = new QuizQuestion("What is the powerhouse of the cell?",
				new String[] { "A. Ribosome", "B. Mitochondrion", "C. Nucleus", "D. Golgi apparatus" }, 2);

		questions[3] = new QuizQuestion("how many fingers in human hands",
				new String[] { "A. 9", "B. 4", "C. 5", "D. 10" }, 3);

		questions[4] = new QuizQuestion("CS in engineering stands for ",
				new String[] { "A. Common Stand", "B. Computer Science", "C. Computer Sketch", "D. Customer Science" },
				1);

		return questions;
	}

	private static int playQuiz(QuizQuestion[] questions) {
		int score = 0;

		for (QuizQuestion question : questions) {
			System.out.println("Question: " + question.getQuestionText());
			String[] options = question.getOptions();
			for (String option : options) {
				System.out.println(option);
			}

			startTimer(10); // Start timer for 10 seconds

			System.out.print("Enter your answer (A, B, C, or D): ");
			String userAnswer = getUserAnswerWithTimer();

			questionTimer.cancel(); // Cancel timer after user provides answer

			int userAnswerIndex = -1;
			switch (userAnswer.toLowerCase()) {
			case "a":
				userAnswerIndex = 0;
				break;
			case "b":
				userAnswerIndex = 1;
				break;
			case "c":
				userAnswerIndex = 2;
				break;
			case "d":
				userAnswerIndex = 3;
				break;
			default:
				System.out.println("Invalid choice. Skipping question.");
				continue;
			}

			if (question.isCorrectAnswer(userAnswerIndex)) {
				System.out.println("Correct!\n");
				score++;
				System.out.println();
			} else {
				System.out.println("Incorrect.\n");
			}
		}

		return score;
	}

	private static String getUserAnswerWithTimer() {
		String userAnswer = "";
		while (userAnswer.isEmpty()) {
			userAnswer = scanner.nextLine().toUpperCase();
		}
		return userAnswer;
	}

	private static void startTimer(int seconds) {
		questionTimer = new Timer();
		questionTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("\nTime's up! Moving to the next question.");
				scanner.nextLine(); // Consume any remaining user input
			}
		}, seconds * 1000);
	}

	private static void displayResult(int score, int totalQuestions, QuizQuestion[] questions) {
		System.out.println("Quiz completed!");
		System.out.println("Your score: " + score + " out of " + totalQuestions);
		System.out.println("Summary:");

		// Display correct and incorrect answers
		System.out.println("Correct answers: " + score);
		System.out.println("Incorrect answers: " + (totalQuestions - score));

		// Display correct answers for incorrectly answered questions
		System.out.println("\nCorrect Answers");

		for (QuizQuestion question : questions) {
			int correctAnswerIndex = question.getCorrectAnswerIndex();
			String[] options = question.getOptions();

			// Check if the question was answered incorrectly
			if (!question.isCorrectAnswer(correctAnswerIndex)) {
				System.out.println("Question: " + question.getQuestionText());
				System.out.println("Correct Answer: " + options[correctAnswerIndex]);
				System.out.println();
			}
		}
	}
}

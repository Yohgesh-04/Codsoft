import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApplication {
    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Berlin", "4. Madrid"}, 0),
        new Question("What is 5 + 7?", new String[]{"1. 10", "2. 11", "3. 12", "4. 13"}, 2),
        new Question("What is the largest planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2),
    };

    private static int score = 0;
    private static int[] userAnswers = new int[questions.length];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz Application!");

        for (int i = 0; i < questions.length; i++) {
            askQuestion(i);
        }

        displayResults();
    }

    private static void askQuestion(int questionIndex) {
        Question question = questions[questionIndex];
        System.out.println("\n" + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                recordAnswer(questionIndex, -1); // -1 indicates no answer was given
            }
        };

        timer.schedule(task, 10000); // 10-second timer

        System.out.print("Enter your answer (1-4): ");
        int answer = scanner.nextInt() - 1;

        timer.cancel(); // Cancel the timer if answer is submitted within time
        recordAnswer(questionIndex, answer);
    }

    private static void recordAnswer(int questionIndex, int answer) {
        userAnswers[questionIndex] = answer;
        if (answer == questions[questionIndex].correctAnswerIndex) {
            score++;
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i].questionText);
            System.out.println("Your answer: " + (userAnswers[i] >= 0 ? questions[i].options[userAnswers[i]] : "No answer"));
            System.out.println("Correct answer: " + questions[i].options[questions[i].correctAnswerIndex]);
        }
    }
}

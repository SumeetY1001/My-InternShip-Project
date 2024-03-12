import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}

class Quiz {
    private Question[] questions;

    public Quiz() {
        questions = new Question[5];
        questions[0] = new Question("Number of primitive data types in Java are?", new String[]{"A) 6 ", "B) 7 ", "C) 8 ", "D) 9"}, 2);
        questions[1] = new Question("What is the size of float and double in java?", new String[]{"A) 32 and 32", "B) 32 and 64", "C) 64 and 64", "D) 64 and 32"}, 1);
        questions[2] = new Question("Automatic type conversion is possible in which of the possible cases?", new String[]{"A) byte to int", "B) int to long", "C) long to int", "D) short to int"}, 1);
        questions[3] = new Question("In which year was the first version of Java released?", new String[]{"A) 1991", "B) 1995", "C) 1998", "D) 2000"}, 1);
        questions[4] = new Question("What is the primary function of JRE?", new String[]{"A) Compilation", "B) Debugging", "C) Execution", "D) Development"}, 2);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            question.display();
            char userChoice = getValidatedInput(scanner);
            int userChoiceIndex = userChoice - 'A';

            if (question.isCorrect(userChoiceIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectAnswer());
            }
            System.out.println();
        }

        // Display final score
        System.out.println("Quiz complete!");
        System.out.println("Your score: " + score + "/" + questions.length);

        scanner.close();
    }

    private char getValidatedInput(Scanner scanner) {
        String input;
        do {
            System.out.print("Enter your choice (A, B, C, or D): ");
            input = scanner.nextLine().toUpperCase();
        } while (!input.matches("[A-D]"));
        return input.charAt(0);
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public void display() {
        System.out.println(question);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public boolean isCorrect(int userChoiceIndex) {
        return userChoiceIndex == correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        return options[correctAnswerIndex];
    }
}

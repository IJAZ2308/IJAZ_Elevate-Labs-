import java.util.*;

class Question {
    String question;
    String[] options;
    int correctOption; // index of correct option (0–3)

    // Constructor
    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    // Display the question and options
    public void displayQuestion() {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    // Check answer
    public boolean isCorrect(int userAnswer) {
        return userAnswer - 1 == correctOption;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Create questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "London", "Paris", "Madrid"}, 2));
        questions.add(new Question("Which language runs in a web browser?",
                new String[]{"Java", "C", "Python", "JavaScript"}, 3));
        questions.add(new Question("Who developed Java?",
                new String[]{"Microsoft", "Sun Microsystems", "Google", "Apple"}, 1));
        questions.add(new Question("Which data structure uses LIFO?",
                new String[]{"Queue", "Stack", "Array", "Linked List"}, 1));

        // Step 2: Quiz loop
        int score = 0;
        System.out.println("🎯 Welcome to the Quiz App!");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.displayQuestion();
            System.out.print("Your answer (1-4): ");
            int answer = sc.nextInt();

            if (q.isCorrect(answer)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.options[q.correctOption]);
            }
        }

        // Step 3: Display final result
        System.out.println("\n🏁 Quiz Finished!");
        System.out.println("Your Score: " + score + " / " + questions.size());

        sc.close();
    }
}

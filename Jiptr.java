import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Jiptr {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jiptr [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes));
    }

    private static void runPrompt() throws IOException {
        // get input from user
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        // infinite loop
        for (;;) {
            System.out.println("> ");
            // read from user
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.tokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
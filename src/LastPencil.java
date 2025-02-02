import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class LastPencil {
    private int pencils;
    private final HashMap<Integer, String> players = new HashMap<>();
    private String currentPlayer;
    private final String BOT = "Jack";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        players.put(1, "John");
        players.put(2, "Jack");

        this.pencils = promptForPencilCount(scanner);
        this.promptForFirstPlayer(scanner);

        while (this.pencils > 0) {
            displayPencils();
            displayTurnMessage();

            if (this.currentPlayer.equals(BOT)) {
                botMove();
            } else {
                playerMove(scanner);
            }

            if (this.pencils == 0) {
                System.out.printf("%s won!\n", togglePlayerTurn());
                break;
            }

            togglePlayerTurn();
        }

        scanner.close();
    }

    private int promptForPencilCount(Scanner scanner) {
        System.out.println("How many pencils would you like to use:");
        int pencils;

        while (true) {
            String input = scanner.nextLine();
            try {
                pencils = Integer.parseInt(input);
                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        return pencils;
    }

    private void botMove() {
        int move;

        if (this.pencils % 4 == 0) {
            move = 3;
        } else if (this.pencils % 4 == 3) {
            move = 2;
        } else if (this.pencils % 4 == 2 || this.pencils == 1 ) {
            move = 1;
        } else {
            move = new Random().nextInt(Math.min(3, pencils)) + 1;
        }

        System.out.println(move);
        this.pencils -= move;
    }

    private void playerMove(Scanner scanner) {
        int move;
        while (true) {
            if (scanner.hasNextInt()) {
                move = scanner.nextInt();
                scanner.nextLine();

                if (move < 1 || move > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } else if (move > this.pencils) {
                    System.out.println("Too many pencils were taken");
                } else {
                    break;
                }
            } else {
                System.out.println("Possible values: '1', '2' or '3'");
                scanner.nextLine();
            }
        }

        this.pencils -= move;
    }

    private String togglePlayerTurn() {
        this.currentPlayer = this.currentPlayer.equals(players.get(1)) ? players.get(2) : players.get(1);
        return this.currentPlayer;
    }

    private void promptForFirstPlayer(Scanner scanner) {
        System.out.println("Who will be the first (John, Jack):");

        String name;
        while (true) {
            name = scanner.nextLine();
            if (name.equals("John") || name.equals("Jack")) {
                break;
            }
            System.out.println("Choose between 'John' and 'Jack'");
        }

        this.currentPlayer = name;
    }

    private void displayTurnMessage() {
        System.out.printf("%s's turn!\n", this.currentPlayer);
    }

    private void displayPencils() {
        System.out.println("|".repeat(this.pencils));
    }
}
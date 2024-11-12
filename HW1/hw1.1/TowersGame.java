import java.util.Scanner;

public class TowersGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rings: ");
        int numRings = scanner.nextInt();
        Towers towers = new Towers(numRings);

        boolean continuePlaying = true;
        while (continuePlaying) {
            towers.displayTowers();

            System.out.print("Continue? (yes/no): ");
            String choice = scanner.next().toLowerCase();

            if (choice.equals("no")) {
                continuePlaying = false;
                break;
            }

            System.out.print("Enter start peg and end peg (e.g., 1 2): ");
            int startPeg = scanner.nextInt();
            int endPeg = scanner.nextInt();

            if (validMove(towers, startPeg, endPeg)) {
                towers.move(startPeg, endPeg);
            }
        }

        System.out.println("Game over!");
        scanner.close();
    }

    private static boolean validMove(Towers game, int startPeg, int endPeg) {
        if (startPeg < 1 || startPeg > 3 || endPeg < 1 || endPeg > 3 || startPeg == endPeg) {
            System.out.println("Invalid move: Peg numbers must be 1, 2, or 3, and start peg must be different from end peg.");
            return false;
        }

        if (game.countRings(startPeg) == 0) {
            System.out.println("Invalid move: Start peg is empty.");
            return false;
        }

        if (game.countRings(endPeg) > 0 && game.getTopDiameter(startPeg) >= game.getTopDiameter(endPeg)) {
            System.out.println("Invalid move: Cannot place a larger ring on top of a smaller ring.");
            return false;
        }

        return true;
    }
}

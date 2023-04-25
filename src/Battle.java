import java.util.Scanner;

public class Battle {
    private static boolean win = false;

    public static void start() {
        System.out.println("Player 1, place your ships to the game field\n");
        Player player1 = new Player();
        System.out.println("Player 2, place your ships to the game field\n");
        Player player2 = new Player();
        while (!win) {
            // turn player1
            Field.printField(player2.fog);
            System.out.println("---------------------");
            Field.printField(player1.playerField);
            System.out.println("Player 1, it's your turn:");
            shoot(player2);
            // turn player2
            Field.printField(player1.fog);
            System.out.println("---------------------");
            Field.printField(player2.playerField);
            System.out.println("Player 2, it's your turn:");
            shoot(player1);
        }
    }

    private static void shoot(Player player) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println();
            int[] cord = player.inputCord(scanner.next());
            System.out.println();
            if (player.playerField[cord[0]][cord[1]] != '~') {
                player.setPlayerField(cord[0], cord[1], 'X');
                if (player.fog[cord[0]][cord[1]] == '~') {
                    player.counter++;
                    if (player.counter == 17) {
                        System.out.println();
                        System.out.println("You sank the last ship. Player won. Congratulations!");
                        win = true;
                    }
                }
                player.fog[cord[0]][cord[1]] = 'X';
                player.playerField[cord[0]][cord[1]] = 'X';
                if (Player.checkNeighbors(cord[0], cord[1], cord[0], cord[1], player.playerField)) {
                    System.out.println("You sank a ship! Specify a new target:");
                } else {
                    System.out.println("You hit a ship! Try again:");
                }
            } else {
                player.fog[cord[0]][cord[1]] = 'M';
                player.playerField[cord[0]][cord[1]] = 'M';
                System.out.println("You missed. Try again:");
            }
            Player.enter();
        } catch (IllegalAccessException e) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }
    }

}

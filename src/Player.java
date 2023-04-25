import java.util.Scanner;

public class Player extends Field {
    protected char[][] playerField;
    protected int counter;
    protected char[][] fog = Field.createField();

    public Player() {
        this.playerField = field;
        create();
    }

    public void create() {
        for (Ship ship : Ship.values()) {
            Field.printField(this.playerField);
            System.out.printf("Enter the coordinates of the %s (%d cells): \n", ship.getPrintName(), ship.getLength());
            this.play(ship);
        }
        Field.printField(this.playerField);
        Player.enter();
    }

    public void play(Ship ship) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            String temp = scanner.nextLine();
            String[] array = temp.split(" ");
            System.out.println();
            try {
                int[] cord1 = inputCord(array[0]);
                int[] cord2 = inputCord(array[1]);
                if (setPlayerShip(cord1, cord2, ship)) break;
            } catch (IllegalAccessException e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
    }

    private boolean setPlayerShip(int[] cord1, int[] cord2, Ship ship) {
        int i, j, y, x;
        if (cord1[0] > cord2[0]) {
            y = cord1[0];
            i = cord2[0];
        } else {
            y = cord2[0];
            i = cord1[0];
        }
        if (cord1[1] > cord2[1]) {
            x = cord1[1];
            j = cord2[1];
        } else {
            x = cord2[1];
            j = cord1[1];
        }
        try {
            validCord(y, x, i, j, ship);
            for (int k = i; k <= y; k++) {
                for (int l = j; l <= x; l++) {
                    setPlayerField(k, l, 'O');
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage() + " Try again:");
            return false;
        }
        return true;
    }


    private void validCord(int y, int x, int i, int j, Ship ship) throws IllegalAccessException {
        if ((y == i) == (x == j)) {
            throw new IllegalAccessException("Error! Wrong ship location!");
        }
        if (Math.abs((y - i) - (x - j)) + 1 != ship.getLength()) {
            throw new IllegalAccessException("Error! Wrong length of the " + ship.getPrintName() + "!");
        }
        if (!checkNeighbors(y, x, i, j, playerField)) {
            throw new IllegalAccessException("Error! You placed it too close to another one.");
        }
    }

    protected static boolean checkNeighbors(int y, int x, int i, int j, char[][] playerField) {
        boolean yMinusBorder = true, xMinusBorder = true, yPlusBorder = true, xPlusBorder = true;
        for (int k = i; k <= y; k++) {
            for (int l = j; l <= x; l++) {
                if (k == 0) yMinusBorder = false;
                if (k == 9) yPlusBorder = false;
                if (l == 0) xMinusBorder = false;
                if (l == 9) xPlusBorder = false;
                if (playerField[k][l] == 'O') {
                    return false;
                }
                if (yMinusBorder) {
                    if (playerField[k-1][l] == 'O') {
                        return false;
                    }
                }
                if (yPlusBorder) {
                    if (playerField[k+1][l] == 'O') {
                        return false;
                    }
                }
                if (xMinusBorder) {
                    if (playerField[k][l-1] == 'O') {
                        return false;
                    }
                }
                if (xPlusBorder) {
                    if (playerField[k][l+1] == 'O') {
                        return false;
                    }
                }
                yMinusBorder = true;
                xMinusBorder = true;
                yPlusBorder = true;
                xPlusBorder = true;
            }
        }
        return true;
    }

    public void setPlayerField(int y, int x, char N) {
        playerField[y][x] = N;
    }

    public static void enter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

}
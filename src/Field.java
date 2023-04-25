public class Field {
    protected final char[][] field;
    protected static final String[] Y = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    protected static final String[] X = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public Field() {
        this.field = createField();
    }

    protected static char[][] createField() {
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
        return field;
    }

    protected static void printField(char[][] field) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int y = 0; y < 10; y++) {
            System.out.print(Y[y]);
            for (int x = 0; x < 10; x++) {
                System.out.print(" " + field[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    protected int[] inputCord(String inputCord) throws IllegalAccessException {
        String x;
        String y;
        y = String.valueOf(inputCord.charAt(0));
        if (contains(y, Y)) {
            if (inputCord.length() == 3) {
                x = inputCord.charAt(1) + String.valueOf(inputCord.charAt(2));
            } else if (inputCord.length() == 2) {
                x = String.valueOf(inputCord.charAt(1));
            } else {
                throw new IllegalAccessException();
            }
            if (contains(x, X)) {
                return new int[] {getIndex(y, Y), getIndex(x, X)};
            } else {
                throw new IllegalAccessException();
            }
        } else {
            throw new IllegalAccessException();
        }
    }

    private boolean contains(String c, String[] array) {
        for (String x : array) {
            if (x.equalsIgnoreCase(c)) {
                return true;
            }
        }
        return false;
    }

    private int getIndex(String cord, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(cord)) {
                return i;
            }
        }
        return 0;
    }

}

public class Main {
    public static void main(String[] args) {
        String result = TicTacToeGame.getInstance().startGame();
        System.out.println("Game Result: " + result);
    }
}
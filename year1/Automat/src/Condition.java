import java.util.ArrayList;

public class Condition {
    ArrayList<Move> moves = new ArrayList<>();
    int position;

    public Condition(ArrayList<Move> moves, int position) {
        this.moves = moves;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
}

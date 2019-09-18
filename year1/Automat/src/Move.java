public class Move {
    int start;
    int end;
    String lexem;

    public Move(int start, int end, String lexem) {
        this.start = start;
        this.end = end;
        this.lexem = lexem;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }
}

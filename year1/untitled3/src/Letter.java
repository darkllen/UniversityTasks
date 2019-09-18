public class Letter {
    String input; // $ - end of string
    int p; char c;
    public Letter(String input){ this.input=input; p=0; c=' '; }
    public char nextChar() {
        if (p<input.length()) c=input.charAt(p++); else c='$'; return c;
    }
}

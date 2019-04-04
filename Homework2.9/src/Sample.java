import java.util.Random;

public class Sample {
    private int possibleRes;
    private int leftSide;
    private int rightSide;
    private int result=-999;
    private int resultReal;
    private String action;

    /**create randomSample
     *
     * @param possibleRes
     */
    public Sample(int possibleRes) {
        Random random = new Random();
        this.possibleRes = possibleRes;
        int act =  random.nextInt(2);

        switch (act){
            case 0:
                action = "+";
                leftSide = random.nextInt(possibleRes);
                rightSide = random.nextInt(possibleRes-leftSide);
                resultReal= leftSide+rightSide;
                break;
            case 1:
                action = "-";
                leftSide = random.nextInt(possibleRes);
                while (leftSide==0)leftSide = random.nextInt(possibleRes);
                rightSide = random.nextInt(leftSide);
                resultReal = leftSide-rightSide;


        }
            }

    public int getResultReal() {
        return resultReal;
    }

    public void setResultReal(int resultReal) {
        this.resultReal = resultReal;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(int leftSide) {
        this.leftSide = leftSide;
    }

    public int getRightSide() {
        return rightSide;
    }

    public void setRightSide(int rightSide) {
        this.rightSide = rightSide;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

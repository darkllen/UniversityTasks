public class Main {
    public static void main(String[] args) {


        ARS oilmax = ARSFactory.getARS(ARSTypes.OILMAX);
        ARS kievKuzov = ARSFactory.getARS(ARSTypes.KIEVKUZOV);

        System.out.println(oilmax.toString());
        System.out.println(kievKuzov.toString());
    }
}

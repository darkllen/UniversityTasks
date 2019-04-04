public abstract class ARSFactory {

    public static ARS getARS (ARSTypes types){
        switch (types){
            case OILMAX:return new Oilmax();
            case KIEVKUZOV:return new KievKuzov();
            default:throw new IllegalArgumentException("wrong type");
        }
    }
}

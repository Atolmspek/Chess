
public class Pawn extends PiezaAjedrez {
    
    boolean primerMov;
    
    public Pawn(String tipo, String color, int x, int y) {
        super(tipo, color, x, y);
        primerMov=true;
    }

    @Override
    public int movPosibles() {
        if (primerMov) {
            primerMov = false;
            return x + 2;
        }
        return x + 1;
    }

}


public abstract class PiezaAjedrez {
    public String tipo;
    public String color;
    public int x;
    public int y;
    
    public PiezaAjedrez(String tipo, String color, int x, int y) {
        this.tipo=tipo;
        this.color=color;
        this.x=x;
        this.y=y;
    }
    
    public abstract int movPosibles();
}

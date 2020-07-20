public class OffByN implements CharacterComparator{
    private int dif;

    public OffByN(int n){
        dif = n;
    };


    @Override
    public boolean equalChars(char x, char y){
        return (x - y == dif) || (y - x == dif);
    }
}

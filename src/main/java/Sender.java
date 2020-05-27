import java.math.BigInteger;

public class Sender extends User {
    public Sender(int k, BigInteger n) {
        super(k,n);
    }

    public BigInteger sendX() {
        return getX();
    }

    public BigInteger sendY(int[] e) {
        return getY(e);
    }
}

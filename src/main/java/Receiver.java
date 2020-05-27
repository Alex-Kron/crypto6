import java.math.BigInteger;

public class Receiver extends User {
    public Receiver(int k, BigInteger n) {
        super(k, n);
    }

    public int[] sendE() {
        return getE();
    }

    public boolean acceptRound(BigInteger x, BigInteger y, BigInteger[] u) {
        return accept(y,u,x);
    }
}

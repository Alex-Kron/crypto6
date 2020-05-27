import java.math.BigInteger;
import java.util.Random;

public class User {
    private BigInteger[] s;
    private int[] b;
    private BigInteger[] u;
    private int k;
    private BigInteger n;
    private BigInteger r;
    private int[] e;

    protected User(int k, BigInteger n) {
        s = new BigInteger[k];
        b = new int[k];
        u = new BigInteger[k];
        this.k = k;
        this.n = n;
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            s[i] = new BigInteger(n.bitLength(), r);
            while (s[i].compareTo(n) >= 0 || s[i].compareTo(BigInteger.ZERO) <= 0) {
                s[i] = new BigInteger(n.bitLength(), r);
            }
            b[i] = r.nextInt(2);
            BigInteger tmp = BigInteger.valueOf(-1).pow(b[i]);
            u[i] = tmp.multiply(MathFunction.inverse(s[i].pow(2), n)).mod(n);
        }
    }

    protected BigInteger getX() {
        Random rand = new Random();
        BigInteger r = new BigInteger(n.bitLength(), rand);
        while (r.compareTo(n) >= 0 || r.compareTo(BigInteger.ZERO) <= 0) {
            r = new BigInteger(n.bitLength(), rand);
        }
        this.r = r;
        int bit = rand.nextInt(2);
        BigInteger x = BigInteger.valueOf(-1).pow(bit).multiply(r.pow(2)).mod(n);
        return x;
    }

    protected int[] getE() {
        int[] e = new int[k];
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            e[i] = r.nextInt(2);
        }
        this.e = e;
        return e;
    }

    protected BigInteger getY(int[] e) {
        BigInteger y = r;
        for (int i = 0; i < k; i++) {
            y = y.multiply(s[i].pow(e[i])).mod(n);
        }
        return y;
    }

    protected boolean accept(BigInteger y, BigInteger[] ui, BigInteger x) {
        BigInteger z = y.pow(2);
        for (int i = 0; i < k; i++) {
            z = z.multiply(ui[i].pow(e[i])).mod(n);
        }
        return z.compareTo(BigInteger.ZERO) != 0 && (z.compareTo(x) == 0 || z.compareTo(x.negate()) == 0);
    }

    protected BigInteger[] getOpenKey() {
        return u;
    }
}

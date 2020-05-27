import java.math.BigInteger;
import java.util.Random;


public class Center {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;

    public static void main(String[] args) {
        int k = 3;
        int t = 3;
        Center center = new Center(3);
        Sender s1 = new Sender(k, center.getN());
        Sender s2 = new Sender(k, center.getN().subtract(BigInteger.ONE));
        Receiver r1 = new Receiver(k, center.getN());
        if (center.rounds(t, s1, r1)) {
            System.out.println("Первый прошёл проверку");
        } else {
            System.out.println("Первый НЕ прошёл проверку");
        }
        if (center.rounds(t, s2, r1)) {
            System.out.println("Второй прошёл проверку");
        } else {
            System.out.println("Второй НЕ прошёл проверку");
        }
    }

    public Center(int size) {
        byte[] pb = new byte[size];
        byte[] qb = new byte[size];
        Random rand = new Random();
        rand.nextBytes(pb);
        rand.nextBytes(qb);
        p = new BigInteger(pb);
        q = new BigInteger(qb);
        while (!MathFunction.simpleTest(p,3)) {
            rand.nextBytes(pb);
            p = new BigInteger(pb);
        }
        while (!MathFunction.simpleTest(q,3)) {
            rand.nextBytes(qb);
            q = new BigInteger(qb);
        }
        n = p.multiply(q);
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return n;
    }

    public boolean rounds(int t, Sender a, Receiver b) {
        for (int i = 0; i < t; i++) {
            BigInteger x = a.sendX();
            int[] e = b.sendE();
            BigInteger y = a.sendY(e);
            if (!b.acceptRound(x,y, a.getOpenKey())) {
                return false;
            }
        }
        return true;
    }
}

package bandeau;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BandeauLock extends Bandeau {
    boolean bandeauLibre;
    private final Lock verrouBandeau= new ReentrantLock();


    public BandeauLock() {
        super();
        this.bandeauLibre=true;
    }

    public void verrouillerBandeau() {
        verrouBandeau.lock();
    }

    public void déverrouillerBandeau() {
        verrouBandeau.unlock();
    }
}

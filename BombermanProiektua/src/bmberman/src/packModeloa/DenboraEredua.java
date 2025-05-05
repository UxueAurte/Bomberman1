package bmberman.src.packModeloa;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class DenboraEredua extends Observable {
    private long hasieraDenbora;
    private long iraupena;
    private Timer timer;
    private boolean martxan;

    private static DenboraEredua instantzia = null;

    private DenboraEredua() {
        this.iraupena = 0;
        this.martxan = false;
    }

    public static DenboraEredua getDenboraEredua() {
        if (instantzia == null) {
            instantzia = new DenboraEredua();
        }
        return instantzia;
    }

    public void hasieratu() {
        if (!martxan) {
            hasieraDenbora = System.currentTimeMillis();
            martxan = true;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (martxan) {
                        iraupena = System.currentTimeMillis() - hasieraDenbora;
                        setChanged();
                        notifyObservers(iraupena);
                    }
                }
            }, 0, 1000); // Segundoero eguneratu
        }
    }

    public void gelditu() {
        if (martxan) {
            martxan = false;
            if (timer != null) {
                timer.cancel();
                timer.purge();
            }
        }
    }

    public long getIraupena() {
        return iraupena;
    }

    public String getIraupenaFormatuan() {
        long segunduak = (iraupena / 1000) % 60;
        long minutuak = (iraupena / (1000 * 60)) % 60;
        long orduak = iraupena / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d", orduak, minutuak, segunduak);
    }
}

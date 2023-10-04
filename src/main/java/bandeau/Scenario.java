package bandeau;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire pour représenter la classe-association UML
 */
class ScenarioElement {
    Effect effect;
    int repeats;

    ScenarioElement(Effect e, int r) {
        effect = e;
        repeats = r;
    }
}

/**
 * Un scenario mémorise une liste d'effets, et le nombre de repetitions pour chaque effet
 * Un scenario sait se jouer sur un bandeau.
 */
public class Scenario extends Thread {

    private boolean iAmFree = true;




    private final List<ScenarioElement> myElements = new LinkedList<>();


    public Scenario() {
    }

    /**
     * Ajouter un effect au scenario.
     *
     * @param e       l'effet à ajouter
     * @param repeats le nombre de répétitions pour cet effet
     */
    public void addEffect(Effect e, int repeats) {
        if (!iAmFree) {
            System.out.println("Scénario en cours de défilement");
        } // on ne peut pas modifier
        else {
            myElements.add(new ScenarioElement(e, repeats));
        }
    }

    /**
     * Jouer ce scenario sur un bandeau
     *
     * @param b le bandeau ou s'afficher.
     */
    public void playOn(BandeauLock b) {

        Thread thread = new Thread(
                () -> {
                    iAmFree=false;
                    b.verrouillerBandeau();
                    for (
                            ScenarioElement element : myElements) {
                        for (int repeats = 0; repeats < element.repeats; repeats++) {
                            element.effect.playOn(b);
                        }
                    }
                    b.déverrouillerBandeau();
                }
        );
        thread.start();
    }


}

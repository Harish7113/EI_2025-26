

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final List<Observer> observerList = new ArrayList<>();
    private float currentPrice;

    public void register(Observer obs) {
        observerList.add(obs);
    }

    public void unregister(Observer obs) {
        observerList.remove(obs);
    }

    public void changePrice(float newPrice) {
        this.currentPrice = newPrice;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (Observer obs : observerList) {
            obs.onPriceUpdate(currentPrice);
        }
    }
}

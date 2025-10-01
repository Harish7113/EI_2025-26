

public class Investor implements Observer {
    private final String investorName;

    public Investor(String investorName) {
        this.investorName = investorName;
    }

    @Override
    public void onPriceUpdate(float latestPrice) {
        System.out.println(">> " + investorName + " notified: Stock price changed to " + latestPrice);
    }
}

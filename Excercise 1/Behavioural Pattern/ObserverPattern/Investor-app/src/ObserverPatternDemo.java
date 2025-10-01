// ObserverPatternDemo.java


public class ObserverPatternDemo {
    public static void main(String[] args) {
        Stock stock = new Stock();

        Investor alice = new Investor("Alice");
        Investor bob = new Investor("Bob");

        stock.register(alice);
        stock.register(bob);

        stock.changePrice(100.0f);
        stock.changePrice(105.5f);
    }
}

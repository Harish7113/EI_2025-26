public class FootballPlayer implements Player {
    private String name;
    private String position;
    private int jerseyNumber;

    public FootballPlayer(String name, String position, int jerseyNumber) {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public Player clone() {
        return new FootballPlayer(this.name, this.position, this.jerseyNumber);
    }

    @Override
    public void showDetails() {
        System.out.println("Player: " + name + ", Position: " + position + ", Jersey: " + jerseyNumber);
    }

    // Setters for customization after cloning
    public void setName(String name) {
        this.name = name;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
}

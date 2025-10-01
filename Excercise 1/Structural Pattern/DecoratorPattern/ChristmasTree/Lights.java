public class Lights extends TreeDecorator {
    public Lights(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String decorate() {
        return super.decorate() + " with Lights";
    }
}

public class Star extends TreeDecorator {
    public Star(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String decorate() {
        return super.decorate() + " with Star";
    }
}

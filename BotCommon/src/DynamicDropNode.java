import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

public class DynamicDropNode extends AbstractBotNode {

    public DynamicDropNode(AbstractScript c, Filter excludeFromDrop) {
        super(c);
        this.excludeFromDrop = excludeFromDrop;
    }

    Filter excludeFromDrop;
    Condition condition = ()->{
        c.getInventory().dropAllExcept(excludeFromDrop);
        c.getInventory().deselect();

        return c.getInventory().onlyContains(excludeFromDrop);
    };

    @Override
    public boolean isValid() {
        return c.getInventory().isFull();
    }

    @Override
    public int execute() {
        c.log("Dropping");

        c.sleepUntil(condition, 20000);
        return 0;
    }
}

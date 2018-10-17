import org.dreambot.api.script.AbstractScript;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class DropperNode extends AbstractBotNode {
    public DropperNode(AbstractScript c) {
        super(c);
    }

    @Override
    public boolean isValid() {
       return c.getInventory().isFull();
    }

    @Override
    public int execute() {
        c.log("Dropping");
        c.sleepUntil(condition, 30000);
        return 0;
    }

    org.dreambot.api.utilities.impl.Condition condition = ()->{
        c.getInventory().dropAllExcept(item -> item.getName().endsWith(" pickaxe"));
        return c.getInventory().isEmpty();
    };

}

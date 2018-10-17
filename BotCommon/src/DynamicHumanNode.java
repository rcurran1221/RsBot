import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DynamicHumanNode extends AbstractBotNode {

    public DynamicHumanNode(AbstractScript c, Condition isValid, Runnable execute){
        super(c);
        this.isValid = isValid;
        this.exectue = execute;
    }

    Condition isValid;
    Runnable exectue;

    @Override
    public boolean isValid() {
        return isValid.verify();
    }

    @Override
    public int execute() {
        exectue.run();
        return 0;
    }
}

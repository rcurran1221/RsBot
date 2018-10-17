import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

public class DynamicKillScriptNode extends AbstractBotNode {

    public DynamicKillScriptNode(AbstractScript c, Condition killCondition){
        super(c);
        this.killCondition = killCondition;

    }

    Condition killCondition;

    @Override
    public boolean isValid() {
        return killCondition.verify();
    }

    @Override
    public int execute() {
        c.sleep(15000);
        c.getTabs().logout();
        c.stop();
        return 0;
    }
}

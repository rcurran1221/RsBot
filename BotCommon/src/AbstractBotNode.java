import org.dreambot.api.script.AbstractScript;

public abstract class AbstractBotNode {

    protected final AbstractScript c;

    public AbstractBotNode(AbstractScript c){
        this.c = c;
    }

    public abstract boolean isValid();

    public abstract int execute();
}

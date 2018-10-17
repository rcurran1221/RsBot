import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.script.AbstractScript;

import java.util.Random;

public class BankNodeAbstract extends AbstractBotNode {

    private BankLocation bankLocation = BankLocation.EDGEVILLE;
    private Random r = new Random();

    public BankNodeAbstract(AbstractScript c){
        super(c);
    }

    @Override
    public boolean isValid() {
        return !c.getLocalPlayer().isAnimating() && c.getInventory().isFull();
    }

    @Override
    public int execute() {
        c.getBank().open(bankLocation);
        if(c.getBank().isOpen()) {
            c.getBank().depositAllExcept(item -> item.getName().endsWith(" axe"));
            c.sleep(r.nextInt(500) + 500);
            c.getBank().close();
        }

        return CutterBankerN.returnValue;
    }
}

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.items.Item;

public class DynamicBankNode extends AbstractBotNode{

    public DynamicBankNode(AbstractScript c, BankLocation banklocation, Filter<Item> noBankFilter) {
        super(c);
        this.bankLocation = banklocation;
        this.noBankFilter = noBankFilter;
    }

    BankLocation bankLocation;
    Filter<Item> noBankFilter;

    Condition bankHasBeenOpened = ()->{
        if(bankLocation.getArea(4).contains(c.getLocalPlayer())){
            return c.getBank().open(bankLocation);
        }
        if(!c.getBank().open(bankLocation)){
            c.sleepUntil(()->!c.getLocalPlayer().isMoving(), 8000);
            return false;
        }
        return false;
    };

    @Override
    public boolean isValid() {
        return c.getInventory().isFull();
    }

    @Override
    public int execute() {
        c.log("Banking...");

        c.sleepUntil(bankHasBeenOpened, 20000);
        c.getBank().depositAllExcept(noBankFilter);
        c.getBank().close();

        return 0;
    }
}

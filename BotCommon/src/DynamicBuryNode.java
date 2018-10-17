import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.items.Item;

import java.util.List;

public class DynamicBuryNode extends AbstractBotNode {

    public DynamicBuryNode(AbstractScript c, String[] bones){
        super(c);
        this.bones = bones;
    }

    String[] bones;

    @Override
    public boolean isValid() {
        return c.getInventory().isFull() && c.getInventory().contains(bones);
    }

    @Override
    public int execute() {
        List<Item> items = c.getInventory().all();

        for(Item item: items){
            for(String bone : bones){
                if(item.getName().equals(bone)){
                    item.interact("Bury");
                    c.sleepUntil(()->item == null, 2000);
                }
            }
        }

        return 0;
    }
}

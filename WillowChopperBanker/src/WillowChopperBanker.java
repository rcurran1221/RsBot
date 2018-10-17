import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.items.Item;

import java.util.Random;


@ScriptManifest(name = "WillowChopperBanker", version = 1, author = "me", category = Category.WOODCUTTING)
public class WillowChopperBanker extends AbstractScript {

    AbstractBotNode botNodes[];
    Filter<Item> noBankFilter = item -> item.getName().endsWith("axe");
    Area treeArea = new Area(3083, 3239, 3091, 3227);
    Random r = new Random();

    @Override
    public void onStart(){
        botNodes = new AbstractBotNode[]{new DynamicBankNode(this, BankLocation.DRAYNOR, noBankFilter )
                ,new DynamicWalkAreaNode(this, treeArea, Calculations.random(5000, 6000))
                ,new DynamicChopNode(this, new String[]{"Willow"})
                ,new AntiBanNode(this, ()->r.nextDouble() > 0.93)};
    }

    @Override
    public int onLoop() {
        for(AbstractBotNode node : botNodes){
            if(node.isValid()){
                node.execute();
            }
        }
        return 0;
    }
}

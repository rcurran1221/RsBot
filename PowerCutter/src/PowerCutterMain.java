import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.items.Item;

import java.util.Random;


@ScriptManifest(name = "PowerCutter", author = "me", version = 1, category = Category.WOODCUTTING)
public class PowerCutterMain extends AbstractScript {

    AbstractBotNode[] botNodes;
    Filter<Item> dropAllExcept = item -> item.getName().endsWith("axe");
    Random r = new Random();



    @Override
    public void onStart(){
        botNodes = new AbstractBotNode[]{new DynamicKillScriptNode(this, ()-> getSkills().getRealLevel(Skill.WOODCUTTING) >= 30)
                ,new DynamicDropNode(this, dropAllExcept)
                ,new DynamicChopNode(this,true)
                ,new DynamicHumanNode(this, ()-> getWalking().getRunEnergy() > 75 && !getWalking().isRunEnabled(), ()->getWalking().toggleRun())};
    }


    @Override
    public int onLoop() {
        for(AbstractBotNode botNode : botNodes){
            if(botNode.isValid()){
                botNode.execute();
            }
        }

        return 0;
    }
}

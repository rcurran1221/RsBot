import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
        import org.dreambot.api.script.Category;
        import org.dreambot.api.script.ScriptManifest;

import java.util.Random;


@ScriptManifest(name = "FighterLooter", version = 1, author = "me", category = Category.COMBAT)
public class FighterLooterMain extends AbstractScript {

    AbstractBotNode[] botNodes;
    Area walkToArea = new Area(new Tile(3184, 3301), new Tile(3170, 3290));
    Area anchorArea = new Area(new Tile(3179, 3297), new Tile(3175, 3294));

    Random r = new Random();

    Runnable runnable = ()->{
        if(getTabs().open(Tab.COMBAT)) {
            getWidgets().getWidget(593).getChild(9).interact();
        }
    };

    @Override
    public void onStart() {
        botNodes = new AbstractBotNode[]{
                new DynamicFighterNode(this, "Chicken", walkToArea)
                ,new PLooterNode(this, new String[] {"Feather", "Bones"})
                ,new DynamicBuryNode(this, new String[]{"Bones"})
                ,new DynamicAnchorNode(this, anchorArea, walkToArea, 12)
                ,new DynamicHumanNode(this, ()->getSkills().getRealLevel(Skill.ATTACK) >= 20 && getPlayerSettings().getConfig(43) != 1 , runnable)
                ,new DynamicKillScriptNode(this, ()->getSkills().getRealLevel(Skill.STRENGTH) >= 20)
                ,new AntiBanNode(this, ()-> r.nextDouble() > 0.94)};
    }

    @Override
    public int onLoop() {
        for(AbstractBotNode botNode : botNodes){
            if(botNode.isValid()){
                botNode.execute();
            }
        }
        return 25;
    }
}

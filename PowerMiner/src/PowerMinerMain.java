import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.util.Random;

@ScriptManifest(author = "me", name = "PowerMiner", version = 1, category = Category.MINING)
public class PowerMinerMain extends AbstractScript {

    AbstractBotNode minerNodes[];
    Random r = new Random();

    //power mines two copper rocks in south-east Varrock mine, no walker.
    private final Tile firstTile = new Tile(3289, 3363);
    private final Tile secondTile = new Tile( 3290, 3362);
    private final Tile tiles[] = new Tile[] {firstTile, secondTile};

    @Override
    public void onStart(){
        minerNodes =
                new AbstractBotNode[]{new DropperNode(this)
                        , new AntiBanNode(this, ()->r.nextDouble() > 0.95)
                        , new DynamicMinerNode(this, tiles)
                        , new DynamicKillScriptNode(this, ()->getSkills().getRealLevel(Skill.MINING) >= 15)};
    }


    @Override
    public int onLoop() {
        for(AbstractBotNode minerNode : minerNodes){
            if (minerNode.isValid()){
                minerNode.execute();
            }
        }
        return 500;
    }
}

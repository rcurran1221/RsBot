import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.event.CameraEvent;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.Character;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.EventListener;

public class DynamicChopNode extends AbstractBotNode
{
    public DynamicChopNode(AbstractScript c, String[] trees){
        super(c);
        this.trees = trees;
    }

    public DynamicChopNode(AbstractScript c, boolean powerCutting){
        super(c);
        this.powerCutting = powerCutting;
    }

    Boolean powerCutting = false;
    String[] trees;
    CameraEvent rotateToPitchHandler = c.getCamera().rotateToPitchEvent(383);

    @Override
    public boolean isValid() {
        return !c.getInventory().isFull();
    }

    @Override
    public int execute() {
        c.log("Chopping");
        if(powerCutting){
            if(c.getSkills().getRealLevel(Skill.WOODCUTTING) >= 15){
                trees = new String[]{"Oak", "Tree"};
                c.log("Trees set to Oak and Tree");
            }
            else{
                trees = new String[]{"Tree"};
                c.log("Trees set to Tree");
            }
        }

        GameObject tree = c.getGameObjects().closest(trees);
        if(tree == null){
            return 0;
        }

        Tile treeTile = tree.getTile();
        if(treeTile == null){
            return 0;
        }

        if(!tree.interact("Chop down")){
            return 0;
        }

        rotateToPitchHandler.run();

        if(c.getLocalPlayer().isInCombat()){
            Character npc = c.getLocalPlayer().getInteractingCharacter();
            if(npc == null){
                return 0;
            }
            npc.interact("Attack");
            c.sleepUntil(()->!npc.exists(), 30000);
        }

        c.sleepUntil(()->!c.getLocalPlayer().isMoving() || !tree.exists(),10000);

        if(!tree.exists()){
            return 0;
        }

        if(c.getDialogues().canContinue()){
            if(!tree.interact("Chop down")){
                return 0;
            }
            rotateToPitchHandler.run();
        }

        if(c.sleepUntil(()->c.getLocalPlayer().isAnimating(), 10000)){
            c.sleepUntil(()->!c.getGameObjects().getTopObjectOnTile(treeTile).getName().equals(tree.getName())
                            || c.getDialogues().canContinue()
                            || c.getInventory().isFull()
                    , 2*60*1000);
        }

        return 0;
    }
}

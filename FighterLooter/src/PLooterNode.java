import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

public class PLooterNode extends AbstractBotNode
{
    public PLooterNode(AbstractScript c, String[] loots) {
        super(c);
        this.loots = loots;
    }

    String[] loots;
    NPC targetNPC;


    //won't loot if target is one hit...
    @Override
    public boolean isValid() {
        targetNPC = DynamicFighterNode.targetNPC;
        if(targetNPC.getInteractingCharacter() != null){
            if(c.getLocalPlayer().isInCombat()){
                if(targetNPC.getInteractingCharacter().getName().equals(c.getLocalPlayer().getName())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int execute() {

        c.sleepUntil(()->!targetNPC.exists(), 30000);

        c.log("Looting");

        GroundItem[] groundItems = c.getGroundItems().getGroundItems(targetNPC.getTile());
        if(groundItems == null){
            return 25;
        }
        for (GroundItem groundItem : groundItems){
            if(groundItem == null){
                continue;
            }
            for(String loot : loots){
                if(groundItem.getName().equals(loot)) {
                    groundItem.interact("Take");

                    c.log("picking up items");

                    c.sleepUntil(() -> c.getLocalPlayer().isMoving(), 1000);
                    c.sleepUntil(() -> !groundItem.exists(), 5000);
                }
            }
        }

        return 0;
    }
}

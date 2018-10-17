import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.NPC;

public class DynamicFighterNode extends AbstractBotNode {

    public DynamicFighterNode(AbstractScript c, String target, Area combatArea) {
        super(c);
        this.target = target;
        this.combatArea = combatArea;
    }

    String target;
    public static NPC targetNPC;
    Area combatArea;

    @Override
    public boolean isValid() {
        return !c.getLocalPlayer().isInCombat();
    }

    @Override
    public int execute() {
        targetNPC = c.getNpcs().closest(npc -> npc.getName().equals(target) && !npc.isInCombat() && npc != null && combatArea.contains(npc));
        if(targetNPC.isOnScreen()){
            if(!targetNPC.interactForceLeft("Attack")){
                return 0;
            }
            c.log("Attacking target");
            c.sleepUntil(()->targetNPC.isInCombat(), 2000);
        }
        return 0;
    }
}

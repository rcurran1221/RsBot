import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.NPC;

public class DynamicFishingNode extends AbstractBotNode {

    DynamicFishingNode(AbstractScript c, String fishingType, String fishingSpotType){
        super(c);
        this.fishingType = fishingType;
        this.fishingSpotType = fishingSpotType;
    }

    String fishingType;
    String fishingSpotType;

    @Override
    public boolean isValid() {
        return !c.getLocalPlayer().isAnimating() && !c.getInventory().isFull();
    }

    @Override
    public int execute() {
        NPC fishingSpot = c.getNpcs().closest(fishingSpotType);

        if(fishingSpot == null) {
            return 0;
        }

        fishingSpot.interact(fishingType);

        c.sleepUntil(()->!c.getLocalPlayer().isMoving(), 8000);

        return 0;
    }
}

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

public class DynamicWalkAreaNode extends AbstractBotNode {

    public DynamicWalkAreaNode(AbstractScript c, Area targetArea, int sleepInterval){
        super(c);
        this.targetArea = targetArea;
        this.sleepInterval = sleepInterval;

    }

    int sleepInterval;
    Area targetArea;
    Tile targetTileInArea;
    Condition arrivedInArea = ()-> {
        if(targetArea.contains(c.getLocalPlayer())) {
            return true;
        }
        c.getWalking().walk(targetTileInArea);
        c.sleep(Calculations.random(6000, 7000));
        return false;
    };

    @Override
    public boolean isValid() {
        return !targetArea.contains(c.getLocalPlayer()) && !c.getInventory().isFull();
    }

    @Override
    public int execute() {

        c.log("area walking...");

        targetTileInArea = targetArea.getCenter();

        c.sleepUntil(arrivedInArea, sleepInterval);

        return 0;
    }
}

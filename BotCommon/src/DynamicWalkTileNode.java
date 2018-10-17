import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

public class DynamicWalkTileNode extends AbstractBotNode {

    public DynamicWalkTileNode(AbstractScript c, Tile targetTile){
        super(c);
        this.targetTile = targetTile;
    }

    Tile targetTile;
    Condition arrivedAtTile = ()->{
        if(c.getLocalPlayer().distance(targetTile) == 0){
            return true;
        }
        c.getWalking().walkExact(targetTile);
        c.sleepUntil(()->!c.getLocalPlayer().isMoving(), 8000);
        return false;
    };

    @Override
    public boolean isValid() {
        return !c.getInventory().isFull() && c.getLocalPlayer().distance(targetTile) != 0;
    }

    @Override
    public int execute() {
        c.log("Walking");

        c.sleepUntil(arrivedAtTile, 60*1000*2);

        return 0;
    }
}

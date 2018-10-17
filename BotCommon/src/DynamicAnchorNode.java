import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;

public class DynamicAnchorNode extends AbstractBotNode {

    public DynamicAnchorNode(AbstractScript c, Area anchorArea, Area walkToArea, double leash){
        super(c);
        this.anchorArea = anchorArea;
        this.walkToArea = walkToArea;
        this.leash = leash;
    }

    Area anchorArea;
    double leash;
    Area walkToArea;

    @Override
    public boolean isValid() {
        return !c.getLocalPlayer().isInCombat()
                && !anchorArea.contains(c.getLocalPlayer())
                && c.getLocalPlayer().distance(anchorArea.getNearestTile(c.getLocalPlayer())) >= leash;
    }

    @Override
    public int execute() {
        c.log("Walking to walkToArea");
        c.getWalking().walk(walkToArea.getRandomTile());
        c.sleepUntil(()-> anchorArea.contains(c.getLocalPlayer()), 10000);
        c.log("Player has arrived in anchorArea");
        return 25;
    }
}

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.Random;

public class WoodcutNodeAbstract extends AbstractBotNode {

    private String treeType = "Yew";
    private Random r = new Random();
    private Area treeArea = new Area();

    //this will get you banned, searching for yews sucks.

    public WoodcutNodeAbstract(AbstractScript c) {
        super(c);
    }

    @Override
    public boolean isValid() {
        return !c.getLocalPlayer().isAnimating() && !c.getInventory().isFull();
    }

    @Override
    public int execute() {
        GameObject tree = c.getGameObjects().closest(treeType);

        if (tree != null) {
            tree.interact("Chop down");
            c.sleep((int)tree.distance() * 200 + r.nextInt(500));
        }
        else{
            //assuming yews that are cut down are null...
            //would be bette if there was a way to know which yew spot was going to regenerate first

            //walks to middle area between yews
            if(!treeArea.contains(c.getLocalPlayer())){
                c.getWalking().walk(treeArea.getRandomTile());
            }
        }

        return CutterBankerN.returnValue;
    }
}

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class DynamicMinerNode extends AbstractBotNode {

    public DynamicMinerNode(AbstractScript c, Tile[] tiles) {
        super(c);
        this.tiles = tiles;
    }

    Tile[] tiles;

    @Override
    public boolean isValid() {
        return !c.getInventory().isFull();
    }

    @Override
    public int execute() {

        c.log("Mining...");

        if(c.getCamera().getPitch() < 370){
            c.getCamera().rotateToPitch(383);
        }

        for(Tile tile : tiles){

            GameObject rock = c.getGameObjects().getTopObjectOnTile(tile);

            if(rock != null){
                if(rock.getModelColors() != null){
                    if(!rock.interactForceLeft("Mine")){
                        if(c.getInventory().getSelectedItemName() != null){
                            c.getInventory().deselect();
                        }
                        if(!rock.interact("Mine")){
                            return 0;
                        }
                    }

                    c.sleepUntil(()->c.getLocalPlayer().isAnimating()
                            || c.getDialogues().canContinue()
                            || isRockEmpty(tile), 5000);
                    c.sleepUntil(()->isRockEmpty(tile)
                            || c.getDialogues().canContinue(), 20000);
                }
            }
        }

        c.log("Done mining...");
        return 0;
    }

    private boolean isRockEmpty(Tile tile){
        return c.getGameObjects().getTopObjectOnTile(tile).getModelColors() == null;
    }
}

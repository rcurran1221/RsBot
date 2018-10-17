import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class PFireMakeCookDrop extends AbstractBotNode {

    public PFireMakeCookDrop(AbstractScript c){super(c);}

    @Override
    public boolean isValid() {
        return c.getInventory().fullSlotCount() == 27;
    }

    @Override
    public int execute() {
        ChopWoodGetLog();
        GameObject fire = GetFire();

        //use shrimp/choves on fire

        //drop all but axe, net, tinder

        return 0;
    }

    private void ChopWoodGetLog(){
        GameObject closestTree = c.getGameObjects().closest(tree -> tree !=null && tree.getName() == "Tree");
        if(closestTree.interact("Chop")){
            c.sleepUntil(()->!c.getGameObjects().getTopObjectOnTile(closestTree.getTile()).getName().equals(closestTree.getName()), 25000);
        }

        if(!c.getInventory().contains("Logs")) {
            ChopWoodGetLog();
        }
    }

    private GameObject GetFire(){
        GameObject fire = c.getGameObjects().closest("Fire");

        if(fire.isOnScreen()){
            return fire;
        }

        Tile fireTile = c.getLocalPlayer().getTile();

        c.getInventory().get("Tinderbox").useOn("Logs");
        c.sleepUntil(()->!c.getInventory().contains("Logs"), 10000);

        fire = c.getGameObjects().getTopObjectOnTile(fireTile);

        if(fire == null){
            GetFire();
        }

        return fire;
    }

    private void CookOnFire(GameObject fire){
        if (fire == null){
            ChopWoodGetLog();
            GameObject newFire = GetFire();
            CookOnFire(newFire);
        }

        if(!c.getInventory().contains("Raw Shrimp", "Raw Anchovies")){
            return;
        }


    }

}

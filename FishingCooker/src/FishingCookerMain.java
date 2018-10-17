import org.dreambot.api.script.AbstractScript;

public class FishingCookerMain extends AbstractScript {

    AbstractBotNode[] botNodes;




    @Override
    public void onStart(){
        botNodes = new AbstractBotNode[]{new DynamicFishingNode(this, "Small Net", "Fishing Spot" )};

    }


    @Override
    public int onLoop() {
        return 0;
    }
}

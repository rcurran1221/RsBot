import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.util.Random;

@ScriptManifest(name = "VarrockMinerBanker", version = 1, author = "me", category = Category.MINING)
public class MinerBankerMain extends AbstractScript {

    AbstractBotNode[] botNodes;
    Random r = new Random();

    @Override
    public void onStart(){
        botNodes = new AbstractBotNode[]{
                new DynamicBankNode(this, BankLocation.VARROCK_EAST, item -> item.getName().endsWith("axe"))
                , new DynamicWalkTileNode(this, new Tile(3286, 3368))
                , new DynamicMinerNode(this,
                        new Tile[] {new Tile(3286, 3369), new Tile(3285, 3368)})};
    }

    @Override
    public int onLoop() {
        for(AbstractBotNode botNode : botNodes){
            if(botNode.isValid()){
                botNode.execute();
            }
        }
        return 500;
    }
}

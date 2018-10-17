import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

@ScriptManifest(name = "TestScript", author = "me", version = 1, category = Category.WOODCUTTING)
public class TestMain extends AbstractScript {



    @Override
    public int onLoop() {

        sleepUntil(()->getBank().open(BankLocation.VARROCK_EAST), 60*1000*20);

        return 0;
    }
}

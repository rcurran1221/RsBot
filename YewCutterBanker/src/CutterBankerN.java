import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.util.Random;


@ScriptManifest(author = "Barry Obama", category = Category.WOODCUTTING, description = "Edgeville Yew Cutter", name = "Edgeville Yew Cutter", version = 1)
public class CutterBankerN extends AbstractScript {

    private static Random r = new Random();
    private static AbstractBotNode[] botNodes;
    public static int returnValue = r.nextInt(500) + 1200;

    @Override
    public void onStart(){
        botNodes = new AbstractBotNode[]{new BankNodeAbstract(this), new WoodcutNodeAbstract(this), new HumanNodeAbstract(this)};
    }


    //needs a walking Node
    @Override
    public int onLoop() {
        for (AbstractBotNode botNode: botNodes){
            if(botNode.isValid()){
                botNode.execute();
            }
        }

        return r.nextInt(500) + 1200 ;
    }
}

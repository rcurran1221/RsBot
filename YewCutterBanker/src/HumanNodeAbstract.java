import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;

import java.util.Random;

public class HumanNodeAbstract extends AbstractBotNode {

    private Random r = new Random();

    public HumanNodeAbstract(AbstractScript c){
        super(c);
    }

    @Override
    public boolean isValid() {
        return c.getLocalPlayer().isAnimating();
    }

    @Override
    public int execute() {
        if(r.nextDouble() > 0.987){
            c.getSkills().hoverSkill(Skill.WOODCUTTING);
            //maybe randomly open tabs...
            c.getTabs().open(Tab.INVENTORY);
        }

        return 500;
    }
}

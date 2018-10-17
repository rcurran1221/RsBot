import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.hotkeys.TabKey;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.utilities.impl.Condition;

import java.util.Random;

public class AntiBanNode extends AbstractBotNode {

    public AntiBanNode(AbstractScript c, Condition condition){
        super(c);
        this.condition = condition;
    }

    Random r = new Random();
    int skillsCount = Skill.values().length;
    int tabsCount = Tab.values().length;

    Condition condition;




    @Override
    public boolean isValid() {
        return condition.verify();
    }

    @Override
    public int execute() {

        c.log("Antiban");

        switch (r.nextInt(3)){
            case 0: c.getMouse().moveMouseOutsideScreen();
                break;
            case 1: c.getCamera().rotateToEntity(c.getLocalPlayer());
                break;
            case 2: c.getMouse().move(c.getLocalPlayer());
                break;
        }
        return 0;
    }
}

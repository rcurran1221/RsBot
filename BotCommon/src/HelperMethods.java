import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;

public class HelperMethods {

    public static boolean LevelGained(AbstractScript c, int levelComparator, Skill skill){
        return levelComparator != c.getSkills().getRealLevel(skill);
    }
}

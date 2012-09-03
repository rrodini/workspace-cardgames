package card.game;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//import org.junit.runners.Suite.SuiteClasses;
import card.core.*;

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses(
{
	TestPokerGame2Players.class,
	TestPokerHandComparator.class,
	TestPokerRanker.class,
	TestWarGame2Players.class,
	TestWarGame3Players.class,
	TestWarGame4Players.class,
	TestWarInitialConditions.class,
	TestWarSimulatedRounds.class,
	// card.core classes
	TestCardTrick.class,
}
)

// the actual class is empty
public class TestRunAll {
}


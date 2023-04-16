import org.junit.Assert;
import org.junit.Test;
import com.vishal.bean.*;
import com.vishal.constants.Suits;
import java.util.ArrayList;

public class DeckSuffleTest {
	//Test to check shuffling is done or not
	@Test
	public void checkShuffling() {

		ArrayList<Card> unShuffled = new ArrayList<Card>();

		for (Suits suits : Suits.values()) {

			for (int i = 1; i <= 13; i++) {
				unShuffled.add(new Card(i, suits));
			}

		}

		Deck deck = new Deck();
		ArrayList<Card> shuffled = deck.getDeck();
		// to check if the new deck created is shuffled or not.
		Assert.assertEquals(false, unShuffled.toString() == shuffled.toString());
	}
}

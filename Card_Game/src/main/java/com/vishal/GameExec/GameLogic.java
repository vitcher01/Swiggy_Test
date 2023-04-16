package com.vishal.GameExec;

import java.util.ArrayList;
import java.util.Scanner;

import com.vishal.bean.*;

/* Business Logic class
   Execute the GameLogic() through Main method.
 */

public class GameLogic {
	private ArrayList<Card> deck;

	private ArrayList<Player> players;

	private ArrayList<Card> drawPile;

	private ArrayList<Card> discardPile;

	public void GameBegins() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter No of Players Playing (Min 2 and Max 4)");
		int playerCount = sc.nextInt();

		// Close Scanner to avoid leak of resource
		sc.close();

		if (playerCount < 2 || playerCount > 4) {
			throw new Exception("Minimun 2 and upto 4 can play this Game. Retry!!");
		}

		/*
		 * Deck is created and shuffled
		 */

		deck = new Deck().getDeck();

		players = new ArrayList<Player>();
		for (int i = 1; i <= playerCount; i++) {
			Player p = new Player(i);

			// Distribute 5 cards to each player
			for (int j = 1; j <= 5; j++) {
				p.drawCard(deck.get(deck.size() - 1));
				deck.remove(deck.size() - 1);
			}
			players.add(p);
			// System.out.println(p.getCardsInHand());
		}

		/*
		 * Since cards are faced down the last card in deck will be the first card in
		 * discard pile
		 */

		discardPile = new ArrayList<Card>();
		discardPile.add(deck.get(deck.size() - 1));
		deck.remove(deck.size() - 1);

		/*
		 * Now the rest of the card from deck becomes the drawPile
		 */
		drawPile = new ArrayList<Card>();
		for (Card card : deck) {
			drawPile.add(card);
		}

		// Initial params for starting player and choosing
		// a particular direction

		int playerTurn = 0;
		int direction = 1;
		/*
		 * We also maintain no of cards to be drawn in the particular turn, because of
		 * queen and jack
		 */

		int cardsToDraw = 1;

		while (true) {

			/*
			 * if the draw pile is empty, game ends in a draw.
			 */
			if (drawPile.size() < cardsToDraw) {
				System.out.println("Insufficient cards, Game Draw");
				break;
			}

			playerTurn %= playerCount;
			if (playerTurn < 0)
				playerTurn += playerCount; // if playerturn goes to negative integers.
			playerTurn %= playerCount;

			// Initialized below params to check for matching case.

			boolean matched = false;
			int matchedCard = -1;
			Card topDiscardCard = discardPile.get(discardPile.size() - 1); // top card of the discard pile with whom the
																			// player will try to match his cards.
			System.out.println("Top Card in Discard Pile is = " + discardPile.get(discardPile.size() - 1));

			/*
			 * matching mechanism of Set of Cards of Each player during his turn.
			 */
			for (Card currentPlayerCard : players.get(playerTurn).getCardsInHand()) {
				/*
				 * Matching condition for each turn player will try to match each of his card
				 * with the discard pile top card if the cards have same numbers or same suits.
				 */

				if (currentPlayerCard.getNumber() == topDiscardCard.getNumber()
						|| currentPlayerCard.getSuit() == topDiscardCard.getSuit()) {

					// check if action card on discardPile top

					if (topDiscardCard.getNumber() == 1 || topDiscardCard.getNumber() == 11
							|| topDiscardCard.getNumber() == 12 || topDiscardCard.getNumber() == 13) {
						// Player cannot stack action cards so they will skip.
						if (currentPlayerCard.getNumber() == topDiscardCard.getNumber())
							continue;
					}

					// if cards match
					System.out.println("Player " + players.get(playerTurn).getId()
							+ " has matching Card So, now Discard pile top card = " + currentPlayerCard);

					/*
					 * to check if the current player has to take more than one card or not,
					 * depending upon the action card which the previous player may have played.
					 */
					if (cardsToDraw > 1) {
						while (cardsToDraw-- > 0) {
							System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");
							/*
							 * adding the card to player's hand and removing it from the discard pile.
							 */
							players.get(playerTurn).drawCard(drawPile.get(drawPile.size() - 1));
							drawPile.remove(drawPile.size() - 1);
						}
						cardsToDraw = 1;
					}

					/*
					 * if the player matches his card then he removes the matched card from his hand
					 * and place it to the discard pile.
					 */
					players.get(playerTurn).playCard(currentPlayerCard);
					discardPile.add(currentPlayerCard);
					matched = true;
					matchedCard = currentPlayerCard.getNumber();
					break;
				}
			}

			/*
			 * if not matched then the player have to take a card from the draw pile and
			 * keep it with him.
			 */

			if (matched == false) {
				System.out.println("No cards match for player " + players.get(playerTurn).getId() + " Taking "
						+ cardsToDraw + " Card(s)");
				while (cardsToDraw-- > 0) {
					System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");
					players.get(playerTurn).drawCard(drawPile.get(drawPile.size() - 1));
					drawPile.remove(drawPile.size() - 1);
				}
				cardsToDraw = 1;
			}

			/*
			 * if player has matched and is left with no cards then, he has won the match
			 */
			if (matched == true && players.get(playerTurn).getCardsInHand().size() == 0) {

				System.out.println("Yayay!!! Player " + players.get(playerTurn).getId() + " has won the match");

				break;
			}


			// Move to next player
			playerTurn += direction;

			System.out.println("------------------------------NEXT TURN--------------------------");
		}
	}
}

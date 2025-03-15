OVERVIEW:
This codebase provides a basic implementation of a variation of the Queen’s Blood game, designed to
simulate a 2-player card game. The design assumes that the users are familiar with the basic rules
of the card game, as the implementation doesn’t include any explanation or tutorial. The game is
also limited to two players, each taking turns based on their available moves.

QUICK START:
To use this codebase:
1) Create a new PawnsBoardDeckConfig to load the deck configuration.
   EX: DeckConfiguration deckConfig = new PawnsBoardDeckConfig();

2) Create a PawnsBoardModel with a board width, height, random object, and the PawnsBoardDeckConfig object.
   EX: model = new PawnsBoardModel(5, 3, new Random(6), deckConfig);

3) Create a new deck of cards for each player by calling loadDeckConfig with a FileReader.
   String path = "docs" + File.separator + "deck.config";
   File config = new File(path);
   List<GameCard> p1Deck = deckConfig.loadDeckConfig(new FileReader(config));
   List<GameCard> p2Deck = deckConfig.loadDeckConfig(new FileReader(config));

4) Call startGame with a player 1 deck, player 2 deck, starting hand size, and boolean indicating whether shuffle to be on.
   EX: model.startGame(p1Deck, p2Deck, 5, true);

5) Call drawNextCard() before each player makes a move.
   model.drawNextCard();

6) Call the desired move, either place a card on the board or pass:
    - to place: call placeCardInPosition with the card index, row, and column
      EX: model.placeCardInPosition(0, 1, 1);
    - to pass: call pass();
      EX: model.pass();

KEY COMPONENTS:
- PawnsBoardTextualView: Displays the board visually while updating the UI based on the updates of the model
- PawnsBoardModel: Manages the board and keeps track of the state of the game
    - Has game play behaviors, including starting the game, placing a card, passing, performing the
      influence effect of placing a card, getting a cell at a specific board position, setting the
      next player, determining the current player, drawing the next card, getting the player's hand,
      determining if the game is over
    - Has scoring behaviors, including calculating the score of each player, determining the winner
      and winning score of the game

The driving components of this codebase include the PawnsBoardModel, which enforces the rules and
updates the game state; and the users' actions, which initiate changes in the game's state.

The driven components of this codebase include the board, which updates in response to changes in
the game state, and the view, which visually reflects these updates without influencing gameplay.


KEY SUB-COMPONENTS:
- Cell: Represents a cell on the board; can be empty, filled with 1~3 pawns, or have a card
    - EmptyCell: Represents a (gray) cell with no pawns or cards on the board
    - Pawns: Represents a cell with pawns (either 1, 2, or 3) and a color (representing which player currently owns the pawns)
    - GameCard: Represents a game card used to play in the game with a cost, value, and influenceGrid,
      which is a list of Positions relative to the center card position (2,2)
- Player: Represents a player in the game with a unique hand, deck, and color (red or blue)

SOURCE ORGANIZATION:
The main model interface, QueensBlood, can be found: src/cs3500/pawnsboard/model/QueensBlood.java
The main model class, PawnsBoardModel, can be found: src/cs3500/pawnsboard/model/PawnsBoardModel.java

The Cell interface can be found: src/cs3500/pawnsboard/model/Cell.java
The EmptyCell class can be found: src/cs3500/pawnsboard/model/EmptyCell.java
The GameCard class can be found: src/cs3500/pawnsboard/model/GameCard.java
The Pawns class can be found: src/cs3500/pawnsboard/model/Pawns.java

The DeckConfiguration interface can be found: src/cs3500/pawnsboard/model/DeckConfiguration.java
The PawnsBoardDeckConfig class can be found: src/cs3500/pawnsboard/model/PawnsBoardDeckConfig.java

The Player class can be found: src/cs3500/pawnsboard/model/Player.java

The Position class can be found: src/cs3500/pawnsboard/model/Position.java

The view interface, QueensBloodTextualView can be found: src/cs3500/pawnsboard/view/QueensBloodTextualView.java
The view class PawnsBoardTextualView can be found: src/cs3500/pawnsboard/view/PawnsBoardTextualView.java

The PawnsBoard (main method) can be found: src/cs3500/pawnsboard/PawnsBoard.java



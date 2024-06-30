package Gamestates;

/**
 * The Gamestate enum represents the different states of the game.
 * <p>
 * This enum is used to manage the current state of the game.
 * The states include SINGLEPLAYER, MENU, MULTIPLAYER, DEAD, LOKAL_MULTIPLAYER, EXIT, and RETRY.
 * </p>
 *
 * @author
 */

public enum Gamestate {

    /**
     * The SINGLEPLAYER state represents the game in single player mode.
     */

    SINGLEPLAYER,

    /**
     * The MENU state represents the game in the menu screen.
     */

    MENU,

    /**
     * The MULTIPLAYER state represents the game in multiplayer mode.
     */

    MULTIPLAYER,

    /**
     * The DEAD state represents the game when the player is dead.
     */

    DEAD,

    /**
     * The LOKAL_MULTIPLAYER state represents the game in local multiplayer mode.
     */

    LOKAL_MULTIPLAYER,

    /**
     * The EXIT state represents the game when it is being exited.
     */

    EXIT,

    /**
     * The RETRY state represents the game when the player is retrying a level or game.
     */

    RETRY;

    /**
     * The state variable is used to keep track of the current game state.
     */

    public static Gamestate state = MENU;
}

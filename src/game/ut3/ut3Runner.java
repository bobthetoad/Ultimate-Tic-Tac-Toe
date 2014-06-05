/**
 * ut3Runner.java
 */

package game.ut3;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>ut3Runner</code> class runs a game of ultimate tic tac toe.
 *
 * @author javedmohamed
 * @version 0.2
 * @since 0.1
 */
public class ut3Runner {
	public static void main(String[] args) {
		int response = JOptionPane.showConfirmDialog(new Frame(), "Do you want to play a game of Ultimate Tic-Tac-Toe?",
				"Ultimate Tic-Tac-Toe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == 0) {
			// create a game board
			ut3Board board = new ut3Board();
			// show that board to the world
			board.show();
			board.resize();
		}
	}
}

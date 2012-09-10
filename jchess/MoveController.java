/*
 * Created on 05-Aug-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jchess;

import java.util.Iterator;

import move.EnPassant;
import move.Move;
import move.SlideMoveNoCapture;
import data.Board;
import data.Location;
import data.Pawn;
import data.Piece;

/**
 * @author StuartI
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MoveController {

	private Board board;
	private boolean isWhite = true;
	private boolean locked = false;

	private MoveList moveList;
	private int moveNumber = 0;

	private Pawn promotion;
	private PawnPromotion promotionWindow;

	/**
	 * @param board
	 */
	public MoveController(Board chessBoard, MoveList list) {

		// TODO Auto-generated constructor stub
		board = chessBoard;
		moveList = list;
	}

	/*
	 * 
	 * TODO
	 */
	public boolean canMove() {
		return true;
	}

	public boolean isCheck(boolean flag) {

		Piece king = board.getKing(flag);
		int xLoc = king.getX();
		int yLoc = king.getY();

		// Check for attacking knights.
		if (board.anyKnightAttacking(flag, king)) {
			return true;
		}

		// Check for attacking bishop / queen.

		// To the right and up
		for (int xPos = xLoc + 1, yPos = yLoc - 1; xPos < 8 && yPos >= 0; xPos++, yPos--) {
			if (board.isDiagonalAttacker(xPos, yPos, flag)) {
				return true;
			}
			if (board.isPiece(xPos, yPos)) {
				break;
			}
		}

		// To the right and down
		for (int xPos = xLoc + 1, yPos = yLoc + 1; xPos > 8 && yPos > 8; xPos++, yPos++) {
			if (board.isDiagonalAttacker(xPos, yPos, flag)) {
				return true;
			}
			if (board.isPiece(xPos, yPos)) {
				break;
			}
		}

		// To the left and up
		for (int xPos = xLoc - 1, yPos = yLoc - 1; xPos >= 0 && yPos >= 0; xPos--, yPos--) {
			if (board.isDiagonalAttacker(xPos, yPos, flag)) {
				return true;
			}
			if (board.isPiece(xPos, yPos)) {
				break;
			}
		}

		// To the left and down
		for (int xPos = xLoc - 1, yPos = yLoc + 1; xPos >= 0 && yPos < 8; xPos--, yPos++) {
			if (board.isDiagonalAttacker(xPos, yPos, flag)) {

				return true;
			}
			if (board.isPiece(xPos, yPos)) {
				break;
			}
		}

		// To the right only
		for (int xPos = xLoc + 1; xPos < 8; xPos++) {
			if (board.isHorizontalAttacker(xPos, yLoc, flag)) {
				return true;
			}
			if (board.isPiece(xPos, yLoc)) {
				break;
			}
		}

		// To the left only
		for (int xPos = xLoc - 1; xPos >= 0; xPos--) {
			if (board.isHorizontalAttacker(xPos, yLoc, flag)) {
				return true;
			}
			if (board.isPiece(xPos, yLoc)) {
				break;
			}
		}

		// To the up only
		for (int yPos = yLoc - 1; yPos >= 0; yPos--) {
			if (board.isHorizontalAttacker(xLoc, yPos, flag)) {
				return true;
			}
			if (board.isPiece(xLoc, yPos)) {
				break;
			}
		}

		// To the down only
		for (int yPos = yLoc + 1; yPos < 8; yPos++) {
			if (board.isHorizontalAttacker(xLoc, yPos, flag)) {
				return true;
			}
			if (board.isPiece(xLoc, yPos)) {
				break;
			}
		}

		// Check for attacking pawns.
		int yPos = yLoc + 1;

		if (flag) {
			yPos -= 2;
		}

		Piece pawn1 = board.getPiece(xLoc - 1, yPos);
		if (pawn1 != null && pawn1.isPawn()) {

			if (pawn1.isWhite() != flag) {
				return true;
			}
		}

		Piece pawn2 = board.getPiece(xLoc + 1, yPos);
		if (pawn2 != null && pawn2.isPawn()) {
			if (pawn2.isWhite() != flag) {
				return true;
			}
		}

		return false;
	}

	public boolean isValidMove(Piece piece, Location to) {
		System.out.println("From location = " + piece.getLoc());
		System.out.println("To   location = " + to);
		Piece destination = board.getPiece(to);

		// Is there one of your pieces there
		if (piece == null) {
			return false;
		}

		if (piece.isWhite() != isWhite) {
			return false;
		}

		// You can not take your own pieces.
		if (destination != null) {

			if (destination.isWhite() == isWhite) {
				return false;
			}
		}

		// Check piece can be moved to the new location.
		Iterator move = piece.getMoves().iterator();

		while (move.hasNext()) {
			Move aMove = (Move) move.next();
			if (aMove.isValid(piece.getLoc(), to, board)) {
				System.out.println("Found a valid move!");
				if (aMove instanceof EnPassant) {
					board.remove(Move.getEnPassantPawn());
				}
				if (piece instanceof Pawn
						&& aMove instanceof SlideMoveNoCapture) {
					Move.setEnPassantPawn((Pawn) piece);

				} else {
					Move.setEnPassantPawn(null);
				}
				return true;
			}
		}

		// Ok have checked not sliding though a piece

		return false;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public boolean move(Piece piece, Location to) {

		if (isValidMove(piece, to)) {
			// Start transaction
			Location from = piece.getLoc();

			int xLoc = from.getXPos();
			int yLoc = from.getYPos();

			Piece saveCaptured = board.getPiece(to);

			String captured = "";
			if (board.isPiece(to.getXPos(), to.getYPos())) {
				captured = "+";
			}
			board.setPiece(piece, to);

			// Check if in check after move.

			if (isCheck(isWhite)) {
				// Can not move so rollback move
				System.out.println("You are still in check if you move there");
				board.remove(to);
				if (saveCaptured != null) {
					board.addPiece(saveCaptured);
				}

				piece.setLocation(new Location(xLoc, yLoc));
				board.setPiece(piece);
				return false;
			}

			// End of transaction

			piece.moved();
			if (piece.isPawn() && (piece.getY() == 0 || piece.getY() == 7)) {
				promotion = (Pawn) piece;
				promotionWindow = new PawnPromotion(piece.isWhite(), this);
				locked = true;
			}

			moveNumber++;
			togglePlayer();

			String check = "";
			if (isCheck(isWhite)) {
				check = "!";
			}

			String desc = moveNumber + ". " + from + " - " + to + captured
					+ check;
			moveList.add(desc);

			return true;
		}

		return false;
	}

	/**
	 * @param name
	 */
	public void promoteTo(String name) {
		// TODO Auto-generated method stub
		locked = false;
		Piece newPiece = Piece.createPiece(promotion, name);
		board.remove(promotion);
		board.setPiece(newPiece);
		board.getPieces().add(newPiece);
		promotionWindow.close();

	}

	/**
	 * 
	 */
	public void reset() {
		// TODO Auto-generated method stub
		moveList.clear();
		board.reset();
		isWhite = true;
		locked = false;
		promotion = null;
		moveNumber = 0;
		if (promotionWindow != null) {
			promotionWindow.close();
		}
	}

	public void togglePlayer() {
		isWhite = !isWhite;
	}
}
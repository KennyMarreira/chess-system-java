package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean [getBoard().getLinhas()][getBoard().getColunas()];
		
		Position p = new Position(0, 0);
		
		// acima
		p.setValues(position.getLinha() - 1, position.getColuna());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// abaixo
		p.setValues(position.getLinha() + 1, position.getColuna());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValues(position.getLinha(), position.getColuna() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValues(position.getLinha(), position.getColuna() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nw
		p.setValues(position.getLinha() - 1, position.getColuna() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ne
		p.setValues(position.getLinha() - 1, position.getColuna() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sw
		p.setValues(position.getLinha() + 1, position.getColuna() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// se
		p.setValues(position.getLinha() + 1, position.getColuna() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// #Special Move Castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #Special Move Kingside Rook
			Position posT1 = new Position(position.getLinha(), position.getColuna() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getLinha()][position.getColuna() + 2] = true;
				}
			}
			// #Special Move Queenside Rook
			Position posT2 = new Position(position.getLinha(), position.getColuna() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() - 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() - 2);
				Position p3 = new Position(position.getLinha(), position.getColuna() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null ) {
					mat[position.getLinha()][position.getColuna() - 2] = true;
				}
			}
		}
		
		
		return mat;
	}
	
	
	
}

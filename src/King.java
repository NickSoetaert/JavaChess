/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */
import javax.swing.ImageIcon;

public class King extends Piece{
    public boolean isInCheck; //should this actually go in the gameboard class? It will be hard to update this
    //value with only the information afforded to a Piece object.
    public King(PieceColor color){
        _type = PieceType.KING;
        isInCheck = false;
        if(color == PieceColor.WHITE_PIECE){
            this._pieceImage = new ImageIcon("Images/whiteKing.png");
            _color = PieceColor.WHITE_PIECE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackKing.png");
            _color = PieceColor.BLACK_PIECE;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination){
        return true;
            
    }
}
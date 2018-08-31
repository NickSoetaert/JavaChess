/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */
import java.awt.Point;
import javax.swing.ImageIcon;

public class Bishop extends Piece{
    public Bishop(PieceColor color){
        _type = PieceType.BISHOP;
        if(color == PieceColor.WHITE_PIECE){
            this._pieceImage = new ImageIcon("Images/whiteBishop.png");
            _color = PieceColor.WHITE_PIECE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackBishop.png");
            _color = PieceColor.BLACK_PIECE;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination){
        Point startPoint = start.getCordinates();
        Point endPoint = destination.getCordinates();
        return(
                Math.abs(startPoint.x - endPoint.x) 
                == 
                Math.abs(startPoint.y - endPoint.y)
                );
    }
}

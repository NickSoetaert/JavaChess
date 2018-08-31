/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */
import java.awt.Point;
import javax.swing.ImageIcon;

public class Queen extends Piece{
    public Queen(PieceColor color){
        _type = PieceType.QUEEN;
        if(color == PieceColor.WHITE_PIECE){
            this._pieceImage = new ImageIcon("Images/whiteQueen.png");
            _color = PieceColor.WHITE_PIECE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackQueen.png");
            _color = PieceColor.BLACK_PIECE;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination) {
        Point startPoint = start.getCordinates();
        Point endPoint = destination.getCordinates();
        boolean bishopMove = (Math.abs(startPoint.x - endPoint.x)
                            == Math.abs(startPoint.y - endPoint.y));
        boolean rookMove = ((startPoint.y - endPoint.y == 0)
                            || (startPoint.x - endPoint.x == 0));
        return(bishopMove || rookMove);
    }
}
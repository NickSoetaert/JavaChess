/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */
import java.awt.Point;
import javax.swing.ImageIcon;

public class Queen extends Piece{
    public Queen(SideColor color){
        _type = PieceType.QUEEN;
        if(color == SideColor.WHITE){
            this._pieceImage = new ImageIcon("Images/whiteQueen.png");
            _color = SideColor.WHITE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackQueen.png");
            _color = SideColor.BLACK;
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
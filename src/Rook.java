/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */

import java.awt.Point;
import javax.swing.ImageIcon;


public class Rook extends Piece {
    public Rook(SideColor color){
         _type = PieceType.ROOK;
        if(color == SideColor.WHITE){
            this._pieceImage = new ImageIcon("Images/whiteRook.png");
            _color = SideColor.WHITE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackRook.png");
            _color = SideColor.BLACK;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination) {
        Point startPoint = start.getCordinates();
        Point endPoint = destination.getCordinates();
        return((startPoint.y - endPoint.y == 0) || (startPoint.x - endPoint.x == 0));
    }
}

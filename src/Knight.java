/*
 * Nick Soetaert
 * December 18, 2017
 */

import java.awt.Point;
import javax.swing.ImageIcon;

public class Knight extends Piece {
    public Knight(SideColor color){
        _type = PieceType.KNIGHT;
        if(color == SideColor.WHITE){
            this._pieceImage = new ImageIcon("Images/whiteKnight.png");
            _color = SideColor.WHITE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackKnight.png");
            _color = SideColor.BLACK;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination) {
        Point startPoint = start.getCordinates();
        Point endPoint = destination.getCordinates();
        //CASE1: x is +-1, y is +-2
        //CASE2: x is +-2, y is +-1
        boolean caseOne = ((Math.abs(startPoint.x - endPoint.x) == 1)
                        && Math.abs(startPoint.y - endPoint.y) == 2);
        boolean caseTwo = ((Math.abs(startPoint.x - endPoint.x) == 2)
                        && Math.abs(startPoint.y - endPoint.y) == 1);
        return(caseOne || caseTwo);
    }
}
/*
 * Nick Soetaert
 * December 18, 2017
 */
import java.awt.Point;
import javax.swing.ImageIcon;

public class Bishop extends Piece{
    public Bishop(SideColor color){
        _type = PieceType.BISHOP;
        if(color == SideColor.WHITE){
            this._pieceImage = new ImageIcon("Images/whiteBishop.png");
            _color = SideColor.WHITE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackBishop.png");
            _color = SideColor.BLACK;
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

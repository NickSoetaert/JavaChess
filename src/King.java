/*
 * Nick Soetaert
 * December October 4, 2018
 */
import javax.swing.ImageIcon;
import java.lang.Math.*;

public class King extends Piece{
    public boolean isInCheck; //should this actually go in the gameboard class? It will be hard to update this
    //value with only the information afforded to a Piece object.
    public King(SideColor color){
        _type = PieceType.KING;
        isInCheck = false;
        if(color == SideColor.WHITE){
            this._pieceImage = new ImageIcon("Images/whiteKing.png");
            _color = SideColor.WHITE;
        } else {
            this._pieceImage = new ImageIcon("Images/blackKing.png");
            _color = SideColor.BLACK;
        }
    }
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination){
        int xCordDistance = Math.abs(start.getCordinates().x - destination.getCordinates().x);
        int yCordDistance = Math.abs(start.getCordinates().y - destination.getCordinates().y);
        return ((xCordDistance <= 1) && (yCordDistance <= 1));
    }
}
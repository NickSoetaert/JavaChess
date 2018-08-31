
import javax.swing.ImageIcon;

/*
 * Nick Soetaert
 * December 17, 2017
 * Copyright (c) 2017 Nick Soetaert
 */

public abstract class Piece {
    protected ImageIcon _pieceImage;
    protected boolean _isCaptured = false; //Incomplete implementation of this. How should I utilize? Is this even needed?
    protected boolean _hasMoved = false;
    protected PieceColor _color;
    protected PieceType _type;
    
    protected abstract boolean checkIfMoveShapeIsLegal(Tile start, Tile destination);
    public ImageIcon getImageIcon(){
        return _pieceImage;
    }
    public void setHasMoved(){
        _hasMoved = true;
    }
    public boolean getHasMoved(){
        return _hasMoved;
    }
    public PieceType getType(){
        return _type;
    }
    //Checks if piece you're trying to caputre is an enemy piece or not
    protected boolean capturingEnemy(Piece enemy){
        if(enemy != null){
            return enemy._color != this._color;
        } else {
            return false;
        }
    }
}

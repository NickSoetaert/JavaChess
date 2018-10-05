/*
 * Nick Soetaert
 * December 17, 2017
 */


import javax.swing.ImageIcon;

public abstract class Piece {
    protected ImageIcon _pieceImage;
    protected boolean _isCaptured = false; //Incomplete implementation of this. How should I utilize? Is this even needed?
    protected boolean _hasMoved = false;
    protected SideColor _color;
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
    
    public SideColor getColor(){
        return _color;
    }
    
    //Checks if piece you're trying to caputre is an enemy piece or not
    protected boolean isNotAttackingSameTeam(Piece otherPiece){
        if(otherPiece != null){  //if there is another piece...
            return otherPiece._color != this._color;
        } else {
            return true; //if the square is empty, you are not attacking a friendly
        }
    }
    
    //Checks if square you're moving to contains an enemy piece or not
    protected boolean isCapturingEnemy(Piece otherPiece){
        if(otherPiece != null){  //if there is another piece...
            return otherPiece._color != this._color; //if colors are different, return true
        } else {
            return false; //if the square is empty, you are not attacking an enemy
        }
    }
    
    //checks if it is your turn to move
    protected boolean isMyTurn(SideColor toMove){
        return this.getColor() == toMove;
    }
    
}

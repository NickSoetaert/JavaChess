/*
 * Nick Soetaert
 * December 18, 2017
 * Copyright (c) 2017 Nick Soetaert
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;

public class Tile {
    private final JLabel _label;
    private Piece _piece; //the piece currently on the tile. If no piece, then null
    private final Point _tileCordinate; //0,0 is bottom left, 7,7 is upper right.
    private final TileColor _color;
    private boolean isUnderAttack; //for moving kings onto the tile.
    //To consider: do we create a variable for each team, or just for both?
    //For just one, recalculate each turn, use turn logic. (Only pieces that are 
    //white "exist" on the board)
    
    //private static final Color DARK_COLOR = new Color(240, 22, 22); //dark red
    //private static final Color DARK_COLOR = new Color(50, 200, 100); //green
    private static final Color DARK_COLOR = new Color(10, 50, 140); //blue
    //private static final Color DARK_COLOR = new Color(75, 0, 75); //dark purple
    
    //constructor: Gives corddinates and color to tile, along with preping the layout for pieces.
    Tile(Point cord, TileColor color){
        _label = new JLabel();
        _piece = null;
        _tileCordinate = cord;
        _color = color;
        _label.setOpaque(true);
        _label.setHorizontalAlignment(JLabel.CENTER); //allows placing images in the center of squares
        _label.setVerticalAlignment(JLabel.CENTER);
        if (color == TileColor.WHITE) {
            _label.setBackground(Color.WHITE);
        } else {
            _label.setBackground(DARK_COLOR);
        }
    }
    

    //places a specified piece on a single specific game tile,
    //and also updates the tile's JLabel to hold the image of a piece.
    public void setPieceOnTile(Piece piece){
        _piece = piece;
        _label.setIcon(piece.getImageIcon());
    }
    //completely removes all aspects of a piece from a tile
    public void removePiece(){
        _piece = null;
        _label.setIcon(null);
    }
    //returns the specified piece on a game tile.
    public Piece getPiece(){
        return _piece;
    }
    //returns true if there is no piece on the tile.
    public boolean isEmpty(){
        return(_piece == null);
    }
    //returns true if there is a piece on the tile
    public boolean isNotEmpty(){
        return(_piece != null);
    }
    //returns the specific cell's corresponding JLabel. Used in buildBoard()
    public JLabel getLabel(){
        return _label;
    }
    //returns the specific cell's cordinates
    public Point getCordinates(){
        return _tileCordinate;
    }
    //set the text on a JLabel. Used for debugging cordinates.
    public void setText(String text){
        _label.setText(text);
    }
    //Adds an action listener to specific JLabel
    public void addMouseListener(MouseListener me){
        _label.addMouseListener(me);
    }
    
    public void highlight() {
        _label.setBackground(Color.YELLOW);
    }

    public void removeHighlight() {
        if (_color == TileColor.WHITE) {
            _label.setBackground(Color.WHITE);
        } else {
            _label.setBackground(DARK_COLOR);
        }
    }
}

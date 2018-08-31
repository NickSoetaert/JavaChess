/*
 * Nick Soetaert
 * December 17, 2017
 * Copyright (c) 2017 Nick Soetaert
 */

import javax.swing.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pawn extends Piece {
    private boolean _canTakeEP = false; //true when pawn can be captured en passant
    private final int _singleDirectionalMoveUnit;
    public Pawn(PieceColor color){
        _type = PieceType.PAWN;
        if(color == PieceColor.WHITE_PIECE){
            this._pieceImage = new ImageIcon("Images/whitePawn.png");
            _color = PieceColor.WHITE_PIECE;
            _singleDirectionalMoveUnit = -1;
        } else {
            this._pieceImage = new ImageIcon("Images/blackPawn.png");
            _color = PieceColor.BLACK_PIECE;
            _singleDirectionalMoveUnit = 1;
        }
    }
    //checks if any move made by a Pawn is legal.
    @Override
    public boolean checkIfMoveShapeIsLegal(Tile start, Tile destination){
        boolean canMove = checkVerticalMove(start, destination) 
                        || checkCapture(start, destination);
        return canMove;
    }
    //check if a non-capturing move is legal or not, regardless of pieces on board
    private boolean checkVerticalMove(Tile startTile, Tile endTile){
        Point start = startTile.getCordinates();
        Point destination = endTile.getCordinates();
        if(endTile.isNotEmpty()){
            return false;
        }
        else if (_hasMoved == false) { //able to advance two on first move
            //remember that x cordinates are y cordinates for some reason
            return(((start.x == destination.x + _singleDirectionalMoveUnit) //check if moved one square
                    || (start.x == destination.x + (2 * _singleDirectionalMoveUnit))) //check if moved two squares
                    && (start.y == destination.y)); //check to make sure the pawn didn't move horizontally
        } else {
            return ((start.x == destination.x + _singleDirectionalMoveUnit) //check if moved one square
                    && (start.y == destination.y)); //check to make sure the pawn didn't move horizontally
        }
    }
    //check if a diagonal move is legal, regardless of peices on board.
    private boolean checkCapture(Tile startTile, Tile endTile){ //change from Point to tile, check for enemy on destination tile.
        Point start = startTile.getCordinates();
        Point destination = endTile.getCordinates();
        Piece enemyPiece = endTile.getPiece();
        return((start.x == destination.x + _singleDirectionalMoveUnit) 
                && (Math.abs(start.y - destination.y) == 1) //make sure we're only moving one square, either left or right
                && capturingEnemy(enemyPiece));
    }

    public void promote(Tile promotionSquare){
        if(promotionSquare.getCordinates().x == 0 || //if the tile is on a back rank...
            promotionSquare.getCordinates().x == 7){
            PromotePopUp popUp = new PromotePopUp(promotionSquare);
            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //TODO: Fix promotion.
            popUp.setVisible(true);
        }
    }
   
    private class PromotePopUp extends JFrame{
        private final static int DEFAULT_WIDTH = 300;
        private final static int DEFAULT_HEIGHT = 200;
        PromotePopUp(Tile promotionSquare){
            PieceColor color = promotionSquare.getPiece()._color;
            
            final JButton queen = new JButton(); 
            final JButton rook = new JButton();
            final JButton knight = new JButton();
            final JButton bishop = new JButton();
            
            class PromoteQueenListener implements ActionListener {
            @Override
                public void actionPerformed(ActionEvent ae) {
                    promotionSquare.removePiece();
                    promotionSquare.setPieceOnTile(new Queen(color));
                    PromotePopUp.this.dispose();
                }
            }
            
            class PromoteRookListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    promotionSquare.removePiece();
                    promotionSquare.setPieceOnTile(new Rook(color));
                    PromotePopUp.this.dispose();
                }
            }
            
            class PromoteBishopListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    promotionSquare.removePiece();
                    promotionSquare.setPieceOnTile(new Bishop(color));
                    PromotePopUp.this.dispose();
                }
            }
            
            class PromoteKnightListener implements ActionListener {
            @Override
                public void actionPerformed(ActionEvent ae) {
                    promotionSquare.removePiece();
                    promotionSquare.setPieceOnTile(new Knight(color));
                    PromotePopUp.this.dispose();
                }
            }
            
            PromoteQueenListener pq = new PromoteQueenListener();
            PromoteRookListener pr = new PromoteRookListener();
            PromoteBishopListener pb = new PromoteBishopListener();
            PromoteKnightListener pk = new PromoteKnightListener();
            
            queen.addActionListener(pq);
            rook.addActionListener(pr);
            bishop.addActionListener(pb);
            knight.addActionListener(pk);
            
            //set white by default
            ImageIcon queenImage = new ImageIcon("Images/whiteQueen.png");
            ImageIcon rookImage = new ImageIcon("Images/whiteRook.png");
            ImageIcon knightImage = new ImageIcon("Images/whiteKnight.png");
            ImageIcon bishopImage = new ImageIcon("Images/whiteBishop.png");
            
            //if the piece isn't white, change the images to black
            if(color == PieceColor.BLACK_PIECE){
                queenImage = new ImageIcon("Images/blackQueen.png");
                rookImage = new ImageIcon("Images/blackRook.png");
                knightImage = new ImageIcon("Images/blackKnight.png");
                bishopImage = new ImageIcon("Images/blackBishop.png");
            }
            queen.setIcon(queenImage);
            rook.setIcon(rookImage);
            knight.setIcon(knightImage);
            bishop.setIcon(bishopImage);
            
            this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //TODO: figure out why it is still possible to close
            JPanel panel = new JPanel();
            panel.add(queen);
            panel.add(rook);
            panel.add(knight);
            panel.add(bishop);
            this.add(panel);
        }
    }
    //TODO: Add en passant functionality
}

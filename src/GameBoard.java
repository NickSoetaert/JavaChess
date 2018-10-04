/*
 * Nick Soetaert
 * December 17, 2017
 * Copyright (c) 2017 Nick Soetaert
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

public class GameBoard extends JPanel{
    private static boolean whiteToMove = true;
    private static int moveNumber = 1;
    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    //a 2d array of all game tiles. 0,0 represents the bottom left, 7,7 top right.
    private static final Tile[][] SQUARES = new Tile[NUM_ROWS][NUM_COLUMNS];
    
    //Used to keep track of what tile we're moving a piece from
    private static Tile _from;
    private static boolean _pieceIsSelected = false;
    
    //White Pieces
    Pawn whitePawns[] = new Pawn[8];
    List<Rook> whiteRooks = new ArrayList<>();
    List<Knight> whiteKnights = new ArrayList<>();
    List<Bishop> whiteBishops = new ArrayList<>();
    List<Queen> whiteQueens = new ArrayList<>();
    King whiteKing = new King(PieceColor.WHITE_PIECE);
    
    //Black Pieces
    Pawn blackPawns[] = new Pawn[8];
    List<Rook> blackRooks = new ArrayList<>();
    List<Knight> blackKnights = new ArrayList<>();
    List<Bishop> blackBishops = new ArrayList<>();
    List<Queen> blackQueens = new ArrayList<>();
    King blackKing = new King(PieceColor.BLACK_PIECE);

    //constructor
    public GameBoard(){
        this.setLayout(new BorderLayout());
        
        ChessBoard board = new ChessBoard();
        this.add(board, BorderLayout.CENTER);
        setUpPieces();
        
        //BUG: If you click between the lowest tile and the "reset" button, you'll cause an error.
        ResetButtonPanel clearButton = new ResetButtonPanel();
        this.add(clearButton, BorderLayout.SOUTH);
    }
    
    //creates the game board, with the cordinates 0,0 at the bottom
    //left corner and 7,7 at the top right.
    private class ChessBoard extends JPanel{
        ChessBoard(){
            this.setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
            this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            this.setSize(800,800); //doesn't work yet because the panel just snaps to the size of the JFrame
            this.addMouseListener(new ClickListener());
            
            for(int i = NUM_ROWS-1; i >= 0; i--){
                for(int j = 0; j < NUM_COLUMNS; j++){
                    Point tileCordinate = new Point(i,j);
                    if((i+j)%2 == 0){
                        SQUARES[i][j] = new Tile(tileCordinate, TileColor.DARK);
                        //SQUARES[i][j].getLabel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
                        SQUARES[i][j].getLabel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        //SQUARES[i][j].setText(Integer.toString(i) + " " + Integer.toString(j));
                    } else {
                        SQUARES[i][j] = new Tile(tileCordinate, TileColor.WHITE);
                        SQUARES[i][j].getLabel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        //SQUARES[i][j].setText(Integer.toString(i) + " " + Integer.toString(j));
                    }
                    //add the current tile's JLabel to the JPanel
                    this.add(SQUARES[i][j].getLabel()); 
                }
            }
        }
    }
    
    //Adds all pieces to their initial positions on the board.
    private void setUpPieces(){
        //WHITE PIECES:
        //create white pawns on square 1,0 to 1,7, and then put in array.
        for(int i = 0; i < 8; i++){
            Pawn pawn = new Pawn(PieceColor.WHITE_PIECE);
            whitePawns[i] = pawn;
            setPiece(SQUARES[1][i], pawn);
        }
        //white rooks on 0,0 and 0,7
        for(int i = 0; i < 2; i++){
            Rook rook = new Rook(PieceColor.WHITE_PIECE);
            whiteRooks.add(rook);
        }
        setPiece(SQUARES[0][0], whiteRooks.get(0));
        setPiece(SQUARES[0][7], whiteRooks.get(1));

        //white knights on 0,1 and 0,6
        for(int i = 0; i < 2; i++){
            Knight knight = new Knight(PieceColor.WHITE_PIECE);
            whiteKnights.add(knight);
        }
        setPiece(SQUARES[0][1], whiteKnights.get(0));
        setPiece(SQUARES[0][6], whiteKnights.get(1));
        
        //white bishops on 0,2 and 0,5
        for(int i = 0; i < 2; i++){
            Bishop bishop = new Bishop(PieceColor.WHITE_PIECE);
            whiteBishops.add(bishop);
        }
        setPiece(SQUARES[0][2], whiteBishops.get(0));
        setPiece(SQUARES[0][5], whiteBishops.get(1));
        
        //white queen on 0,3
        {
        Queen queen = new Queen(PieceColor.WHITE_PIECE);
        whiteQueens.add(queen);
        }
        setPiece(SQUARES[0][3], whiteQueens.get(0));
        
        //white king on 0,4
        setPiece(SQUARES[0][4], whiteKing);
        
        //BLACK PIECES:
        //create all black pawns on SQUARES 6,0 to 6,7
        for(int i = 0; i < 8; i++){
            Pawn pawn = new Pawn(PieceColor.BLACK_PIECE);
            blackPawns[i] = pawn;
            setPiece(SQUARES[6][i], pawn);
        }
        //black rooks on 7,0 and 7,7
        for (int i = 0; i < 2; i++) {
            Rook rook = new Rook(PieceColor.BLACK_PIECE);
            blackRooks.add(rook);
        }
        setPiece(SQUARES[7][0], blackRooks.get(0));
        setPiece(SQUARES[7][7], blackRooks.get(1));

        //black knights on 7,1 and 7,6
        for (int i = 0; i < 2; i++) {
            Knight knight = new Knight(PieceColor.BLACK_PIECE);
            blackKnights.add(knight);
        }
        setPiece(SQUARES[7][1], blackKnights.get(0));
        setPiece(SQUARES[7][6], blackKnights.get(1));

        //black bishops on 7,2 and 7,5
        for (int i = 0; i < 2; i++) {
            Bishop bishop = new Bishop(PieceColor.BLACK_PIECE);
            blackBishops.add(bishop);
        }
        setPiece(SQUARES[7][2], blackBishops.get(0));
        setPiece(SQUARES[7][5], blackBishops.get(1));

        //black queen on 7,3
        {
        Queen queen = new Queen(PieceColor.BLACK_PIECE);
        blackQueens.add(queen);
        }
        setPiece(SQUARES[7][3], blackQueens.get(0));

        //black king on 7,4
        setPiece(SQUARES[7][4], blackKing);
    }
    
    //Uses Tile's setPieceOnTile function to set a piece on a tile.
    //Adds NO features, is just for readability.
    private void setPiece(Tile square, Piece piece){
        square.setPieceOnTile(piece);
    }
    
    //Moves a piece from one square to another. Does NOT check for move
    //legality; that is handled by each individual piece's move function.
    private void movePiece(Tile moveFrom, Tile moveTo){
        Point cord = moveTo.getCordinates(); 
        Piece piece = moveFrom.getPiece();
        if(checkForPiecesInMovePath(moveFrom, moveTo)
        && piece.checkIfMoveShapeIsLegal(moveFrom, moveTo)
        && piece.notCapturingEnemy(moveTo.getPiece())){
            moveFrom.removePiece(); //remove piece from start tile...
            setPiece(SQUARES[cord.x][cord.y], piece); //and set it on end tile
            piece.setHasMoved(); //note that the piece has moved at least once
            whiteToMove = !whiteToMove; //flip whose move it is
            moveNumber++;
            
            if (piece instanceof Pawn) {
                ((Pawn) piece).promote(moveTo);
            }
        } else {
            System.out.println("Illegal move");
        }
        
    }
    /*
    checks for any pieces BETWEEN start to target square on a move.
    Does not need to check for legality of the "shape" of the move,
    as each individual Piece has methods to do that.
    Also does not check if the capturing move is legal, as Piece has a
    method to do that.
    returns true if move is legal.
    */
    private boolean checkForPiecesInMovePath(Tile moveFrom, Tile moveTo){
        boolean canMove = true;
        Point startCord = moveFrom.getCordinates();
        Point endCord = moveTo.getCordinates();
        int moveXDist = startCord.x - endCord.x; //if moving right, moveXDist is negative
        int moveYDist = startCord.y - endCord.y; //if moving up, moveYDist is negative
        int squaresToCheck; //total number of squares that must be checked.
        int xDirection, yDirection;
        
        //get the direction of horizontal movement:
        if(moveXDist > 0){ //if moving to the left
            xDirection = -1;
        } else if (moveXDist < 0){ //moving right
            xDirection = 1;
        } else { //not moving horizontally
            xDirection = 0;
        }
        //get the direction of vertical movement:
        if (moveYDist > 0) { //moving down
            yDirection = -1;
        } else if (moveYDist < 0) { //moving up
            yDirection = 1;
        } else { //not moving vertically
            yDirection = 0;
        }

        //get the number of squares that must be checked for pieces
        if (Math.abs(moveXDist) > Math.abs(moveYDist)) {
            squaresToCheck = Math.abs(moveXDist + xDirection);
        } else {
            squaresToCheck = Math.abs(moveYDist + yDirection);
        }
        
        //knights can jump over pieces
        if(moveFrom.getPiece().getType() != PieceType.KNIGHT){
            for(int i = 1; i <= squaresToCheck; i++){
                //BUG HERE: GOING OUT OF BOUNDS. Bug may lie in squaresToCheck.
                //TODO Bug ctrl f ctrl-f ctrlf error
                canMove = canMove && SQUARES[startCord.x + (i * xDirection)]
                                            [startCord.y + (i * yDirection)].isEmpty();
            }  
        }
        return canMove;
    }

    //If a piece isn't selected, clicking on a game tile with a piece will
    //select a piece to move. If a piece is already selected, clicking on any
    ///tile will call movePiece() on said tiles.
    private class ClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent me){
            //java inverts x and y axis for some reason, so that's why
            //I set y to me.getX() and x to me.getY()
            int y = Math.floorDiv(me.getX(), 100); //800 pix wide, so divide by 100 to get 8 even SQUARES
            int x = 7- Math.floorDiv(me.getY(), 100); //800 pix tall, remembering to invert the y axis
            //if a piece is selected, and the user clicks on a non-empty square...
            if(_pieceIsSelected == false && SQUARES[x][y].isNotEmpty()){ //TODO: Chage isNotEmpty to hasPieceForCorrectTurn() or something
                _from = SQUARES[x][y];
                _from.highlight();
                _pieceIsSelected = true;
                //turn++
            } else if(_from == SQUARES[x][y]){ //if the user clicks twice on the same square: deselect piece, remove highlight
                _pieceIsSelected = false; //deselect pieces.
                _from.removeHighlight();
            } else if(_pieceIsSelected){ //if a user properly attempts to move a piece: move the piece, dehighlight home square
                _from.removeHighlight();
                Tile to = SQUARES[x][y];
                movePiece(_from, to);
                _pieceIsSelected = false;
            } else { //user clicked on an empty square while selecting a piece
                System.out.println("Must select a piece!");
            }
        }
    }

    private class ResetButtonPanel extends JPanel {
        JButton clearButton = new JButton("Reset Board");
        ResetButtonPanel() {
            clearButton.addActionListener(new ResetListener());
            this.add(clearButton);
        }
    }
    private class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    SQUARES[i][j].removePiece();
                    SQUARES[i][j].removeHighlight();
                }
            }
            setUpPieces();
        }
    }
}
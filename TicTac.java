import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class TicTac extends JFrame //jframe allows to build windows
{
	public boolean winner, playAgain = false; //keeps track if winner is foundm prevents checkerboard from updating if won and user has not yet said if he wants to play another
	public boolean debugging = false;//for personal debugging
	public boolean CircleChance = true;//keeps track if it is circles or squares chance to input on board
	public String str = " "; //str holds who the winner is, str2 holds whose turn it is
	public String str2 = " ";
	int ticData[] = {-1, -1, -1, //0 1 2
					 -1, -1, -1 ,//3 4 5
					 -1 ,-1, -1};// 6 7 8
	/**
	0 IS CIRCLE AND FIRST (who goes first not slot)
	1 IS SQUARE AND SECOND
	-1 IS EMPTY*/
	//the way the clicking method works ensures the order this array holds the values is the same as the actualy order the users entered in the virtual board, if click 5th spot in virtual board then it occupies the 5th spot in the array...
	private JPanel mousepanel; 
	private JLabel statusbar;
	
	public static void main (String args[])
	{
		TicTac tic = new TicTac();
		tic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tic.setSize(450, 325);
		tic.setVisible(true);
	}
	
	public void didAnyoneWinCircle()
	{
		if(ticData[0] == 0 && ticData[1] == 0 && ticData[2] == 0) //0 horizontal
		{
			str = "WINNER: CIRCLE PLAYER AT TOP HORIZONTAL"; winner = true;
		}
		else if(ticData[0] == 0 && ticData[3] == 0 && ticData[6] == 0) //0 vertical
		{
			str = "WINNER: CIRCLE PLAYER AT LEFT VERTICAL"; winner = true;
		}
		else if(ticData[0] == 0 && ticData[4] == 0 && ticData[8] == 0) //0 diag
		{
			str = "WINNER: CIRCLE PLAYER AT BACKWARD SLANTED DIAGONAL"; winner = true;
		}
		else if(ticData[1] == 0 && ticData[4] == 0 && ticData[7] == 0) //1 vertical
		{
			str = "WINNER: CIRCLE PLAYER AT MIDDLE VERTICAL"; winner = true;
		}
		else if(ticData[2] == 0 && ticData[5] == 0 && ticData[8] == 0) //2 vertical
		{
			str = "WINNER: CIRCLE PLAYER AT RIGHT VERTICAL"; winner = true;
		}
		else if(ticData[2] == 0 && ticData[4] == 0 && ticData[6] == 0) //2 diag
		{
			str = "WINNER: CIRCLE PLAYER AT FORWARD SLANTED DIAGONAL"; winner = true;
		}
		else if(ticData[3] == 0 && ticData[4] == 0 && ticData[5] == 0) //3 horizontal
		{
			str = "WINNER: CIRCLE PLAYER AT MIDDLE HORIZONTAL"; winner = true;
		}
		else if(ticData[6] == 0 && ticData[7] == 0 && ticData[8] == 0) //6 horizontal
		{
			str = "WINNER: CIRCLE PLAYER AT BOTTOM HORIZONTAL"; winner = true;
		}
		else if(ticData[0] != -1 && ticData[1] != -1 && ticData[2] != -1 && ticData[3] != -1 && ticData[4] != -1 && ticData[5] != -1 && ticData[6] != -1 && ticData[7] != -1 && ticData[8] != -1) //if all spots are taken and still no winner...
		{
			str = "IT IS A TIE"; winner = true;
		}
	}
	
	public void didAnyoneWinSquare()
	{
		if(ticData[0] == 1 && ticData[1] == 1 && ticData[2] == 1) //0 horizontal
		{
			str = "WINNER: SQUARE PLAYER AT TOP HORIZONTAL"; winner = true;
		}
		else if(ticData[0] == 1 && ticData[3] == 1 && ticData[6] == 1) //0 vertical
		{
			str = "WINNER: SQUARE PLAYER AT LEFT VERTICAL"; winner = true;
		}
		else if(ticData[0] == 1 && ticData[4] == 1 && ticData[8] == 1) //0 diag
		{
			str = "WINNER: SQUARE PLAYER AT BACKWARD SLANTED DIAGONAL"; winner = true;
		}
		else if(ticData[1] == 1 && ticData[4] == 1 && ticData[7] == 1) //1 vertical
		{
			str = "WINNER: SQUARE PLAYER AT MIDDLE VERTICAL"; winner = true;
		}
		else if(ticData[2] == 1 && ticData[5] == 1 && ticData[8] == 1) //2 vertical
		{
			str = "WINNER: SQUARE PLAYER AT RIGHT VERTICAL"; winner = true;
		}
		else if(ticData[2] == 1 && ticData[4] == 1 && ticData[6] == 1) //2 diag
		{
			str = "WINNER: SQUARE PLAYER AT FORWARD SLANTED DIAGONAL"; winner = true;
		}
		else if(ticData[3] == 1 && ticData[4] == 1 && ticData[5] == 1) //3 horizontal
		{
			str = "WINNER: SQUARE PLAYER AT MIDDLE HORIZONTAL"; winner = true;
		}
		else if(ticData[6] == 1 && ticData[7] == 1 && ticData[8] == 1) //6 horizontal
		{
			str = "WINNER: SQUARE PLAYER AT BOTTOM HORIZONTAL"; winner = true;
		}
	}
	
	public TicTac()
	{
		super("Tic Tac Toe coded by Johnny Ceja");
		mousepanel = new JPanel();
		mousepanel.setBackground(Color.WHITE);
		add(mousepanel, BorderLayout.CENTER);
		statusbar = new JLabel ("default");
		add(statusbar, BorderLayout.SOUTH);
		Handlerclass handler = new Handlerclass();
		mousepanel.addMouseListener(handler); //allow it to have the method addMouseListener
		mousepanel.addMouseMotionListener(handler);
		statusbar.setText("First player is the the circle's player");
	}
	
	public void paint(Graphics g)//draw the outline for tic tac toe, this is refreshed frame by frame so you have to somehow make it remember the order the places where placed... each time refreshed it goes "back in time" and does this and it should match what the users have entered
	{
		g.setColor(new Color(238, 238, 238)); //color of the default background, "whiteout" what previous players turn to place a piece
		g.fillRect(210, 60, 250, 25);
		g.setColor(Color.RED);
		g.drawString(str, 10, 45);
		g.drawString(str2, 210, 75);
		g.drawString("Click here to play again.", 220, 290);
		g.setColor(Color.BLACK);
		g.drawString("First player is the circle, second is square, click on where you", 10, 270);
		g.drawString("want to place your piece on the board.", 10, 290);
		g.fillRect(50, 50, 50, 50);
		g.setColor(Color.WHITE); //alternate to make easier to know where to click
		g.fillRect(100, 50, 50, 50); g.setColor(Color.BLACK);
		g.fillRect(150, 50, 50, 50); g.setColor(Color.WHITE);
		g.fillRect(50, 100, 50, 50); g.setColor(Color.BLACK);
		g.fillRect(100, 100, 50, 50);g.setColor(Color.WHITE);
		g.fillRect(150, 100, 50, 50);g.setColor(Color.BLACK);
		g.fillRect(50, 150, 50, 50); g.setColor(Color.WHITE); 
		g.fillRect(100, 150, 50, 50);g.setColor(Color.BLACK); 
		g.fillRect(150, 150, 50, 50);
		if(ticData[0] == 0){g.setColor(Color.RED);g.fillOval(60, 60, 25, 25);} //if first spot in board is a circle...
		if(ticData[0] == 1){g.setColor(Color.RED);g.fillRect(60, 60, 25, 25);} //if first spot in board is a square
		if(ticData[1] == 0){g.setColor(Color.RED);g.fillOval(110, 60, 25, 25);}
		if(ticData[1] == 1){g.setColor(Color.RED);g.fillRect(110, 60, 25, 25);}
		if(ticData[2] == 0){g.setColor(Color.RED);g.fillOval(160, 60, 25, 25);}
		if(ticData[2] == 1){g.setColor(Color.RED);g.fillRect(160, 60, 25, 25);}
		if(ticData[3] == 0){g.setColor(Color.RED);g.fillOval(60, 110, 25, 25);} //next row
		if(ticData[3] == 1){g.setColor(Color.RED);g.fillRect(60, 110, 25, 25);}
		if(ticData[4] == 0){g.setColor(Color.RED);g.fillOval(110, 110, 25, 25);}
		if(ticData[4] == 1){g.setColor(Color.RED);g.fillRect(110, 110, 25, 25);}
		if(ticData[5] == 0){g.setColor(Color.RED);g.fillOval(160, 110, 25, 25);}
		if(ticData[5] == 1){g.setColor(Color.RED);g.fillRect(160, 110, 25, 25);}
		if(ticData[6] == 0){g.setColor(Color.RED);g.fillOval(60, 160, 25, 25);} //last row
		if(ticData[6] == 1){g.setColor(Color.RED);g.fillRect(60, 160, 25, 25);}
		if(ticData[7] == 0){g.setColor(Color.RED);g.fillOval(110, 160, 25, 25);}
		if(ticData[7] == 1){g.setColor(Color.RED);g.fillRect(110, 160, 25, 25);}
		if(ticData[8] == 0){g.setColor(Color.RED);g.fillOval(160, 160, 25, 25);}
		if(ticData[8] == 1){g.setColor(Color.RED);g.fillRect(160, 160, 25, 25);}
		if(playAgain) //if user says he wants to play again (if he clicks 'click here to play again') then override  "winner" text otherwise keep it there
		{
			g.setColor(new Color(238, 238, 238)); //color of the default background, "whiteout" what previous players turn to place a piece
			g.fillRect(10, 10, 400, 35);
			playAgain = false;
		}
		//doing this method every second will show show an image of exactly where the user has placed his/hers pieces in the game	
	}
	
	private class Handlerclass implements MouseListener, MouseMotionListener
	{
		public void mouseClicked(MouseEvent event) //even holds data of mouse such as where human clicked
		{
			if((event.getX() > 210 && event.getX() < 340) && (event.getY() > 246 && event.getY() < 265))
			{
				str = " ";
				str2 = " ";
				playAgain = true;
				repaint();
				winner = false; //this makes it so it now says whose turn it is again
				CircleChance = true;
				for(int i = 0; i < 9; i++)
				{
					ticData[i] = -1;//the values it originially were
				}
			}
			if((event.getX() > 380 && event.getX() <= 383) && (event.getY() > 0 && event.getY() <= 5 )) //just for me to debug, able to see coordinates of mouse if click a secret area
			{
					debugging = true;	
			}
			if(!winner)
			{
				if((event.getX() > 41 && event.getX() < 91) && (event.getY() > 20 && event.getY() < 70) && ticData[0] == -1)//if click very specific area AND that area hasn't already been selected
				{
					if(CircleChance)
					{
						ticData[0] = 0; //if clicked check if it is circles chance to enter, if yes then set this element of the array to 0
						CircleChance = false;
					}
					else 
					{
					ticData[0] = 1; 
					CircleChance = true;
					} //if clicked but not circles chance then set to 1 which is SQUARE
				}
				else if((event.getX() > 91 && event.getX() < 141) && (event.getY() > 20 && event.getY() < 70) && ticData[1] == -1)
				{
					if(CircleChance)
					{
						ticData[1] = 0;
						CircleChance = false;
					}
					else 
					{
					ticData[1] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 141 && event.getX() < 191) && (event.getY() > 20 && event.getY() < 70) && ticData[2] == -1)
				{
					if(CircleChance)
					{
						ticData[2] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[2] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 41 && event.getX() < 91) && (event.getY() > 70 && event.getY() < 120) && ticData[3] == -1)
				{
					if(CircleChance)
					{
						ticData[3] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[3] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 91 && event.getX() < 141) && (event.getY() > 70 && event.getY() < 120) && ticData[4] == -1)
				{
					if(CircleChance)
					{
						ticData[4] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[4] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 141 && event.getX() < 191) && (event.getY() > 70 && event.getY() < 120) && ticData[5] == -1)
				{
					if(CircleChance)
					{
						ticData[5] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[5] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 41 && event.getX() < 91) && (event.getY() > 120 && event.getY() < 170) && ticData[6] == -1)
				{
					if(CircleChance)
					{
						ticData[6] = 0;
						CircleChance = false;
					}
					else 
					{
					ticData[6] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 91 && event.getX() < 141) && (event.getY() > 120 && event.getY() < 170) && ticData[7] == -1)
				{
					if(CircleChance)
					{
						ticData[7] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[7] = 1; 
					CircleChance = true;
					}
				}
				else if((event.getX() > 141 && event.getX() < 192) && (event.getY() > 120 && event.getY() < 170) && ticData[8] == -1)
				{
					if(CircleChance)
					{
						ticData[8] = 0; 
						CircleChance = false;
					}
					else 
					{
					ticData[8] = 1; 
					CircleChance = true;
					}
				}
			}
			for(int i: ticData)
			{
				System.out.print(i + " "); //debugging, displays in console 
			}
			System.out.println("");
			if(!CircleChance && !winner) //checks whose turn it is
			{
				str2 = "Now is the SQUARE player's turn to go.";
			}
			else if(CircleChance && !winner) str2 = "Now is the CIRCLE player's turn to go.";
			
			didAnyoneWinCircle(); //call this method to see if anyone has won now that someone has placed their "tic"
			didAnyoneWinSquare(); //this is because if there is a winner you do not want to potentially have another winner or update the screen until they want to play again
			repaint();
		}
		//these unused methods are here to prevent an error from ocurring due to not implementing methods that have been called with the "implements", although the erro the not "break" the program
		public void mouseEntered(MouseEvent event) //what happens when mouse enters mousePanel
		{
		}
		public void mouseExited(MouseEvent event) //up until here are methods of MouseListener (5 methods)
		{
		}
		public void mousePressed(MouseEvent event) 
		{
		}
		public void mouseReleased(MouseEvent event)
		{
		}
		public void mouseDragged(MouseEvent event) //click and drag
		{
		}
		public void mouseMoved(MouseEvent event) //dragging mouse without clicking
		{
			if(debugging) //do not want the coordinates to be showen to the user
			{
			statusbar.setText(String.format("coords %d %d", event.getX(), event.getY()));
			}
		}
	}
}

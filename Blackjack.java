import Package.Hand;
import Package.Deck;
import Package.Card;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

class Blackjack implements ActionListener
{  Deck deck;
   Hand computer;
   Hand player;
   JFrame jfrm;
   JButton Hit;
   JButton Fold;
   JButton Restart;
   JPanel top, bottom, mid;
   
   //Resets the cards and enables new game
   void restart()
    {  deck=null; 
       computer=player=null;
   	   
   	   deck=new Deck();
   	   computer=new Hand(deck);
   	   player=new Hand(deck);
   	   jfrm.getContentPane().removeAll(); //Removes all components from the screen
   	}  

   
   //Constructor for the class, creates the JFrame,buttons, and calls GUI function
   Blackjack() 
    {   jfrm=new JFrame("BlackJack");
        Hit=new JButton("Hit");
        Fold=new JButton("Fold");
        Restart=new JButton("Restart");
    	
    	Hit.addActionListener(this);
    	Fold.addActionListener(this);
    	Restart.addActionListener(this);
    	restart();
        GUI();       
    }
   
   //Defining the actions to be taken for button clicks
   public void actionPerformed(ActionEvent ae)
     { jfrm.getContentPane().removeAll();
       
       if(ae.getActionCommand().equals("Hit"))
          { Play();
          }  
       
       //Ends player's game, and finishes computer's turn 
       if(ae.getActionCommand().equals("Fold") || player.getBust()==true )
         {  player.Fold();
            while(computer.getHit()==true)
               Play();
         }
       
       if(ae.getActionCommand().equals("Restart"))
         { restart();
         }  
       GUI();    
     }
    
   
   void Play()
     {  if(player.getHit()==true)
            player.Draw();  

     	//Automates computer's turn
     	Random rand=new Random();       
     	if(computer.getHit()==true)
          {   if(computer.getTotal()<=14)
	              computer.Draw();
	          else if(computer.getTotal()>=19) 
	          	      computer.Fold();
	          else if((rand.nextInt(2)+1)==1)
	          	 	   computer.Draw();
	          	 	else 
	          	 	  computer.Fold();;
	       }
	  
	 }     
    
    
    //Makes GUI
    void GUI()
      { top=bottom=mid=null;
        top=new JPanel();
    	bottom=new JPanel();
    	mid=new JPanel();
       
        jfrm.setLayout(new BorderLayout());
      	jfrm.setSize(1500,725);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	String filenames[];
    	ImageIcon images[];
    	
    	//toString returns all the card names,this is assigned to filenames
    	
        //Display the player cards
    	filenames=(player.toString()).split("\\\t");
    	images=new ImageIcon[filenames.length];
        
    	jfrm.add(top,BorderLayout.NORTH);
    	jfrm.add(bottom,BorderLayout.SOUTH);
    	jfrm.add(mid,BorderLayout.CENTER);

        top.add(new JLabel("You"));
        
        //All the card images needed are added to screen
        for(int i=0;i<filenames.length;i++)
          { images[i]=new ImageIcon("Cards/"+filenames[i]+".gif");
            top.add(new JLabel(images[i]));
          }
        top.add(new JLabel("Total:"+player.getTotal()));
        

        //Display the computer cards
        filenames =null;
        images=null;
        
        filenames=(computer.toString()).split("\\\t");
        
        if(player.getHit()==true)            //If player is still playing,computer's cards are hidden
          for(int i=0;i<filenames.length;i++)
          	 filenames[i]="b";
    	
    	bottom.add(new JLabel("Computer"));
        images=new ImageIcon[filenames.length];
        
        for(int i=0;i<filenames.length;i++)
          { images[i]=new ImageIcon("Cards/"+filenames[i]+".gif");
            bottom.add(new JLabel(images[i]));
          }
        	

        //If both players have folded,result is to be displayed
        if(player.getHit()==false && computer.getHit()==false)
          { bottom.add(new JLabel("Total:"+computer.getTotal()));
          	String msg="";
            
          	if(player.getBust()==true && computer.getBust()==true)
                msg="Both busted";
            else if(player.getTotal()==computer.getTotal())
            	msg="Tie";
            
            if(player.getBust()==true && computer.getBust()==false)
                msg="Computer wins";
            if(computer.getTotal()>player.getTotal() && computer.getBust()==false)
                msg="Computer wins";
            if(computer.getBust()==true && player.getBust()==false)
               msg="You win";
            if(computer.getTotal()<player.getTotal() && player.getBust()==false)
                msg="You win";
            
            JLabel score=new JLabel(msg);
            score.setHorizontalAlignment(JLabel.CENTER);
            score.setVerticalAlignment(JLabel.CENTER);
            jfrm.add(score);           	
           }
        
        //If game has not ended,hit and fold buttons need to be on screen
        else 
          { 
          	mid.add(Hit);
            mid.add(Fold);
          }
        
        top.add(Restart);
    	jfrm.setVisible(true);
    }

    public static void main(String[] args)
       {  SwingUtilities.invokeLater(new Runnable()
             {  public void run() 
    			  {	 new Blackjack();
    			  }
    	     });
       }
    	
}






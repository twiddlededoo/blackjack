package Package;
import java.util.Random;

//Class to represent the playing deck

public class Deck    
{ Card Drawn[];  //Holds all cards already dealt
  
  public Deck()
    { Drawn=null;
    }
  
  public Card Draw()
    {  Random rand=new Random();
  	   Card arr[]=new Card[1];
  	   
       //If some cards are already drawn,the old cards are copied to new array
  	   if(Drawn!=null)                 
  	      { arr=new Card[Drawn.length+1];
  	  	    for(int i=0;i<Drawn.length;i++)
              arr[i]=Drawn[i];
          }  
       
       while(true)
         { int val=rand.nextInt(13)+1; //Returns a random value from 1 to 13  
           char suit='s';   
           
           switch(rand.nextInt(4)+1)  //Creates a random value from 1 to 4
              { case 1: suit='s';
                        break;
                case 2: suit='c';
                        break;
                case 3: suit='h';
                        break;
                case 4: suit='d';
                        break; 
              }
           
           Card temp=new Card(val,suit);
           

           /*Searches the deck with new card to see if it is drawn already
             If it is drawn, the card is added to the deck and returned.
             If not drawn,the loop continues until an undrawn card is created
           */
           if(Search(temp)==false)
             { arr[arr.length-1]=temp;
               Drawn=arr;
               return temp;
             }
          }  
    }   
  	 
  boolean Search(Card x)
    { if(Drawn==null)
         return false;
      
      for(int i=0;i<Drawn.length;i++)
    	  if(x.name.equals(Drawn[i].name))
    	 	  return true;
      
      return false;	 
    
     }

}

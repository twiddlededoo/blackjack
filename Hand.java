package Package;

//Class to represent an individual player's hand of cards
public class Hand
{ Card hand[];
  int total;
  boolean hit;
  boolean bust;
  Deck deck;
  
  public Hand(Deck deck)
    { this.deck=deck;
  	  hand=new Card[2];
  	
  	  //Each player starts with two cards
      for(int i=0;i<2;i++)
  	   {  hand[i]=this.deck.Draw();
       }
      bust=false;
      hit=true;
      Total();
    }

  public void Draw()
    {  Card arr[]=new Card[hand.length+1]; //New array to add the new card to
  	
       for(int i=0;i<hand.length;i++)  //Copies old cards to new array
  	 	 arr[i]=hand[i];
  	   arr[arr.length-1]=deck.Draw();
  	   hand=arr;	 
       Total();
    } 
   
  void Total()
  {  total=0;
  	 int jacknos=0;  //For number of Js.Needed because J can be 11 or 1
     for(int i=0;i<hand.length;i++)
       {  char x=hand[i].getVal();
          
          if(x>='2' && x<='9') //For this range,the value is equal to the numeric value itself
           	 total+=x-'0';
          if(x=='t' || x=='k' || x=='q')  
           	 total+=10;
          if(x=='a')  
           	  total+=1;
          if(x=='j')
           	{  total+=11;
           	   jacknos++;
           	}
        }
       
     /*If the value has exceeded 21, we check to see if there is a J in the deck
       A J can be 11 or 1. By default we take J as 11, but if value is exceeding 21,
       we make it 1
     */
     while (total>21 && jacknos>=1)
         {  jacknos-=1;
         	total-=10;
         }
     if(total>21)
       {  bust=true;
       	  hit=false;
       }
  } 
  
  public String toString()
   {  String str="";
   	  for(int i=0;i<hand.length;i++)
          str+=hand[i]+"\t";
      return str;
   }
  
   public int getTotal()
   { return total;
   } 
   
   public void Fold()
   {
     hit=false;
   }
  
   public boolean getHit()
    {
       return hit;
    }

   public boolean getBust()
    {
      return bust;
     }

}

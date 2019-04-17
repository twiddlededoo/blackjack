package Package;

//Class to represent a single card
public class Card
{ char val;
  char suit;
  String name;
  
  Card(int val,char suit)
  {  if(val>=2 && val<=9)
  		this.val=Character.forDigit(val,10);  //Getting character version of number
  	else 
  	 {	switch(val)
  	     { case 1:this.val='a';
                  break;
           case 10:this.val='t'; 
                  break;      
  	       case 11:this.val='j';
  	              break;
  	       case 12:this.val='q';
  	               break;
  	       case 13:this.val='k';
  	              break;
  	     }
  	  }
   
  	this.suit=suit;
    name="";
    name=name+this.val+suit;
  
}
  
  
  public char getVal()
    {  return val; 
    }
  
  
  public String toString()
   { return name; 
   }
  
}

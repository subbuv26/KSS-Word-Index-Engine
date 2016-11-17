import java.io.*;
import java.util.*;
import java.lang.*;
/*import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;*/


/*--------------------------------------------------------------------------
 The SkipListItem class is an sitem which is going to be inserted into the  
                            Dictionary
----------------------------------------------------------------------------*/
class SkipListItem extends Item
{
	public SkipListItem[] levels;
	
	public SkipListItem(String word,int frequency)
	{
		this.word = word;
		this.frequency = frequency;
	}
	/*The constructer which is going to be fill the sitem values when the
	  object is created */
	public SkipListItem(String word,int frequency, int levelvalue)
	{
		this.word = word;
		this.frequency = frequency;
		this.levels = new SkipListItem[levelvalue+1];
	}
	/*----------------------------------------------------------------------*/
	public SkipListItem()
	{
			this.levels = new SkipListItem[8+1];
			for(int i=8; i>=1; i--)
				this.levels[i] = null;
	}
	public void print(SkipListItem sitem)
	{
		System.out.println("word="+sitem.word+
							"\tfrequency="+sitem.frequency);
		return;
	}
}
/*--------------------------------------------------------------------------*/
class Operations extends SkipListItem
{
	public static SkipListItem head = new SkipListItem();
	
	/*-----The function which going to insert the word into Dictionary------*/
	public Item addItem(Item gitem)
	{
		String word = gitem.word;
		int frequency = gitem.frequency;
		
		SkipListItem temp = head;			   //Intialising temp to head
		SkipListItem fanin = new SkipListItem();//Create a "fanin"named object
		for(int i=8; i>=1; i--)        //pointing all levels to the head
			fanin.levels[i] = head;
		SkipListItem fanout = new SkipListItem();//Creating an empty"fanout"
														//object  
		for(int i=8; i>=1; i--)
		{
			boolean flag = false;
			/*---------------------------------------------------------------
			 If word is already exist then frequency should be incresed by 
			 -------------given frequency and return from function----------*/
			if(temp.levels[i] != null)
			{
				if((temp.levels[i].word).compareTo(word) == 0)
				{
					if((temp.levels[i].frequency+frequency) < 0)
						return (null);
					temp.levels[i].frequency += frequency;
					gitem.frequency = temp.levels[i].frequency;
						return (gitem);
				}
			}
			/*--------------------------------------------------------------*/
			
			/*If word is not exist, Fill the values of "fanin" & "fanout" to
			add the word to Dictionary. 
			fanin  :  It stores all level pointers which will point the new
						node
			fanout :  It stores all level pointers which the new node has to
						point-----------------------------------------------*/
			while((temp.levels[i] != null) &&
				((temp.levels[i].word).compareTo(word) < 0))
			{
				temp = temp.levels[i];
				fanin.levels[i] = temp;
				flag = true;
			}
			if(flag == false)
				fanin.levels[i] = temp;
				
			fanout.levels[i] = temp.levels[i];
		/*------------------------------------------------------------------*/
		}
		
		/*------Generating a random number to obtain the levels for an-------
		  ------------------------sitem to be added-------------------------*/
		Random rand = new Random();
		int level = rand.nextInt(7)+1;
		/*------------------------------------------------------------------*/
		
		
		/*Creating the sitem with given word,frequency & obtained random
		levels*/
		SkipListItem sitem = new SkipListItem(word, frequency, level);
		/*------------------------------------------------------------------*/
		
		
		/*Add that sitem into Dictionary by using "fanin" & "fanout"
							pointers								*/
		for(int i=level; i>=1; i--)
		{
			sitem.levels[i] = fanout.levels[i];
			fanin.levels[i].levels[i] = sitem;
		}
		/*------------------------------------------------------------------*/
		return (gitem);
	}
	
	/*The function which going to delete the word into Dictionary*/
	public Item deleteItem(Item gitem)
	{
		String word = gitem.word;
		int frequency = gitem.frequency;
		
		SkipListItem temp = head;			   //Intialising temp to head
		SkipListItem fanin = new SkipListItem();//Create a "fanin" named
												//object &
		for(int i=8; i>=1; i--)        //pointing all levels to the head
			fanin.levels[i] = head;
		SkipListItem fanout = new SkipListItem();    //Creating an empty
													//"fanout" object 
		for(int i=8; i>=1; i--)
		{
			boolean flag = false;
			/*----------------------------------------------------------------
			 If word is already exist then frequency should be decreased by 
			 -------------by one and return from function-------------------*/
			if(temp.levels[i] != null)
			{
				if(((temp.levels[i].word).compareTo(word)==0)&&
					(temp.levels[i].frequency!=frequency))
				{
					temp.levels[i].frequency -= frequency;
					gitem.frequency = temp.levels[i].frequency;
						return (gitem);
				}
			}
			/*--------------------------------------------------------------*/
			
			/*If word is not exist, Fill the values of "fanin" & "fanout" to
			insert the word 
			fanin  :  It stores all level pointers which are going to be
				      pointed
			fanout :  It stores all level pointers which are going to be
					  pointed                                              */
			while((temp.levels[i] != null)&&
				((temp.levels[i].word).compareTo(word) < 0))
			{
				temp = temp.levels[i];
				fanin.levels[i] = temp;
				flag = true;
			}
			if(flag == false)
				fanin.levels[i] = temp;
			if(temp.levels[i] != null)
			{
				if((temp.levels[i].word).compareTo(word) == 0)
					fanout.levels[i] = temp.levels[i].levels[i];
				else
					fanout.levels[i] = temp.levels[i];
			}
			else
				fanout.levels[i] = temp.levels[i];
			/*--------------------------------------------------------------*/
		}
		/*-------Check whether the word is exist or not in Dictionary-------*/
		if((temp.levels[1] != null) && 
				((temp.levels[1].word).compareTo(word) == 0))
		{
			/*deleteItem that sitem into Dictionary by using "fanin"&"fanout"
		                         pointers                               */
			for(int i=8; i>=1; i--)
				fanin.levels[i].levels[i] = fanout.levels[i];
			gitem.frequency = 0;
			return (gitem);
			/*--------------------------------------------------------------*/
		}
		else
			return (null);
		/*------------------------------------------------------------------*/
	}

	public Item search(String word)
	{
		Item item = new Item();
		SkipListItem temp = head;
		for(int i=8; i>=1; i--)
		{
			/*Travesing through the dictionary and stop at the node
						contains the search word*/
			while((temp.levels[i] != null) &&
				((temp.levels[i].word).compareTo(word) < 0))
			{
				temp = temp.levels[i];
			}
			/*store that node into an item and return it*/
			if(temp.levels[i] != null)
			{
				if((temp.levels[i].word).compareTo(word) == 0)
				{
					item.word = temp.levels[i].word;
					item.frequency = temp.levels[i].frequency;
					return(item);
				}
			}
		}
		return null;
	}
	
	public ArrayList<Item> wordsWithFrequency(int freq)
	{
		SkipListItem temp = head;
		ArrayList<Item> array = new ArrayList<Item>();
		/*------------Travesing through the dictionary----------------------*/
		while(temp.levels[1] != null)
		{
		    Item item = new Item();
			if(temp.levels[1].frequency == freq)
			{
			    item.word = temp.levels[1].word;
				item.frequency = temp.levels[1].frequency;
				//store the items with corresponding frequency
				array.add(item);	
			}
			
			temp = temp.levels[1];
		}
		return array;
	}
	
	public ArrayList<Item> topFrequencyWords(int number)
	{
		SkipListItem temp = head;
		int[] frequency = new int[number+1];
		ArrayList<Item> array = new ArrayList<Item>();
		boolean flag;
		/*---------store the top frequency's into an array----------------*/
		while(temp.levels[1] != null)
		{
			flag = true;
			int minfreq = minFrequency(frequency);
			if(frequency[minfreq] < temp.levels[1].frequency)
			{
				for(int i=1;i<frequency.length;i++)
				{
					if(frequency[i] == temp.levels[1].frequency)
						flag = false;
				}
				if(flag == true)
					frequency[minfreq] = temp.levels[1].frequency;
			}
			temp = temp.levels[1];
		}
		/*---------------------------------------------------------------*/
		
		Arrays.sort(frequency);
		// reverse the frequency array
		for(int i=0;i<frequency.length/2;i++) 
		{
			// swap the elements
			int t = frequency[i];
			frequency[i] = frequency[frequency.length-(i+1)];
			frequency[frequency.length-(i+1)] = t;
		}
		
		/*----Add all items which equals to any frequency in above array----*/
		for(int i=0; i<frequency.length; i++)
		{
			temp = head;
			while(temp.levels[1] != null)
			{
			    Item item = new Item();
				if(temp.levels[1].frequency == frequency[i])
				{
			        item.word = temp.levels[1].word;
				    item.frequency = temp.levels[1].frequency;
				    array.add(item);
			    }
				temp = temp.levels[1];
			}
			
		}
		return array;
		/*-----------------------------------------------------------------*/
	}
	
	/*----------This function returns minimum value from input array-------*/
	public int minFrequency(int[] frequency)
	{
		int minfreq = 1;
		for(int i=1; i<frequency.length; i++)
		{
			if(frequency[minfreq] > frequency[i])
				minfreq = i;
		}
		return minfreq;
	}
	/*--------------------------------------------------------------------*/
	
	public ArrayList<Item> leastFrequencyWords(int number)
	{
		SkipListItem temp = head;
		int[] frequency = new int[number+1];
		ArrayList<Item> array = new ArrayList<Item>();
		boolean flag;
		
		/*---------store the least frequency's into an array----------------*/
		while(temp.levels[1] != null)
		{
			flag = true;
			int maxfreq = maxFrequency(frequency);
			if((frequency[maxfreq] > temp.levels[1].frequency) ||
				(frequency[maxfreq] == 0))
			{
				for(int i=1;i<frequency.length;i++)
				{
					if(frequency[i] == temp.levels[1].frequency)
						flag = false;
				}
				if(flag == true)
					frequency[maxfreq] = temp.levels[1].frequency;
			}
			temp = temp.levels[1];
		}
		
		//sort the arrays 
		Arrays.sort(frequency);
		
		/*----Add all items which equals to any frequency in above array----*/
		for(int i=1; i<frequency.length; i++)
		{
			temp = head;
			while(temp.levels[1] != null)
			{
			    Item item = new Item();
				if(temp.levels[1].frequency == frequency[i])
				{
			        item.word = temp.levels[1].word;
				    item.frequency = temp.levels[1].frequency;
				    array.add(item);// adding the items
			    }
				temp = temp.levels[1];
			}
		}
		return array;
		/*-----------------------------------------------------------------*/
	}
	
	/*----------This function returns maximum value from input array-------*/
	public int maxFrequency(int[] frequency)
	{
		int maxfreq = 1;
		for(int i=1;i<frequency.length;i++)
		{
			if(frequency[i] == 0)
				return i;
			if(frequency[maxfreq] < frequency[i])
				maxfreq = i;
		}
		return maxfreq;
	}
	/*---------------------------------------------------------------------*/
}

class KSSDictionary extends Operations
{
	public static void main(String args[])throws java.io.IOException
	{
		Operations o = new Operations();
		
		if(args.length == 0)
		{
			System.out.println("The Usage <class file><Input File>");
			return;
		}
		Item item = new Item();
		String line;
		String[] lineparts;
		
		System.out.println("---------------ADD FUNCTION------------------");
		FileInputStream fs = new FileInputStream(args[0]);
		DataInputStream ds = new DataInputStream(fs);
		InputStreamReader ir = new InputStreamReader(ds);
		BufferedReader br = new BufferedReader(ir);
		while((line = br.readLine()) != null)
		{
			lineparts = line.split(", ");
			item.word = lineparts[0];
			
			item.frequency = Integer.parseInt(lineparts[1]);

			Item test;
			test = o.addItem(item);
			if(test == null)
				System.out.println("THE WORD IS NOT ADDED");
			else if(test.frequency == item.frequency)
			System.out.println("THE "+test.word+" IS ADDED TO DICTIONARY");
			
			else
				System.out.println("THE "+test.word+
									"WORD INSTANCE INCREASED TO "+
									test.frequency);
		}
		System.out.println("----------------------------------------------");
		
		System.out.println("*********The words in Dictionary is**********");
		SkipListItem temp = head;
		while(temp.levels[1] != null)
		{
			o.print(temp.levels[1]);
			temp = temp.levels[1];
		}
		System.out.println("----------------------------------------------");
		
		System.out.println("--------------DELETE FUNCTION-----------------");
		Item test;
		item.word = "krishna";
		item.frequency = 1;
		test = o.deleteItem(item);
		//System.out.println("tf="+test.frequency);
		if(test != null)
		{
		if(test.frequency == 0)
			System.out.println("THE "+item.word+" IS COMPLETLY DELETED");
		else
			System.out.println("THE "+item.word+
								" INSTANCE IS DECREASED BY ONE");
		}
		else 
			System.out.println("THE "+item.word+" IS NOT IN DICTIONARY");
		
		item.word = "veer";
		item.frequency = 1;
		test = o.deleteItem(item);
		//System.out.println("tf="+test.frequency);
		if(test != null)
		{
		if(test.frequency == 0)
			System.out.println("THE "+item.word+" IS COMPLETLY DELETED");
		else
			System.out.println("THE "+item.word+
								" INSTANCE IS DECREASED BY ONE");
		}
		else 
			System.out.println("THE "+item.word+" IS NOT IN DICTIONARY");
		System.out.println("----------------------------------------------");
		
		System.out.println("--------------------SEARCH--------------------");
		//Queries q = new Queries();
		test = o.search("krishna");
		if(test == null)
			System.out.println("NO THE WORD IS NOT IN DICTIONARY");
		else
			System.out.println("YES THE WORD \""+test.word+
								"\" IS IN DICTIONARY WITH FREQUENCY "+
									test.frequency);
		System.out.println("----------------------------------------------");
		
		
		System.out.println("*********The words in Dictionary is**********");
		temp = head;
		while(temp.levels[1] != null)
		{
			o.print(temp.levels[1]);
			temp = temp.levels[1];
		}
		System.out.println("----------------------------------------------");
		
		
		System.out.println("--------------WORDS WITH FREQUENCY------------");
		ArrayList<Item> check;
		check = o.wordsWithFrequency(10);
		if(check.size() != 0)
		{
			System.out.println("THE WORD WITH GIVEN FREQUENCY ARE");
			for (int i = 0; i < check.size(); i++)
				System.out.println(check.get(i).word);
		}
		else
			System.out.println("THE WORD WITH GIVEN FREQUENCY Isn't EXIST");
		System.out.println("----------------------------------------------");
		
		
		
		System.out.println("-------------TOP MOST FREQUENCYS--------------");
		check = o.topFrequencyWords(10);
		if(check != null)
		{
			System.out.println("*The words with top most frequencys are*:");
			for (int i = 0; i < check.size(); i++)
				System.out.println("WORD = "+check.get(i).word+
								"\tFREQUENCY = "+check.get(i).frequency);
		}
		else
			System.out.println("THE WORD WITH GIVEN FREQUENCY Isn't EXIST");
		System.out.println("-----------------------------------------------");
		
		
		
		System.out.println("--------------LEAST MOST FREQUENCYS------------");
		check = o.leastFrequencyWords(8);
		if(check != null)
		{
			System.out.println("*The words with least most frequencys are*:");
			for (int i = 0; i < check.size(); i++)
				System.out.println("WORD = "+check.get(i).word+
								"\tFREQUENCY = "+check.get(i).frequency);
		}
		else
			System.out.println("THE WORD WITH GIVEN FREQUENCY Isn't EXIST");
		System.out.println("-----------------------------------------------");
		
		temp = head;
		System.out.println("*********The words in Dictionary is**********");
		while(temp.levels[1] != null)
		{
			o.print(temp.levels[1]);
			temp = temp.levels[1];
		}
	}
}


//Andrea Shulman
//April 11, 2015
//Contest #4
//Quine-McClusky Algorithm

import java.util.*;
public class Quine
{
   private int[] terms;
   private String[] booleans;
   private ArrayList<String> preBool;
   private ArrayList<String> xGuys;
   private ArrayList<String> holdIt;
   private int hold;
   public Quine(String str)
   {
      preBool=new ArrayList<String>();
      xGuys=new ArrayList<String>();
      hold=0;
      stringProcessor(str);
      booleans= new String[terms.length];
      
      for(int i=0;i<terms.length;i++){
         booleans[i]=Integer.toBinaryString(terms[i]);}
      for(int j=0;j<booleans.length;j+=2)
         for(int k=1;k<booleans.length;k++)
         {
            if(booleans[j].length()<booleans[k].length())
               booleans[j]="0"+booleans[j];
            else if(booleans[k].length()<booleans[j].length())
               booleans[k]="0"+booleans[k];
         }
      for(int num=0;num<booleans.length-1;num++)
      {
         for(int j=1;j<booleans.length;j++)
            compare(booleans[num],booleans[j],preBool);
      }   
      if(booleans.length%2!=0)
            compare(booleans[booleans.length-1],booleans[0],preBool);
      LinkedHashSet<String> hash = new LinkedHashSet<String>(preBool);
      ArrayList<String> list = new ArrayList<String>(hash);
      preBool=list;
      ArrayList<String> temp=preBool;
      String tested =booleanFunction(preBool);
      ArrayList<String> temporary=new ArrayList<String>();
      ArrayList<String> holdIt=new ArrayList<String>();
      String str4="";
      for(int i=0;i<xGuys.size()-1;i++)
         for(int j=i+1;j<xGuys.size();j++)
            if(xGuys.get(i).indexOf("x")==xGuys.get(j).indexOf("x")){
               for(int k=0;k<xGuys.get(i).length();k++){
                  if(xGuys.get(i).charAt(k)==xGuys.get(j).charAt(k))
                     str4+=xGuys.get(i).charAt(k);
                  else
                     str4+="x";
               }
               holdIt.add(str4);
               str4="";
            }
        if(holdIt.size()>1)
      {LinkedHashSet<String> hash2 = new LinkedHashSet<String>(holdIt);//prebool to temp
      ArrayList<String> list2 = new ArrayList<String>(hash2);
      holdIt=list2;}
         else
            holdIt=list;
      String str5="";
      
      for(int i=0; i<terms.length;i++){
         if(terms[i]>7)
         {
            for(int j=0;j<holdIt.size();j++)
            {
               if(holdIt.get(j).indexOf("xxx")!=-1){
                  holdIt.remove(j);
                  j--;}
            }
         }
      }
      str5=booleanFunction(holdIt);
      if(str5.length()>1 && str5.charAt(str5.length()-1)=='+')
         str5=str5.substring(0,str5.length()-1);
      if(str5.length()>1 && str5.charAt(0)=='+')
         str5=str5.substring(1);

      System.out.println(str5);
   }
   
   public void stringProcessor(String str)
   {
      ArrayList<Integer> commas=new ArrayList<Integer>(); //array with indexes of commas
      int count=0;
      
      for(int i=0; i<str.length();i++)
         if((str.substring(i,i+1)).equals(","))
         {
            commas.add(i);
            count++;
         }
      count=1;
      terms=new int [commas.size()];
      terms[0]=Integer.parseInt(str.substring(0,commas.get(0)));
      for(int j=0;j<commas.size()-1;j++)
      {
         terms[count]=Integer.parseInt(str.substring(commas.get(j)+1,commas.get(j+1)));
         count++;
      }     
   }
   
   public void compare(String str1, String str2, ArrayList<String> list)
   {
      String str3="";
      
      for(int j=0;j<str1.length();j++)
      {
         if(str1.charAt(j)==str2.charAt(j))
            str3+=str1.charAt(j);
         else
            str3+="x";
      }
      int counter = 0;
      for(int i=0; i<str3.length(); i++ )
          if(str3.charAt(i) == 'x')
              counter++;
      if(counter==1)
         list.add(str3);   
   }
   
   public String booleanFunction(ArrayList<String> temp)
   {
      String str="";
      LinkedHashSet<String> hash = new LinkedHashSet<String>(temp);//prebool to temp
      ArrayList<String> list = new ArrayList<String>(hash);
      for(int i=0;i<list.size();i++)
      {
         str+=toLetter(list.get(i));
         if(hold!=list.size()-1)
            str+="+";
         hold++;
      }
      return str;
   }
   
   public String toLetter(String str)
   {         
      xGuys.add(str);
      String ret="";
      int iter=0;
      for(int j=0;j<str.length();j++)
      {
         if(iter==0)
         {
            if(str.charAt(j)=='1')
               ret+="A";
            else if(str.charAt(j)=='0')
               ret+="a";
         }
         else if(iter==1)
         {
            if(str.charAt(j)=='1')
               ret+="B";
            else if(str.charAt(j)=='0')
               ret+="b";
         }
         else if(iter==2)
         {
            if(str.charAt(j)=='1')
               ret+="C";
            else if(str.charAt(j)=='0')
               ret+="c";
         }
         else if(iter==3)
         {
            if(str.charAt(j)=='1')
               ret+="D";
            else if(str.charAt(j)=='0')
               ret+="d";
         }
         iter++;
      }
      return ret;
   }
   
   public static void main(String[]args)
   {
      /*Quine test1=new Quine("2,6,-1");
      Quine test2=new Quine("2,3,5,7,-1");
      Quine test3=new Quine("1,3,4,5,6,7,-1");
      Quine test4=new Quine("8,9,10,11,12,14,15,-1");
      Quine test5=new Quine("9,10,11,12,13,14,15,-1");*/
      
      Quine test1=new Quine("1,2,3,-1");
      Quine test2=new Quine("0,2,4,6,-1");
      Quine test3=new Quine("1,3,5,7,-1");
      Quine test4=new Quine("1,3,5,7,9,11,-1");
      Quine test5=new Quine("2,4,6,8,10,12,14,-1");
   } 
}
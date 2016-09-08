# ACSL-McClusky

//Andrea Shulman
//April 11, 2015
//Contest #4
//Quine-McClusky Algorithm

import java.util.*;
public class McClusky
{
   private String[] terms;
   private String[] booleans;
   private ArrayList<String> preBool;
   private ArrayList<String> end;
   private ArrayList<String> condensed;
   private ArrayList<String> oneX;
   private ArrayList<Integer> xs;
   private int hold;
   private boolean cond;
   public McClusky(String str)
   {
      preBool=new ArrayList<String>();
      end=new ArrayList<String>();
      oneX=new ArrayList<String>();
      condensed=new ArrayList<String>();
      xs=new ArrayList<Integer>();
      cond=false;
      hold=0;
      stringProcessor(str);
      booleans= new String[terms.length];
      for(int i=0;i<terms.length;i++)
         booleans[i]=terms[i];
      //for(int i=0; i<booleans.length;i++)
         //System.out.println(booleans[i]);
      for(int j=0;j<booleans.length;j+=2)
         for(int k=1;k<booleans.length;k++)
         {
            //System.out.println(j+" "+k);
            if(booleans[j].length()<booleans[k].length())
               booleans[j]="0"+booleans[j];
            else if(booleans[k].length()<booleans[j].length())
               booleans[k]="0"+booleans[k];
         }
      //for(int i=0; i<booleans.length;i++)
         //System.out.println(booleans[i]);
      for(int num=0;num<booleans.length-1;num++)
      {
         for(int j=1;j<booleans.length;j++)
            compare(booleans[num],booleans[j],preBool);
      }   
      if(booleans.length%2!=0)
            compare(booleans[booleans.length-1],booleans[0],preBool);
      ArrayList<String> temp=preBool;
      //preBool=condense(temp);
      
      //for(int i=0;i<temp.size();i++)
         //System.out.println(temp.get(i));
      //booleans=new String[terms.length];
      
      //condense();
      //condense();
      /*ArrayList<String> temp=preBool;
      for(int num=0;num<temp.size()-1;num++)
      {
         for(int j=1;j<temp.size();j++)
            compare2(temp.get(num),temp.get(j));
      }   
      if(temp.size()%2!=0)
            compare2(temp.get(temp.size()-1),temp.get(0));
      preBool=end;*/
      booleanFunction();
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
      count=0;
      //for(int i=0; i<commas.size();i++)
      //   System.out.println(commas.get(i));
      terms=new String [commas.size()];
      for(int j=0;j<commas.size();j++)
      {
         terms[count]=str.substring(commas.get(j)-1,commas.get(j));
         count++;
      }
      
      //for(int i=0; i<terms.length;i++)
         //System.out.println(terms[i]);
   }
   
   public void compare(String str1, String str2, ArrayList<String> list)
   {
      //if(booleans.length%2==0)
      String str3="";
      
      for(int j=0;j<str1.length();j++)
      {
         if(str1.charAt(j)==str2.charAt(j))
            str3+=str1.charAt(j);
         else
            str3+="x";
      }
      if(cond)
      {
         //if(str1.equals(str2))
         //   list.add(str3);
         int steps=0;
         for(int i=0; i<str3.length(); i++ )
          if(str3.charAt(i) == 'x')
              steps++;
         if(steps==1)
            oneX.add(str3);
         else
            list.add(str3);
         return;
      }
      //System.out.println(str3);
      int counter = 0;
      for(int i=0; i<str3.length(); i++ )
          if(str3.charAt(i) == 'x')
              counter++;
      if(counter==1)
         list.add(str3);
      //condense();       
   }
   
   public void booleanFunction()
   {
      //for(int i=0; i<preBool.size();i++)
        // System.out.println(preBool.get(i));
      String str="";
      LinkedHashSet<String> hash = new LinkedHashSet<String>(preBool);//prebool to temp
      ArrayList<String> list = new ArrayList<String>(hash);
      for(int i=0;i<list.size();i++)
      {
         str+=toLetter(list.get(i));
         if(hold!=list.size()-1)
            str+="+";
         hold++;
      }
      System.out.println(str);
   }
   
   public String toLetter(String str)
   {         
      System.out.println(str);
      String ret="";
      int iter=0;
      for(int j=0;j<str.length();j++)
      {
         //if(str.charAt(j)=='x')
         //{
            //xs.add(j);//xs will hold a list of positions where there is an x. the index will be the string it is
            
         //}
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
      //for(int i=0; i<xs.size();i++)
        //System.out.println(xs.get(i));
      return ret;
      //System.out.println(str);
   }
   
   public ArrayList<String> condense(ArrayList<String> list)
   {
      cond=true;
      for(int i=0;i<list.size();i++)
      {
         for(int j=0;j<list.get(i).length();j++)
         {
            if(list.get(i).charAt(j)=='x')
               xs.add(j);
         }
      }
      if(xs.size()==0)
         return list;
      for(int i=0; i<xs.size();i++)
        System.out.println(xs.get(i));
      int steps=0;
      for(int k=0;k<xs.size();k++)
      {
         for(int h=0;h<xs.size();h++){
            if(xs.get(k)==xs.get(h)){
               compare(list.get(k),list.get(h),condensed);
               //System.out.println(condensed.get(steps));
               //steps++;
               }
         }
      }
      if(oneX.size()==1)
         list.add(oneX.get(0));
      else
      {
         for(int k=0;k<oneX.size();k++)
         {
         for(int h=0;h<oneX.size();h++){
            if(oneX.get(k)==oneX.get(h)){
               compare(oneX.get(k),oneX.get(h),condensed);
               //System.out.println(condensed.get(steps));
               //steps++;
               }
         }
      }
      }
      for(int k=0;k<oneX.size();k++)
      {
         for(int h=0;h<oneX.size();h++)
            if(oneX.get(k).indexOf("x")==oneX.get(h).indexOf("x"))
               compare(oneX.get(k),oneX.get(h),condensed);
      }
      /*int holder = -1;
      ArrayList<String> temp=preBool;
      cond=true;
      for(int i=0;i<preBool.size()-1;i++){
         holder=preBool.get(i).indexOf("x");
         for(int j=1;j<preBool.size();j++){
            if(holder==preBool.get(j).indexOf("x"))
            {
               String str3="";
               for(int k=0;k<preBool.get(i).length();k++)
               {
                  if(preBool.get(i).charAt(k)==preBool.get(j).charAt(k))
                     str3+=preBool.get(i).charAt(k);
                  else
                     str3+="x";
               }
               int counter = 0;
               for(int h=0; h<str3.length(); h++ )
                   if(str3.charAt(h) == 'x')
                       counter++;
               if(counter==1)
                  temp.add(str3);
               //compare(preBool.get(i),preBool.get(j));
               temp.remove(i);
               temp.remove(j);
               //temp.add(str3);
               j=preBool.size();
            }
         }
      }
      preBool=temp;
      //ArrayList<String> temp=preBool;
      for(int num=0;num<temp.length-1;num++)
      {
         for(int j=1;j<temp.length;j++)
            compare(temp[num],temp[j]);
      }   
      if(temp.length%2!=0)
            compare(temp[temp.length-1],temp[0]);
      
      String str3="";
      for(int j=0;j<str1.length();j++)
      {
         if(str1.charAt(j)==str2.charAt(j))
            str3+=str1.charAt(j);
         else
            str3+="x";*/
      return condensed;
   }

   public void compare2(String str1, String str2)
   {
      //if(booleans.length%2==0)
      String str3="";
      for(int j=0;j<str1.length();j++)
      {
         if(str1.charAt(j)==str2.charAt(j))
            str3+=str1.charAt(j);
         else
            str3+="x";
      }
      /*if(cond)
      {
         preBool.add(str3);
         return;
      }*/
      //System.out.println(str3);
      /*int counter = 0;
      for(int i=0; i<str3.length(); i++ )
          if(str3.charAt(i) == 'x')
              counter++;
      if(counter==1)
         end.add(str3);*/
      //condense();       
   }

   
   public static void main(String[]args)
   {
      McClusky test1=new McClusky("2,6,-1");
      //Quine test2=new Quine("2,3,5,7,-1");
      //Quine test3=new Quine("1,3,4,5,6,7,-1");
      //Quine test4=new Quine("8,9,10,11,12,14,15,-1");
      //Quine test5=new Quine("9,10,11,12,13,14,15,-1");
   } 
}

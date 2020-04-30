package dldsimulator;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.canvas.GraphicsContext;
import static javafx.scene.input.KeyCode.J;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Interface extends Canvas{
   
   JButton NotButton,OrButton,WireButton,AndButton,input1Button,input0Button,evaluateButton,clearButton,DeleteButton,outputButton,deleteButton;
   
   ArrayList<LogicOutputer> Gatearraylist= new ArrayList();
   ArrayList<AndGate1>and=new ArrayList();
   ArrayList<OrGate1>or=new ArrayList();
   ArrayList<NotGate1>not=new ArrayList();
   ArrayList<Line>line=new ArrayList();
   int Gatenumber=0,andpointx,andpointy,orpointx,orpointy,notpointx,notpointy,deletepointx=0,deletepointy=0,count1=0;
   int andFlag=0,deleteFlag=0,orFlag=0,notFlag=0,wireFlag=0,wireStart=0,finaloutput=0,outputFlag=0,input0Flag=0,input1Flag=0;
   int wirepointx,wirepointy,wirepointx1,wirepointy1;
   int array1[][]=new int[1000][2],array2[]=new int[1000],visited[]=new int[1000];
   int dfs1[][]=new int[1000][1000],l=0,d=0;
   int outputpointx[]=new int[10],outputpointy[]=new int[10],output=0,s3,evaluateFlag=0;
   public int input0pointx[]=new int[1000],input0pointy[]=new int[1000],input1pointx[]=new int[1000],input1pointy[]=new int[1000];
   String str[]=new String[1000];
   int outputCheck[]=new int[100];
   LogicOutputer k1,k2;
   
   public Interface(){
      JFrame J = new JFrame("Dld Simulator");
      J.setSize(1200,700);
      J.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.setBounds(201,0,999,700);
      this.setBackground(new Color(255,255,255));
      
      this.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
                if(deleteFlag==1)
                {
                    deletepointx=e.getX();
                    deletepointy=e.getY();
                        for(AndGate1 x:and)
                        {
                            if(x.pointx<=deletepointx && (x.pointx+120)>=deletepointx && x.pointy<=deletepointy && (x.pointy+100)>=deletepointy){
                                    int t=and.indexOf(x);
                                    int l=x.count;
                                    and.remove(x);
                                    for(int i=0;i<Gatenumber;i++){
                                        if(Gatearraylist.get(i).Getpointx()<=deletepointx && (Gatearraylist.get(i).Getpointx()+120)>=deletepointx && (Gatearraylist.get(i).Getpointy()-35)<=deletepointy && (Gatearraylist.get(i).Getpointy()+65)>=deletepointy){
                                            Gatearraylist.remove(i);
                                            break;
                                        } 
                                    }
                                    int p1=x.pointx;
                                    int q1=x.pointy+35;
                                    int q2=x.pointy+62;
                                    int p3=x.pointx+120;
                                    int q3=x.pointy+50;
                                   for(int i=0;i<3;i++){
                                    for(Line x1:line)
                                    {
                                        int p=x1.pointx;
                                        int q=x1.pointy;
                                        int r=x1.pointx1;
                                        int s=x1.pointy1;
                                        if((p1-r)<=30 && (p1-r)>=0 && (q1-s)<=15 && (q1-s)>=0){
                                            line.remove(x1);
                                            break;
                                        }
                                        if((p1-r)<=30 && (p1-r)>=0 && (q2-s)<=15 && (q2-s)>=0){
                                           line.remove(x1);
                                           break;
                                        }
                                        if((p3-p)<=30 && (p3-p)>=0 && (q3-q)<=15 && (q3-q)>=0){
                                            line.remove(x1);
                                            System.out.println("3");
                                            break;
                                        }
                                    }
                                   }
                                    Gatenumber-=1;
                                    break;
                            }
                        }
                        for(OrGate1 x:or){
                             if(x.pointx<=deletepointx && (x.pointx+120)>=deletepointx && x.pointy<=deletepointy && (x.pointy+100)>=deletepointy)
                                 {
                                         int t=or.indexOf(x);
                                         int l=x.count;
                                         or.remove(x);
                                         for(int i=0;i<Gatenumber;i++)
                                         {
                                            if(Gatearraylist.get(i).Getpointx()<=deletepointx && (Gatearraylist.get(i).Getpointx()+120)>=deletepointx && (Gatearraylist.get(i).Getpointy()-35)<=deletepointy && (Gatearraylist.get(i).Getpointy()+65)>=deletepointy){
                                              Gatearraylist.remove(i);
                                              break;
                                            }    
                                         }
                                    int p1=x.pointx;
                                    int q1=x.pointy+35;
                                    int q2=x.pointy+62;
                                    int p3=x.pointx+120;
                                    int q3=x.pointy+50;
                                   for(int i=0;i<3;i++){
                                    for(Line x1:line)
                                    {
                                        int p=x1.pointx;
                                        int q=x1.pointy;
                                        int r=x1.pointx1;
                                        int s=x1.pointy1;
                                        if((p1-r)<=30 && (p1-r)>=0 && (q1-s)<=15 && (q1-s)>=0){
                                            line.remove(x1);
                                            break;
                                        }
                                        if((p1-r)<=30 && (p1-r)>=0 && (q2-s)<=15 && (q2-s)>=0){
                                           line.remove(x1);
                                           break;
                                        }
                                        if((p3-p)<=30 && (p3-p)>=0 && (q3-q)<=15 && (q3-q)>=0){
                                            line.remove(x1);
                                            System.out.println("3");
                                            break;
                                        }
                                    }
                                   }
                                         Gatenumber-=1;
                                         break;
                                 }
                        }
                       for(NotGate1 x:not){
                           if(x.pointx<=deletepointx && (x.pointx+120)>=deletepointx && x.pointy<=deletepointy && (x.pointy+100)>=deletepointy)
                           {
                                   int t=not.indexOf(x);
                                   int l=x.count;
                                   not.remove(x);
                                   for(int i=0;i<Gatenumber;i++)
                                   {
                                       if(Gatearraylist.get(i).Getpointx()<=deletepointx && (Gatearraylist.get(i).Getpointx()+120)>=deletepointx && (Gatearraylist.get(i).Getpointy()-35)<=deletepointy && (Gatearraylist.get(i).Getpointy()+65)>=deletepointy){
                                          Gatearraylist.remove(i);
                                          break;
                                       }    
                                    }
                                    int p1=x.pointx;
                                    int q1=x.pointy+50;
                                    int p3=x.pointx+120;
                                    int q3=x.pointy+50;
                                   for(int i=0;i<2;i++){
                                    for(Line x1:line)
                                    {
                                        int p=x1.pointx;
                                        int q=x1.pointy;
                                        int r=x1.pointx1;
                                        int s=x1.pointy1;
                                        if((p1-r)<=30 && (p1-r)>=0 && (q1-s)<=25 && (q1-s)>=0){
                                            line.remove(x1);
                                            break;
                                        }
                                        if((p3-p)<=30 && (p3-p)>=0 && (q3-q)<=25 && (q3-q)>=0){
                                            line.remove(x1);
                                            System.out.println("3");
                                            break;
                                        }
                                    }
                                  }
                                    Gatenumber-=1;
                                    break;
                           }
                       }
                }
                repaint();
          }

         @Override
         public void mousePressed(MouseEvent e) {
             if(andFlag==1)
             {
                 andpointx=e.getX();
                 andpointy=e.getY();
             }
             if(orFlag==1)
             {
                 orpointx=e.getX();
                 orpointy=e.getY();
             }
             if(notFlag==1){
                 notpointx=e.getX();
                 notpointy=e.getY();
             }
             if(outputFlag==1){
                 outputpointx[output]=e.getX();
                 outputpointy[output]=e.getY();
             }
             if(wireFlag==1)
             {
                 wirepointx=e.getX();
                 wirepointy=e.getY();
                 wirepointx1=e.getX();
                 wirepointy1=e.getY();
                 if(Gatearraylist.size()<1){
                   JOptionPane.showMessageDialog(null,"At least draw two gates");
                 }
                 else{
                  for(int j=0;j<Gatenumber;j++)
                            {   
                                double p,q,r,s;
                                p=(double)e.getX();
                                q=(double)Gatearraylist.get(j).Getpointx2();
                                r=(double)e.getY();
                                s=(double)Gatearraylist.get(j).Getpointy2();
                                s3=Gatearraylist.get(j).Getcount();
                                int z1=(int)Math.abs(p-q);
                                int z2=(int)Math.abs(r-s);
                                System.out.println(s3);
                                if(z1<=20 && z1>=0 && z2<=12 && z2>=0)
                                {
                                    if(array2[s3]==1)
                                    {
                                        wireStart=2;
                                        break;
                                    }
                                   array2[s3]=1;
                                   System.out.println(s3);
                                   wireStart=1;
                                   break;
                                }
                                
                            }
                           if(wireStart==0)
                           {
                              JOptionPane.showMessageDialog(null,"Draw line from Output pin"); 
                           }
                           if(wireStart==2)
                           {
                              JOptionPane.showMessageDialog(null,"Sorry you can't do it");  
                           }
                      }
                 }
               if(input0Flag==1)
                {
                    input0pointx[l]=e.getX();
                    input0pointy[l]=e.getY(); 
                }
                if(input1Flag==1)
                {
                    input1pointx[d]=e.getX();
                    input1pointy[d]=e.getY(); 
                }
             repaint();
         }

         @Override
         public void mouseReleased(MouseEvent e) {
           if(andFlag==1)
           {
               Gatenumber++;
               AndGate1 a=new AndGate1();
               a.pointx=andpointx;
               a.pointy=andpointy;
               a.count=Gatenumber;
               and.add(a);
               AndGate b=new AndGate();
               str[Gatenumber]="ANDGATE";
               b.pointx=andpointx;
               b.pointy=andpointy+35;
               b.pointx1=andpointx;
               b.pointy1=andpointy+62;
               b.pointx2=andpointx+120;
               b.pointy2=andpointy+50;
               b.count=Gatenumber;
               Gatearraylist.add(b);
           }
           if(orFlag==1)
           {
               Gatenumber++;
               OrGate1 o=new OrGate1();
               o.pointx=orpointx;
               o.pointy=orpointy;
               o.count=Gatenumber;
               or.add(o);
               OrGate o1=new OrGate();
               str[Gatenumber]="ORGATE";
               o1.pointx=orpointx;
               o1.pointy=orpointy+35;
               o1.pointx1=orpointx;
               o1.pointy1=orpointy+62;
               o1.pointx2=orpointx+120;
               o1.pointy2=orpointy+50;
               o1.count=Gatenumber;
               Gatearraylist.add(o1);
           }
           if(notFlag==1)
           {
               Gatenumber++;
               NotGate1 n=new NotGate1();
               n.pointx=notpointx;
               n.pointy=notpointy;
               n.count=Gatenumber;
               not.add(n);
               NotGate n1=new NotGate();
               str[Gatenumber]="NOTGATE";
               n1.pointx=notpointx;
               n1.pointy=notpointy+50;
               n1.pointx2=notpointx+120;
               n1.pointy2=notpointy+50;
               n1.count=Gatenumber;
               Gatearraylist.add(n1);
           }
           if(wireFlag==1 && wireStart==1)
           {
                int f=0,m=0,n=0;
                
                for(int j=0;j<Gatenumber;j++)
                {
                   double p,q,r,s;
                     p=(double)wirepointx;
                     q=(double)Gatearraylist.get(j).Getpointx2();
                     r=(double)wirepointy;
                     s=(double)Gatearraylist.get(j).Getpointy2();
                     m=Gatearraylist.get(j).Getcount();
                     int z1=(int)Math.abs(p-q);
                     int z2=(int)Math.abs(r-s);
                     if(z1<=25 && z1>=0 && z2<=10 && z2>=0)
                     {
                           
                            wirepointx1=Gatearraylist.get(j).Getpointx();
                            wirepointy1=Gatearraylist.get(j).Getpointy();
                            break;
                     } 
                }
                for(int j=0;j<Gatenumber;j++)
                {   
                     double p,q,r,s,q1,s1;
                     p=(double)e.getX();
                     q=(double)Gatearraylist.get(j).Getpointx();
                     r=(double)e.getY();
                     s=(double)Gatearraylist.get(j).Getpointy();
                     n=Gatearraylist.get(j).Getcount();
                     int z1=(int)Math.abs(p-q);
                     int z2=(int)Math.abs(r-s);
                     
                     if(z1<=25 && z1>=0 && z2<=10 && z2>=0 && array1[j][0]!=1)
                     {
                            f=1;
                            array1[j][0]=1;
                            wirepointx1=Gatearraylist.get(j).Getpointx();
                            wirepointy1=Gatearraylist.get(j).Getpointy();
                            break;
                     }
                     q1=(double)Gatearraylist.get(j).Getpointx1();
                     s1=(double)Gatearraylist.get(j).Getpointy1();
                     n=Gatearraylist.get(j).Getcount();
                     z1=(int)Math.abs(p-q1);
                     z2=(int)Math.abs(r-s1);
                     if(z1<=25 && z1>=0 && z2<=10 && z2>=0 && array1[j][1]!=1)
                     {
                            f=1;
                            array1[j][1]=1;
                            wirepointx1=Gatearraylist.get(j).Getpointx1();
                            wirepointy1=Gatearraylist.get(j).Getpointy1();
                            break;
                     }
                }
                double p1=(double)outputpointx[output]+40;
                double q1=(double)e.getX();
                double r1=(double)outputpointy[output]+90;
                double s1=(double)e.getY();
                int z1=(int)Math.abs(p1-q1);
                int z2=(int)Math.abs(r1-s1);
                if(z1<=50 && z1>=0 && z2<=20 && z2>=0)
                    {
                        f=2;
                        finaloutput=m;
                        wirepointx1=outputpointx[output]+40;
                        wirepointy1=outputpointy[output]+95;
                        System.out.println(wirepointx1+" "+wirepointy1);
                    }
                if(f!=2){
                    dfs1[m][n]=1;
                    dfs1[n][m]=1;
                }
                System.out.println(m+" "+n);
                wireStart=0;
                if(f==1 || f==2)
                {
                    Line l=new Line();
                    l.pointx=wirepointx;
                    l.pointy=wirepointy;
                    l.pointx1=wirepointx1;
                    l.pointy1=wirepointy1;
                    System.out.println(l.pointx1+" "+l.pointy1);
                    line.add(l);
                }
                else if(f==0){
                    wirepointx1=wirepointx;
                    wirepointy1=wirepointy;
                    array2[s3]=0;
                }
           }
            if(input0Flag==1)
                       {
                           //checking the parent for 0 input
                           for(int j=0;j<Gatenumber;j++)
                            {
                                double p,q,r,s,q1,s1;
                                p=(double)input0pointx[l]+(double)5;
                                q=(double)Gatearraylist.get(j).Getpointx();
                                r=(double)input0pointy[l]+(double)10;
                                s=(double)Gatearraylist.get(j).Getpointy();
                               System.out.print("Second: "+p+" "+r+" "+q+" "+s+" ");
                                int z1=(int)Math.abs(p-q);
                                int z2=(int)Math.abs(r-s);
                                LogicOutputer k2 = Gatearraylist.get(j);
                                if(z1<=20 && z1>=0 && z2<=10 && z2>=0)
                                {
                                   if(k2 instanceof AndGate)
                                   {
                                       AndGate g = (AndGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(false));
                                       int l2 = g.count;
                                       System.out.println("And Gate: "+l2);
                                   }
                                    if(k2 instanceof OrGate)
                                   {
                                       OrGate g=(OrGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(false));
                                       int l2 = g.count;
                                       System.out.println("Or Gate: "+l2);
                                   }
                                    if(k2 instanceof NotGate)
                                    {
                                        NotGate g=(NotGate) k2;
                                        g.setParentConnection(new DirectLogicOutputter(false));
                                        int l2 = g.count;
                                        System.out.println("Not Gate: "+l2);
                                    }
                                   break;
                                }
                               q1=(double)Gatearraylist.get(j).Getpointx1();
                                s1=(double)Gatearraylist.get(j).Getpointy1();
                                System.out.println(q1+" "+s1);
                                int z3=(int)Math.abs(p-q1);
                                int z4=(int)Math.abs(r-s1);
                                if(z3<=20 && z3>=0 && z4<=10 && z4>=0)
                                {
                                   if(k2 instanceof AndGate)
                                   {
                                       AndGate g = (AndGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(false));
                                       int l2 = g.count;
                                       System.out.println("And Gate: "+l2);
                                   }
                                    if(k2 instanceof OrGate)
                                   {
                                       OrGate g=(OrGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(false));
                                       int l2 = g.count;
                                       System.out.println("Or Gate: "+l2);
                                   }
                                    if(k2 instanceof NotGate)
                                    {
                                        NotGate g=(NotGate) k2;
                                        g.setParentConnection(new DirectLogicOutputter(false));
                                        int l2 = g.count;
                                        System.out.println("Not Gate: "+l2);
                                    }
                                   break;
                                }
                            }
                       }
                       if(input1Flag==1)
                       {
                           //checking the parent for 1 input
                           for(int j=0;j<Gatenumber;j++)
                            {
                                double p,q,r,s,q1,s1;
                                p=(double)input1pointx[d]+(double)5;
                                q=(double)Gatearraylist.get(j).Getpointx();
                                r=(double)input1pointy[d]+(double)10;
                                s=(double)Gatearraylist.get(j).Getpointy();
                                System.out.print("Second: "+p+" "+r+" "+q+" "+s+" ");
                                int z1=(int)Math.abs(p-q);
                                int z2=(int)Math.abs(r-s);
                                LogicOutputer k2 = Gatearraylist.get(j);
                                if(z1<=20 && z1>=0 && z2<=10 && z2>=0)
                                {
                                   if(k2 instanceof AndGate)
                                   {
                                       AndGate g = (AndGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(true));
                                       int l2 = g.count;
                                       System.out.println("And Gate: "+l2);
                                   }
                                    if(k2 instanceof OrGate)
                                   {
                                       OrGate g=(OrGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(true));
                                       int l2 = g.count;
                                       System.out.println("Or Gate: "+l2);
                                   }
                                    if(k2 instanceof NotGate)
                                    {
                                        NotGate g=(NotGate) k2;
                                        g.setParentConnection(new DirectLogicOutputter(true));
                                        int l2 = g.count;
                                        System.out.println("Not Gate: "+l2);
                                    }
                                   break;
                                }
                                q1=(double)Gatearraylist.get(j).Getpointx1();
                                s1=(double)Gatearraylist.get(j).Getpointy1();
                                System.out.println(q1+" "+s1);
                                int z3=(int)Math.abs(p-q1);
                                int z4=(int)Math.abs(r-s1);
                                if(z3<=20 && z3>=0 && z4<=10 && z4>=0)
                                {
                                   if(k2 instanceof AndGate)
                                   {
                                       AndGate g = (AndGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(true));
                                       int l2 = g.count;
                                       System.out.println("And Gate: "+l2);
                                   }
                                    if(k2 instanceof OrGate)
                                   {
                                       OrGate g=(OrGate) k2;
                                       g.addParentConnection(new DirectLogicOutputter(true));
                                       int l2 = g.count;
                                       System.out.println("Or Gate: "+l2);
                                   }
                                    if(k2 instanceof NotGate)
                                    {
                                        NotGate g=(NotGate) k2;
                                        g.setParentConnection(new DirectLogicOutputter(true));
                                        int l2 = g.count;
                                        System.out.println("Not Gate: "+l2);
                                    }
                                   break;
                                }
                            }
                       }
           repaint();
         }

         @Override
         public void mouseEntered(MouseEvent e) {
             
         }
         @Override
         public void mouseExited(MouseEvent e) {
             
         }

       }
  );
  
  this.addMouseMotionListener(new MouseMotionListener() {
         @Override
         public void mouseDragged(MouseEvent e) {
                        if(andFlag==1)
                        {
                            andpointx=e.getX();
                            andpointy=e.getY();
                        }
                        if(orFlag==1)
                        {
                            orpointx=e.getX();
                            orpointy=e.getY();
                        }
                        if(notFlag==1)
                        {
                            notpointx=e.getX();
                            notpointy=e.getY();
                        }
                        if(wireFlag==1 && wireStart==1)
                        {
                           wirepointx1=e.getX();
                           wirepointy1=e.getY();
                        }
                        if(outputFlag==1){
                            outputpointx[output]=e.getX();
                            outputpointy[output]=e.getY();
                        }
                          if(input0Flag==1)
                          {
                             input0pointx[l]=e.getX();
                             input0pointy[l]=e.getY(); 
                          }
                        if(input1Flag==1)
                          {
                             input1pointx[d]=e.getX();
                             input1pointy[d]=e.getY(); 
                          }
                        repaint();
                        
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void mouseMoved(MouseEvent e) {
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             
         }
     });
  
   
  
      J.add(this);
      //interface design
      
      ImageIcon icon=new ImageIcon("src\\dldsimulator\\images\\try1.jpg");
      AndButton=new JButton("          AND",icon);
      AndButton.setBounds(0,0,200,50);
      J.add(AndButton);
      
      ImageIcon icon1=new ImageIcon("src\\dldsimulator\\images\\try2.jpg");
      OrButton = new JButton("         OR",icon1);
      OrButton.setBounds(0,50,200,50);
      J.add(OrButton);
      
      ImageIcon icon2=new ImageIcon("src\\dldsimulator\\images\\try3.jpg");
      NotButton=new JButton("         NOT",icon2);
      NotButton.setBounds(0,100,200,50);
      J.add(NotButton);
      
      ImageIcon icon3=new ImageIcon("src\\dldsimulator\\images\\wire.jpg");
      WireButton=new JButton("         WIRE",icon3);
      WireButton.setBounds(0,180,200,50);
      J.add(WireButton);   
      
      ImageIcon icon4=new ImageIcon("src\\dldsimulator\\images\\0.jpg");
      input0Button=new JButton("         0",icon4);
      input0Button.setBounds(0,260,200,50);
      J.add(input0Button);
      
      ImageIcon icon5=new ImageIcon("src\\dldsimulator\\images\\1.jpg");
      input1Button=new JButton("         1",icon5);
      input1Button.setBounds(0,310,200,50);
      J.add(input1Button);
      
      evaluateButton=new JButton("Evaluate");
      evaluateButton.setBounds(0,390,200,50);
      J.add(evaluateButton);
      
      outputButton=new JButton("Output");
      outputButton.setBounds(0,440,200,50);
      J.add(outputButton);
      
      clearButton=new JButton("Clear");
      clearButton.setBounds(0,490,200,50);
      J.add(clearButton);
      
      ImageIcon icon6=new ImageIcon("src\\dldsimulator\\images\\Delete.jpg");
      deleteButton=new JButton("",icon6);
      deleteButton.setBounds(0,540,200,50);
      J.add(deleteButton);
      
      J.setLayout(null);
      J.setVisible(true);
         
      AndButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
             andpointx=2500;andpointy=2500;
             andFlag=1;
             deleteFlag=0;orFlag=0;notFlag=0;wireFlag=0; outputFlag=0;input0Flag=0;input1Flag=0;
          }
      });
     OrButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
             orpointx=2500;orpointy=2500;
             orFlag=1;
             andFlag=0;deleteFlag=0;notFlag=0;wireFlag=0; outputFlag=0;input0Flag=0;input1Flag=0;
          }
      });
   NotButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
            notpointx=2500;notpointy=2500;
            notFlag=1;
            andFlag=0;orFlag=0;deleteFlag=0;wireFlag=0; outputFlag=0;input0Flag=0;input1Flag=0;
          }
      });
   WireButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            wireFlag=1;
            wirepointx=2500;
            wirepointy=2500;
            wirepointx1=2500;
            wirepointy1=2500;
            input0Flag=0;input1Flag=0;andFlag=0;orFlag=0;deleteFlag=0;notFlag=0; outputFlag=0;
            repaint();
         }
       
   });
   input0Button.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            input0Flag=1;
            andFlag=0;orFlag=0;deleteFlag=0;notFlag=0; outputFlag=0;input1Flag=0;wireFlag=0;
            l++;
            input0pointx[l]=2500;
            input0pointy[l]=2500;
            repaint();
         }
       
   });
   input1Button.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
           input1Flag=1;
           andFlag=0;orFlag=0;deleteFlag=0;notFlag=0; outputFlag=0;input0Flag=0;wireFlag=0;evaluateFlag=0;
           d++;
           input1pointx[d]=2500;
           input1pointy[d]=2500;
            repaint();
         }
       
   });  
    evaluateButton.addActionListener(new ActionListener(){
         
        @Override
         public void actionPerformed(ActionEvent e) {
             evaluateFlag=1;
             andFlag=0;orFlag=0;deleteFlag=0;notFlag=0; outputFlag=0;input0Flag=0;wireFlag=0;input1Flag=0;
            System.out.println(and.size()+" "+Gatearraylist.size());
            System.out.println("Final: "+finaloutput);
         /*   for(int i=0;i<Gatenumber;i++)
            {
                System.out.println(Gatearraylist.get(i).Getpointx()+" "+Gatearraylist.get(i).Getpointy()+" "+Gatearraylist.get(i).Getpointx1()+" "+Gatearraylist.get(i).Getpointy1()+" "+Gatearraylist.get(i).Getpointx2()+" "+Gatearraylist.get(i).Getpointy2());
            }*/
            int stack[]=new int[1000],count1=0,i=0;
            stack[i++]=finaloutput;
            visited[finaloutput]=1;
            count1++;
            while(count1!=0)
            {
                int u=stack[i-1];
                i--;
                count1--;
                k1=Gatearraylist.get(u-1);
                System.out.println(u+": ");
                for(int j=1;j<=Gatenumber;j++)
                {
                    if(dfs1[u][j]==1 && visited[j]==0){
                        System.out.print(j+" ");
                        k2=Gatearraylist.get(j-1);
                        stack[i++]=j;
                        visited[j]=1;
                        count1++;
                        if(str[u]=="ANDGATE"){
                          //  System.out.print(str[u]+" ");
                            AndGate g = (AndGate) k1;
                            g.addParentConnection(k2);
                        }
                        if(str[u]=="ORGATE"){
                            //System.out.print(str[u]+" ");
                            OrGate g=(OrGate) k1;
                            g.addParentConnection(k2);
                        }
                        if(str[u]=="NOTGATE"){
                          //  System.out.print(str[u]+" ");
                            NotGate g=(NotGate) k1;
                            g.setParentConnection(k2);
                        }
                    }
                }
                System.out.println();
            }
          //  finaloutput=2;
          /*  LogicOutputer k3=Gatearraylist.get(0);
            AndGate g=(AndGate) k3;
            g.addParentConnection(new DirectLogicOutputter(false));
            g.addParentConnection(new DirectLogicOutputter(true));*/
            System.out.println(finaloutput+" "+Gatearraylist.get(finaloutput-1).output()+" "+evaluateFlag);
            repaint();
         }   
   });
    outputButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
             output++;
             outputFlag=1;
             andFlag=0;orFlag=0;deleteFlag=0;notFlag=0;wireFlag=0;input0Flag=0;input1Flag=0;
             outputpointx[output]=2500;
             outputpointy[output]=2500;
             repaint();
         } 
    });
    
    clearButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
             and.removeAll(and);
             or.removeAll(or);
             not.removeAll(not);
             line.removeAll(line);
             Gatearraylist.removeAll(Gatearraylist);
             Gatenumber=0;
             andFlag=0;
             orFlag=0;
             notFlag=0;
             wireFlag=0;
             outputFlag=0;
             output=0;
             input0Flag=0;
             input1Flag=0;
             evaluateFlag=0;
             l=0;
             d=0;
             for(int i=0;i<100;i++)
                 visited[i]=0;
             for(int i=0;i<100;i++)
             {
                 for(int j=0;j<2;j++)
                     array1[i][j]=0;
             }
             for(int i=0;i<100;i++)
             {
                 for(int j=0;j<100;j++)
                     dfs1[i][j]=0;
             }
             for(int i=0;i<100;i++)
                 array2[i]=0;
             for(int i=0;i<10;i++)
                 outputCheck[i]=0;
             repaint();
         }
        
    });
    
    deleteButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            deleteFlag=1;
            andFlag=0;orFlag=0;notFlag=0;wireFlag=0;outputFlag=0;input0Flag=0;input1Flag=0;
         }
        
    });
   }
     
    public void paint(Graphics g){
      g.drawLine(0,0,1200,0);
      g.drawLine(0,0,0,700);
      g.drawLine(0,660,1150,660);
      
       if(output>=1)
      {
          ImageIcon m;
          Image n;
          for(int i=1;i<=output;i++){
             if(outputCheck[i]==1){
                 m = new ImageIcon("src\\dldsimulator\\images\\output1.jpg");
                 n = m.getImage();
             }
             else{
               m = new ImageIcon("src\\dldsimulator\\images\\output0.jpg");
               n = m.getImage();  
             }
             g.drawImage(n,outputpointx[i],outputpointy[i],null);
          }
      }
      if(evaluateFlag==1)
      {
          if(Gatearraylist.get(finaloutput-1).output())
            {
                ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\output1.jpg");
                Image n = m.getImage();
                int p=outputpointx[output];
                int p1=outputpointy[output];
                outputCheck[output]=1;
                g.drawImage(n,p,p1,null);
            }
          if(!Gatearraylist.get(finaloutput-1).output())
            {
                ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\output0.jpg");
                Image n = m.getImage();
                int p=outputpointx[output];
                int p1=outputpointy[output];
                outputCheck[output]=0;
                g.drawImage(n,p,p1,null);
            }
      }     
      //Works of AndGate
      if(andFlag==1)
      {
          ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\AndGate.jpg");
          Image n = m.getImage();
          g.drawImage(n,andpointx,andpointy,null);
      }
      for(AndGate1 x:and)
      {
         ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\AndGate.jpg");
         Image n = m.getImage();
         g.drawImage(n,x.pointx,x.pointy,null);
      }
     if(andFlag==1)
     {
         g.setColor(Color.blue);
         g.drawRect(andpointx,andpointy,120,100);
     }
     
     //Works of OrGate
     if(orFlag==1)
     {
          ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\OrGate.jpg");
          Image n = m.getImage();
          g.drawImage(n,orpointx,orpointy,null);
     }
     for(OrGate1 x:or)
     {
         ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\OrGate.jpg");
         Image n = m.getImage();
         g.drawImage(n,x.pointx,x.pointy,null);
     }
     if(orFlag==1)
     {
         g.setColor(Color.blue);
         g.drawRect(orpointx,orpointy,120,100);
     }
     
     //works of NotGate
     if(notFlag==1)
     {
         ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\NotGate.jpg");
         Image n = m.getImage();
         g.drawImage(n,notpointx,notpointy,null);
     }
     for(NotGate1 x:not){
         ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\NotGate.jpg");
         Image n = m.getImage();
         g.drawImage(n,x.pointx,x.pointy,null);
     }
     if(notFlag==1){
         g.setColor(Color.BLUE);
         g.drawRect(notpointx,notpointy,120,100);
     }
     
     //Works of Wire
     if(wireFlag==1)
     {
         Graphics2D g2 = (Graphics2D) g;
         g2.setStroke(new BasicStroke(5));
         g2.setColor(Color.red);
         g2.drawLine(wirepointx,wirepointy,wirepointx1,wirepointy1);
     }
     for(Line x:line)
     {
         Graphics2D g2 = (Graphics2D) g;
         g2.setStroke(new BasicStroke(5));
         g2.setColor(Color.red);
         g2.drawLine(x.pointx,x.pointy,x.pointx1,x.pointy1);
     }
     
     //Input related
     if(l>=1)
      {
         
          ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\0.jpg");
          Image n = m.getImage();
          for(int i=1;i<=l;i++)
          g.drawImage(n,input0pointx[i],input0pointy[i],null);
          
          
      }
    if(d>=1)
      {
         
          ImageIcon m = new ImageIcon("src\\dldsimulator\\images\\1.jpg");
          Image n = m.getImage();
          for(int i=1;i<=d;i++)
          g.drawImage(n,input1pointx[i],input1pointy[i],null);    
      }
   }
}
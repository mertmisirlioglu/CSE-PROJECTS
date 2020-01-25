/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject2;

import java.util.Scanner;

/**
 *
 * @author Mert
 */
public class Gameproject2 {
    

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Welcome to 'Tahini : The Greatest Lover' game");
        System.out.println("Please enter to any charecter for contunie...");
        String contunie= input.next();
        System.out.println(" One day our main character wakes up in a kitchen "
               +"that he has never seen before.  When he wakes up, he realize\n"
               +" that the  only love of him life  boiled grape juice is "
               +"without him. The only goal in this kitchen is finding to\n"
               +" boiled grape juice. But its terrible enemies jam, nutella,"
               +"butter,peanut butter and olive will not allows it. Weapons\n "
               +"are only spoons and forks. Thanks to the little notes from "
               +"boiled grape  juice leaves around, our hero will find to\n "
               +"his great love..");
        System.out.println("\n\nAre you ready for the start?");
        System.out.println("If your answer is yes please enter to any charecter.");
        System.out.println("If your answer is no, you can wait until the when you get ready.");
        String start= input.next();
       
        int moves =0;
        int health=20;
        boolean endHealth = false;
        int spoon=0;
        int fork=0;
        boolean emptyroomcheck= false;
        
        int x=(int)(Math.random()*11);
        int y=(int)(Math.random()*11);
        
        System.out.println("\nYou are on "+ x +","+y+" now. You can go between "
                + "0,0 and 10,10 . If you want to go up you can \npress '8' , "
                + "'2' for down , '6' for right and '4' for left. You should "
                + "be careful because you dont know  what you will encounter."
                + "\nWelcome to the world of Tahini's. ");
        
        long StartTime= System.currentTimeMillis();
        long EndTime= 0;
        long totaltime= 0;
        boolean finishtime=false;
        
        int gift1x=(int)(Math.random()*11);
        int gift1y=(int)(Math.random()*11);
        int gift2x=(int)(Math.random()*11);
        int gift2y=(int)(Math.random()*11);
        
        int monster1x=(int)(Math.random()*11);
        int monster1y=(int)(Math.random()*11);
        int monster2x=(int)(Math.random()*11);
        int monster2y=(int)(Math.random()*11);
        int monster3x=(int)(Math.random()*11);
        int monster3y=(int)(Math.random()*11);
        int monster4x=(int)(Math.random()*11);
        int monster4y=(int)(Math.random()*11);
        int monster5x=(int)(Math.random()*11);
        int monster5y=(int)(Math.random()*11);
        
        int exitx=(int)(Math.random()*11);
        int exity=(int)(Math.random()*11);
        
        while ( x!=exitx || y!=exity  && !endHealth && totaltime<=179 ){
            
            System.out.println("Make your move!");
            moves=input.nextInt();
            
            if (moves==8){
                y++;
                 if (y>10){
                       y=10;
                        System.out.println("You can not go any further up.");
                    }                 
                      }
            if (moves==2){
                  y--; 
                  if (y<0){
                       y=0;
                        System.out.println("You cant go more down.");
                    }
                        }             
            if (moves==4){
                   x--; 
                   if (x<0){
                    x=0;
                    System.out.println("You cant go more left.");
                    }
                     }

            if (moves==6){
                   x++; 
                    if (x>10){
                       x=10;
                        System.out.println("You cant go more left.");
                    }
                } 
            if (x==gift1x && y==gift1y){ 
                System.out.println("You won a spoon.(weapon)");
                spoon=1;
                emptyroomcheck=true;                  
                 }
                     else if (x==gift2x && y==gift2y){
                System.out.println("You won a fork.(weapon)");
                fork=1;
                    emptyroomcheck=true;
                     }
                     else if (x==monster1x && y==monster1y){
                System.out.println("Your great enemy is here. The Jam. Hope you have a weapon.");
                emptyroomcheck=true;
                if(spoon==1 && fork==1){
                    System.out.println("You have two weapons so that you lost only 2 health point.");
                    health=health-2;
                    
                }
                else if(spoon==0 && fork==1){
                    System.out.println("You have only fork so that you lost  5 health point.");
                    health=health-5;
                    
                }
                else if(spoon==1 && fork==0){
                    System.out.println("You have only spoon so that you lost  3 health point.");
                    health=health-3;
                    
                }
                else if(spoon==0 && fork==0){
                    System.out.println("You haven't any weapon so that you lost  7 health point.");
                    health=health-7;                    
                }                
                     }
                     else if (x==monster2x && y==monster2y){
                System.out.println("Your great enemy is here. The Nutella. Hope you have a weapon.");
                emptyroomcheck=true;
                if(spoon==1 && fork==1){
                    System.out.println("You have two weapons so that you lost only 2 health point.");
                    health=health-2;                    
                }
                else if(spoon==0 && fork==1){
                    System.out.println("You have only fork so that you lost  3 health point.");
                    health=health-3;                    
                }
                else if(spoon==1 && fork==0){
                    System.out.println("You have only spoon so that you lost  6 health point.");
                    health=health-6;                    
                }
                else if(spoon==0 && fork==0){
                    System.out.println("You haven't any weapon so that you lost  8 health point.");
                    health=health-8;                    
                }
                     }
                     else if (x==monster3x && y==monster3y){
                System.out.println("Your great enemy is here. The Butter. Hope you have a weapon.");
                emptyroomcheck=true;
                if(spoon==1 && fork==1){
                    System.out.println("You have two weapons so that you lost only 1 health point.");
                    health=health-1;                    
                }
                else if(spoon==0 && fork==1){
                    System.out.println("You have only fork so that you lost  3 health point.");
                    health=health-3;                    
                }
                else if(spoon==1 && fork==0){
                    System.out.println("You have only spoon so that you lost  5 health point.");
                    health=health-5;                    
                }
                else if(spoon==0 && fork==0){
                    System.out.println("You haven't any weapon so that you lost  8 health point.");
                    health=health-8;                   
                }
                     }
                     else if (x==monster4x && y==monster4y){
                System.out.println("Your great enemy is here. The Peanut butter. Hope you have a weapon.");
                emptyroomcheck=true;
                if(spoon==1 && fork==1){
                    System.out.println("You have two weapons so that you lost only 3 health point.");
                    health=health-3;                    
                }
                else if(spoon==0 && fork==1){
                    System.out.println("You have only fork so that you lost  5 health point.");
                    health=health-5;                    
                }
                else if(spoon==1 && fork==0){
                    System.out.println("You have only spoon so that you lost  6 health point.");
                    health=health-6;                   
                }
                else if(spoon==0 && fork==0){
                    System.out.println("You haven't any weapon so that you lost  9 health point.");
                    health=health-9;                   
                }
                     }
                     else if (x==monster5x && y==monster5y){
                System.out.println("Your great enemy is here. The Olive. Hope you have a weapon.");
                emptyroomcheck=true;
                if(spoon==1 && fork==1){
                    System.out.println("You have two weapons so that you lost only 2 health point.");
                    health=health-2;                   
                }
                else if(spoon==0 && fork==1){
                    System.out.println("You have only fork so that you lost  3 health point.");
                    health=health-3;                  
                }
                else if(spoon==1 && fork==0){
                    System.out.println("You have only spoon so that you lost  3 health point.");
                    health=health-3;                  
                }
                else if(spoon==0 && fork==0){
                    System.out.println("You haven't any weapon so that you lost  4 health point.");
                    health=health-4;                  
                }               
                     }
                      if (x==exitx && y==exity){
                System.out.println("You have reached the love of your life. Tahini and Boiled grape juice live happily ever after. \nGAME END");
                      emptyroomcheck=true;
                      }
                                         
            if (health<=0){
                     endHealth= true;   
                    break;
            }        
            EndTime= System.currentTimeMillis();
            totaltime= (EndTime-StartTime)/1000;
            if (totaltime>=180){
                finishtime=true;
                 break;
           }
            if (x!=exitx || y!=exity){
            System.out.println("\nYour coordinates are : " +x+","+y+ "            Your health point is: "+health 
              +"           Remaining time is: "+(180-totaltime));
            }
             if (emptyroomcheck==false){
                          System.out.println("This room is empty,please go on.");
                         
                      }
             if (emptyroomcheck==true){
                 emptyroomcheck=false;
             }          
   }                   
       if (totaltime>179)
        System.out.println("YOUR TİME İS UP \nGAME OVER");
       if (health<=0)
            System.out.println("YOUR HEALTH İS OVER \nGAME OVER");
            }
           }
            
            
        
         
   
        


package com.snakeladder;

import java.util.Scanner;
class playerPos{
    public int position,no_die_rolls;
    playerPos(){
        this.position=0;
        this.no_die_rolls=0;
    }
}
class Board_ele{
    public int cur_value,ladder_val,snake_value;
    public boolean is_ladder,is_snake;
    Board_ele(int cur_value,int ladder_val,int snake_value,boolean is_ladder,boolean is_snake){
        this.cur_value=cur_value;
        this.ladder_val=ladder_val;
        this.snake_value=snake_value;
        this.is_ladder=is_ladder;
        this.is_snake=is_snake;
    }
    public void print_prop(){
        System.out.println("object properties");
        System.out.println("Element position "+this.cur_value);
        System.out.println("Element ladder value "+this.ladder_val);
        System.out.println("Element snake value "+this.snake_value);
        System.out.println("Element has ladder : "+is_ladder);
        System.out.println("Element has snake : "+is_snake);
    }
}
public class SnakeLadder{
    static boolean checkwinner(int players_position){
        if(players_position>=19){
            return true;
        }
        else{
            return false;
        }
    }
    static void play(int i,playerPos[] ply,Board_ele[] ele){
        System.out.println("player "+(i+1)+" is at");
        ele[ply[i].position].print_prop();
        int dice = (int) (Math.floor(Math.random()*10)%6);
        if(ply[i].position+dice<=19){
            ply[i].position+=dice;
            ply[i].no_die_rolls+=1;
            if(ele[ply[i].position].is_ladder){
                play(i, ply, ele);
            }
            else if(ele[ply[i].position].is_snake){
                ply[i].position-=ele[ply[i].position].snake_value;
            }
            else{
            //do nothing 
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to snake n ladder");
        Board_ele[] ele=new Board_ele[100];
        for(int i=0;i<5;i++){
            ele[i]=new Board_ele(i, 0, 0, false, false);
        }
        for(int i=95;i<100;i++){
            ele[i]=new Board_ele(i, 0, 0, false, false);
        }
        for(int i=5;i<95;i++){
            int bd_ele_prop=(int) (Math.floor(Math.random()*10)%3);
            if(bd_ele_prop==0){
                ele[i]=new Board_ele(i, 0, 0, false, false);
            }
            else if(bd_ele_prop==1){
                int lad_val;
                while(true){
                    lad_val= (int) (Math.floor(Math.random()*100)%100);
                    if(lad_val>i+1){
                        break;
                    }
                    else{
                        continue;
                    }
                }
                ele[i]=new Board_ele(i,lad_val, 0, true, false);
            }
            else{
                int snake_val;
                while(true){
                    snake_val= (int) (Math.floor(Math.random()*100)%100);
                    if(snake_val<i){
                        break;
                    }
                    else{
                        continue;
                    }
                }
                ele[i]=new Board_ele(i, 0, snake_val, false, true);
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Players");
        int n=sc.nextInt();
        sc.close();
        //int position[]=new int[n];
        playerPos[] ply=new playerPos[n];
        for(int j=0;j<n;j++){
            ply[j]=new playerPos();
        }
        while(true){
            for(int i=0;i<n;i++){
                play(i,ply,ele);
                System.out.println("player "+(i+1)+" is at");
                ele[ply[i].position].print_prop();
                if(checkwinner(ply[i].position)){
                    System.out.println("player "+(i+1)+" is the winner");
                    System.out.println("number of times dice is rolled :"+ply[i].no_die_rolls);
                    System.exit(0);
                }
            }
        }
    }
}
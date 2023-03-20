/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject;

/**
 *
 * @author USER
 */
class Cards{
    public static Boolean cardused[][]=new Boolean [4][13];
    Cards()
    {
        int i,j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 13; j++) {
                cardused[i][j] = false;
            }
        }
    }
    public static Boolean useCard(int suit,int number)
    {
        if(cardused[suit][number]==false)
        {
            cardused[suit][number]=true;
            return true;
        }
        else return false;
    }
    public void reUseCards()
    {
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<13;j++)
            {
                cardused[i][j] = false;
            }
        }
    }
    
}

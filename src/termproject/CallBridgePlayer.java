/*
 * We need to replace the scanner
 */
package termproject;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author USER
 */
class CallBridgePlayer extends Cards{
    private int myTotalCards,calls,stakes,points;
    private Socket connectionSocket;
    private int cards[]=new int[13];
    private Boolean playable[]=new Boolean[13];
    private Boolean first_mover;
    private Boolean is_human_player;
    Scanner input= new Scanner(System.in);
    CallBridgePlayer() {
//        this.connectionSocket = connectionSocket;
        myTotalCards=0;
        calls=0;
        stakes=0;
        points=0;
        first_mover=false;
        int i,j;
        for(j=0;j<13;j++){
                playable[j]=false;
        }
    }

    public CallBridgePlayer(Boolean is_human_player) {
        this.is_human_player = is_human_player;
        myTotalCards=0;
        calls=0;
        stakes=0;
        points=0;
        first_mover=false;
        int i,j;
        for(j=0;j<13;j++){
                playable[j]=false;
        }
    }
    public Boolean IsHumanPlayer()
    {
        return is_human_player;
    }
    public void clear_for_current_game()
    {
        myTotalCards=0;
        calls=0;
        stakes=0;
        first_mover=false;
        int j;
        for(j=0;j<13;j++){
                playable[j]=false;
        }
    }
    public void clear_for_new_game()
    {
        myTotalCards=0;
        calls=0;
        stakes=0;
        points=0;
        first_mover=false;
        int j;
        for(j=0;j<13;j++){
                playable[j]=false;
        }
    }
//    public Socket getSocket()
//    {
//        return connectionSocket;
//    }
    public void first_mover()
    {
        first_mover=true;
    }
    public void updatePoints()
    {
        if(stakes>=calls*2 || stakes<calls) points=points-calls;
        else points=points+calls;
    }
    public int getPoints()
    {
        return points;
    }
    public void receivesACard(int suit,int number)
    {
        if(myTotalCards==13) return;
        cards[myTotalCards]=suit*13+number;
        playable[myTotalCards]=true;
        myTotalCards++;
        if(myTotalCards==13)
        {
            int i,j,swap;
            for(i=0;i<13;i++)
            {
                for(j=i;j>0&&cards[j]<cards[j-1];j--)
                {
                    swap=cards[j];
                    cards[j]=cards[j-1];
                    cards[j-1]=swap;
                }
            }
        }
    }
    public int []getAllCards()
    {
        return cards;
    }
    public Boolean setCall(int c)
    {
        if(c<1||c>13) return false;
        else
        {
            calls=c;
            return true;
        }
    }
    public int[] currentlyAvailableCards() {
        int truecnt = 0;
        for (int i = 0; i < 13; i++) {
            if (playable[i]) {
                truecnt++;
            }
        }
        int myhand[] = new int[truecnt];
        for (int i = 0, j = 0; j < truecnt; i++) {
            if (playable[i]) {
                myhand[j++] = cards[i];
            }
        }
        return myhand;
    }
    
//    public int currentCardsOnPlay(int []curcards)
//    {
//        
//    }
    
    
    public int setCall()
    {
        int call;

        int spadecnt = 0, acecnt = 0, kingcnt = 0, queencnt = 0;

        for (int x : currentlyAvailableCards()) {
            if (x / 13 == 0) {
                spadecnt++;
            } else if (x % 13 == 0) {
                acecnt++;
            } else if (x % 13 == 1) {
                kingcnt++;
            } else if (x % 13 == 2) {
                queencnt++;
            }
        }

        call = max((spadecnt) / 3 + (acecnt + kingcnt + queencnt) / 2 + 1, 2);
        call = min(call, 6);
        calls=call;
        return calls;
    }
    public int Calls()
    {
        return calls;
    }
    public int Stakes()
    {
        return stakes;
    }
    public Boolean is_valid_move(int index,int suit)
    {
        if(cards[index]/13==suit) return true;
        Boolean result= true;
        int i;
        for(i=0;i<13;i++)
        {
            if(playable[i]&&cards[i]/13==suit)
            {
                result=false;
                break;
            }
        }
        return result;
    }
    public int moveTheCard(int index)
    {
        playable[index]=false;
        if(first_mover)
        {
            first_mover=false;
        }
        return cards[index];
    }
    public int moveCard(int curcards[])
    {
        for(int i=0; i<curcards.length; i++){
            int val = curcards[i];
            cardused[val/13][val%13]=true;
        }
        int tmp[] = currentlyAvailableCards();
        int count=0;
        for(int i=0; i<tmp.length; i++){
            if(!cardused[tmp[i]/13][tmp[i]%13]) count++;
        }
        int myhand[] = new int[count];
        for(int i=0, k=0; i<tmp.length; i++){
            if(!cardused[tmp[i]/13][tmp[i]%13]) myhand[k++]=tmp[i];
        }
        
        int cnt[] = new int[4];
        int len = myhand.length;
        int curlen = curcards.length;
        for (int i = 0; i < myhand.length; i++) {
            cnt[myhand[i] / 13]++;
        }

        if (curlen != 0) {
            int bigest=51;
            int smolestforme = 0;
            int smolestnonspade=0;
            
            for(int i=0; i<len; i++){
                if(smolestforme%13<myhand[i]%13)
                    smolestforme =  myhand[i];
                if(myhand[i]/13!=0 && smolestnonspade%13<myhand[i]%13)
                    smolestnonspade = myhand[i];
            }
            
            int currentsuit = curcards[0] / 13;
            for (int i = 0; i < curlen; i++) {
                bigest = min(bigest, curcards[i]);
            }

            if (curlen == 3) {
                if (cnt[currentsuit] == 0){
                    if(cnt[0]==0) return smolestforme;
                    else {
                        for (int i = len - 1; i >= 0; i--) {
                            if (myhand[i] < bigest && myhand[i]/13==0) {
                                return myhand[i];
                            }
                        }
                        for (int i = len - 1; i >= 0; i--) {
                            if (myhand[i]/13==currentsuit) {
                                return myhand[i];
                            }
                        }
                    }
                }
                
                else{
                    if(bigest/13!=0){
                        int largestinsuit=51;
                        for(int i=0; i<curlen; i++){
                            if(curcards[i]/13==currentsuit) 
                                largestinsuit=min(largestinsuit, curcards[i]);
                        }
                        
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit && myhand[i]<largestinsuit){
                                return myhand[i];
                            }
                        }
                        
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit){
                                return myhand[i];
                            }
                        }
                        
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==0){
                                return myhand[i];
                            }
                        }
                        
                        return smolestforme;
                    }
                    else{
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit) return myhand[i];
                        }
                        
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==0 && myhand[i]<bigest) return myhand[i];
                        }
                        
                        if(smolestforme/13!=0) return smolestforme;
                        else return smolestnonspade;
                    }
                }
                
            } else if (curlen >= 1) {

                if (cnt[currentsuit] == 0) {
                    if (bigest / 13 == 0) {
                        for (int i = len - 1; i >= 0; i--) {
                            if (myhand[i] < bigest) {
                                return myhand[i];
                            }
                        }
                        return smolestforme;
                        
                    } else {
                        for (int i = len - 1; i >= 0; i--) {
                            if (myhand[i] / 13 == 0) {
                                return myhand[i];
                            }
                        }
                        return smolestforme;
                    }
                } else {
                    if(bigest/13==0 && currentsuit!=0){
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit) return myhand[i];
                        }
                    }
                    
                    if(bigest/13==0 && currentsuit==0){
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit && myhand[i]<bigest) return myhand[i];
                        }
                        
                        for(int i=len-1; i>=0; i--){
                            if(myhand[i]/13==currentsuit) return myhand[i];
                        }
                    }
                    
                    for (int i = len - 1; i >= 0; i--) {
                        if (myhand[i] / 13 == currentsuit) {
                            int k = myhand[i] % 13;
                            Boolean willbeplayed = true;
                            for (int j = k - 1; j >= 0; j--) {
                                if (!cardused[currentsuit][j]) {
                                    willbeplayed = false;
                                }
                            }
                            for (int m = curlen - 1; m >= 0; m--) {
                                if (curcards[m] / 13 == currentsuit && k < curcards[m] % 13) {
                                    willbeplayed = false;
                                }
                            }

                            if (willbeplayed) {
                                return myhand[i];
                            }
                            if(myhand[i]%13==0) return myhand[i];
                        }
                    }

                    for (int i = len - 1; i >= 0; i--) {
                        if (myhand[i] / 13 == currentsuit) {
                            return myhand[i];
                        }
                    }

                }
            }
        } else {
            if (len > 5) {
                int chosen = 0;
                if (cnt[1] == 1) {
                    chosen = 1;
                }
                if (cnt[2] == 1) {
                    chosen = 2;
                }
                if (cnt[3] == 1) {
                    chosen = 3;
                }

                if (chosen != 0) {
                    for (int i = 0; i < len; i++) {
                        if (myhand[i] / 13 == chosen) {
                            return myhand[i];
                        }
                    }
                }
            }

            for (int i = 0; i < myhand.length; i++) {
                int s = myhand[i] / 13;
                int num = myhand[i] % 13;

                Boolean largerfound = false;
                for (int j = num - 1; j >= 0; j--) {
                    if (!cardused[s][j]) {
                        largerfound = true;
                    }
                }

                if (!largerfound) {
                    return myhand[i];
                }
            }

            return myhand[len - 1];
        }

        return myhand[len - 1];
    }
    public void getStake()
    {
        stakes++;
    }
    
    public Boolean isPlayable(int index){
        return playable[index];
    }
    
}

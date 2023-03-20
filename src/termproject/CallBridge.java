package termproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
class CallBridge extends Cards implements Runnable{
    private CallBridgePlayer players[] ;
    private int current_round_value[]=new int[4];
//    private BufferedReader inFromClient;
//    private PrintWriter outToClient2;
    private Random rand = new Random();
    private int First_mover;
    public Scanner input = new Scanner(System.in);
    private File ServerToConsole,ConsoleToServer;
    private BufferedReader inFromClient;
    private PrintWriter outToClient2;
    private int total_points;
    public CallBridge(File IF,File OF) throws FileNotFoundException{
        //final Formatter file_creator = new Formatter(OF+".txt");
        ServerToConsole = OF;
        //final Formatter file_creator2 = new Formatter(IF+".txt");
        ConsoleToServer = IF;
        inFromClient = new BufferedReader(new FileReader(ServerToConsole));
        outToClient2 = new PrintWriter(ConsoleToServer);
    
       CallBridgePlayer Cplayers[]={new CallBridgePlayer(true),new CallBridgePlayer(false),new CallBridgePlayer(false),new CallBridgePlayer(false),};
       players=Cplayers;
       total_points = 10;
       System.out.println("Entered constructor");
       System.gc();
    }
    public void CardPlayedBy(int playerIndex,int suit) throws IOException
    {
        
        int cardIndex,card;
//        inFromClient = new BufferedReader(new InputStreamReader(players[playerIndex].getSocket().getInputStream())); 
//        outToClient2 = new PrintWriter(players[playerIndex].getSocket().getOutputStream());
//        System.gc();
        System.out.println("current suit = "+ suit );
        do
        {
            
            int inputcard;
            Scanner input = new Scanner(System.in);
            inputcard= input.nextInt();
            System.out.println(inputcard);
//            outToClient2.println("Make your move");
//            outToClient2.flush();
//            String CardInfo = inFromClient.readLine();
//            String strings[]=CardInfo.split("#");
//            cardIndex=Integer.parseInt(strings[0]);
//            System.out.println("cardIndex="+cardIndex);
//            System.gc();
            cardIndex = inputcard;
        }while(!players[playerIndex].is_valid_move(cardIndex, suit));
        
//        outToClient2.println("Moved!");
//        outToClient2.flush();
//        System.gc();
        card = players[playerIndex].moveTheCard(cardIndex);
        useCard(card/13, card%13);
        
        int i;
        current_round_value[playerIndex]= card;
        for(i=1;i<4;i++)
        {
//            outToClient2 = new PrintWriter(players[(playerIndex+i)%4].getSocket().getOutputStream());
            int CardIndexOnUserEnd = ((cardIndex - i*13)%52 + 52)%52;
//            String CardInfo =CardIndexOnUserEnd+ "#" + card;
//            outToClient2.println(CardInfo);
//            outToClient2.flush();
        }
    }
    public void CardPlayedBy(int playerIndex) throws IOException
    {
        int cardIndex,card=0;
        String CardInfo;
//        inFromClient = new BufferedReader(new InputStreamReader(players[playerIndex].getSocket().getInputStream())); 
//        outToClient2 = new PrintWriter(players[playerIndex].getSocket().getOutputStream());
        
        do{
            //System.out.println("Try to make first move");
    //        System.gc();
            outToClient2.println("Make your move");
            outToClient2.flush();
            while(!inFromClient.ready()) continue;
            
            System.out.println("ready fot intput");
            CardInfo = inFromClient.readLine();
            Scanner input = new Scanner(System.in);
    //        CardInfo = input.nextLine();
            String strings[]=CardInfo.split("#");
            cardIndex=Integer.parseInt(strings[0]);
        }while(!players[playerIndex].isPlayable(cardIndex)||(current_round_value[First_mover]!=-1&&!players[playerIndex].is_valid_move(cardIndex, current_round_value[First_mover]/13)));

//        System.out.println(CardInfo);
        //System.out.println("Moved");
            outToClient2.println("Moved!");
            outToClient2.flush();
            String strings[]=CardInfo.split("#");
            cardIndex=Integer.parseInt(strings[0]);
//        System.out.println("cardIndex="+cardIndex);
        System.gc();
        card = players[playerIndex].moveTheCard(cardIndex);
        outToClient2.println(cardIndex+"#"+card);
            outToClient2.flush();
        useCard(card/13, card%13);
        //System.out.println("Card is found");
        int i;
        current_round_value[playerIndex]= card;
        /*for(i=1;i<4;i++)
        {
//            PrintWriter outToClient2 = new PrintWriter(players[(playerIndex+i)%4].getSocket().getOutputStream());
            int CardIndexOnUserEnd = ((cardIndex - i*13)%52 + 52)%52;
            CardInfo =CardIndexOnUserEnd+ "#" + card;
//            System.out.println(CardInfo);
//            outToClient2.println(CardInfo);
//            outToClient2.flush();
        }*/
    }
    public void divideTheCards()
    {
        int searchCard=rand.nextInt(13),i,j;
        for(i=0;i<4;i++)
        {
            searchCard=rand.nextInt(13);
            while(!useCard(0,searchCard)) searchCard=rand.nextInt(13);
            try
            {
                players[i].receivesACard(0,searchCard);
            }
            catch(NullPointerException ne)
            {
                //System.out.println("A null pointer exception during dividing the cards");
            }
        }
        for(i=0;i<12;i++)
        {
            for(j=0;j<4;j++)
            {
                searchCard=rand.nextInt(52);
                while(!useCard(searchCard/13,searchCard%13)) searchCard=rand.nextInt(52);
                players[j].receivesACard(searchCard/13,searchCard%13);
            }
        }
        System.out.println("Dividing is complete");
        reUseCards();
    }
    public void sendCards()
    {
        int i,j;
        for(i=0;i<1;i++)
        {
            int cards[]=players[i].getAllCards();
            //                PrintWriter outToClient2 = new PrintWriter(players[i].getSocket().getOutputStream());
            //System.out.println("No Exception");
            outToClient2.println("Receive your cards");
            for(j=0;j<13;j++)
            {
                System.out.print(cards[j]+" ");
                String message = (cards[j]) +"#"+"sent";
                //System.out.println(message);
                    outToClient2.println(message);
                    outToClient2.flush();
            }
            //System.out.println("");
            
        }
    }
    private int CurrentFirstMover()
        {
            int i,First_Mover=0;
            for(i=0;i<4;i++)
            {
                if(current_round_value[i]<current_round_value[First_Mover])
                {
                    First_Mover=i;
                }
            }
            players[First_Mover].getStake();
            players[First_Mover].first_mover();
            outToClient2.println(players[0].Stakes()+"#"+players[(1)%4].Stakes()+"#"+players[(2)%4].Stakes()+"#"+players[(3)%4].Stakes()+"#"+"hands");
            outToClient2.flush();
            //System.out.println("Player "+(First_Mover)+" has got the hand"); // made this 0 indexed for debugging
            return First_Mover;
        }
        private int InitialFirstMover()
        {
            int i,First_Mover=0;
            for(i=0;i<4;i++)
            {
                if(players[i].Calls()>players[First_Mover].Calls())
                {
                    First_Mover=i;
                }
            }
            players[First_Mover].first_mover();
            return First_Mover;
        }    
        private void clear_for_current_game()
        {
            int i;
            for(i=0;i<4;i++)
            {
                players[i].clear_for_current_game();
            }
            reUseCards();
            outToClient2.println(players[0].Stakes()+"#"+players[(1)%4].Stakes()+"#"+players[(2)%4].Stakes()+"#"+players[(3)%4].Stakes()+"#"+"hands");
            outToClient2.flush();
            outToClient2.println(players[0].Calls()+"#"+players[(1)%4].Calls()+"#"+players[(2)%4].Calls()+"#"+players[(3)%4].Calls()+"#"+"calls");
            outToClient2.flush();
        }
       private void winner() {
        int i, winner_index;
        winner_index = 0;
        for (i = 0; i < 4; i++) {
            players[i].updatePoints();
            if (players[i].getPoints() > players[winner_index].getPoints()) {
                winner_index = i;
            }
        }
        String result = "Game Over!#You ";
        for (i = 0; i < 1; i++) {

            if (players[i].getPoints() == players[winner_index].getPoints()) {
                outToClient2.println(result + "won!");
            } else {
                outToClient2.println(result + "lost!");
            }
            outToClient2.flush();
        }
    }
        
    public void gamePlay() throws IOException
    {
        System.out.println("starting gameplay");
        int move;
        int i,j,k;
        First_mover=InitialFirstMover();
        
        for(i=0;i<13;i++)
        {
            //showAllPlayerCards();
            //System.out.println("Move for player "+(First_mover+1));
            //System.out.println("move of "+ First_mover);
            j = 0;
            current_round_value[First_mover] =-1;
            if (!players[First_mover].IsHumanPlayer()){
                //System.out.print("first mover bot "+First_mover);
                int currentCardsOnBoard[] = new int[0];
                int x = players[First_mover].moveCard(currentCardsOnBoard);
                System.out.println(" played   "+ x);
                
                current_round_value[First_mover] = x;
                cardused[x/13][x%13]=true;
                int cardIndexOnUserEnd=((First_mover+j)%4)*13+12-i;
                String CardInfo = (cardIndexOnUserEnd) + "#" + x;
                outToClient2.println(CardInfo);
                outToClient2.flush();
            } else {
                //System.out.println("human turn ");
                CardPlayedBy((First_mover + j) % 4);
            }

            for(j=1;j<4;j++)
            {
                //System.out.println("Move for player "+((First_mover+j)%4+1));
                
                if (!players[(First_mover + j) % 4].IsHumanPlayer()) {
                    System.out.print("bot "+(First_mover + j) % 4+ " played ");
                    int currentCardsOnBoard[] = new int[j];
                    for (k = 0; k < j; k++) {
                        currentCardsOnBoard[k] = current_round_value[(k + First_mover) % 4];
                    }
                    
                    
                    int x = players[(First_mover + j) % 4].moveCard(currentCardsOnBoard);
                    
                    
                    System.out.println("  "+ x);
                    current_round_value[(First_mover + j) % 4] = x;
                    cardused[x/13][x%13]=true;
                    int cardIndexOnUserEnd=((First_mover+j)%4)*13+12-i;
                    String CardInfo = (cardIndexOnUserEnd) + "#" + x;
                    outToClient2.println(CardInfo);
                    outToClient2.flush();
                } else {
                    //System.out.println("human move");
                    CardPlayedBy((First_mover + j) % 4);
                }

            }
            
            /*for(int z=0; z<4; z++){
                System.out.println(z + " er curroundval = "+current_round_value[z]);
            }*/
            First_mover=CurrentFirstMover();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }
    
        
        public void setAllCalls() throws IOException{
            //System.out.println("setting calls ");
            for(int i=0; i<4; i++){
                if(players[i].IsHumanPlayer()){
                    String message = "Make your call";
                    outToClient2.println(message);
                    outToClient2.flush();
                    System.out.println("Calling message sent to player "+ (i+1));
                    while(!inFromClient.ready()) continue;
                    String call = inFromClient.readLine();
                    int calls = Integer.parseInt(call);
                    players[i].setCall(calls);
                    outToClient2.println("Call is set!");
                    outToClient2.flush();
                    //System.out.println("For "+(i+1)+": Call is set to "+players[i].Calls());
                }
                else{
                    players[i].setCall();
                }
                //System.out.println("call of player "+i+" = "+players[i].Calls());
            }
            outToClient2.println(players[0].Calls()+"#"+players[(1)%4].Calls()+"#"+players[(2)%4].Calls()+"#"+players[(3)%4].Calls()+"#"+"calls");
            outToClient2.flush();
            
        }
        
        public void showAllPlayerCards(){
            for(int i=0; i<4; i++){
                int arr[] = players[i].getAllCards();
                for(int j=0; j<arr.length; j++){
                    if(cardused[arr[j]/13][arr[j]%13]) 
                        System.out.print(arr[j] + " (" +j + " used) "  );
                    else                         
                        System.out.print(arr[j] + " (" +j + ") "  );
                }
                System.out.println("");
            }
        }
        public void updatePoints()
        {
            int i;
            for(i=0;i<4;i++)
            {
                players[i].updatePoints();
            }
            for(i=0;i<1;i++)
            {
               outToClient2.println(players[i].getPoints()+"#"+players[(i+1)%4].getPoints()+"#"+players[(i+2)%4].getPoints()+"#"+players[(i+3)%4].getPoints()+"#"+"points");
               outToClient2.flush();
            }
        }
        public Boolean isGameOver()
        {
            Boolean result = false;
            int i;
            for(i=0;i<4;i++)
            {
                result = result | players[i].getPoints()>=total_points;
            }
            return result;
        }
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (!isGameOver()) {
            divideTheCards();
            sendCards();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                setAllCalls();
            } catch (IOException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                gamePlay();
            } catch (IOException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(CallBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
            updatePoints();
            clear_for_current_game();
        }
        winner();
    }
}
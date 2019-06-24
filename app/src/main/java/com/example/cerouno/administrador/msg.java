package com.example.cerouno.administrador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;


public class msg {

    public static void msg(Context Contexto, String txt){
        try {
            Toast.makeText(Contexto, txt, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            self:echo("toast: error:"+e.getMessage());
        }
        self:echo(txt);
    }

    public static int echo (String valor){
        Console( "echo", valor );
        return 0;
    }

    public static void Console(String tipo, String texto){
        Log.d(tipo,texto);

    }
    /*
    public static void esperar(long milisegundos){

        boolean t=true;
        long ini = System.currentTimeMillis() + milisegundos;

        while(t){
            t= (ini - System.currentTimeMillis() )< 1;
        }

    }
    */
    public static void var_dump(Object o){
        /*
        BlackjackHand blackjackHand = new BlackjackHand(
                new Card('6', SPADES),
                Arrays.asList(new Card('4', CLUBS), new Card('A', HEARTS)));

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<BlackjackHand> jsonAdapter = moshi.adapter(BlackjackHand.class);

        String json = jsonAdapter.toJson(blackjackHand);
        System.out.println(json);
        */
        Gson gson;
        gson = new Gson();
        gson.toJson(o);
        Console("VARDUMP", "\r\n------------------------\n");
        Console( "variable:"+o.getClass() , toDump(gson) );
    }

    public static void esperar(long milisegundos){
        long iniT = System.currentTimeMillis();
        while(( iniT + milisegundos ) < System.currentTimeMillis()){
            echo("esperando.");
        }

    }


    /*************************************
     *
     * funciones estaticas y privadas
     *
     ****************************/

    private static String toDump(Gson g){
        indice=0;
        return toDump(g.toString(),0);
    }

    static int indice=0;
    private static String toDump(String g,int tab){

        int[]banderas={0,0,0,0,0};
        String tabulador=spaces(tab,' ');
        String rt = tabulador+"";
        char c =' ';
        for (int a=indice;a<g.length();a++ ){
            c=g.charAt(a);

            if (c=='[')banderas[0]=1; // abriendo factor
            if (c==']'){
                banderas[0] = 2;// cerrando factor
            }


//            if (c==':' || c=='=' )banderas[2]=1;// key:value pasando de key a value

            if (c==','){
                banderas[1]=1;// otro campo.
                banderas[2]=0;// parte de key nuevamnete.
            }

            rt += Character.toString(c);

            if (banderas[1]==1){
                // retorno de carro.
                rt+="\r\n"+tabulador;
                banderas[1]=0;
            }
            if (banderas[0]==1){
                // se inicia un ciclo de busqueda para una sub rutina:
                indice=a+1;
                rt+="\r\n"+toDump(g,tab+1)+"\r\n";
                banderas[0]=0;
            }
            if (banderas[0]==2){
                // encontre un fin de registro sin comienzo.
                break;
            }
        }
        return rt;
    }

    private static String spaces(int cantidad,char caracter){

        char[] c=new char[cantidad];

        for(int a=0;a<cantidad;a++){
            c[a]=caracter;
        }
        return new String(c);
    }

}

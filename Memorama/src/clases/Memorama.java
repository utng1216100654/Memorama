/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Random;

/**
 *
 * @author nany
 */
public class Memorama {

    private String[] tablero = new String[28];
    private String jugador1;
    private String jugador2;
    private int paresJ1;
    private int paresJ2;
    private int catego;
    private boolean turnoJ1;    // true= Tira el jugador1, false= Tira el jugador2
    private boolean banNuevo;   // false= No se han registrado los datos de un nuevo juego
    // true= Ya se registraron los datos de un nuevo juego
    private int tiro1;  //Primer tiro
    private int tiro2;  //Segundo tiro
    private boolean banTiro1 = true; //true= tiro1, false= tiro2
    private boolean[] volteadas = new boolean[28];
    private int nSel;   //Número de tarjetas seleccionadas

    public Memorama() {
        paresJ1 = 0;
        paresJ2 = 0;
        catego = 0;
        turnoJ1 = true;
        banNuevo = false;
        banTiro1 = true;
        nSel = 0;
        for(int i=0; i<28; i++){
            volteadas[i]=false;
        }        
    }
    
    public void inicializar(){
        paresJ1 = 0;
        paresJ2 = 0;
        //catego = 0;
        turnoJ1 = true;
        banNuevo = false;
        banTiro1 = true;
        nSel = 0;
        for(int i=0; i<28; i++){
            volteadas[i]=false;
        }
    }
    
    public boolean isBanTiro1() {
        return banTiro1;
    }

    public int getTiro1() {
        return tiro1;
    }

    public int getTiro2() {
        return tiro2;
    }

    public void setTiro(int tiro) {
        if (!volteadas[tiro]) {//No ha sido voleada esa tarjeta?
            if (banTiro1) {//Tiro1
                tiro1 = tiro;
                banTiro1 = false;
            } else {
                tiro2 = tiro;
                banTiro1 = true;
            }
            volteadas[tiro] = true;
            nSel++;          //<<-----------
        }
    }

    public boolean verificar() {
        if (nSel != 0 && tablero[tiro1].equals(tablero[tiro2])) {
            if (turnoJ1) { //Es turno del jugador1
                paresJ1++;
                nSel = 0;
            } else {
                paresJ2++;
                nSel = 0;
            }
            return true;
        } else {
            volteadas[tiro1] = false;//Pone disponibles ambas tarjetas
            volteadas[tiro2] = false;
            turnoJ1 = !turnoJ1;  //cambia de turno
            nSel = 0;     //<<-------
            return false;
        }
    }

    public int getnSel() {
        return nSel;
    }

    public void setBanNuevo(boolean banNuevo) {
        this.banNuevo = banNuevo;
    }

    public boolean isBanNuevo() {
        return banNuevo;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public void setCatego(int catego) {
        this.catego = catego;
    }

    public int getCatego() {
        return catego;
    }

    public String getNomCatego() {
        if (catego == 1) {
            return "Harry Potter";
        } else if (catego == 2) {
            return "Mario Bros";
        } else if (catego == 3) {
            return "Toy Story";
        } else {
            return null;
        }
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getParesJ1() {
        return paresJ1;
    }

    public int getParesJ2() {
        return paresJ2;
    }

    public String getTablero(int pos) {
        return tablero[pos];
    }

    /**
     * Metodo que devuelve el ganador del juego
     * @return 1=Jugador #1 2:Jugador #2
     */
    public int ganador() {
        if (paresJ1 + paresJ2 == 14) {
            if (paresJ1>paresJ2) {
                return 1;   //gano el J1
            }else if(paresJ2>paresJ1){
            return 2;       //gano el J2
            }else{
                return 3; //empate
            }
        }else{
            return 0;   //aun no termina el juego
        }

    }

    public boolean isTurnoJ1() {
        return turnoJ1;
    }

    public void setnSel(int nSel) {
        this.nSel = nSel;
    }
    
    
    
    public void seleccionarImagenes() {
        boolean[] seleccionadas = new boolean[30];
        Random r1 = new Random();
        int pos = 0;
        int i = 0;        //<-----
        switch (catego) {
            case 1:     //famosos
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (seleccionadas[i] == false) {
                        tablero[i] = "/harry/h" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/harry/h" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }
                }
                break;
            case 2:     //mario bros
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/mario/m" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/mario/m" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }
                }
                break;
            case 3:     //toy story
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/toy/t" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/toy/t" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }
                }
            
                
        }
    }

    public void mezclarImagenes() {
        Random r1 = new Random();
        int x;
        int y;
        String tempo = "";
        for (int i = 0; i < 500; i++) {
            x = r1.nextInt(28);
            y = r1.nextInt(28);
            tempo = tablero[x];
            tablero[x] = tablero[y];
            tablero[y] = tempo;
        }
    }
}

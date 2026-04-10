package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte1;

import Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte1.Exception.FormatoVotoStringaErrato;
import Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte1.Exception.VotoNonValido;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Studente {
    private final String nome;
    private final String classe;
    private final List<Double> voti;

    private final static ArrayList<Double> VOTI_DECIMALI_VALIDI = new ArrayList<>(Arrays.asList(0.00, 0.25, 0.50, 0.75, 0.90));

    public Studente(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        voti = new ArrayList<>();
    }

    public void aggiungiVoto(double voto) {
        if (voto < 1 || voto > 10) throw new VotoNonValido();
        double parteDecimale = voto - Math.floor(voto);
        if (String.valueOf(voto).charAt(2) == '9') parteDecimale = 0.90;
        if (!VOTI_DECIMALI_VALIDI.contains(parteDecimale)) throw new VotoNonValido();
        voti.add(voto); 
    }

    public void registraVotoString(String stringaVoto) {
        if (stringaVoto.charAt(0) == '1' && stringaVoto.charAt(1) == '0') {
            if (stringaVoto.length() == 2) {
                aggiungiVoto(10);
                return;
            }
            if (stringaVoto.length() == 3 && stringaVoto.charAt(2) == '-') {
                aggiungiVoto(9.9);
                return;
            }
        }

        double votoNumerico = Integer.parseInt("" + stringaVoto.charAt(0));
        char secondoChar;
        try {
            secondoChar = stringaVoto.charAt(1);
        } catch (StringIndexOutOfBoundsException e) {
            aggiungiVoto(votoNumerico);
            return;
        }

        if (secondoChar == '+' && stringaVoto.length() == 2) {
            aggiungiVoto(votoNumerico + 0.25);
            return;
        }

        if (secondoChar == '½' && stringaVoto.length() == 2) {
            aggiungiVoto(votoNumerico + 0.50);
            return;
        }

        if (secondoChar == '/') {
            if (stringaVoto.length() == 3 && stringaVoto.charAt(2) == (votoNumerico + 1) + '0') {
                aggiungiVoto(votoNumerico + 0.75);
                return;
            }
            if (stringaVoto.length() == 4 && stringaVoto.charAt(2) == '1' && stringaVoto.charAt(3) == '0') {
                aggiungiVoto(votoNumerico + 0.75);
                return;
            }
        }

        if (secondoChar == '-' && stringaVoto.length() == 2) {
            aggiungiVoto(votoNumerico - 1 + 0.9);
            return;
        }
        throw new FormatoVotoStringaErrato();
    }

    public double getMedia() {
        if (getVoti().isEmpty()) return 0;
        double somma = 0;
        for (double num : getVoti()) {
            somma += num;
        }
        return somma / getVoti().size();
    }

    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }

    public List<Double> getVoti() {
        return voti;
    }

    public static void main(String[] args) {
        Studente s = new Studente("Mario", "3IC");
        s.aggiungiVoto(5);
        s.aggiungiVoto(10);
        s.aggiungiVoto(8);
        s.aggiungiVoto(2);
        try {
            s.aggiungiVoto(-4);
        } catch (Exception e) {
            System.out.println("Numero negativo");
        }
        s.registraVotoString("9+");
        try {
            s.registraVotoString("5.76");
        } catch (Exception e) {
            System.out.println("Numero invalido");
        }
        s.registraVotoString("9/11");
    }
}
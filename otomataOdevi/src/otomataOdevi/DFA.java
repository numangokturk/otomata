package otomataOdevi;
import java.util.Scanner;

public class DFA {
    private String currentState;
    private final String[] acceptStates = {"q0"};  // Kabul durumları
    private final String[][] transitionTable = {
        {"q0", "q1"},  // q0 -> q1
        {"q1", "q0"}   // q1 -> q0
    };

    public DFA() {
        this.currentState = "q0";  // Başlangıç durumu
    }

    // Geçiş tablosunu kullanarak dizgiyi işle
    public boolean processInput(String inputString) {
        System.out.println("Başlangıç durumu: " + currentState);
        for (char c : inputString.toCharArray()) {
            if (c != '0' && c != '1') {
                System.out.println("Hatalı giriş: '" + c + "' kabul edilmiyor.");
                return false;
            }
            // Geçiş tablosunu kullanarak durumu güncelle
            currentState = transitionTable[getStateIndex(currentState)][c - '0'];
            System.out.println("Girdi: " + c + ", Yeni Durum: " + currentState);
        }
        return isAcceptState(currentState);
    }

    // Kabul durumu kontrolü
    private boolean isAcceptState(String state) {
        for (String acceptState : acceptStates) {
            if (state.equals(acceptState)) {
                return true;
            }
        }
        return false;
    }

    // Durumun dizideki indeksini bul
    private int getStateIndex(String state) {
        switch (state) {
            case "q0":
                return 0;
            case "q1":
                return 1;
            default:
                return -1;
        }
    }

    // Otomata sıfırla
    public void reset() {
        this.currentState = "q0";  // Başlangıç durumuna dön
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DFA dfa = new DFA();

        while (true) { 
        	System.out.println("Dizgi girebilirsiniz!!");
            System.out.print("Bir dizgi girin (çıkmak için 'exit'): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            dfa.reset();  // Otomata sıfırla
            boolean isAccepted = dfa.processInput(input);
            if (isAccepted) {
                System.out.println("Dizgi KABUL EDİLDİ.");
            } else {
                System.out.println("Dizgi KABUL EDİLMEDİ.");
            }
        }
        scanner.close();
      
    }
}

import java.util.Random;

public class InsertSortPerformance {
    public static void main(String[] args) {
        int[] tamanhos = {50, 500, 1000, 5000, 10000};
        int numExecucoes = 5;

        for (int tamanho : tamanhos) {
            long tempoTotal = 0;
            long totalTrocas = 0;
            long totalIteracoes = 0;

            for (int execucao = 0; execucao < numExecucoes; execucao++) {
                int[] vetor = gerarArrayAleatorio(tamanho);

                long inicio = System.nanoTime();
                InsertionSort.insertionSort(vetor);
                long fim = System.nanoTime();

                tempoTotal += (fim - inicio);
                totalTrocas += InsertionSort.trocas;
                totalIteracoes += InsertionSort.iteracoes;
            }

            long tempoMedio = tempoTotal / numExecucoes;
            long trocasMedias = totalTrocas / numExecucoes;
            long iteracoesMedias = totalIteracoes / numExecucoes;

            System.out.println("Tamanho do Array: " + tamanho);
            System.out.println("Tempo Médio (nanossegundos): " + tempoMedio);
            System.out.println("Trocas Médias: " + trocasMedias);
            System.out.println("Iterações Médias: " + iteracoesMedias);
            System.out.println();
        }
    }

    public static int[] gerarArrayAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1000);
        }
        return vetor;
    }
}

class InsertionSort {
    static int trocas = 0;
    static int iteracoes = 0;

    public static void insertionSort(int array[]) {
        int tamanho = array.length;
        trocas = 0;
        iteracoes = 0;

        for (int passo = 1; passo < tamanho; passo++) {
            int chave = array[passo];
            int j = passo - 1;
            iteracoes++;

            while (j >= 0 && chave < array[j]) {
                array[j + 1] = array[j];
                trocas++;
                j--;
            }
            array[j + 1] = chave;
        }
    }
}
import java.text.DecimalFormat;
import java.util.Random;

public class BubbleSortPerformance {

    public static void main(String[] args) {
        int[] tamanhos = {50, 500, 1000, 5000, 10000};
        int numExecucoes = 5;

        for (int tamanho : tamanhos) {
            long tempoMedio = calcularTempoMedio(tamanho, numExecucoes);
            long trocasMedias = calcularTrocasMedias(tamanho, numExecucoes);
            long iteracoesMedias = calcularIteracoesMedias(tamanho, numExecucoes);

            String tempoFormatado = formatarTempo(tempoMedio);

            exibirResultados(tamanho, tempoFormatado, trocasMedias, iteracoesMedias);
        }
    }

    public static long calcularTempoMedio(int tamanho, int numExecucoes) {
        long tempoTotal = 0;

        for (int execucao = 0; execucao < numExecucoes; execucao++) {
            int[] vetor = gerarArrayAleatorio(tamanho);

            long inicio = System.nanoTime();
            BubbleSort.bubbleSort(vetor);
            long fim = System.nanoTime();

            tempoTotal += (fim - inicio);
        }

        return tempoTotal / numExecucoes;
    }

    public static long calcularTrocasMedis(int tamanho, int numExecucoes) {
        long totalTrocas = 0;

        for (int execucao = 0; execucao < numExecucoes; execucao++) {
            int[] vetor = gerarArrayAleatorio(tamanho);

            BubbleSort.bubbleSort(vetor);
            totalTrocas += BubbleSort.trocas;
        }

        return totalTrocas / numExecucoes;
    }

    public static long calcularIteracoesMedias(int tamanho, int numExecucoes) {
        long totalIteracoes = 0;

        for (int execucao = 0; execucao < numExecucoes; execucao++) {
            int[] vetor = gerarArrayAleatorio(tamanho);

            BubbleSort.bubbleSort(vetor);
            totalIteracoes += BubbleSort.iteracoes;
        }

        return totalIteracoes / numExecucoes;
    }

    public static String formatarTempo(long tempo) {
        DecimalFormat formatoDecimal = new DecimalFormat("###,###,###");
        return formatoDecimal.format(tempo);
    }

    public static int[] gerarArrayAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1000);
        }

        return vetor;
    }

    public static void exibirResultados(int tamanho, String tempoFormatado, long trocasMedias, long iteracoesMedias) {
        System.out.println("Tamanho do Array: " + tamanho);
        System.out.println("Tempo Médio (nanossegundos): " + tempoFormatado);
        System.out.println("Trocas Médias: " + trocasMedias);
        System.out.println("Iterações Médias: " + iteracoesMedias);
        System.out.println();
    }
}

class BubbleSort {
    static int trocas = 0;
    static int iteracoes = 0;

    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        trocas = 0;
        iteracoes = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                iteracoes++;
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocas++;
                }
            }
        }
    }
}
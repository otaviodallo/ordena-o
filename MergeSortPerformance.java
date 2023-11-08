import java.text.DecimalFormat;
import java.util.Random;

public class MergeSortPerformance {

    public static void main(String[] args) {
        int[] tamanhos = {50, 500, 1000, 5000, 10000};
        int numExecucoes = 5;

        for (int tamanho : tamanhos) {
            long tempoTotal = 0;
            long totalTrocas = 0;
            long totalIteracoes = 0;

            for (int execucao = 0; execucao < numExecucoes; execucao++) {
                int[] arr = gerarArrayAleatorio(tamanho);

                long inicio = System.nanoTime();
                ResultadoOrdenacao resultado = mergeSort(arr, 0, arr.length - 1);
                long fim = System.nanoTime();

                tempoTotal += (fim - inicio);
                totalTrocas += resultado.trocas;
                totalIteracoes += resultado.iteracoes;
            }

            long tempoMedio = tempoTotal / numExecucoes;
            long trocasMedias = totalTrocas / numExecucoes;
            long iteracoesMedias = totalIteracoes / numExecucoes;

            DecimalFormat formatoDecimal = new DecimalFormat("###,###,###");
            String tempoFormatado = formatoDecimal.format(tempoMedio);

            System.out.println("Tamanho do Array: " + tamanho);
            System.out.println("Tempo Médio (nanossegundos): " + tempoFormatado);
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

    public static ResultadoOrdenacao mergeSort(int[] vetor, int lo, int hi) {
        if (lo == hi) {
            return new ResultadoOrdenacao(0, 0);
        }

        int mid = (lo + hi) / 2;

        ResultadoOrdenacao primeiraMetade = mergeSort(vetor, lo, mid);
        ResultadoOrdenacao segundaMetade = mergeSort(vetor, mid + 1, hi);

        ResultadoOrdenacao mesclagem = mesclarDoisArraysOrdenados(vetor, lo, mid, hi);

        return new ResultadoOrdenacao(primeiraMetade.trocas + segundaMetade.trocas + mesclagem.trocas, primeiraMetade.iteracoes + segundaMetade.iteracoes + mesclagem.iteracoes);
    }

    public static ResultadoOrdenacao mesclarDoisArraysOrdenados(int[] vetor, int lo, int mid, int hi) {
        int n1 = mid - lo + 1;
        int n2 = hi - mid;

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        for (int i = 0; i < n1; i++) {
            esquerda[i] = vetor[lo + i];
        }

        for (int j = 0; j < n2; j++) {
            direita[j] = vetor[mid + 1 + j];
        }

        int i = 0, j = 0, k = lo;
        int trocas = 0;
        int iteracoes = 0;

        while (i < n1 && j < n2) {
            iteracoes++;
            if (esquerda[i] <= direita[j]) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
                trocas++;
            }
            k++;
        }

        while (i < n1) {
            vetor[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < n2) {
            vetor[k] = direita[j];
            j++;
            k++;
        }

        return new ResultadoOrdenacao(trocas, iteracoes);
    }
}

class ResultadoOrdenacao {
    int trocas;
    int iteracoes;

    ResultadoOrdenacao(int trocas, int iteracoes) {
        this.trocas = trocas;
        this.iteracoes = iteracoes;
    }
}
import java.util.Random;

public class HeapSortPerformance {
    int trocas = 0;
    int iteracoes = 0;

    public void ordenar(int vetor[]) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            ajustarHeap(vetor, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;
            trocas++;

            ajustarHeap(vetor, i, 0);
        }
    }

    void ajustarHeap(int vetor[], int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        // Verificar se o filho esquerdo existe e é maior que o pai
        if (esquerda < n) {
            iteracoes++;
            if (vetor[esquerda] > vetor[maior])
                maior = esquerda;
        }

        // Verificar se o filho direito existe e é maior que o pai ou o filho esquerdo
        if (direita < n) {
            iteracoes++;
            if (vetor[direita] > vetor[maior])
                maior = direita;
        }

        // Se o maior elemento não é o pai, trocar e reajustar
        if (maior != i) {
            int troca = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = troca;
            trocas++;

            ajustarHeap(vetor, n, maior);
        }
    }

    static void imprimirArray(int vetor[]) {
        int n = vetor.length;
        for (int i = 0; i < n; ++i)
            System.out.print(vetor[i] + " ");
        System.out.println();
    }

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
                HeapSortPerformance hs = new HeapSortPerformance();
                hs.ordenar(vetor);
                long fim = System.nanoTime();

                tempoTotal += (fim - inicio);
                totalTrocas += hs.trocas;
                totalIteracoes += hs.iteracoes;
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
        int[] arr = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }
}
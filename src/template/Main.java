package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.BLACK;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.BLUE;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.LIGHTGRAY;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.LIME;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.MOUSE_BUTTON_LEFT;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.ORANGE;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.RAYWHITE;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.RED;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de projeto básico da JSGE.
 *
 * JSGE basic project template.
 *
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {

    private int[] array;
    private int[] pior;
    private int[] nProximos;

    private List<int[]> arraysSelectionSort;
    private List<int[]> arraysInsertionSort;
    private List<int[]> arraysShellSort;
    private List<int[]> arraysMergeSort;
    private List<int[]> arraysQuickSort;
    private List<int[]> arraysHeapSort;
    private List<int[]> arraysBucketSort;
    private List<int[]> arraysCoutingSort;

    private int copiaAtualSelectionSort;
    private int copiaAtualInsertionSort;
    private int copiaAtualShellSort;
    private int copiaAtualMergeSort;
    private int copiaAtualQuickSort;
    private int copiaAtualHeapSort;
    private int copiaAtualBucketSort;
    private int copiaAtualCountingSort;

    private double tempoParaMudar;
    private double contadorTempo;

    private int tamanho;
    private int espaco;

    public Main() {

        super(
                800, // largura           / width
                830, // altura            / height
                "Ordenação Gráfica - JSGE", // título            / title
                60, // quadros por segundo desejado / target FPS
                true, // suavização          / antialiasing
                false, // redimensionável     / resizable
                false, // tela cheia          / full screen
                false, // sem decoração       / undecorated
                false, // sempre no topo      / always on top
                false // fundo invisível     / invisible background
        );

    }

    @Override
    public void create() {

        array = new int[]{7, 3, 1, 2, 9, 4, 6, 8, 5, 10};
        pior = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        nProximos = new int[]{4, 2, 8, 2, 6, 6, 2, 10, 8, 3};

        arraysSelectionSort = new ArrayList<>();
        arraysInsertionSort = new ArrayList<>();
        arraysShellSort = new ArrayList<>();
        arraysMergeSort = new ArrayList<>();
        arraysQuickSort = new ArrayList<>();
        arraysHeapSort = new ArrayList<>();
        arraysBucketSort = new ArrayList<>();
        arraysCoutingSort = new ArrayList<>();

        selectionSort(array.clone());
        insertionSort(array.clone());
        shellSort(array.clone());
        mergeSort(array.clone());
        quickSort(array.clone());
        heapSort(array.clone());
        bucketSort(array.clone());
        countingSort(array.clone());

        tempoParaMudar = 0.5;

        tamanho = 15;
        espaco = 5;

    }

    @Override
    public void update(double delta) {

        contadorTempo += delta;

        if (contadorTempo >= tempoParaMudar) {
            contadorTempo = 0;

            if (copiaAtualSelectionSort < arraysSelectionSort.size() - 1) {
                copiaAtualSelectionSort++;
            }

            if (copiaAtualInsertionSort < arraysInsertionSort.size() - 1) {
                copiaAtualInsertionSort++;
            }

            if (copiaAtualShellSort < arraysShellSort.size() - 1) {
                copiaAtualShellSort++;
            }

            if (copiaAtualMergeSort < arraysMergeSort.size() - 1) {
                copiaAtualMergeSort++;
            }

            if (copiaAtualQuickSort < arraysQuickSort.size() - 1) {
                copiaAtualQuickSort++;
            }

            if (copiaAtualHeapSort < arraysHeapSort.size() - 1) {
                copiaAtualHeapSort++;
            }

            if (copiaAtualBucketSort < arraysBucketSort.size() - 1) {
                copiaAtualBucketSort++;
            }

            if (copiaAtualCountingSort < arraysCoutingSort.size() - 1) {
                copiaAtualCountingSort++;
            }
        }

        if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if (mouseNoBotao(11, 10, 100, 40)) {
                reiniciarArray(nProximos.clone());
                System.out.println("Proximos");
            }
            if (mouseNoBotao(122, 10, 100, 40)) {
                reiniciarArray(array.clone());
                System.out.println("Aleatorio");
            }
            if (mouseNoBotao(233, 10, 100, 40)) {
                reiniciarArray(pior.clone());
                System.out.println("Pior caso");
            }
        }

    }

    @Override
    public void draw() {
        clearBackground(LIGHTGRAY);

        int coluna1 = 25;
        int coluna2 = getScreenWidth() / 2 + 20;

        int linha1 = 90;
        int linha2 = 280;
        int linha3 = 470;
        int linha4 = 660; 

        int painelLargura = getScreenWidth() / 2 - 35;
        int painelAltura = 180;

        // Desenha o texto e os arrays — linha 1
        drawText("SelectionSort - O(n²)", coluna1, linha1 - 20, 20, BLACK);
        desenharArray(arraysSelectionSort.get(copiaAtualSelectionSort), coluna1, linha1 + painelAltura - 30);

        drawText("InsertionSort - O(n²)", coluna2, linha1 - 20, 20, BLACK);
        desenharArray(arraysInsertionSort.get(copiaAtualInsertionSort), coluna2, linha1 + painelAltura - 30);

        // linha 2
        drawText("ShellSort - O(n²)", coluna1, linha2 - 20, 20, BLACK);
        desenharArray(arraysShellSort.get(copiaAtualShellSort), coluna1, linha2 + painelAltura - 30);

        drawText("MergeSort - O(n.lgn)", coluna2, linha2 - 20, 20, BLACK);
        desenharArray(arraysMergeSort.get(copiaAtualMergeSort), coluna2, linha2 + painelAltura - 30);

        // linha 3
        drawText("QuickSort - O(n²)", coluna1, linha3 - 20, 20, BLACK);
        desenharArray(arraysQuickSort.get(copiaAtualQuickSort), coluna1, linha3 + painelAltura - 30);

        drawText("HeapSort - O(n.lgn)", coluna2, linha3 - 20, 20, BLACK);
        desenharArray(arraysHeapSort.get(copiaAtualHeapSort), coluna2, linha3 + painelAltura - 30);

        // linha 4 — BucketSort e CountingSort
        drawText("BucketSort - O(n+k)", coluna1, linha4 - 20, 20, BLACK);
        desenharArray(arraysBucketSort.get(copiaAtualBucketSort), coluna1, linha4 + painelAltura - 30);

        drawText("CountingSort - O(n+k)", coluna2, linha4 - 20, 20, BLACK);
        desenharArray(arraysCoutingSort.get(copiaAtualCountingSort), coluna2, linha4 + painelAltura - 30);

        // Molduras — linhas 1, 2, 3
        drawRoundRectangle(10, linha1 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha1 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(10, linha2 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha2 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(10, linha3 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha3 - 30, painelLargura + 20, painelAltura, 10, BLACK);

        // Molduras — linha 4
        drawRoundRectangle(10, linha4 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha4 - 30, painelLargura + 20, painelAltura, 10, BLACK);

        // Botões
        drawRoundRectangle(10, 9, 101, 41, 10, BLACK);
        fillRoundRectangle(11, 10, 100, 40, 10, LIME);
        drawText("PRÓXIMOS", 20, 26, 16, RAYWHITE);

        drawRoundRectangle(121, 9, 101, 41, 10, BLACK);
        fillRoundRectangle(122, 10, 100, 40, 10, ORANGE);
        drawText("ALEATÓRIO", 126.5, 26, 16, RAYWHITE);

        drawRoundRectangle(232, 9, 101, 41, 10, BLACK);
        fillRoundRectangle(233, 10, 100, 40, 10, RED);
        drawText("PIOR CASO", 237.5, 26, 16, RAYWHITE);

        drawText("Ordenação Gráfica", getScreenWidth() / 2 + 81, 10, 24, BLACK);
        drawText("JSGE", getScreenWidth() / 2 + 162, 38, 32, BLACK);

        drawText("OBS: Todas as complexidades calculadas a partir do pior caso!!!", coluna2, 820, 10, BLACK);
    }

    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            copiarArray(array, TipoOrdenacao.SelectionSort);
            trocar(array, i, min);
        }
        copiarArray(array, TipoOrdenacao.SelectionSort);
    }

    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j = j - 1;
                copiarArray(array, TipoOrdenacao.InsertionSort);
            }
            array[j + 1] = chave;
            copiarArray(array, TipoOrdenacao.InsertionSort);
        }
    }

    private void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int chave = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > chave) {
                    array[j] = array[j - gap];
                    j -= gap;
                    copiarArray(array, TipoOrdenacao.ShellSort);
                }
                array[j] = chave;
                copiarArray(array, TipoOrdenacao.ShellSort);
            }
        }
    }

    private void mergeSort(int[] array) {
        mergeSortRec(array, 0, array.length - 1);
        copiarArray(array, TipoOrdenacao.MergeSort);
    }

    private void mergeSortRec(int[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSortRec(array, esq, meio);
            mergeSortRec(array, meio + 1, dir);
            merge(array, esq, meio, dir);
            copiarArray(array, TipoOrdenacao.MergeSort);
        }
    }

    private void merge(int[] array, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = array[esq + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[meio + 1 + j];
        }
        int i = 0, j = 0;
        int k = esq;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    private void quickSort(int[] array) {
        quickSortRec(array, 0, array.length - 1);
        copiarArray(array, TipoOrdenacao.QuickSort);
    }

    private void quickSortRec(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partition(array, inicio, fim);
            quickSortRec(array, inicio, pi - 1);
            quickSortRec(array, pi + 1, fim);
        }
    }

    private int partition(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (array[j] < pivo) {
                i++;
                trocar(array, i, j);
                copiarArray(array, TipoOrdenacao.QuickSort);
            }
        }
        trocar(array, i + 1, fim);
        copiarArray(array, TipoOrdenacao.QuickSort);
        return i + 1;
    }

    private void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }
        for (int i = array.length - 1; i > 0; i--) {
            trocar(array, 0, i);
            copiarArray(array, TipoOrdenacao.HeapSort);
            heapify(array, i, 0);
        }
        copiarArray(array, TipoOrdenacao.HeapSort);
    }

    private void heapify(int[] array, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        if (esq < n && array[esq] > array[maior]) {
            maior = esq;
        }
        if (dir < n && array[dir] > array[maior]) {
            maior = dir;
        }
        if (maior != i) {
            trocar(array, i, maior);
            copiarArray(array, TipoOrdenacao.HeapSort);
            heapify(array, n, maior);
        }
    }

    private void bucketSort(int[] array) {
        int n = array.length;

        int max = array[0];
        for (int v : array) {
            if (v > max) {
                max = v;
            }
        }

        List<Integer>[] baldes = new List[n];
        for (int i = 0; i < n; i++) {
            baldes[i] = new ArrayList<>();
        }

        for (int v : array) {
            int idx = (int) ((double) v / max * (n - 1));
            baldes[idx].add(v);
        }

        int pos = 0;
        for (List<Integer> balde : baldes) {
            // InsertionSort dentro do balde
            for (int i = 1; i < balde.size(); i++) {
                int chave = balde.get(i);
                int j = i - 1;
                while (j >= 0 && balde.get(j) > chave) {
                    balde.set(j + 1, balde.get(j));
                    j--;
                }
                balde.set(j + 1, chave);
            }
            for (int v : balde) {
                array[pos++] = v;
                copiarArray(array, TipoOrdenacao.BucketSort);
            }
        }

        copiarArray(array, TipoOrdenacao.BucketSort);
    }

    private void countingSort(int[] array) {
        int n = array.length;

        int max = array[0];
        for (int v : array) {
            if (v > max) {
                max = v;
            }
        }

        int[] contagem = new int[max + 1];
        for (int v : array) {
            contagem[v]++;
        }

        int pos = 0;
        for (int val = 0; val <= max; val++) {
            while (contagem[val] > 0) {
                array[pos++] = val;
                contagem[val]--;
                copiarArray(array, TipoOrdenacao.CountingSort);
            }
        }

        copiarArray(array, TipoOrdenacao.CountingSort);
    }

    private void reiniciarArray(int[] array) {
        arraysSelectionSort.clear();
        arraysInsertionSort.clear();
        arraysShellSort.clear();
        arraysMergeSort.clear();
        arraysQuickSort.clear();
        arraysHeapSort.clear();
        arraysBucketSort.clear();
        arraysCoutingSort.clear();

        copiaAtualSelectionSort = 0;
        copiaAtualInsertionSort = 0;
        copiaAtualShellSort = 0;
        copiaAtualMergeSort = 0;
        copiaAtualQuickSort = 0;
        copiaAtualHeapSort = 0;
        copiaAtualBucketSort = 0;
        copiaAtualCountingSort = 0;

        selectionSort(array.clone());
        insertionSort(array.clone());
        shellSort(array.clone());
        mergeSort(array.clone());
        quickSort(array.clone());
        heapSort(array.clone());
        bucketSort(array.clone());
        countingSort(array.clone());

        contadorTempo = 0;
    }

    private boolean mouseNoBotao(int x, int y, int largura, int altura) {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return (mouseX >= x
                && mouseX <= x + largura
                && mouseY >= y
                && mouseY <= y + altura);
    }

    private void trocar(int[] array, int i, int min) {
        int t = array[i];
        array[i] = array[min];
        array[min] = t;
    }

    private void copiarArray(int[] array, TipoOrdenacao sort) {
        int[] copia = new int[array.length];
        System.arraycopy(array, 0, copia, 0, array.length);

        switch (sort) {
            case SelectionSort:
                arraysSelectionSort.add(copia);
                break;
            case InsertionSort:
                arraysInsertionSort.add(copia);
                break;
            case ShellSort:
                arraysShellSort.add(copia);
                break;
            case MergeSort:
                arraysMergeSort.add(copia);
                break;
            case QuickSort:
                arraysQuickSort.add(copia);
                break;
            case HeapSort:
                arraysHeapSort.add(copia);
                break;
            case BucketSort:
                arraysBucketSort.add(copia);
                break;
            case CountingSort:
                arraysCoutingSort.add(copia);
                break;
            default:
                break;
        }
    }

    private void desenharArray(int[] a, int xIni, int yIni) {
        for (int i = 0; i < a.length; i++) {
            int altura = tamanho * a[i];
            fillRectangle(xIni + (espaco + tamanho) * i, yIni - altura - espaco, tamanho, altura, BLUE);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private enum TipoOrdenacao {
        SelectionSort,
        InsertionSort,
        ShellSort,
        MergeSort,
        QuickSort,
        HeapSort,
        BucketSort,
        CountingSort;
    }
}

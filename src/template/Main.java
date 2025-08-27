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

    private int copiaAtualSelectionSort;
    private int copiaAtualInsertionSort;
    private int copiaAtualShellSort;
    private int copiaAtualMergeSort;
    private int copiaAtualQuickSort;
    private int copiaAtualHeapSort;

    private double tempoParaMudar;
    private double contadorTempo;

    private int tamanho;
    private int espaco;

    public Main() {

        super(
                800, // largura           / width
                630, // altura            / height
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

        selectionSort(array.clone());
        insertionSort(array.clone());
        shellSort(array.clone());
        mergeSort(array.clone());
        quickSort(array.clone());
        heapSort(array.clone());

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
        
        int painelLargura = getScreenWidth() / 2 - 35;
        int painelAltura = 180;

        // Desenha o texto e os arrays
        drawText("SelectionSort - O(n²)", coluna1, linha1 - 20, 20, BLACK);
        desenharArray(arraysSelectionSort.get(copiaAtualSelectionSort), coluna1, linha1 + painelAltura - 30);
        
        drawText("InsertionSort - O(n²)", coluna2, linha1 - 20, 20, BLACK);
        desenharArray(arraysInsertionSort.get(copiaAtualInsertionSort), coluna2, linha1 + painelAltura - 30);
        
        drawText("ShellSort - O(n²)", coluna1, linha2 - 20, 20, BLACK);
        desenharArray(arraysShellSort.get(copiaAtualShellSort), coluna1, linha2 + painelAltura - 30);

        drawText("MergeSort - O(n.lgn)", coluna2, linha2 - 20, 20, BLACK);
        desenharArray(arraysMergeSort.get(copiaAtualMergeSort), coluna2, linha2 + painelAltura - 30);

        drawText("QuickSort - O(n²)", coluna1, linha3 - 20, 20, BLACK);
        desenharArray(arraysQuickSort.get(copiaAtualQuickSort), coluna1, linha3 + painelAltura - 30);
        
        drawText("HeapSort - O(n.lgn)", coluna2, linha3 - 20, 20, BLACK);
        desenharArray(arraysHeapSort.get(copiaAtualHeapSort), coluna2, linha3 + painelAltura - 30);

        // Desnha a moldura 
        drawRoundRectangle(10, linha1 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha1 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(10, linha2 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha2 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(10, linha3 - 30, painelLargura + 20, painelAltura, 10, BLACK);
        drawRoundRectangle(coluna2 - 15, linha3 - 30, painelLargura + 20, painelAltura, 10, BLACK);

        // Desenha os botoes
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
        
        drawText( "OBS: Todas as complexidades calculadas a partir do pior caso!!!", coluna2, 624, 10, BLACK);
    }

    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            copiarArray(array, "SelectionSort");
            trocar(array, i, min);
        }
        copiarArray(array, "SelectionSort");
    }

    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j = j - 1;
                copiarArray(array, "InsertionSort");
            }
            array[j + 1] = chave;
            copiarArray(array, "InsertionSort");
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
                    copiarArray(array, "ShellSort");
                }
                array[j] = chave;
                copiarArray(array, "ShellSort");
            }
        }
    }

    private void mergeSort(int[] array) {
        mergeSortRec(array, 0, array.length - 1);
        copiarArray(array, "MergeSort");
    }

    private void mergeSortRec(int[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSortRec(array, esq, meio);
            mergeSortRec(array, meio + 1, dir);
            merge(array, esq, meio, dir);
            copiarArray(array, "MergeSort");
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
        copiarArray(array, "QuickSort");
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
                copiarArray(array, "QuickSort");
            }
        }
        trocar(array, i + 1, fim);
        copiarArray(array, "QuickSort");
        return i + 1;
    }

    private void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }
        for (int i = array.length - 1; i > 0; i--) {
            trocar(array, 0, i);
            copiarArray(array, "HeapSort");
            heapify(array, i, 0);
        }
        copiarArray(array, "HeapSort");
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
            copiarArray(array, "HeapSort");
            heapify(array, n, maior);
        }
    }

    private void reiniciarArray(int[] array) {
        arraysSelectionSort.clear();
        arraysInsertionSort.clear();
        arraysShellSort.clear();
        arraysMergeSort.clear();
        arraysQuickSort.clear();
        arraysHeapSort.clear();

        copiaAtualSelectionSort = 0;
        copiaAtualInsertionSort = 0;
        copiaAtualShellSort = 0;
        copiaAtualMergeSort = 0;
        copiaAtualQuickSort = 0;
        copiaAtualHeapSort = 0;

        selectionSort(array.clone());
        insertionSort(array.clone());
        shellSort(array.clone());
        mergeSort(array.clone());
        quickSort(array.clone());
        heapSort(array.clone());

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

    private void copiarArray(int[] array, String algoritmo) {
        int[] copia = new int[array.length];
        System.arraycopy(array, 0, copia, 0, array.length);

        switch (algoritmo) {
            case "SelectionSort":
                arraysSelectionSort.add(copia);
                break;
            case "InsertionSort":
                arraysInsertionSort.add(copia);
                break;
            case "ShellSort":
                arraysShellSort.add(copia);
                break;
            case "MergeSort":
                arraysMergeSort.add(copia);
                break;
            case "QuickSort":
                arraysQuickSort.add(copia);
                break;
            case "HeapSort":
                arraysHeapSort.add(copia);
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
}
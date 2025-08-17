package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
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

    private List<int[]> arraysSelectionSort;
    private List<int[]> arraysInsertionSort;
    private List<int[]> arraysShellSort;
    private List<int[]> arraysMergeSort;

    private int copiaAtualSelectionSort;
    private int copiaAtualInsertionSort;
    private int copiaAtualShellSort;
    private int copiaAtualMergeSort;

    private double tempoParaMudar;
    private double contadorTempo;

    private int tamanho;
    private int espaco;
    private int xIni;
    private int yIni;

    public Main() {

        super(
                800, // largura                      / width
                600, // altura                       / height
                "Ordenações", // título                       / title
                60, // quadros por segundo desejado / target FPS
                true, // suavização                   / antialiasing
                false, // redimensionável              / resizable
                false, // tela cheia                   / full screen
                false, // sem decoração                / undecorated
                false, // sempre no topo               / always on top
                false // fundo invisível              / invisible background
        );

    }

    /**
     * Cria o mundo do jogo. Esse método executa apenas uma vez durante a
     * inicialização da engine.
     *
     * Creates the game world. This method runs just one time during engine
     * initialization.
     */
    @Override
    public void create() {

        array = new int[]{7, 3, 1, 2, 9, 4, 6, 8, 5, 10};

        arraysSelectionSort = new ArrayList<>();
        arraysInsertionSort = new ArrayList<>();
        arraysShellSort = new ArrayList<>();
        arraysMergeSort = new ArrayList<>();
        
        
        selectionSort(array.clone());
        insertionSort(array.clone());
        shellSort(array.clone());
        mergeSort(array.clone());
        
        

        tempoParaMudar = 0.5;

        tamanho = 20;
        espaco = 5;
        xIni = 10;
        yIni = getScreenHeight() / 2;

    }

    /**
     * Lê a entrada do usuário e atualiza o mundo do jogo. Os métodos de entrada
     * devem ser usados aqui. Atenção: Você NÃO DEVE usar nenhum dos métodos de
     * desenho da engine aqui.
     *
     *
     * Reads user input and update game world. Input methods should be used
     * here. Warning: You MUST NOT use any of the engine drawing methods here.
     *
     * @param delta O tempo passado, em segundos, de um quadro para o outro.
     * Time passed, in seconds, between frames.
     */
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
        }

    }

    /**
     * Desenha o mundo do jogo. Todas as operações de desenho DEVEM ser feitas
     * aqui.
     *
     * Draws the game world. All drawing related operations MUST be performed
     * here.
     */
    @Override
    public void draw() {
        clearBackground(LIGHTGRAY);
        
        drawText("SelectionSort", xIni, 50, 20, BLACK);
        desenharArray(arraysSelectionSort.get(copiaAtualSelectionSort), xIni, yIni );
        
        drawText("InsertionSort", getScreenWidth() / 2 + xIni, 50, 20, BLACK);
        desenharArray(arraysInsertionSort.get(copiaAtualInsertionSort), getScreenWidth() / 2 + xIni, yIni );
        
        drawText("ShellSort", xIni, yIni + 50, 20, BLACK);
        desenharArray(arraysShellSort.get(copiaAtualShellSort), xIni, 2 * yIni );
        
        drawText("MergeSort", getScreenWidth()/ 2 + xIni, yIni + 50, 20, BLACK);
        desenharArray(arraysMergeSort.get(copiaAtualMergeSort), getScreenWidth() / 2 + xIni, 2 * yIni);
        
        drawLine(getScreenWidth() / 2, 0, getScreenWidth() / 2, getScreenHeight(), BLACK);
        drawLine(0, getScreenHeight() / 2, getScreenWidth(), getScreenHeight() / 2, BLACK);
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
            default:
                break;
        }
    }

    private void desenharArray(int[] a, int xIni, int yIni) {

        for (int i = 0; i < a.length; i++) {

            int altura = tamanho * a[i];
            
            fillRectangle( xIni + (espaco + tamanho) * i , yIni - altura - espaco, tamanho, altura, BLUE);
            
        }

    }

    /**
     * Instancia a engine e a inicia.
     *
     * Instantiates the engine and starts it.
     */
    public static void main(String[] args) {
        new Main();
    }

}

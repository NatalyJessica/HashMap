import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Cloneable {
    private class Element {
        private K chave;
        private V valor;

        public Element(K k, V v) {
            this.chave = k;
            this.valor = v;
        }

        // getters, setters e overrides
        public K getChave() {
            return this.chave;
        }

        public V getValor() {
            return this.valor;
        }

        public void setChave(K k) {
            this.chave = k;
        }

        public void setValor(V v) {
            this.valor = v;
        }

        // Override do equals
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Element element = (Element) o;
            return (chave != null ? chave.equals(element.chave) : element.chave == null);
        }

        // Override do hashCode
        @Override
        public int hashCode() {
            return chave != null ? chave.hashCode() : 0;
        }

        // Override do toString
        @Override
        public String toString() {
            return "Element{" +
                    "chave=" + chave +
                    ", valor=" + valor +
                    '}';
        }

        // Override do clone
        @Override
        @SuppressWarnings("unchecked")
        public Element clone() {
            try {
                // Clona o próprio objeto
                Element cloned = (Element) super.clone();
                // Se K e V forem cloneáveis, clone as referências. Caso contrário, mantém a
                // cópia superficial.
                if (this.chave instanceof Cloneable) {
                    cloned.chave = (K) this.chave.getClass().getMethod("clone").invoke(this.chave);
                }
                if (this.valor instanceof Cloneable) {
                    cloned.valor = (V) this.valor.getClass().getMethod("clone").invoke(this.valor);
                }
                return cloned;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao clonar o Element", e);
            }
        }

    }

    private ListaSimplesDesordenada<Element>[] vetor;
    private int qtdElems = 0, qtdPosOcupadas = 0;
    private int capacidadeInicial;
    private float txMinDesperdicio, txMaxDesperdicio;

    // construtores
    public HashMap(int capacidadeInicial, float txMinDesperdicio, float txMaxDesperdicio) {
        this.capacidadeInicial = capacidadeInicial;
        this.txMinDesperdicio = txMinDesperdicio;
        this.txMaxDesperdicio = txMaxDesperdicio;
        vetor = new ListaSimplesDesordenada[capacidadeInicial];
    }

    // Método para adicionar um item
    public void guardeUmItem(K chave, V valor) throws Exception {
        if (qtdElems / (float) vetor.length > txMaxDesperdicio) {
            redimensionarVetor(vetor.length * 2);
        }

        int hash = chave.hashCode();
        int indice = Math.abs(hash) % vetor.length;

        if (vetor[indice] == null) {
            vetor[indice] = new ListaSimplesDesordenada<>();
            qtdPosOcupadas++;
        }
        // Criar um novo Elemento com chave e valor
        Element newElement = new Element(chave, valor);
        // Verificar se a chave já existe na lista
        ListaSimplesDesordenada<Element> lista = vetor[indice];
        for (Element elem : lista) {
            if (elem.getChave().equals(chave)) {
                throw new Exception("Chave duplicada: " + chave);
            }
        }
        // Se a chave não existe, adiciona o novo elemento na lista
        lista.guardeUmItemNoInicio(newElement);
        // Incrementa o contador de elementos
        qtdElems++;
    }

    /*
     * public V recupereUmItem (K chave) throws Exception
     * {}
     */
    
    public void removaUmItem(K chave) throws Exception {
        // Calcula o hash e o índice no vetor
        int hash = chave.hashCode();
        int indice = Math.abs(hash) % vetor.length;

        ListaSimplesDesordenada<Element> lista = vetor[indice];
        if (lista == null) {
            throw new NoSuchElementException("Chave não encontrada: " + chave);
        }
        // Percorre a lista para encontrar o elemento com a chave fornecida
        Iterator<Element> iterador = lista.iterator();
        while (iterador.hasNext()) {
            Element elem = iterador.next();
            if (elem.getChave().equals(chave)) {
                lista.removaItemIndicado(elem); // Remove o elemento da lista
                qtdElems--; // Decrementa o contador de itens armazenados
    
                // Se a lista ficou vazia após a remoção, atualiza `qtdPosOcupadas`
                if (lista.isVazia()) {
                    vetor[indice] = null;
                    qtdPosOcupadas--;
                }

                if ((vetor.length - qtdElems) / (float) vetor.length > txMinDesperdicio && vetor.length > capacidadeInicial) {
                    redimensionarVetor(vetor.length / 2);
                }

                return;
            }
        }
        throw new NoSuchElementException("Chave não encontrada: " + chave);
    }

    private void redimensionarVetor(int novaCapacidade) throws Exception {
        ListaSimplesDesordenada<Element>[] novoVetor = new ListaSimplesDesordenada[novaCapacidade];

        for (ListaSimplesDesordenada<Element> lista : vetor) {
            if (lista != null) {
                for (Element elem : lista) {
                    int hash = elem.getChave().hashCode();
                    int indice = Math.abs(hash) % novaCapacidade;

                    if (novoVetor[indice] == null) {
                        novoVetor[indice] = new ListaSimplesDesordenada<>();
                    }

                    novoVetor[indice].guardeUmItemNoInicio(elem);
                }
            }
        }

        vetor = novoVetor;
    }
    
    // overrides
    // Método toString para imprimir o HashMap
    @Override
    public String toString() {
        String result = "HashMap:\n"; // Inicia com a representação inicial

        // Percorrer o vetor e listar os elementos de cada posição
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != null) {
                result += "Índice " + i + ": "; // Concatenar o índice
                for (Element elem : vetor[i]) {
                    result += elem.toString() + " "; // Concatenar o elemento
                }
                result += "\n"; // Nova linha após cada índice
            }
        }

        return result; // Retorna a representação final
    }

    public int getQtdElems() {
        return qtdElems;
    }
}

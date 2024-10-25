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
    public HashMap(int capacidadeInicial) {
        this.capacidadeInicial = capacidadeInicial;
        vetor = new ListaSimplesDesordenada[capacidadeInicial];
    }

   

    /*
     * public V recupereUmItem (K chave) throws Exception
     * {}
     * public void removaUmItem (K chave) throws Exception
     * {}
     */

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
}
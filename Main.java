public class Main {
    public static void main(String[] args) {
        try {
            // Criando uma instância de HashMap com capacidade inicial de 10
            HashMap<String, String> map = new HashMap<>(4, 0.25f, 0.75f);

           
            // Inserindo elementos no HashMap
            map.guardeUmItem("chave1", "valor1");
            map.guardeUmItem("chave2", "valor2");
            map.guardeUmItem("chave3", "valor3");
            map.guardeUmItem("chave4", "valor4");

            System.out.println("HashMap após inserção de 4 elementos:");
            System.out.println(map);

            // Inserindo mais um elemento para forçar o redimensionamento
            map.guardeUmItem("chave5", "valor5");

            System.out.println("HashMap após inserção de um 5º elemento (redimensionamento esperado):");
            System.out.println(map);

            // Removendo elementos
            map.removaUmItem("chave1");
            map.removaUmItem("chave2");

            System.out.println("HashMap após remoção de 2 elementos:");
            System.out.println(map);

            // Removendo mais elementos para forçar a redução do vetor
            map.removaUmItem("chave3");
            map.removaUmItem("chave4");

            System.out.println("HashMap após remoção de mais 2 elementos (redução esperada):");
            System.out.println(map);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

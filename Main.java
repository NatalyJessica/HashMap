public class Main {
    public static void main(String[] args) {
        try {
            // Criando uma instância de HashMap com capacidade inicial de 10
            HashMap<Integer, String> map = new HashMap<>(4, 0.25f, 0.75f);

           
            // Inserindo elementos no HashMap
            map.guardeUmItem(1, "valor1");
            map.guardeUmItem(2, "valor2");
            map.guardeUmItem(3, "valor3");
            map.guardeUmItem(4, "valor4");

            System.out.println("HashMap após inserção de 4 elementos:");
            System.out.println(map);

            // Inserindo mais um elemento para forçar o redimensionamento
            map.guardeUmItem(11, "valor5");

            System.out.println("HashMap após inserção de um 5º elemento (redimensionamento esperado):");
            System.out.println(map);

            // Removendo elementos
            map.removaUmItem(2);
            map.removaUmItem(4);

            map.recupereUmItem(11);
            System.out.println("recuperado!" );

            System.out.println("HashMap após remoção de 2 elementos:");
            System.out.println(map);

            // Removendo mais elementos para forçar a redução do vetor
            map.removaUmItem(3);
          
            
            System.out.println("HashMap após remoção de mais 2 elementos (redução esperada):");
            System.out.println(map);    

            map.recupereUmItem(3);
            System.out.println("recuperado!");




        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // Criando uma instância de HashMap com capacidade inicial de 10
            HashMap<String, Integer> map = new HashMap<>(3);

            // Adicionando itens ao HashMap
            map.guardeUmItem("chave1", 1);
            map.guardeUmItem("chave2", 200);
            map.guardeUmItem("chave3", 300);

            // Adicionando mais itens para testar o comportamento do hash
            map.guardeUmItem("chave4", 400);
            map.guardeUmItem("chave5", 500);

            // Imprimindo o conteúdo do HashMap
            System.out.println(map.toString());

            // Atualizando um valor de uma chave existente
            map.guardeUmItem("chave1", 150);

            map.guardeUmItem("chave6", 600);
            map.guardeUmItem("chave7", 10);
            map.guardeUmItem("chave8", 400);
            map.guardeUmItem("chave9", 5);

            // Imprimindo o conteúdo do HashMap
            System.out.println(map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

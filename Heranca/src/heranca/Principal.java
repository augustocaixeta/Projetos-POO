package heranca;

public class Principal {
    public static void main(String[] args) {
        Funcionario maria = new Funcionario();
        maria.setNome("Maria");
        maria.setSobrenome("Beatriz");
        maria.setSalario(620.0);

        System.out.println("Funcionario: " + maria.getNome() +
            " " + maria.getSobrenome() + "\n"
            + "Salario: R$ " + maria.getSalario());

        FuncionarioComissionado marcelo = new FuncionarioComissionado();
        marcelo.setNome("Marcelo");
        marcelo.setSobrenome("Medeiros");
        marcelo.setSalario(100.00);
        marcelo.setComissao(20.00);
        marcelo.setVendas(10);

        System.out.println();

        System.out.println("Funcionario Comissionado: " + marcelo.getNome() + " " +
            marcelo.getSobrenome() + "\n" + "Salario: R$ " + marcelo.getSalario() + "\n" + "Comissao: " + marcelo.getComissao() + "\n" + "Vendas: " + marcelo.getVendas());
        
        System.out.println("Adicionando mais 3 vendas:\n");
        
        marcelo.adicionarVendas(3);

        System.out.println("Numero de Vendas: " + marcelo.getVendas() +
            "\n" + "Comissao: R$ " + marcelo.calcularComissao());

        System.out.println("\nApos limpar as vendas:\n");
        marcelo.limparVendas();

        System.out.println("Numero de Vendas: " + marcelo.getVendas() +
            "\n" + "Comissao: R$ " + marcelo.calcularComissao());
    }
}

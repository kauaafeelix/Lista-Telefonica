package org.example;

import org.example.DAO.ContatoDAO;
import org.example.model.Contato;
import org.example.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        inicio();
    }
    public static void inicio(){
        boolean sair = false;
        System.out.println("""
                \n====================== LISTA TELEFONICA ========================\n
                1. Cadastrar contato: Nome, Telefone, Email, Observação.
                \n2. Listar todos os contatos cadastrados.
                \n3. Buscar contato por nome.
                \n4. Atualizar dados de um contato (telefone, email, observação).
                \n5. Remover contato.
                \n6. Sair do sistema.
                \n Digite sua opção: 
                """);
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){
            case 1:{
                cadastrarContato();;
                break;
            }
            case 2:{
                listarContatos();
                break;
            }
            case 3:{
                buscarContatoPorNome();
                break;
            }

            case 4:{
                atualizarContato();
                break;
            }
            case 6:{
                sair = true;
                break;
            }
        }
        if (!sair){
            inicio();
        }
    }
    public static void cadastrarContato(){
        System.out.println("---------Cadastrar Contato----------\n");
        System.out.println("Digite o nome do contato: ");
        String nome = sc.nextLine();

        System.out.println("Digite o telefone do contato: ");
        String telefone = sc.nextLine();

        System.out.println("Digite o email do contato: ");
        String email = sc.nextLine();

        System.out.println("Digite o observacao do contato: ");
        String observacao = sc.nextLine();

        var contato = new Contato (nome, telefone, email, observacao);

        var contatoDao = new ContatoDAO();
        try{
        contatoDao.inserirContato(contato);
            System.out.println("\nTelefone inserido com sucesso! ");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no banco de dados.");
            e.printStackTrace();
        }


    }
    public static void listarContatos(){
        System.out.println("--------Lista de Contatos--------");
        List<Contato> contatos = new ArrayList<>();
        var contatoDao = new ContatoDAO();
        try{
        contatos = contatoDao.listarContatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            Utils.exibirContatos(contatos);

    }
    public static void buscarContatoPorNome() {
        System.out.println("--------Buscar Contato por Nome-------\n");
        System.out.println("Digite o nome do contato que deseja buscar: ");
        String nome = sc.nextLine();
        List<Contato> contatos = new ArrayList<>();
        try {
            var contatoDao = new ContatoDAO();
            contatos = contatoDao.buscarContatoPorNome(nome);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro no banco de dados!");
            e.printStackTrace();
        }
            Utils.exibirContatos(contatos);
    }
    public static void atualizarContato(){
        System.out.println("-----Atualização de Contato-----\n");

    }
}
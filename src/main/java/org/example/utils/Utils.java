package org.example.utils;

import org.example.DAO.ContatoDAO;
import org.example.model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    static Scanner sc = new Scanner (System.in);
    public static void exibirContatos(List<Contato>contatos){
        if (!contatos.isEmpty()){

        for (Contato contato:contatos){
            System.out.println("\nID: "+contato.getId());
            System.out.println("NOME: "+contato.getNome());
            System.out.println("TELEFONE: "+contato.getTelefone());
            System.out.println("EMAIL: "+contato.getEmail());
            System.out.println("OBSERVAÇÃO: "+contato.getObservacao());
        }
        }else{
            System.out.println("Nenhum contato cadastrado.");
        }
    }
}

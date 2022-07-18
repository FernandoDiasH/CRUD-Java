package br.edu.up;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.up.model.Filme;


public class Programa {

	static EntityManagerFactory emf;
	static EntityManager em;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("prj-jpa-sqlite");
		em = emf.createEntityManager();
		
		Scanner leitor = new Scanner(System.in);
		
		
		int opcao = 0;
		int menu = 0;
		while(opcao != 5) {
			System.out.println("==================");
			System.out.println("Cadastro de Filmes");
			System.out.println("==================");
			while(menu != 5) {
				menu();
				
				System.out.println("opçao:");
				opcao = leitor.nextInt();
				
				if(opcao == 1) {
					System.out.println("Cadastrar novo Filme");
					System.out.println("--------------------");
					
					System.out.println("Nome: ");
					String nomeFilme = leitor.next();
					System.out.println("Categoria: ");
					String categoriaFilme = leitor.next();
					System.out.println("Ano de lançamento: ");
					int anoLanc = leitor.nextInt();
					
					cadastrarFilme(nomeFilme, categoriaFilme, anoLanc);
					
					opcao = 0;
				}
				else if(opcao == 2){
					System.out.println("Atualizar Filme");
					System.out.println("--------------------");
					
					System.out.println("ID: ");
					int id = leitor.nextInt();
					System.out.println("Nome: ");
					String nomeFilme = leitor.next();
					System.out.println("Categoria: ");
					String categoriaFilme = leitor.next();
					System.out.println("Ano de lançamento: ");
					int anoLanc = leitor.nextInt();
					
					atualizarFilme(id, nomeFilme, categoriaFilme, anoLanc);
					opcao = 0;
				}
					
				else if(opcao ==3) {
					mostrarFilmes();
					
					opcao = 0;
				}
				else if(opcao == 4) {
					System.out.println("Digite o ID do filme para apagar:");
					int id = leitor.nextInt();
					apagar(id);
				}
				
				
				
				else if(opcao == 5) {
					System.out.println("Secessao encerrada...");
					menu = 5;
					break;
					
					
				}
				
				
				
			}		
			
			
			
		}
		

		

		
	leitor.close();	
	}
	
	public static void menu() {
		System.out.println("Menu:");
		System.out.println("[1]-Cadastrar Filme");
		System.out.println("[2]-Atualizar Filme");
		System.out.println("[3]-Listar Filmes");
		System.out.println("[4]-Apagar Filme");
		System.out.println("[5]-Sair");
		System.out.println("==================");
	}
	
	
	public static void cadastrarFilme(String nome, String categoria, int ano) {
		
		Filme filme = new Filme();
		filme.setNomeFilme(nome);
		filme.setCategoria(categoria);
		filme.setAnoLanc(ano);
		salvar(filme);
	}
	
	public static void atualizarFilme(int id, String nome, String categoria, int ano) {
		Filme filme = localizar(id);
		String nomeFilme = filme.getNomeFilme();
		String categoriaFilme = filme.getCategoria();
		int anoFilme = filme.getAnoLanc();
		
		if(!nome.equals(nomeFilme)) {
			nomeFilme = nome;
		}
		if (!categoria.equals(categoriaFilme)) {
			categoriaFilme = categoria;
		}
		if(anoFilme != ano) {
			anoFilme = ano;
		}
		
		filme.setNomeFilme(nomeFilme);
		filme.setCategoria(categoriaFilme);
		filme.setAnoLanc(anoFilme);
		
		atualizar(filme);

	}
	
	public static void mostrarFilmes() {
		List<Filme> filme = listarFilmes();
		
		System.out.println("ID - Nome - Categoria - Ano" );
		System.out.println("=======================================" );
		System.out.println("---------------------------------------");
		for (Filme filme2 : filme) {
			System.out.println(filme2.getId() + " - " +  filme2.getNomeFilme() + " - " + filme2.getCategoria() + " - " + filme2.getAnoLanc());
			System.out.println("---------------------------------------");
		}	
		
		System.out.println("=======================================" );
	}
	
	public static List<Filme> listarFilmes(){
		List<Filme> filmes = em.createQuery("from Filme", Filme.class).getResultList();
		
		return filmes;
		
	}
	

	public static Integer salvar(Filme filme) {
		em.getTransaction().begin();
		em.persist(filme);
		em.getTransaction().commit();
		return filme.getId();
	}

	

	public static Filme localizar(Integer id) {
		Filme filme = em.find(Filme.class, id);
		return filme;
	}
	

	public static void atualizar(Filme filme) {
		em.getTransaction().begin();
		em.merge(filme);
		em.getTransaction().commit();
	}

	public static void apagar(Integer id) {
		Filme filme = em.find(Filme.class, id);
		em.getTransaction().begin();
		em.remove(filme);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unused")
	private static void imprimir(Filme filme) {
				System.out.println(filme);
    }
}
package ex.Rest.restEx0306h2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex.Rest.restEx0306h2.model.Produto;

import ex.Rest.restEx0306h2.model.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	 public List<Produto> getProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("{id}")
	Produto getProduto(@PathVariable Long id) {
		return produtoRepository.findById(id).get();
	}
	
	@PostMapping("/cadastrar")
	public Produto cadastrar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
	
	
	@PutMapping("/alterar/{id}")
	Produto updateProduto(@RequestBody Produto novoProduto, @PathVariable Long id) {
		Produto x = produtoRepository.findById(id).get();
		
		if(novoProduto != null && novoProduto.getCodigo() != 0) {
			x.setCodigo(novoProduto.getCodigo());
		}
		
		if(novoProduto.getNome() != null && !novoProduto.getNome().isEmpty()) {
			x.setNome(novoProduto.getNome());
		}
		
		x.setCategoria(novoProduto.getCategoria());
		x.setValor(novoProduto.getValor());
		x.setQuantidade(novoProduto.getQuantidade());
		return produtoRepository.save(x);
	}

}


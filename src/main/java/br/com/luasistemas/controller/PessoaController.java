package br.com.luasistemas.controller;

import br.com.luasistemas.model.Contato;
import br.com.luasistemas.model.Pessoa;
import br.com.luasistemas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by fabricio on 20/04/17.
 */
@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody  Pessoa save(@RequestBody Optional<Pessoa> pessoa){
        if(!pessoa.isPresent() || pessoa.get().getContatos().size() < 3){
            throw new RuntimeException("Informe uma pessoa válida");
        }
        pessoa.get().getContatos().forEach(c->c.setPessoa(pessoa.get()));
        return repository.save(pessoa.get());
    }
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody  Collection<Pessoa> list(){
        return repository.findAll();
    }


    @RequestMapping("/{cpf}")
    public HttpEntity<Pessoa> get(@PathVariable String cpf){
        Optional<Pessoa> pessoa = repository.findByCpf(cpf);
        if(pessoa.isPresent()) {
            Pessoa p = pessoa.get();
            p.add(linkTo(methodOn(Pessoa.class).getContatos()).withSelfRel());
            return  new ResponseEntity<>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping("/{id}/contatos")
    public @ResponseBody  Collection<Contato> getContatos(@PathVariable Long id){
        return repository.getOne(id).getContatos();
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody  Pessoa remove(@PathVariable Long id){
        Optional<Pessoa> pessoa = Optional.ofNullable(repository.findOne(id));
        if(pessoa.isPresent()) {
            repository.delete(id);
        }else{
            throw new RuntimeException("Nenhuma pessoa encontrada com o id: "+id);
        }
        return pessoa.get();
    }
}

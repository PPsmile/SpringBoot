package th.ku.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/book"})
public class BookService {
    @Autowired
    private BookDaoImp repository;

    @GetMapping
    public List<Book> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping
    public Book create(@RequestBody Book book){
        repository.save(book);
        return repository.findById(book.getId());
    }



}

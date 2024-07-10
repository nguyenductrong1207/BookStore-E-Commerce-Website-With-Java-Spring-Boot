package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Book;
import project.bookstore.entity.News;
import project.bookstore.entity.Publisher;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.exception.PublisherNotFoundException;
import project.bookstore.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublisher() {
        return (List<Publisher>) publisherRepository.findAll();
    }

    public Publisher get(Integer id) throws PublisherNotFoundException {
        Optional<Publisher> result = publisherRepository.findById(id);

        return result.orElseThrow(() -> new PublisherNotFoundException("Publisher not found!"));
    }

    public void deleteById(int id) {
        publisherRepository.deleteById(id);
    }
}

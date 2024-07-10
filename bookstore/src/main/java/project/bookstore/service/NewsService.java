package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.bookstore.entity.News;
import project.bookstore.exception.InvalidNewsException;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.repository.NewsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    public Page<News> getPaginatedNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
    public News save(News news) throws InvalidNewsException {
        if (news.getTitle() == null || news.getTitle().length() < 3) {
            throw new InvalidNewsException("News title must be at least 3 characters long");
        }
        if (news.getPublication().isAfter(LocalDate.now())) {
            throw new InvalidNewsException("Publication date cannot be in the future");
        }
        return newsRepository.save(news);
    }

    public List<News> listAll() {
        return newsRepository.findAll();
    }

    public News get(Integer id) throws NewsNotFoundException {
        Optional<News> result = newsRepository.findById(id);

        return result.orElseThrow(() -> new NewsNotFoundException("News not found!"));
    }

    public void delete(Integer id) throws NewsNotFoundException {
        Long count = newsRepository.countById(id);

        if (count == null || count == 0) {
            throw new NewsNotFoundException("Could not find any news with ID " + id);
        }

        newsRepository.deleteById(id);
    }

    public boolean existsByTitle(String title) {
        return newsRepository.existsByTitle(title);
    }
}

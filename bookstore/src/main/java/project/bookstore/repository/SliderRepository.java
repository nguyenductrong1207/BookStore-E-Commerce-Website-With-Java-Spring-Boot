package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Author;
import project.bookstore.entity.Slider;

import java.util.List;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Integer> {

   public Long countById(Integer id);

   @Query("select s from Slider s where s.isSelected = true")
   List<Slider> getSelectedSliders ();

}

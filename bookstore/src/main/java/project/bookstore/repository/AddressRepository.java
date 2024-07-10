package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import project.bookstore.entity.Address;
import project.bookstore.entity.user.User;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public List<Address> findAddressesByUser(User user);

    @Query("select a from  Address  a where  a.id = ?1 and  a.user.id = ?2")
    public Address findByIdAndCustomer(Integer addressId, Integer customerId);

    @Modifying
    @Query("delete from  Address  a where  a.id = ?1 and  a.user.id = ?2")
    public void deleteByIdAndCustomer(Integer addressId, Integer customerId);
}

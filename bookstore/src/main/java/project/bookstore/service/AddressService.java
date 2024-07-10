package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Address;
import project.bookstore.entity.user.User;
import project.bookstore.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findByCustomer(User user){
        return  addressRepository.findAddressesByUser(user);
    }
}

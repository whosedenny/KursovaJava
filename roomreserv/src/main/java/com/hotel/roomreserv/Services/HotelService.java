package com.hotel.roomreserv.Services;
import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Repository.Abstract.IHotelRepository;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelService implements IHotelService {
    private IHotelRepository repos;

    @Override
    public List<Hotel> GetAllHotels() {
        return repos.findAll();
    }
    @Override
    public void AddHotel(Hotel hotel) {
        repos.insert(hotel);
    }
    @Override
    public void UpdateHotel(Hotel hotel) {
        repos.save(hotel);
    }
    @Override
    public void RemoveHotel(Hotel hotel) {
        repos.delete(hotel);
    }
    public Hotel GetHotelById(@PathVariable String id){
        return repos.findById(id).orElse(null);
    }
    @Override
    public List<Hotel> GetHotelsByCity(String city) {
        return GetAllHotels()
                .stream()
                .filter(hotel -> hotel.getCity().equals(city))
                .toList();
    }
    @Override
    public List<Hotel> GetHotelsByStar(int star) {
        return GetAllHotels()
                .stream()
                .filter(hotel -> hotel.getStars() == star)
                .toList();
    }
    @Override
    public Hotel GetHotelByName(String name) {
        return GetAllHotels()
                .stream()
                .filter(hotel -> hotel.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

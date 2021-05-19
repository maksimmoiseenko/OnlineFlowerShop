package maksim.moiseenko.services;

import maksim.moiseenko.models.Flower;
import maksim.moiseenko.repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;
    public List<Flower> findAllFlowers(){
        return flowerRepository.findAll();
    }
    public void save(String name,int cost){
        flowerRepository.save(new Flower(name,cost));
    }
    public void delete(Long id){
        if(flowerRepository.existsById(id))
            flowerRepository.deleteById(id);
    }
    public Optional<Flower> getFlower(Long id){
        if(!flowerRepository.existsById(id)) return null;
        return flowerRepository.findById(id);

    }
    public String editFlowerForm(Long id, Model model){
        if(!flowerRepository.existsById(id)) return "redirect:/flowers";
        Optional<Flower> flower=flowerRepository.findById(id);
        ArrayList<Flower> flower1=new ArrayList<>();
        flower.ifPresent(flower1 :: add);
        model.addAttribute("flower",flower1);
        return "flowerEdit";
    }

    public void editFlower(Long id,String name, int cost){
        Flower flower=new Flower();
        flower.setId(id);
        flower.setName(name);
        flower.setCost(cost);
        flowerRepository.save(flower);
    }
}

package kz.ali.foodHibirnate.controller;

import kz.ali.foodHibirnate.entities.Food;
import kz.ali.foodHibirnate.repositories.FoodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping(value = "/addfood")
    public String addFood() {
        return "add-food";
    }

    @PostMapping(value = "/addfood")
    public String addFood(@RequestParam(name = "food_name") String name,
                         @RequestParam(name = "food_calories") int calories,
                         @RequestParam(name = "food_amount") int amount,
                         @RequestParam(name = "food_price") int price){


        Food food = new Food();
        food.setName(name);
        food.setCalories(calories);
        food.setAmounts(amount);
        food.setPrice(price);

        foodRepository.save(food);  // данный метод выполняет запрос INSERT
        return "redirect:/";  // После успешного добавления в машины в базу даннных пользователь будет перенаправлен на главную страницу (о ней чуть позже рааскажу)
    }

    @GetMapping(value = "/")
    public String foodPage(Model model){
        List<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "foods";  // Главная страница
    }

    @GetMapping(value = "/food")
    public String foodDetails(Model model, @RequestParam(name = "id")Long id){
        Food food = foodRepository.findById(id).orElse(null);
        if(food != null){
            model.addAttribute("food", food);
            return "food-details";
        }else {
            return "redirect:/404";
        }
    }

    @GetMapping(value = "/404")
    public String foodNotFoundPage(){
        return "404";
    }

    @GetMapping(value = "/updatefood")
    public String updateFood(Model model, @RequestParam(name = "id")Long id){
        Food food = foodRepository.findById(id).orElse(null);

        if(food != null){
            model.addAttribute("food", food);
            return "edit-food";
        }else {
            return "redirect:/404";
        }
    }

    @PostMapping(value = "/updatefood")
    public String updateFood(@RequestParam(name = "food_id") Long id,
                             @RequestParam(name = "food_name") String name,
                             @RequestParam(name = "food_calories") int calories,
                             @RequestParam(name = "food_amount") int amount,
                             @RequestParam(name = "food_price") int price){
        Food food = foodRepository.findById(id).orElse(null);
        if(food != null){
            food.setName(name);
            food.setCalories(calories);
            food.setAmounts(amount);
            food.setPrice(price);
            foodRepository.save(food);

            return "redirect:/food?id="+id+"&success";
        }else {
            return "redirect:/404";
        }

    }

    @PostMapping(value = "/deletefood")
    public String deleteFood(Model model, @RequestParam(name = "id")Long id){
        foodRepository.deleteById(id);
        return "redirect:/";
    }


}

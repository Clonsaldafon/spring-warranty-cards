package ru.clonsaldafon.spring_warranty_cards.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.CreateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceAdminViewDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.UpdateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.CreateServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.CreateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.UpdateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.WarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.WarrantyCardViewDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.HomeApplianceMapper;
import ru.clonsaldafon.spring_warranty_cards.mapper.WarrantyCardMapper;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;
import ru.clonsaldafon.spring_warranty_cards.service.HomeApplianceService;
import ru.clonsaldafon.spring_warranty_cards.service.ServiceCenterService;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;
import ru.clonsaldafon.spring_warranty_cards.service.WarrantyCardService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private HomeApplianceService homeApplianceService;
    @Autowired
    private HomeApplianceMapper homeApplianceMapper;

    @Autowired
    private ServiceCenterService serviceCenterService;

    @Autowired
    private WarrantyCardService warrantyCardService;
    @Autowired
    private WarrantyCardMapper warrantyCardMapper;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/home-appliances")
    public String homeAppliances(Model model) {
        List<HomeApplianceAdminViewDto> homeAppliances = homeApplianceService.findAll().stream()
                .map(homeApplianceMapper::toAdminViewDto)
                .collect(Collectors.toList());

        model.addAttribute("homeAppliances", homeAppliances);
        return "admin/home_appliances";
    }

    @GetMapping("/home-appliances/create")
    public String createHomeApplianceForm(Model model) {
        model.addAttribute("createHomeApplianceDto",
                                        new CreateHomeApplianceDto(null, null));
        return "admin/create/create_home_appliance";
    }

    @PostMapping("/home-appliances/create")
    public String createHomeAppliance(@ModelAttribute CreateHomeApplianceDto createHomeApplianceDto) {
        homeApplianceService.create(createHomeApplianceDto);
        return "redirect:/admin/home-appliances";
    }

    @GetMapping("/home-appliances/edit/{id}")
    public String updateHomeApplianceForm(@PathVariable Long id, Model model) {
        HomeAppliance homeAppliance = homeApplianceService.findById(id);
        HomeApplianceDto homeApplianceDto = homeApplianceMapper.toDto(homeAppliance);

        model.addAttribute("homeAppliance", homeApplianceDto);
        return "admin/edit/edit_home_appliance";
    }

    @PutMapping("/home-appliances/edit/{id}")
    public String updateHomeAppliance(@PathVariable Long id,
                                      @ModelAttribute UpdateHomeApplianceDto updateHomeApplianceDto) {
        homeApplianceService.updateById(id, updateHomeApplianceDto);
        return "redirect:/admin/home-appliances";
    }

    @PostMapping("/home-appliances/delete/{id}")
    public String deleteHomeAppliance(@PathVariable Long id) {
        homeApplianceService.deleteById(id);
        return "redirect:/admin/home-appliances";
    }

    @GetMapping("/warranty-cards")
    public String warrantyCards(Model model) {
        List<WarrantyCardViewDto> activeWarrantyCards = warrantyCardService.findAllByWarranty(false).stream()
                .map(warrantyCardMapper::toViewDto)
                .collect(Collectors.toList());

        List<WarrantyCardViewDto> expiredWarrantyCards = warrantyCardService.findAllByWarranty(true).stream()
                .map(warrantyCardMapper::toViewDto)
                .collect(Collectors.toList());

        model.addAttribute("activeWarrantyCards", activeWarrantyCards);
        model.addAttribute("expiredWarrantyCards", expiredWarrantyCards);
        return "admin/warranty_cards";
    }

    @GetMapping("/warranty-cards/create")
    public String createWarrantyCardForm(Model model) {
        model.addAttribute("createWarrantyCardDto",
                                        new CreateWarrantyCardDto(null, null));
        return "admin/create/create_warranty_card";
    }

    @PostMapping("/warranty-cards/create")
    public String createWarrantyCard(@ModelAttribute CreateWarrantyCardDto createWarrantyCardDto) {
        warrantyCardService.create(createWarrantyCardDto);
        return "redirect:/admin/warranty-cards";
    }

    @GetMapping("/warranty-cards/edit/{id}")
    public String updateWarrantyCardForm(@PathVariable Long id, Model model) {
        WarrantyCard warrantyCard = warrantyCardService.findById(id);
        WarrantyCardDto warrantyCardDto = warrantyCardMapper.toDto(warrantyCard);

        model.addAttribute("warrantyCard", warrantyCardDto);
        return "admin/edit/edit_warranty_card";
    }

    @PutMapping("/warranty-cards/edit/{id}")
    public String updateWarrantyCard(@PathVariable Long id,
                                     @ModelAttribute UpdateWarrantyCardDto updateWarrantyCardDto) {
        warrantyCardService.updateById(id, updateWarrantyCardDto);
        return "redirect:/admin/warranty-cards";
    }

    @PostMapping("/warranty-cards/delete/{id}")
    public String deleteWarrantyCard(@PathVariable Long id) {
        warrantyCardService.deleteById(id);
        return "redirect:/admin/warranty-cards";
    }

    @GetMapping("/service-centers")
    public String serviceCenters(Model model) {
        model.addAttribute("serviceCenters", serviceCenterService.findAll());
        return "admin/service_centers";
    }

    @GetMapping("/service-centers/create")
    public String createServiceCenterForm(Model model) {
        model.addAttribute("createServiceCenterDto", new CreateServiceCenterDto(null, null));
        return "admin/create/create_service_center";
    }

    @PostMapping("/service-centers/create")
    public String createServiceCenter(@ModelAttribute CreateServiceCenterDto createServiceCenterDto) {
        serviceCenterService.create(createServiceCenterDto);
        return "redirect:/admin/service-centers";
    }
}

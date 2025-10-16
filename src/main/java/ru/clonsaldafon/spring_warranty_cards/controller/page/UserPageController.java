package ru.clonsaldafon.spring_warranty_cards.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceViewDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.HomeApplianceMapper;
import ru.clonsaldafon.spring_warranty_cards.security.CustomUserDetails;
import ru.clonsaldafon.spring_warranty_cards.service.HomeApplianceService;
import ru.clonsaldafon.spring_warranty_cards.service.ServiceCenterService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserPageController {

    @Autowired
    private HomeApplianceService homeApplianceService;
    @Autowired
    private HomeApplianceMapper homeApplianceMapper;

    @Autowired
    private ServiceCenterService serviceCenterService;

    @GetMapping("/home-appliances")
    public String homeAppliances(Model model, @AuthenticationPrincipal UserDetails user) {
        Long userId = ((CustomUserDetails) user).getId();

        List<HomeApplianceViewDto> activeHomeAppliances = homeApplianceService
                .findAllWithWarrantiesByUser(false, userId)
                .stream()
                .map(homeApplianceMapper::toViewDto)
                .collect(Collectors.toList());

        List<HomeApplianceViewDto> expiredHomeAppliances = homeApplianceService
                .findAllWithWarrantiesByUser(true, userId)
                .stream()
                .map(homeApplianceMapper::toViewDto)
                .collect(Collectors.toList());

        List<HomeApplianceViewDto> homeAppliancesWithoutWarranties = homeApplianceService
                .findAllWithoutWarrantiesByUser(userId)
                .stream()
                .map(homeApplianceMapper::toViewDto)
                .collect(Collectors.toList());

        model.addAttribute("activeHomeAppliances", activeHomeAppliances);
        model.addAttribute("expiredHomeAppliances", expiredHomeAppliances);
        model.addAttribute("homeAppliancesWithoutWarranties", homeAppliancesWithoutWarranties);
        model.addAttribute("allHomeAppliances", homeApplianceService.findAllByUserIsNull());
        return "user/home_appliances";
    }

    @GetMapping("/service-centers")
    public String serviceCenters(Model model) {
        model.addAttribute("serviceCenters", serviceCenterService.findAll());
        return "user/service_centers";
    }
}

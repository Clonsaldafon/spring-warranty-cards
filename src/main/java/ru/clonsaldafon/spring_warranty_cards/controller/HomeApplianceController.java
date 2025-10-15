package ru.clonsaldafon.spring_warranty_cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.HomeApplianceMapper;
import ru.clonsaldafon.spring_warranty_cards.security.CustomUserDetails;
import ru.clonsaldafon.spring_warranty_cards.service.HomeApplianceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/home-appliances")
public class HomeApplianceController {

    @Autowired
    private HomeApplianceService homeApplianceService;

    @Autowired
    private HomeApplianceMapper homeApplianceMapper;

    @GetMapping
    public List<HomeApplianceDto> getAll() {
        return homeApplianceService.findAllByUserIsNull().stream()
                .map(homeApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user")
    public List<HomeApplianceDto> getAllByUserId(@AuthenticationPrincipal UserDetails user) {
        Long userId = ((CustomUserDetails) user).getId();
        return homeApplianceService.findAllByUserId(userId).stream()
                .map(homeApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/warranty")
    public List<HomeApplianceDto> getAllWithWarranties(@RequestParam Boolean isExpired,
                                                       @AuthenticationPrincipal UserDetails user) {
        Long userId = ((CustomUserDetails) user).getId();
        return homeApplianceService.findAllWithWarrantiesByUser(isExpired, userId).stream()
                .map(homeApplianceMapper::toDto)
                .collect(Collectors.toList());
    }
}

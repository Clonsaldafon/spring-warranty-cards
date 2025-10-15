package ru.clonsaldafon.spring_warranty_cards.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.CreateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.UpdateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.CreateServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.ServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.dto.user.UserDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.CreateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.UpdateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.WarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.HomeApplianceMapper;
import ru.clonsaldafon.spring_warranty_cards.mapper.ServiceCenterMapper;
import ru.clonsaldafon.spring_warranty_cards.mapper.UserMapper;
import ru.clonsaldafon.spring_warranty_cards.mapper.WarrantyCardMapper;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;
import ru.clonsaldafon.spring_warranty_cards.model.ServiceCenter;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;
import ru.clonsaldafon.spring_warranty_cards.service.HomeApplianceService;
import ru.clonsaldafon.spring_warranty_cards.service.ServiceCenterService;
import ru.clonsaldafon.spring_warranty_cards.service.UserService;
import ru.clonsaldafon.spring_warranty_cards.service.WarrantyCardService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ServiceCenterService serviceCenterService;
    @Autowired
    private ServiceCenterMapper serviceCenterMapper;

    @Autowired
    private HomeApplianceService homeApplianceService;
    @Autowired
    private HomeApplianceMapper homeApplianceMapper;

    @Autowired
    private WarrantyCardService warrantyCardService;
    @Autowired
    private WarrantyCardMapper warrantyCardMapper;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/users/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/users/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @PostMapping("/service-centers")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceCenterDto createServiceCenter(@Valid @RequestBody CreateServiceCenterDto createServiceCenterDto) {
        ServiceCenter serviceCenter = serviceCenterService.create(createServiceCenterDto);
        return serviceCenterMapper.toDto(serviceCenter);
    }

    @GetMapping("/home-appliances")
    public List<HomeApplianceDto> getAllHomeAppliances() {
        return homeApplianceService.findAll().stream()
                .map(homeApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/home-appliances/warranty")
    public List<HomeApplianceDto> getAllHomeAppliancesWithWarranties(@RequestParam Boolean isExpired) {
        return homeApplianceService.findAllWithWarranties(isExpired).stream()
                .map(homeApplianceMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/home-appliances")
    @ResponseStatus(HttpStatus.CREATED)
    public HomeApplianceDto createHomeAppliance(@Valid @RequestBody CreateHomeApplianceDto createHomeApplianceDto) {
        HomeAppliance homeAppliance = homeApplianceService.create(createHomeApplianceDto);
        return homeApplianceMapper.toDto(homeAppliance);
    }

    @PutMapping("/home-appliances/{id}")
    public HomeApplianceDto updateHomeAppliance(@PathVariable Long id,
                                                @Valid @RequestBody UpdateHomeApplianceDto updateHomeApplianceDto) {
        HomeAppliance homeAppliance = homeApplianceService.updateById(id, updateHomeApplianceDto);
        return homeApplianceMapper.toDto(homeAppliance);
    }

    @GetMapping("/warranty-cards")
    public List<WarrantyCardDto> getAllWarrantyCards() {
        return warrantyCardService.findAll().stream()
                .map(warrantyCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/warranty-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public WarrantyCardDto createWarrantyCard(@Valid @RequestBody CreateWarrantyCardDto createWarrantyCardDto) {
        WarrantyCard warrantyCard = warrantyCardService.create(createWarrantyCardDto);
        return warrantyCardMapper.toDto(warrantyCard);
    }

    @PutMapping("/warranty-cards/{id}")
    public WarrantyCardDto updateWarrantyCardById(@PathVariable Long id,
                                                  @RequestBody UpdateWarrantyCardDto updateWarrantyCardDto) {
        WarrantyCard warrantyCard = warrantyCardService.updateById(id, updateWarrantyCardDto);
        return warrantyCardMapper.toDto(warrantyCard);
    }

    @DeleteMapping("/warranty-cards/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWarrantyCardById(@PathVariable Long id) {
        warrantyCardService.deleteById(id);
    }
}

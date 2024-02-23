package pers.bohan.statelessauthenticationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pers.bohan.statelessauthenticationsystem.entity.User;
import pers.bohan.statelessauthenticationsystem.payload.JwtResponse;
import pers.bohan.statelessauthenticationsystem.security.jwt.JwtUtils;
import pers.bohan.statelessauthenticationsystem.security.services.UserDetailsImpl;
import pers.bohan.statelessauthenticationsystem.service.IUserService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * OAuth2 Log in controller.
 *
 * @author bohan
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/oauth2/login")
    public String index(Model model, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
        Integer id = oauth2User.getAttribute("id");
        Long userId = Long.valueOf(Objects.requireNonNull(id));
        String username = oauth2User.getAttribute("login");
        User user = userService.getById(userId);
        if (user == null) {
            user = new User(userId, username, "user");
            userService.save(user);
        }
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());

        return "index";
    }


    @GetMapping("/oauth2/success")
    public String success(Model model, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient,
                          @AuthenticationPrincipal OAuth2User oauth2User) {
        Integer id = oauth2User.getAttribute("id");
        Long userId = Long.valueOf(Objects.requireNonNull(id));
        String username = oauth2User.getAttribute("login");
        User user = userService.getById(userId);
        if (user == null) {
            user = new User(userId, username, "user");
            userService.save(user);
        }
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());


        String jwt = jwtUtils.generateJwtToken(user.getUsername());

        model.addAttribute("jwt", jwt);
        List<String> roles = Stream.of(user.getRole())
                .collect(Collectors.toList());

        var token = new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                roles);
        model.addAttribute("token", token);
        return "index";
    }

}


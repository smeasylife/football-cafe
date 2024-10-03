package football.cafe.controller.member;

import football.cafe.constant.SessionConst;
import football.cafe.domain.Member;
import football.cafe.dto.SignInForm;
import football.cafe.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SignInController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signIn(Model model) {
        model.addAttribute("signInForm", new SignInForm());
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(@Validated @ModelAttribute SignInForm signInForm, BindingResult bindingResult,
                         @RequestParam(defaultValue = "/") String redirectUrl, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "signin";
        }

        Member member = memberService.login(signInForm.getEmail(), signInForm.getPassword());
        if (member == null) {
            bindingResult.reject("loginFail");
            return "signin";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:" + redirectUrl;
    }
}

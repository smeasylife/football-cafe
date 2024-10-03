package football.cafe.controller.member;

import football.cafe.domain.Member;
import football.cafe.dto.SignUpForm;
import football.cafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Validated @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!signUpForm.getConfirmPassword().equals(signUpForm.getPassword())) {
            bindingResult.rejectValue("confirmPassword", "unmatchedPassword");
            return "signup";
        }

        Member member = new Member();
        member.signUp(signUpForm.getName(), signUpForm.getEmail(), signUpForm.getPassword());
        try {
            memberService.join(member);
        } catch (IllegalStateException e) {
            bindingResult.reject("alreadyExistMember");
            return "signup";
        }

        return "/index";
    }
}

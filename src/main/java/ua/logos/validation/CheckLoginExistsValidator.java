//package ua.logos.validation;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import ua.logos.repository.UserRepository;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class CheckLoginExistsValidator implements ConstraintValidator<CheckLoginExists, String> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        if(userRepository.findByLogin(value) == null) {
//            return true;
//        }
//        return false;
//    }
//}
//
//

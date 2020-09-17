package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marina.springboot.User;
import com.marina.springboot.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	public String doLogic() {
		List<User> users = loginRepository.findAll();
		return users.get(0).getName();
	}
}

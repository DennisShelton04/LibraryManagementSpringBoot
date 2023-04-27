package com.librarymanagement.security;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.librarymanagement.repository.ReadAndWriteRepository;

@Component
public class UserinfoUserDetailService implements UserDetailsService {
    @Autowired
    private ReadAndWriteRepository readAndWriteRepository;
    @Autowired
    private UserInfo userInfo;
    private String UseraddPath = "C:\\Users\\ASUS\\Desktop\\LibraryManagement\\src\\main\\webapp\\DataBase\\AdminFile\\librariandetails.json";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject jobj = readAndWriteRepository.readJsonFiel(UseraddPath);
        JSONObject userdetails;
        if (jobj.containsKey(username)) {
            userdetails = (JSONObject) jobj.get(username);

            String uname = (String) userdetails.get("username");
            userInfo.setUserName(uname);
            String pass = (String) userdetails.get("password");
            userInfo.setPassword(pass);
            String role = (String) userdetails.get("roles");
            userInfo.setRole(role);
            return new UserInfoUserDetails(userInfo);
        }

        throw new UsernameNotFoundException("User not found with username " + username);
    }

}

package com.example.swagger.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.anyLong;
import com.example.swagger.api.model.User;
import com.example.swagger.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest
public class UserControllerTest {
	@MockBean
	private UserService userService;
	@Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
	 @Test
	    void createUser() throws Exception {
	        User user = new User();
	        user.setId(1L);
	        user.setFirstName("sri");
	        user.setLastName("lakshmi");
	        user.setPhoneNumber("9885613895");
	        user.setAge(21);
	        user.setPercentage(80.0);
	        when(userService.createUser(any(User.class))).thenReturn(user);
	        ResultActions resultActions = mockMvc.perform(post("/api/users/save").contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(user)));
	        resultActions.andDo(print()).andExpect(status().isCreated())
	        //.andExpect(jsonPath("$.id",is(user.getId())))
            .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(user.getLastName())))
            .andExpect(jsonPath("$.age", is(user.getAge())))
            .andExpect(jsonPath("$.phoneNumber", is(user.getPhoneNumber())))
	        .andExpect(jsonPath("$.percentage", is(user.getPercentage())));
	 		}
	 
	        @Test
	        void getAllUser() throws Exception {
	            User user = new User();
	            user.setId(1L);
		        user.setFirstName("sri");
		        user.setLastName("lakshmi");
		        user.setPhoneNumber("9885613895");
		        user.setAge(21);
		        user.setPercentage(80.0);

	            List<User> list = new ArrayList<>();
	            list.add(user);
	            when(userService.getAllUsers()).thenReturn(list);
	            this.mockMvc.
	                    perform(get("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(list)))
	                    .andDo(print()).andExpect(status().isOk())
	                    .andExpect(jsonPath("$.size()", is(list.size())));
	        }
	        
	        @Test
	        void getUserById() throws Exception {
	            User user = new User();
	            user.setId(1L);
		        user.setFirstName("sri");
		        user.setLastName("lakshmi");
		        user.setPhoneNumber("9885613895");
		        user.setAge(21);
		        user.setPercentage(80.0);

	            when(userService.getUserById(anyLong())).thenReturn(user);
	            ResultActions resultActions = mockMvc.perform(get("/api/users/{id}", 2L)
	                            .contentType(MediaType.APPLICATION_JSON)
	                            .content(objectMapper.writeValueAsString(user))).andDo(print()).andExpect(status().isOk())
	            		
	                    .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
	                    .andExpect(jsonPath("$.lastName", is(user.getLastName())))
	                    .andExpect(jsonPath("$.Age", is(user.getAge())))
	                    .andExpect(jsonPath("$.PhoneNumber", is(user.getPhoneNumber())))
	        	        .andExpect(jsonPath("$.percentage", is(user.getPercentage())));
	            System.out.println("><><><" + resultActions.toString() + "><><>");
	        }
	        @Test
	        void deleteUserById() throws Exception {
	            User user = new User();
	            user.setId(1L);
		        user.setFirstName("sri");
		        user.setLastName("lakshmi");
		        user.setPhoneNumber("9885613895");
		        user.setAge(21);
		        user.setPercentage(80.0);

	            doNothing().doThrow(new RuntimeException()).when(userService).deleteUser(anyLong());
	            this.mockMvc.perform(delete("/api/users/{id}", 1L)).andExpect(status().isOk());
	        }

	  
	        @Test
	        void updateUserById() throws Exception {
	        	User user = new User();
		        user.setFirstName("sri");
		        user.setLastName("lakshmi");
		        user.setPhoneNumber("9885613895");
		        user.setAge(21);
		        user.setPercentage(80.0);
	            userService.createUser(user);

	           
	            when(userService.updateUser(1L,user)).thenReturn(user);
	            this.mockMvc.perform(put("/api/users/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
	                    .andDo(print()).andExpect(status().isOk())
	                    .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
	                    .andExpect(jsonPath("$.lastName", is(user.getLastName())))
	                    .andExpect(jsonPath("$.Age", is(user.getAge())))
	                    .andExpect(jsonPath("$.PhoneNumber", is(user.getPhoneNumber())))
	        	        .andExpect(jsonPath("$.percentage", is(user.getPercentage())));
	        }
	    

}

package com.InditexEnterprice.price.infraestructura.adapters;

import org.springframework.web.client.RestTemplate;

/*
    This class is just an example that how we could adapt an external connection for get some data.
    Acces on another microService that provides information about Products or similar.

    In this case, Im using JsonPlaceHolder to get all the JsonTemplates that jsonplaceholder provides and with that,
    we could test our system working with big volume of data.
    Ofcourse, is not implemented cause we need to create the Service, domain and controllers.
 */
public class ExternalServiceAdapter {

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter(){
        restTemplate =  new RestTemplate();
    }

    /*
        public AdditionalTest getAdditionalTextInfo(Long id){
            String apiUrl = "https://jsonplaceholder.typicode.com/todos/"+id;
            ResponseEntity<JsonPlaceHolderTodo> response = restTemplate.getForEntity(apiUrl, JsonPlaceHolderTodo.class);
            JsonPlaceHolderTodo todo = response.getBody();
            if(todo == null) return null;

            String apiUrl = "https://jsonplaceholder.typicode.com/users/"+ todo.getUserId();
            ResponseEntity<JsonPlaceHolderUser> userResponse = restTemplate.getForEntity(apiUrl, JsonPlaceHolderUser.class);
            JsonPlaceHolderUser user = userResponse.getBody();
            if(user == null) return null;

            return new AdditionalTest(usser.getId(), user.getName(), user.getEmail());
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter @Setter
        private static class JsonPlaceHolderTodo{
            private Long id;
            private Long userId;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter @Setter
        private static class JsonPlaceHolderUser{
            private Long id;
            private String name;
            private String email;
        }

     */

}

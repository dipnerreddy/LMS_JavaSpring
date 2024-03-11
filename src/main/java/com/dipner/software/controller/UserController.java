package com.dipner.software.controller;

import com.dipner.software.beans.*;
import com.dipner.software.dao.BooksDAO;
import com.dipner.software.dao.ForgetPasswordDAO;
import com.dipner.software.dao.LoginDAO;
import com.dipner.software.repository.BookRepository;
import com.dipner.software.repository.LoginRepository;
import com.dipner.software.repository.UserRepository;
import com.dipner.software.service.LoginService;
import com.dipner.software.service.UserService;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/wifi")
public class UserController {


    @Autowired
    private LoginRepository loginRepository;

    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public UserController(UserService userService,LoginService loginService) {
        this.userService = userService;
        this.loginService=loginService;
    }
    static Log log = LogFactory.getLog(UserController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgetPasswordDAO forgetPasswordDAO;

    @Autowired
    private LoginDAO loginDAO;
    private Users users;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BooksDAO booksDAO;





//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/info")
    public String Hello(){
        return "Hello";
    }

    @PostMapping(value = "/postusers" , produces = "application/json")
    public Response<Users> registerUser(@Valid @RequestBody Users users){

        String username=users.getUsername();
        String email=users.getEmail();
        String password=users.getPassword();

        String emailInDAO=loginDAO.getEmail(email);

        if(email.equals(emailInDAO)){
            //pls login
            return new Response<>("400","User Already exists, please login in the login page",null,true);
        }
        else {
            userService.registerUser(users);
            return new Response<>("200","Registration Success",null,true);
        }
    }

    @PostMapping(value = "/postlogin", produces = "application/json")
    public Response<LoginUsers> loginUsersResponse(@Valid @RequestBody LoginUsers loginUsers){
        String email=loginUsers.getEmail();
        String password = loginUsers.getPassword();
        String emailInDAO=loginDAO.getEmail(email);
        String passwordInDAO=loginDAO.getPassword(email);

        if (email.equals(emailInDAO)) {

            log.error("into if");

           String username= forgetPasswordDAO.getUserName(email);
            log.info("username is :" +username   );

            if (password.equals(passwordInDAO)){
                return new Response<>("200", "success!", loginRepository.save(loginUsers), true);
            }
            else {
                return new Response<>("400","Password is incorrect",null,true);
            }
        } else {
            log.info("into else");
            return new Response<>("400","You are not registered, Please do the registration in the registration page",null,true);
        }
    }





//    @PostMapping(value = "/admin/addBook", produces = "application/json")
//    public Response<Books> addBook(@Valid @RequestBody Books books) {
//        String bookName = books.getBookName();
//        String author = books.getAuthor();
//        String category = books.getCategory();
//        boolean issued = books.isIssued();
////        String bookSpecialID=books.getBookSpecialID();
//        books.incrementBookSpecialID();
//        return new Response<>("200", "Book added successfully", bookRepository.save(books), true);
//    }

    @PostMapping(value = "/admin/addBook", produces = "application/json")
    public Response<Books> addBook(@Valid @RequestBody Books books) {


        String bookName = books.getBookName();
        String author = books.getAuthor();
        String category = books.getCategory();
        boolean issued = false;
        String bookSpecialID=books.getBookSpecialID();

        String bookSpecialIDInDAO=booksDAO.bookSpeciallIDInDAO(bookSpecialID);

        if(bookSpecialID.equals(bookSpecialIDInDAO)){
            return new Response<>("200", "book with that special id is avalible", null,true);
        }
        else {
            Books savedBook = bookRepository.save(books);

            // Return the response with the saved book
            return new Response<>("200", "Book added successfully", savedBook, true);
        }
    }

    @GetMapping(value = "/admin/getBookDetails",produces = "application/json")
    public Response<String> getBookDetails(@Valid @RequestBody Books books){
        String bookName=books.getBookName();
        String bookNameInDAO=booksDAO.checkBook(bookName);
        log.info("bookNameInDAO is :   " + bookNameInDAO );

        if(bookName.equals(bookNameInDAO)){
            log.info("entered into if loop");
            //do
            List<Books> booksList = (List<Books>) booksDAO.searchBookByBookName(bookName);

            Gson gson = new Gson();
            String Data = gson.toJson(booksList);
            log.info("the data is  :" + Data);
            log.info("/////////////////////////////////////////////////////////////////////////");

            String replacedata = Data.replace("\"", "").replace("id:0,", "").replace("},{", "}\r\n{").replace("[", "")
                    .replace("xxx", "").replace("{deviceName:", "").replace(",modelName:", "").replace("}", "")
                    .replace("]", "").replace("modelName:", "").replace("\r\n", "").replace("{roomName:", "")
                    .replace("deviceName:", "").replace("{","");
            log.info(replacedata);
            return new Response<String>("200","success",replacedata,true);

        }
        else {

            log.error("entered into else loop");
            return new Response<>("200","Book name is incorrect or book is not avalible",null,true);
        }
    }



    @DeleteMapping(value = "/admin/deletebook",produces = "application/json")
    public Response<Long> deletebook(@Valid @RequestBody Books books){
       Long id=books.getId();
       Long idInDAO=booksDAO.bookIdInDAO(id);

       if(id.equals(idInDAO)){
           //do
           booksDAO.deleteBook(id);
           return new Response<>("200","Book is deleted Successfully",null,true);
       }
       else {
           return new Response<>("200", "Book id is incorrect", null, true);
       }

    }


    @DeleteMapping(value = "/admin/deleteuser",produces = "application/json")
    public Response<Long> deleteuser(@Valid @RequestBody Users users){
        Long id=users.getId();
        Long idInDAO=loginDAO.userIdInDAO(id);

        if(id.equals(idInDAO)){
            //do
            loginDAO.deleteUser(id);
            return new Response<>("200","User is deleted Successfully",null,true);
        }
        else {
            return new Response<>("200", "User id is incorrect or No User is available  in  with that id", null, true);
        }

    }


    @PostMapping(value = "/borrowbooks")
    public Response<String> borrowBook(@Valid @RequestBody Books books,Users users){
        String userMail=users.getEmail();
        Optional<Users> users1=userRepository.findByEmail(userMail);
        String bookSpecialID=books.getBookSpecialID();
        log.info("userMail is " + users1);

        String bookSpecialIDInDAO=booksDAO.bookSpeciallIDInDAO(bookSpecialID);
        if (bookSpecialID.equals(bookSpecialIDInDAO)){

            booksDAO.updateBookIssued(bookSpecialID);
            log.error("after verification");
//            booksDAO.updateUserBookTable(userMail,bookSpecialID);
                booksDAO.insertUserBookTable(userMail,bookSpecialID);
            return new Response<>("200","book is issued successfully ", null,true);
        }
        else {
            return new Response<>("200","The book is already out; please get in touch with the authors as well.",null,true);
        }
    }


}

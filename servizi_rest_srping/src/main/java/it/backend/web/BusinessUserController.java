package it.backend.web;

import it.backend.model.BusinessUserFilterDTO;
import it.backend.model.BusinessUserPageDTO;
import it.backend.service.contract.IBusinessUserService;
import it.backend.model.BusinessUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessUserController {

    @Autowired
    private IBusinessUserService userService;

   @PostMapping(value="/user/create", consumes="application/json", produces="application/json")
   //@RequestMapping(value = "/create",method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public BusinessUserDTO create(@RequestBody BusinessUserDTO user) {
       return userService.createUser(user);
   }

   @PutMapping(value="/user/update", consumes="application/json", produces="application/json")
   //@RequestMapping(value = "/create",method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.OK)
   public BusinessUserDTO update(@RequestBody BusinessUserDTO user) {
       return userService.updateUser(user);
   }

   @PatchMapping(value="/user/patch", consumes="application/json", produces="application/json")
   //@RequestMapping(value = "/patch",method = RequestMethod.PATCH)
   @ResponseStatus(HttpStatus.OK)
   public BusinessUserDTO patch(@RequestBody BusinessUserDTO user) {
       return userService.mergeUser(user);
   }

    @DeleteMapping(value="/user/delete/{id}")
    //@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
       userService.deleteUser(id);
    }

    @GetMapping(value = "/user/findById/{id}")
    //@RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public ResponseEntity<BusinessUserDTO> findById(@PathVariable Integer id) {
       //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       //UserDetails userDetails = (UserDetails) auth.getPrincipal();
       BusinessUserDTO user = userService.findById(id);
       if(user!=null)
           return new ResponseEntity<BusinessUserDTO>(new BusinessUserDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getFiscalCode()),HttpStatus.OK);
       else
           return new ResponseEntity<BusinessUserDTO>(new BusinessUserDTO(0,"","",""),HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/user/findUsers/{start}/{max}")
    //@RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public ResponseEntity<BusinessUserPageDTO> findUsers(@PathVariable Integer start, @PathVariable Integer max) {
        BusinessUserPageDTO page = userService.findUsers(start,max);
        return makeResponse(page);
    }

    @GetMapping(value = "/user/findUsersByFilter/{start}/{max}")
    //@RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public ResponseEntity<BusinessUserPageDTO> findUsersByFilter(@PathVariable Integer start, @PathVariable Integer max,
                                                                 @RequestBody BusinessUserFilterDTO filter) {
        BusinessUserPageDTO page = userService.findUsersByFilter(start,max,filter);
        return makeResponse(page);
    }

    private ResponseEntity<BusinessUserPageDTO> makeResponse(BusinessUserPageDTO page){
        if(page.getTotal()>0)
            return new ResponseEntity<BusinessUserPageDTO>(page,HttpStatus.OK);
        else
            return new ResponseEntity<BusinessUserPageDTO>(new BusinessUserPageDTO(0,0,0,null),HttpStatus.NOT_FOUND);
    }
}

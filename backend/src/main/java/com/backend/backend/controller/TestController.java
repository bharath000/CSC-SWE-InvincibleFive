package com.backend.backend.controller;
import com.backend.backend.model.Charity_Ads;
import com.backend.backend.model.RestaurentAds;
import com.backend.backend.model.User_Profile;
import com.backend.backend.payload.*;
import com.backend.backend.repository.RestaurentAdsRepository;
import com.backend.backend.repository.UsersRepository;
import com.backend.backend.repository.User_ProfileRepository;
import  com.backend.backend.repository.Charity_AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UsersRepository userRepository;

    @Autowired
    User_ProfileRepository user_profileRepository;

    @Autowired
    Charity_AdsRepository charity_adsRepository;
    @Autowired
    RestaurentAdsRepository restaurentAdsRepository;


    @GetMapping("/all")

    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('VOLUNTER') or hasRole('RESTAURENT')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/volunter")
   @PreAuthorize("hasRole('VOLUNTER')")
    public String moderatorAccess() {
        return "Volunter";
    }

    @GetMapping("/restaurent")
    @PreAuthorize("hasRole('RESTAURENT')")
    public String restaurentAccess() {
        return "Restaurent Board.";
    }
    @GetMapping("/charity")
    @PreAuthorize("hasRole('CHARITY')")
    public String charityAccess() {
        return "Charity Board.";
    }
    @PostMapping("/addprofile")
    @PreAuthorize("hasRole('CHARITY')or hasRole('VOLUNTER') or hasRole('RESTAURENT')")
    public ResponseEntity<User_Profile> addProfile(@RequestBody User_Profile profile) {
        try {
            User_Profile _profile = user_profileRepository
                    .save(new User_Profile(profile.getUserid(), profile.getUser_role(), profile.getEmail(),profile.getZipcode(),profile.getOrganisation_name(),profile.getPhone_number(),profile.getStreat(),profile.getApt_number()));
            return new ResponseEntity<>(_profile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('CHARITY')or hasRole('VOLUNTER') or hasRole('RESTAURENT')")
    public ResponseEntity<?> getTutorialById(@PathVariable("id") long id) {
        Optional<User_Profile> profiledata =  user_profileRepository.findByuserid(id);
        String msg ;
        if (profiledata.isPresent()) {
            return new ResponseEntity<>(profiledata.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( "not found data",HttpStatus.OK);
        }
    }

    @PostMapping("/charity_ads")
    @PreAuthorize("hasRole('CHARITY')")
    public ResponseEntity<?> createAds(@Valid @RequestBody CharityAdRequest charityAdRequest) {
        //yyyy-MM-dd HH:mm:ss
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Charity_Ads cha_ads = new Charity_Ads(charityAdRequest.getCharityid(),dtf.format(now),charityAdRequest.getNumber_meals(),charityAdRequest.getAd_desc(),charityAdRequest.getOrder_status(),charityAdRequest.getZipcode());
        //Optional<User_Profile> profiledata =  user_profileRepository.findByuserid(id);
        //String msg ;
       charity_adsRepository.save(cha_ads);
       return new ResponseEntity<>("your request has been sucessfully posted",HttpStatus.OK);
    }

    // restaurent need to query for order based on zip and date
    @GetMapping("/restaurent/{zipcode}")
   // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<List<Charity_Ads>> getAds_R(@Valid @PathVariable("zipcode") String zipcode) {
        //yyyy-MM-dd HH:mm:ss
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String str = dtf.format(now);
        String[] arrOfStr = str.split(" ", 2);
        System.out.println(zipcode);
        System.out.println(arrOfStr[0]);
        List<Charity_Ads> ads = new ArrayList<Charity_Ads>();
        String q1 = "SELECT * FROM bounty.charity_ads where charity_ads.date_and_time like '+ %arrOfStr[0]% +' and charity_ads.zipcode = "+zipcode+";";
        //charity_adsRepository.findByZipcode(zipcode).forEach(ads::add);
        charity_adsRepository.findByDateContainingAndZipcodeAndOrderstatus(arrOfStr[0],zipcode, "placed").forEach(ads::add);
            //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

        //Optional<User_Profile> profiledata =  user_profileRepository.findByuserid(id);
        //String msg ;


    }

    //restaurent need to see the order that are delivered



    //charity need to see the posted orders
   @GetMapping("/charity/{charity_id}/orders")
   // @PreAuthorize("hasRole('CHARITY')")
    public ResponseEntity<List<Charity_Ads>> getOrders(@Valid @PathVariable("charity_id") Long charity_id) {
       List<Charity_Ads> ads = new ArrayList<Charity_Ads>();

        charity_adsRepository.findByCharityid(charity_id).forEach(ads ::add);
        //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(ads, HttpStatus.OK);
        }
    }
    @GetMapping("/charity/{charity_id}/orders/now")
    // @PreAuthorize("hasRole('CHARITY')")
    public ResponseEntity<List<Charity_Ads>> getOrdersnow(@Valid @PathVariable("charity_id") Long charity_id) {
        List<Charity_Ads> ads = new ArrayList<Charity_Ads>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String str = dtf.format(now);
        String[] arrOfStr = str.split(" ", 2);
        charity_adsRepository.findByDateContainingAndCharityid(arrOfStr[0],charity_id).forEach(ads ::add);
        //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(ads, HttpStatus.OK);
        }
    }
    @GetMapping("/charity/orders/{id}")
    // @PreAuthorize("hasRole('CHARITY')")
    public ResponseEntity<Charity_Ads> getOrderssummary(@Valid @PathVariable("id") Long ad_id) {
        //List<Charity_Ads> ads = new ArrayList<Charity_Ads>();

        Optional<Charity_Ads> tutorialData = charity_adsRepository.findById(ad_id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //charity need to see the order by id about its status on click order summary

    // restaurent accepted and delivered orders
    @GetMapping("/restaurent/order/{id}/acc")
    // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<?> updateTutorial(@PathVariable("id") Long id) {
        Optional<Charity_Ads> adsData = charity_adsRepository.findById(id);
        if (adsData.isPresent()) {
            Charity_Ads _tutorial = adsData.get();
            _tutorial.setOrderstatus("accepted");

            return new ResponseEntity<>(charity_adsRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PostMapping("/charity/order/confirmation/{id}")
    // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<?> orderconfirmation(@RequestBody OrderConfirmRequest orderConfirmRequest) {
        Optional<Charity_Ads> adsData = charity_adsRepository.findById(orderConfirmRequest.getAdsid());
        if (adsData.isPresent()) {
            Charity_Ads _tutorial = adsData.get();
            _tutorial.setOrderstatus("delivered");

            return new ResponseEntity<>(charity_adsRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/restaurent/order/{id}/acc")
    //@PreAuthorize("hasRole('RESTAURENT')")
    public  ResponseEntity<?> setResadre(@Valid @RequestBody RestaurentAdRequest restaurentAdRequest){
        System.out.println(restaurentAdRequest.getAdsid());
        System.out.println(restaurentAdRequest.getResid());
        if(restaurentAdsRepository.existsByAdsid(restaurentAdRequest.getAdsid())) {
            return new ResponseEntity(new ApiResponce(false, "Add has been accepted by other vendor"),
                    HttpStatus.BAD_REQUEST);
        }


        RestaurentAds ads_ids = new RestaurentAds(restaurentAdRequest.getResid(),restaurentAdRequest.getAdsid());
        restaurentAdsRepository.save(ads_ids);
        Optional<Charity_Ads> adsData = charity_adsRepository.findById(restaurentAdRequest.getAdsid());
        if (adsData.isPresent()) {
            Charity_Ads _tutorial = adsData.get();
            _tutorial.setOrderstatus("accepted");

            charity_adsRepository.save(_tutorial);
        }
        return new ResponseEntity<>("updated",HttpStatus.OK);



    }

    //restaurent need to fetch all the order of today and with zame zipcode
    //restaurent need to accept the order with order id , restaurent id and date of its acceptance
    //once restaurent accepts order status changes to accepted
    //if order status chages to acceptance update the order details in the charity end
    //if order accpeted charity need to see the deatils of the ordered accepted restaurent
    // once the delivery done charity need to change the status to deleivery
    //
    @GetMapping("/restaurent/resid/{id}")
    // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<?> tabledata(@PathVariable("id") Long id) {
        List<RestaurentAds> ads = new ArrayList<RestaurentAds>();
        List<Optional<Charity_Ads>> Adds = new ArrayList<>();
        List<Optional<User_Profile>> Profile = new ArrayList<>();
        ArrayList Result = new ArrayList();


        restaurentAdsRepository.findByResid(id).forEach(ads::add);

        for (RestaurentAds charityad:ads
             ) {
           Adds.add(charity_adsRepository.findById(charityad.getAdsid()));
        }
        for (Optional<Charity_Ads> charityads:Adds
      ) {
            Profile.add(user_profileRepository.findByuserid(charityads.get().getCharityid()));
       }
//        for (Optional<Charity_Ads> i : Adds) {
//
//            // accessing each element of array
//
//            System.out.print(i.get().getCharityid() + " ");
//        }
        //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            Result.add(Adds);
            Result.add(Profile);
            return new ResponseEntity<>(Result, HttpStatus.OK);
        }


    }

    @GetMapping("charity/order/accepted/{id}")
    // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<?> charityorderaccepted(@PathVariable("id") Long id) {
        List<RestaurentAds> ads = new ArrayList<RestaurentAds>();
        List<Optional<User_Profile>> Adds = new ArrayList<>();
        List<Optional<User_Profile>> Profile = new ArrayList<>();
        //ArrayList Result = new ArrayList();


        restaurentAdsRepository.findByAdsid(id).forEach(ads::add);

        for (RestaurentAds charityad:ads
        ) {
            Adds.add(user_profileRepository.findByuserid(charityad.getResid()));
            System.out.println(charityad.getResid());
        }
//        for (Optional<Charity_Ads> charityads:Adds
//        ) {
//            Profile.add(user_profileRepository.findByuserid(charityads.get().getCharityid()));
//        }
//        for (Optional<Charity_Ads> i : Adds) {
//
//            // accessing each element of array
//
//            System.out.print(i.get().getCharityid() + " ");
//        }
        //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {

            return new ResponseEntity<>(Adds.get(0), HttpStatus.OK);
        }


    }

    @GetMapping("/charity/order/history/{id}")
    // @PreAuthorize("hasRole('RESTAURENT')")
    public ResponseEntity<?> charityorderhistory(@PathVariable("id") Long id) {
        List<Charity_Ads> ads = new ArrayList<Charity_Ads>();
        List<Optional<RestaurentAds>> adds_res = new ArrayList<>();
        List<Optional<User_Profile>> Profile = new ArrayList<>();
        List<RestaurentAds> ads_res = new ArrayList<RestaurentAds>();
        ArrayList Result = new ArrayList();


        charity_adsRepository.findByCharityid(id).forEach(ads::add);

        for (Charity_Ads charityad:ads
        ) {
          //  System.out.println(charityad.getAd_id());
            if (restaurentAdsRepository.existsByAdsid(charityad.getAd_id())) {
                List<RestaurentAds> ads_rest = restaurentAdsRepository.findByAdsid(charityad.getAd_id());
               // System.out.println(ads_rest);
                Profile.add(user_profileRepository.findByuserid(ads_rest.get(0).getResid()));
            }
            else{
                Profile.add(null);
            }
        }
//        for (RestaurentAds charityads:ads_res
//        ) {
//
//            Profile.add(user_profileRepository.findByuserid(charityads.getResid()));
//        }
//        for (Optional<Charity_Ads> i : Adds) {
//
//            // accessing each element of array
//
//            System.out.print(i.get().getCharityid() + " ");
//        }
        //List res_data = Collections.singletonList(charity_adsRepository.findByZipcode(zipcode));

        if (ads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            Result.add(ads);
            Result.add(Profile);
            return new ResponseEntity<>(Result, HttpStatus.OK);
        }


    }








}

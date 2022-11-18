
package com.almaherplan.www.QTech.controller;

import com.almaherplan.www.QTech.model.Person;
import com.almaherplan.www.QTech.model.RequestQTech;
import com.almaherplan.www.QTech.model.ResponseQTech;
import com.almaherplan.www.QTech.service.QTechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
public class QTechController {

    @Autowired
    QTechService qTechService;

    @GetMapping()
    public String getTest(){
        return "Hi Muhannad ";
    }


    @PostMapping("/getTechPlan")
    public ResponseQTech getQTechPlan(@RequestBody RequestQTech requestQTech){
            return qTechService.responseQTech(requestQTech);
    }

    @PostMapping(value = "/open")
    public String getOpen(@RequestBody Person s){

        String fullNameWithWelcome="Welcome " + s.getLastName()+", "+s.getFirstName();
        System.out.println("Hello "+fullNameWithWelcome);

        return fullNameWithWelcome;
    }

}


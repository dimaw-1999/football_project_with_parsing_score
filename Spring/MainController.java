package com.example.reactProject.rest;

import com.example.reactProject.Services.InterfaceLiga;
import com.example.reactProject.entity.Club;
import com.example.reactProject.entity.Liga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class MainController {





    @Autowired
    private InterfaceLiga interfaceLiga;



    @GetMapping(value="/getLeagueName")
    public ResponseEntity<?> getLeague() {
        List<Liga> ligas = interfaceLiga.getAllLiga();
        for(Liga li:ligas) {
            int[] g = new int[li.getClubs().size()];
            int u=0;
            for(Club cl:li.getClubs()) {
                g[u++]=cl.getScore();
            }
            for(int i=0;i<g.length;i++) {
                for(int j=i+1;j<g.length;j++) {
                    if(g[i]<g[j]) {
                        int temp = g[i];
                        g[i]=g[j];
                        g[j]=temp;
                    }
                }
            }

            List<Club> clubs1 = new ArrayList<>();
            int y=1;
            for(int i=0;i<g.length;i++) {
                for(Club cl2:li.getClubs()) {
                    if(cl2.getScore()==g[i]) {
                        cl2.setNumber(y);
                        y++;
                        if(cl2.getNumber()==g.length || cl2.getNumber()==g.length-1 || cl2.getNumber()==g.length-2) {
                            cl2.setZona(true);
                        }
                        clubs1.add(cl2);
                        li.getClubs().remove(cl2);
                        break;
                    }
                }
            }
            li.setClubs(clubs1);

        }
        return new ResponseEntity<>(ligas,HttpStatus.OK);
    }








}

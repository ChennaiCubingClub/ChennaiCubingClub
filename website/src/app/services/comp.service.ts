import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CompService {

  constructor(private http: HttpClient) { }

  upcomingcomp: any = [
    {
        "date" : "Apr 23 - 26, 2020",
        "site" : "https://www.worldcubeassociation.org/competitions/IndianNationals2020",
        "name" : "Indian Nationals 2020",
        "venue" : "SRM University",
        "location": "Chengalpattu, Tamil Nadu, India"
    },
    {
        "date" : "Jan 31 - Feb 2, 2020",
        "site" : "https://www.worldcubeassociation.org/competitions/CMITessellateCubeOpen2020",
        "name" : "CMI Tessellate Cube Open 2020",
        "venue" : "Chennai Mathematical Institute",
        "location": "Chengalpattu, Tamil Nadu, India"
    }
  ];

  getUpcomingComp(){
    return this.upcomingcomp;
  }

  pastcomp: any = [
            {
                "date" : "Dec 28 - 29, 2019",
                "site" : "https://www.worldcubeassociation.org/competitions/C3CupFinale2019",
                "name" : "C3 Cup Finale 2019",
                "venue" : "JSV Subhiksha Mahal",
                "location": "Kanchipuram, Tamil Nadu, India"
            },
            {
                "date" : "Nov 17, 2019",
                "site" : "https://www.worldcubeassociation.org/competitions/VSVNCubeOpen2019",
                "name" : "VSVN Cube Open 2019",
                "venue" : "Vellaichamy Nadar Polytechnic College",
                "location": "Virudunagar, Tamil Nadu, India"
            },
          {
            "date" : "Oct 19, 2019",
            "site" : "https://www.worldcubeassociation.org/competitions/SRMCubeOpen2019",
            "name" : "SRM Cube Open 2019",
            "venue" : "SRM University",
            "location": "Kanchipuram, Tamil Nadu, India"
          },
          {
            "date" : "Sep 28, 2019",
            "site" : "https://www.worldcubeassociation.org/competitions/HLCCubeChallenge2019",
            "name" : "HLC Cube Challenge 2019",
            "venue" : "HLC International School",
            "location" : "Chennai, Tamil Nadu, India"
          },
          {
            "date" : "Aug 25, 2019",
            "site" : "https://www.worldcubeassociation.org/competitions/ChennaiCubeOpen2019",
            "name" : "Chennai Cube Open 2019",
            "venue" : "Santhosh & Sabari Institute for IAS Exam",
            "location" : "Chennai, Tamil Nadu, India"
          },
          {
            "date" : "Jul 27 - 28, 2019",
            "site" : "https://www.worldcubeassociation.org/competitions/SSNCubeOpen2019",
            "name" : "SSN Cube Open 2019",
            "venue" : "SSN College of Engineering",
            "location" : "Kanchipuram, Tamil Nadu, India"
          },
          {
            "date" : "Jun 9, 2019",
            "site" : "https://www.worldcubeassociation.org/competitions/SriRamachandraCubeOpen2019",
            "name" : "Sri Ramachandra Cube Open 2019",
            "venue" : "Sri Ramachandra College of Pharmacy",
            "location" : "Kanchipuram, Tamil Nadu, India"
          },
          
      {
        "date" : "Mar 2 - 3, 2019",
        "site" : "https://www.worldcubeassociation.org/competitions/CarteBlancheKubeOpen2019",
        "name" : "Carte Blanche Kube Open 2019",
        "venue" : "Madras Institute of Technology",
        "location" : "Kanchipuram, Tamil Nadu, India"
      },
      {
        "date" : "Mar 17, 2019",
        "site" : "https://www.worldcubeassociation.org/competitions/PragyanCubeOpen2019",
        "name" : "Pragyan Cube Open 2019",
        "venue" : "National Institute of Technology",
        "location" : "Tiruchirappalli, Tamil Nadu, India"
      },
      {
        "date" : "Feb 23 - 24, 2019",
        "site" : "https://www.worldcubeassociation.org/competitions/DakshCubeOpen2019",
        "name" : "Daksh Cube Open 2019",
        "venue" : "Saastra Deemed to be University",
        "location" : "Thanjavur, Tamil Nadu, India"
      },
      {
        "date" : "Jan 13, 2019",
        "site" : "https://www.worldcubeassociation.org/competitions/VDCAWinterOpen2019",
        "name" : "VDCA Winter Open 2019",
        "venue" : "Hanuman Wedding Hall",
        "location" : "Virudunagar, Tamil Nadu, India"
      },
      {
        "date" : "Jan 5 - 6, 2019",
        "site" : "https://www.worldcubeassociation.org/competitions/ShaastraCubeOpen2019",
        "name" : "Shaastra Cube Open 2019",
        "venue" : "Community Hall, Indian institute of Technology, Madras",
        "location" : "Chennai, Tamil Nadu, India"
      },
      {
        "date" : "Dec 1, 2018",
        "site" : "https://www.worldcubeassociation.org/competitions/PhoenixC3Open2018",
        "name" : "Phoenix C3 Open 2018",
        "venue" : "Phoenix Marketcity",
        "location": "Chennai, Tamil Nadu, India"
      }    
      ];

      getPostComp(): any{
        return this.pastcomp;
      }

}

import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/services/global.service';
import { HttpClientServiceService } from 'src/app/services/http-client-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

    signedIn = false
    token = ""
    username = ""
    password = ""

  constructor(
    private globalService: GlobalService,
    private httpClient: HttpClientServiceService
  ) { }

  ngOnInit() {
  }

    login(username, password) {
        this.httpClient.postAuth(this.globalService.baseApiURL + "/v1/users/login", {
            username: username,
            password: password
        }, this.token).subscribe((res: {success, token}) => {
            if (res.success == "true") {
                this.signedIn = true
                this.token = res.token
            } else {
                alert("Sign in failed")
            }
        })
    }

    generateC3Scores(competitionId) {
        this.httpClient.postAuth(this.globalService.baseApiURL + "/v1/c3cup/generateCompetitionScores", {
            competitionId: competitionId
        }, this.token).subscribe((res: {success, token}) => {
            //
        })
    }

    addCompetitionToC3(competitionId) {
        this.httpClient.postAuth(this.globalService.baseApiURL + "/v1/c3competitions/addCompetitionToC3", {
            competitionId: competitionId
        }, this.token).subscribe((res: {success, token}) => {
            //
        })
    }

    addCompetitionToC3Cup(competitionId) {
        this.httpClient.postAuth(this.globalService.baseApiURL + "/v1/c3competitions/addCompetitionToC3Cup", {
            competitionId: competitionId
        }, this.token).subscribe((res: {success, token}) => {
            //
        })
    }

}

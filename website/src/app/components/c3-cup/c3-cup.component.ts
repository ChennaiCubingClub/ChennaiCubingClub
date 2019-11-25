import { Component, OnInit } from '@angular/core';
import { ResultsService } from 'src/app/services/results.service';
import { GlobalService } from 'src/app/services/global.service';
import { HttpClientServiceService } from 'src/app/services/http-client-service.service';

@Component({
  selector: 'app-c3-cup',
  templateUrl: './c3-cup.component.html',
  styleUrls: ['./c3-cup.component.css']
})
export class C3CupComponent implements OnInit {

  results: {lastComp, list};
  previousCompetitions: [{competitionId, competitionName, c3scores: []}]

  constructor(
    private resultService: ResultsService,
    private globalService: GlobalService,
    private httpClient: HttpClientServiceService
  ) { }

  ngOnInit() {
    this.httpClient.post(this.globalService.baseApiURL + "/v1/c3cup/getTotalScorecard", {}).subscribe((res: {lastComp, list}) => {
        this.results = res;
        console.log(this.results)
    })
    this.httpClient.post(this.globalService.baseApiURL + "/v1/c3cup/getIndividualScores", {}).subscribe((res: {list: [{competitionId, competitionName, c3scores: []}]}) => {
        this.previousCompetitions = res.list
        console.log(res.list)
    })
  }

}

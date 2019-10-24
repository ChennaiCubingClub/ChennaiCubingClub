import { Component, OnInit } from '@angular/core';
import { ResultsService } from 'src/app/services/results.service';

@Component({
  selector: 'app-c3-cup',
  templateUrl: './c3-cup.component.html',
  styleUrls: ['./c3-cup.component.css']
})
export class C3CupComponent implements OnInit {

  results: any;

  constructor(private resultService: ResultsService) { }

  ngOnInit() {
    this.results = this.resultService.getResults();

  }

}

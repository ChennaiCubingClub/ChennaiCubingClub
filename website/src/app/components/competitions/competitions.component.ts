import { Component, OnInit } from '@angular/core';
import { CompService } from 'src/app/services/comp.service';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent implements OnInit {

  pastcomp: any;
  upcoming: any;

  constructor(private comp: CompService) { }

  ngOnInit() {
    this.pastcomp = this.comp.getPostComp();
    this.upcoming = this.comp.getUpcomingComp();
  }

  
}

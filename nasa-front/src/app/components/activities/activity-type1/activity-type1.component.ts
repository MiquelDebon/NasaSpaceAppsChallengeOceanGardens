import { Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { ActivitiesService } from '../../../services/activities.service';
import { Activity } from 'src/app/models/interfaces';
import { dummyActivity } from 'src/app/models/dummy-activity';
import { phytoStateService } from 'src/app/services/fito-state.service';

@Component({
  selector: 'app-activity-type1',
  templateUrl: './activity-type1.component.html',
  styleUrls: ['./activity-type1.component.scss']
})
export class ActivityType1Component implements OnInit {
  private phytoStateService = inject(phytoStateService);

  @Input() activity: Activity = dummyActivity;
  //crear un botó per cada element de activity.statement[1-...]

  checkAnswer(answer: string) {
    if (answer == this.activity.solution[0]) {
      this.phytoStateService.sendResults({ ...this.activity, userAnswer: [answer], points: true });
    } else {
      this.phytoStateService.sendResults({ ...this.activity, userAnswer: [answer], points: false });
    }
  }

  constructor() { }

  ngOnInit() {

  }

}

import { Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { ActivitiesService } from '../../../services/activities.service';
import { Activity } from 'src/app/models/interfaces';
import { dummyActivity } from 'src/app/models/dummy-activity';
import { phytoStateService } from 'src/app/services/fito-state.service';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-activity-type2',
  templateUrl: './activity-type2.component.html',
  styleUrls: ['./activity-type2.component.scss']
})
export class ActivityType2Component implements OnInit {
  private phytoStateService = inject(phytoStateService);

  private _activity: Activity = dummyActivity;
  optionsArray = [...this._activity.options!];


  @Input()
  set activity(activ: Activity) {
    this._activity = activ;
    this.optionsArray = [...activ.options!]
  }

  get activity(): Activity {
    return this._activity;
  }
  //crear un botó per cada element de activity.statement[1-...]

  checkAnswer(answer: string) {
    if (answer == this.activity.solution[0]) {
      this.phytoStateService.sendResults({ ...this.activity, userAnswer: [answer], points: true });
    } else {
      this.phytoStateService.sendResults({ ...this.activity, userAnswer: [answer], points: false });
    }
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsArray, event.previousIndex, event.currentIndex);
    if (this.compareArrays(this.optionsArray, this._activity.solution)) {
      this.phytoStateService.sendResults({ ...this.activity, userAnswer: this.optionsArray, points: true });
    }
  }
  
compareArrays(arr1: any[], arr2: any[]): boolean {
  if (arr1.length !== arr2.length) {
    return false;
  }

  for (let i = 0; i < arr1.length; i++) {
    if (arr1[i] !== arr2[i]) {
      return false;
    }
  }

  return true;
}


    constructor() { }

  ngOnInit() {

  }

}

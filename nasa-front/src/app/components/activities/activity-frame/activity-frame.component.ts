import { Component, OnInit, Input, inject, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { dummyActivity } from 'src/app/models/dummy-activity';
import { Action } from 'src/app/models/interfaces';
import { activityType1, activityType2, activityType3 } from 'src/app/models/activity-types';
import { ActivitiesService } from 'src/app/services/activities.service';
import { ModalComponent } from 'src/app/shared/components/modal/modal.component';

@Component({
  selector: 'app-activity-frame',
  templateUrl: './activity-frame.component.html',
  styleUrls: ['./activity-frame.component.scss']
})
export class ActivityFrameComponent implements OnInit {
  @ViewChild(ModalComponent) modal!: ModalComponent;

  private route = inject(ActivatedRoute);
  private activitiesService = inject(ActivitiesService);
  
  activityType1 = activityType1;
  activityType2 = activityType2;
  activityType3 = activityType3;

  selectedAction: Action | 0 = 0;
  currentActivity = dummyActivity;

  modalTitle = '';
  modalText = '';

  constructor() { }

  ngOnInit() {
    this.selectedAction = Number(this.route.snapshot.paramMap.get('action')) as Action;
    this.currentActivity = this.activitiesService.getActivity(this.selectedAction);
  }

}

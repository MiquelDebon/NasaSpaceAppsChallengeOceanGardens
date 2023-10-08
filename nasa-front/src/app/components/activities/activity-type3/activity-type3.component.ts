import { Component, Input, OnInit } from '@angular/core';
import { Activity } from 'src/app/models/interfaces';

@Component({
  selector: 'app-activity-type3',
  templateUrl: './activity-type3.component.html',
  styleUrls: ['./activity-type3.component.css']
})
export class ActivityType3Component implements OnInit {
  @Input() activity!: Activity;

  constructor() { }

  ngOnInit() {
  }

}
